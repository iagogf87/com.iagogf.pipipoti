package com.iagogf.pipipoti.data.repository

import android.util.Log
import com.iagogf.pipipoti.data.database.DogImageDao
import com.iagogf.pipipoti.data.entity.DogImageEntity
import com.iagogf.pipipoti.data.network.DogImageResponse
import com.iagogf.pipipoti.data.network.DogImageService
import javax.inject.Inject

class DogImageRepository @Inject constructor( // Inyectamos la API con Hilt
    private val api: DogImageService,
    private val cache: DogImageDao // Variable para almacenar la última imagen obtenida en caso de fallo
) {
    // Obtiene una imagen aleatoria de perro desde la API o, si falla, desde la caché
    suspend fun getDogImage(): DogImageResponse? {
        return try {
            val response = api.getRandomImageDog() //Llamamos a la API
            if (response.status == "success") {
                //Guardamos la imagen en caché para futuros errores
                cache.insertDogImage(DogImageEntity(imageUrl = response.message))
                response //Devuelve la imagen obtenida de la API
            } else {
                obtenerImagenDesdeCache() //Si la API no devuelve éxito, intentamos obtener las imagenes guardada
            }
        } catch (e: Exception) {
            //Si hay un error en la API, usamos la imagen en caché
            Log.e("DogImageRepository", "Error obteniendo la imagen del perro, usando caché", e)
            obtenerImagenDesdeCache()
        }
    }

    // Hacemos que esta función sea suspend para poder llamar a `getLastDogImage()`
    private suspend fun obtenerImagenDesdeCache(): DogImageResponse? {
        return cache.getDogImage()
            ?.let { DogImageResponse(message = it.imageUrl, status = "success") }
    }
}

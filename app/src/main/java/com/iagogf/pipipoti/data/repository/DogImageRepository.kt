package com.iagogf.pipipoti.data.repository

import android.util.Log
import com.iagogf.pipipoti.data.database.DogImageDao
import com.iagogf.pipipoti.data.entity.DogImageEntity
import com.iagogf.pipipoti.data.network.DogImageResponse
import com.iagogf.pipipoti.data.network.DogImageService
import javax.inject.Inject

class DogImageRepository @Inject constructor( // Inyectamos la API con Hilt
    private val api : DogImageService,
    private val cache: DogImageDao // Variable para almacenar la última imagen obtenida en caso de fallo
){
    //Obtiene una imagen aleatoria de perro desde la API
    suspend fun getDogImage(): DogImageResponse? {
        return try {
            val response = api.getRandomImageDog() // Llamamos a la API
            if (response.status == "success") {
                // Guardamos la imagen en caché
                cache.insertDogImage(DogImageEntity(imageUrl = response.message))
                response // Devuelve la respuesta de la API
            } else {
                // Si la API no devuelve éxito, intenta obtener la imagen del caché
                cache.getLastDogImage()?.let {
                    DogImageResponse(message = it.imageUrl, status = "cached")
                }
            }
        } catch (e: Exception) {
            // Si hay un error en la API, usa el caché
            Log.e("DogImageRepository", "Error obteniendo la imagen del perro, usando caché", e)
            cache.getLastDogImage()?.let {
                DogImageResponse(message = it.imageUrl, status = "cached")
            }
        }
    }
}
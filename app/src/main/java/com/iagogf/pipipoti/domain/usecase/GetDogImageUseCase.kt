package com.iagogf.pipipoti.domain.usecase

import com.iagogf.pipipoti.data.network.DogImageResponse
import com.iagogf.pipipoti.data.repository.DogImageRepository
import javax.inject.Inject

//Use Case para obtener una imagen de un perro desde el repositorio (No accede directamente a la API)

class GetDogImageUseCase @Inject constructor(
    private val repository: DogImageRepository
){
    suspend operator fun invoke(): DogImageResponse? {
        return repository.getDogImage() // Llama al repositorio y devuelve la imagen (Si falla API del caché)
    }
}
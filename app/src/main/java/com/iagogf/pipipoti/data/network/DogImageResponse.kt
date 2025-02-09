package com.iagogf.pipipoti.data.network

import kotlinx.serialization.Serializable

@Serializable
data class DogImageResponse(
    val message: String,  // URL de la imagen
    val status: String    // "success" si la petición es correcta
)

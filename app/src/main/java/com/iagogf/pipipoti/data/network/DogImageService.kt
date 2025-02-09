package com.iagogf.pipipoti.data.network

import retrofit2.http.GET

interface DogImageService {
    @GET("breeds/image/random")
    suspend fun getRandomImageDog(): DogImageResponse
}
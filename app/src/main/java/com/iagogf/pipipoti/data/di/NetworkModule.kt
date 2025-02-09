package com.iagogf.pipipoti.data.di

import com.iagogf.pipipoti.data.network.DogImageService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  //Los objetos creados aquí estarán disponibles en toda la aplicación
object NetworkModule {

    // Proveer instancia de Json
    @Singleton
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true // Evita errores si la API devuelve más datos de los esperados
    }

    // Proveer instancia de Retrofit
    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideDogImageService(json: Json): DogImageService {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            // Convertimos la respuesta del JSON automaticamente en objetos kotlin
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(DogImageService::class.java)
    }
}
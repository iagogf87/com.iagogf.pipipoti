package com.iagogf.pipipoti.data.di

import android.content.Context
import androidx.room.Room
import com.iagogf.pipipoti.data.database.DogImageDao
import com.iagogf.pipipoti.data.database.PipipotiDB
import com.iagogf.pipipoti.data.database.EventoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    //Proveer la base de datos única que maneja DogImage y Evento
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PipipotiDB {
        return Room.databaseBuilder(
            context,
            PipipotiDB::class.java,
            "pipipoti_database"
        ).fallbackToDestructiveMigration(false)
            .build()
    }

    //Proveer el DAO de imágenes de perros
    @Provides
    @Singleton
    fun provideDogImageDao(database: PipipotiDB): DogImageDao {
        return database.dogImageDao()
    }

    //Proveer el DAO de eventos
    @Provides
    @Singleton
    fun provideEventoDao(database: PipipotiDB): EventoDao {
        return database.eventoDao()
    }
}

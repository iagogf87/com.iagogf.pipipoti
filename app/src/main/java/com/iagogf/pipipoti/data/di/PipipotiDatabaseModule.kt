package com.iagogf.pipipoti.data.di

import android.content.Context
import androidx.room.Room
import com.iagogf.pipipoti.data.database.PipipotiDB
import com.iagogf.pipipoti.data.database.PipipotiEventoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PipipotiDB {
        return Room.databaseBuilder(
            context,
            PipipotiDB::class.java,
            "pipipoti_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideEventoDao(database: PipipotiDB): PipipotiEventoDao {
        return database.eventoDao()
    }
}

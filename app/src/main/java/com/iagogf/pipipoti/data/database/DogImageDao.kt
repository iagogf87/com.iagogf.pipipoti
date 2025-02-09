package com.iagogf.pipipoti.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iagogf.pipipoti.data.entity.DogImageEntity

//DAO para acceder a la tabla de imágenes (entity) de perros en la base de datos.

@Dao
interface DogImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //Si imagen mismo id reemplaza sino inserta
    suspend fun insertDogImage(image: DogImageEntity)

    @Query("SELECT * FROM DogImageEntity ORDER BY id DESC LIMIT 1")
    suspend fun getLastDogImage(): DogImageEntity? //Devuelve última imagen guardada, si no hay null
}

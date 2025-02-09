package com.iagogf.pipipoti.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iagogf.pipipoti.data.entity.DogImageEntity
import com.iagogf.pipipoti.data.entity.EventoEntity


//Administra Room (base de datos) y la creamos

//EventoEntity es donde esta creada la tabla y sus "celdas" / atributos
@Database(entities = [EventoEntity::class, DogImageEntity::class], version = 2, exportSchema = false)
abstract class PipipotiDB : RoomDatabase() {
    abstract fun eventoDao(): EventoDao
    abstract fun dogImageDao(): DogImageDao
}
package com.iagogf.pipipoti.data.database

import androidx.room.Database
import androidx.room.RoomDatabase


//Administra Room (base de datos) y la creamos

//El User es la clase "User" que creamos con el entiti ("tabla") y sus "celdas" / atributos
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class PipipotiDB : RoomDatabase() {
    abstract fun userDao(): PipipotiUsersDao
}
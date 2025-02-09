package com.iagogf.pipipoti.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//Tabla con una fila para almacenar las imágenes de perros.
@Entity (tableName = "DogImageEntity")
data class DogImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  //Id generado automáticamente
    val imageUrl: String //URL de imagen de perro - La información que vamos a guardar
)
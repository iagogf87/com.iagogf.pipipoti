package com.iagogf.pipipoti.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "EventoEntity")
data class EventoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "fecha_hora") val fechaHora: String = obtenerFechaHoraActual(),
    @ColumnInfo(name = "tipo") val tipo: String, // "Pis" o "Caca"
    @ColumnInfo(name = "resultado") val resultado: String // "Conseguido" o "No conseguido"
) {
    companion object {
        fun obtenerFechaHoraActual(): String {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy -- HH:mm:ss")
            return LocalDateTime.now().format(formatter)
        }
    }
}


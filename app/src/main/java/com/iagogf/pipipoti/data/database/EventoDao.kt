package com.iagogf.pipipoti.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iagogf.pipipoti.data.entity.EventoEntity

@Dao
interface EventoDao {
    // Insertar un nuevo evento en la base de datos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEvento(evento: EventoEntity)

    // Obtener todos los eventos ordenados por fecha y hora en orden descendente
    @Query("SELECT * FROM EventoEntity ORDER BY fecha_hora DESC")
    suspend fun obtenerTodosLosEventos(): List<EventoEntity>

    // Borrar todos los eventos de la base de datos
    @Query("DELETE FROM EventoEntity")
    suspend fun borrarTodosLosEventos()
}

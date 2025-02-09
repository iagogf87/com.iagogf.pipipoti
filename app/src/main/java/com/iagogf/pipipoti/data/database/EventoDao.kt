package com.iagogf.pipipoti.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iagogf.pipipoti.domain.models.PipipotiEventoEntity

@Dao
interface PipipotiEventoDao {
    // Insertar un nuevo evento en la base de datos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEvento(evento: PipipotiEventoEntity)

    // Obtener todos los eventos ordenados por fecha y hora en orden descendente
    @Query("SELECT * FROM PipipotiEventos ORDER BY fecha_hora DESC")
    suspend fun obtenerTodosLosEventos(): List<PipipotiEventoEntity>

    // Borrar todos los eventos de la base de datos
    @Query("DELETE FROM PipipotiEventos")
    suspend fun borrarTodosLosEventos()
}

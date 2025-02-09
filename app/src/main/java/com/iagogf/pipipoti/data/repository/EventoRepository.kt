package com.iagogf.pipipoti.data.repository

import com.iagogf.pipipoti.data.database.PipipotiEventoDao
import com.iagogf.pipipoti.data.entity.EventoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventoRepository @Inject constructor(
    private val dao: PipipotiEventoDao
) {

    suspend fun insertarEvento(evento: EventoEntity) {
        dao.insertarEvento(evento)
    }

    fun obtenerTodosLosEventos(): Flow<List<EventoEntity>> = flow {
        emit(dao.obtenerTodosLosEventos()) // Convierte la lista en un flujo
    }

    suspend fun eliminarTodosLosEventos() {
        dao.borrarTodosLosEventos()
    }
}

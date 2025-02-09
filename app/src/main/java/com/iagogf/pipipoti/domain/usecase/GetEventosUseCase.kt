package com.iagogf.pipipoti.domain.usecase

import com.iagogf.pipipoti.data.repository.EventoRepository
import com.iagogf.pipipoti.data.entity.EventoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEventosUseCase @Inject constructor(
    private val repository: EventoRepository
) {
    fun ejecutar(): Flow<List<EventoEntity>> = repository.obtenerTodosLosEventos()
}

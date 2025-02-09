package com.iagogf.pipipoti.domain.usecase

import com.iagogf.pipipoti.data.repository.EventoRepository
import com.iagogf.pipipoti.data.entity.EventoEntity
import javax.inject.Inject

class InsertEventoUseCase @Inject constructor(
    private val repository: EventoRepository
) {
    suspend fun ejecutar(evento: EventoEntity) {
        repository.insertarEvento(evento)
    }
}
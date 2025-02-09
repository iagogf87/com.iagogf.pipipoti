package com.iagogf.pipipoti.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagogf.pipipoti.data.repository.EventoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.iagogf.pipipoti.domain.usecase.GetEventosUseCase
import com.iagogf.pipipoti.domain.usecase.InsertEventoUseCase
import com.iagogf.pipipoti.data.entity.EventoEntity

@HiltViewModel
class EventoViewModel @Inject constructor(
    private val getEventosUseCase: GetEventosUseCase,
    private val insertEventoUseCase: InsertEventoUseCase,
    private val repository: EventoRepository
) : ViewModel() {

    private val _eventos = MutableStateFlow<List<EventoEntity>>(emptyList())
    val eventos = _eventos.asStateFlow()

    init {
        obtenerEventos()
    }

    fun insertarEvento(tipo: String, resultado: String, onEventoGuardado: () -> Unit) {
        viewModelScope.launch {
            val evento = EventoEntity(tipo = tipo, resultado = resultado)
            insertEventoUseCase.ejecutar(evento) // Ahora llamamos al caso de uso
            obtenerEventos() // Recargar la lista
            onEventoGuardado() // Navegar automáticamente a ResumenScreen
        }
    }

    private fun obtenerEventos() {
        viewModelScope.launch {
            getEventosUseCase.ejecutar().collectLatest { eventosGuardados ->
                _eventos.value = eventosGuardados
            }
        }
    }

    fun eliminarTodosLosEventos() {
        viewModelScope.launch {
            repository.eliminarTodosLosEventos()
        }
    }

}

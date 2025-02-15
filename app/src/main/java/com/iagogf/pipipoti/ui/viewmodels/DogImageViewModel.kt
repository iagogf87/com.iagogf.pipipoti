package com.iagogf.pipipoti.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagogf.pipipoti.domain.usecase.GetDogImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

@HiltViewModel
class DogImageViewModel @Inject constructor(
    private val getDogImageUseCase: GetDogImageUseCase
) : ViewModel() {

    // Estado mutable que almacena la imagen del perro
    private val _dogImageState = mutableStateOf<DogImageState>(DogImageState.Loading)
    val dogImageState: State<DogImageState> = _dogImageState

    init {
        getDogImageAndUpdateState()
    }

    fun onDogImageClicked() {
        getDogImageAndUpdateState()
    }

    // Obtiene la imagen (desde API o caché)
    private fun getDogImageAndUpdateState() {
        viewModelScope.launch {
            _dogImageState.value = DogImageState.Loading

            val dogImage = getDogImageUseCase() // Llamamos al Use Case para obtener la imagen

            if (dogImage != null) {
                _dogImageState.value = DogImageState.Success(dogImage.message) // Imagen obtenida (API o caché)
            } else {
                _dogImageState.value = DogImageState.Error("Error al obtener la imagen")
            }
        }
    }
}

// Estados del ViewModel
sealed class DogImageState {
    object Loading : DogImageState()
    data class Success(val imageUrl: String) : DogImageState()
    data class Error(val message: String) : DogImageState()
}
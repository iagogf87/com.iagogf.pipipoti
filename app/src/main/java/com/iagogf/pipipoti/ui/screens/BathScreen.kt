package com.iagogf.pipipoti.ui.screens

import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.iagogf.pipipoti.R
import com.iagogf.pipipoti.ui.navigation.PipiPotiScreen
import com.iagogf.pipipoti.ui.viewmodels.DogImageState
import com.iagogf.pipipoti.ui.viewmodels.DogImageViewModel

@Composable
fun BathScreen(
    onNavigateToEvento: () -> Unit,
    viewModel: DogImageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var timeLeft by remember { mutableStateOf("00:00") }
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }
    var timer: CountDownTimer? by remember { mutableStateOf(null) }

    // Estado del scroll
    val scrollState = rememberLazyListState()
    val dogImageState by viewModel.dogImageState
    var imageClicked by remember { mutableStateOf(false) } // Detecta clics en la imagen

    // Función para iniciar el temporizador y reproducir la melodía
    fun startCountdown(duration: Int, melodyResId: Int) {
        mediaPlayer?.release()
        timer?.cancel()
        mediaPlayer = MediaPlayer.create(context, melodyResId)
        mediaPlayer?.start()

        timer = object : CountDownTimer((duration * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = millisUntilFinished / 1000 % 60
                timeLeft = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                timeLeft = "00:00"
                mediaPlayer?.release()
                mediaPlayer = null
            }
        }.start()
    }

    // Función para detener temporizador y sonido
    fun stopEverything() {
        timer?.cancel()
        mediaPlayer?.release()
        mediaPlayer = null
        timeLeft = "00:00"
    }

    // Efecto para detener al salir de la pantalla
    DisposableEffect(Unit) {
        onDispose {
            stopEverything()
        }
    }

    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Temporizador
        item {
            Text(
                text = timeLeft,
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 64.sp),
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }

        // Texto de instrucciones
        item {
            Text(
                text = "Seleccione la duración de su estancia en el WC",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Botones de duración
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { startCountdown(120, R.raw.melody_2min) },
                    modifier = Modifier.fillMaxWidth()
                ) { Text(text = "2 minutos") }

                Button(
                    onClick = { startCountdown(210, R.raw.melody_3min30) },
                    modifier = Modifier.fillMaxWidth()
                ) { Text(text = "3 minutos y medio") }

                Button(
                    onClick = { startCountdown(300, R.raw.melody_5min) },
                    modifier = Modifier.fillMaxWidth()
                ) { Text(text = "5 minutos") }
            }
        }

        // Botón STOP
        item {
            Button(
                onClick = { stopEverything() },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(100.dp)
                    .height(100.dp)
            ) {
                Text(text = "STOP", style = MaterialTheme.typography.bodyLarge)
            }
        }

        // Botón Añadir Evento
        item {
            Button(
                onClick = onNavigateToEvento,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(200.dp)
                    .height(60.dp)
            ) {
                Text(text = "¿Qué tal ha ido?", style = MaterialTheme.typography.bodyLarge)
            }
        }

        // Texto antes de la imagen clicable de los perros (API)
        item {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Si te aburres.. haz clic en la imagen :)",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        //Imagen del perro con clic para cambiar
        item {
            when (val state = dogImageState) {
                is DogImageState.Success -> Image(
                    painter = rememberAsyncImagePainter(state.imageUrl),
                    contentDescription = "Imagen de un perro",
                    modifier = Modifier
                        .size(200.dp)
                        .clickable {
                            imageClicked = true // Activa el scroll solo en clic manual
                            viewModel.onDogImageClicked()
                        }
                )

                is DogImageState.Error -> Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error
                )

                DogImageState.Loading -> CircularProgressIndicator()
            }
        }

        //Espaciador extra al final para evitar cortes en la imagen de los perros
        item {
            Spacer(modifier = Modifier.height(150.dp))
        }
    }
}

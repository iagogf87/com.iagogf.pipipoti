package com.iagogf.pipipoti.ui.screens

import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iagogf.pipipoti.R

@Composable
fun BathScreen() {
    val context = LocalContext.current // Contexto para reproducir las melodías
    var timeLeft by remember { mutableStateOf("00:00") }
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }
    var timer: CountDownTimer? by remember { mutableStateOf(null) }

    // Función para iniciar el temporizador y reproducir la melodía
    fun startCountdown(duration: Int, melodyResId: Int) {
        // Detener cualquier reproducción anterior
        mediaPlayer?.release()
        timer?.cancel()
        mediaPlayer = MediaPlayer.create(context, melodyResId)
        mediaPlayer?.start()

        // Iniciar el temporizador
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

    // Función para detener
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Espaciado inicial
        Spacer(modifier = Modifier.height(90.dp))

        // Temporizador
        Text(
            text = timeLeft,
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 64.sp),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Texto de instrucciones
        Text(
            text = "Seleccione la duración de su estancia en el WC",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Botones de duración
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón para 2 minutos
            Button(
                onClick = { startCountdown(120, R.raw.melody_2min) }, // Reproducir melodía de 2 minutos
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "2 minutos")
            }

            // Botón para 3 minutos y medio
            Button(
                onClick = { startCountdown(210, R.raw.melody_3min30) }, // Reproducir melodía de 3 minutos y medio
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "3 minutos y medio")
            }

            // Botón para 5 minutos
            Button(
                onClick = { startCountdown(300, R.raw.melody_5min) }, // Reproducir melodía de 5 minutos
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "5 minutos")
            }
        }

        // Botón STOP
        Spacer(modifier = Modifier.height(16.dp)) // Separación extra
        Button(
            onClick = { stopEverything() }, // Detener todo
            modifier = Modifier
                .padding(top = 16.dp)
                .width(200.dp)
                .height(56.dp)
        ) {
            Text(text = "STOP", style = MaterialTheme.typography.bodyLarge)
        }
    }
}


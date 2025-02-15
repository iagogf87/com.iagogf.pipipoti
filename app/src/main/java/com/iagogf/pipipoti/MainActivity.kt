package com.iagogf.pipipoti

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.iagogf.pipipoti.data.sqlite.MelodyDBContract
import com.iagogf.pipipoti.data.sqlite.MelodyDBHelper
import com.iagogf.pipipoti.ui.theme.PipipotiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
// Clase principal de la aplicación
class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity" // Etiqueta para identificar los mensajes en Logcat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: La aplicación ha sido creada") // Log aplicación creada
        setContent {
            PipipotiTheme {
                PipiPotiApp()
            }
        }
    }

    // Sobreescribimos los métodos del ciclo de vida de la Activity
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: La aplicación está visible para el usuario")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: La aplicación está en el primer plano")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: La aplicación está perdiendo el foco")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: La aplicación ya no es visible para el usuario")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: La actividad ha sido destruida")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.d(TAG, "onLowMemory: El dispositivo está con poca memoria")
    }
}



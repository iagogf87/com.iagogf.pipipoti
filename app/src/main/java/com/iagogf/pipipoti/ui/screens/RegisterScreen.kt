package com.iagogf.pipipoti.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.iagogf.pipipoti.data.database.PipipotiDB
import com.iagogf.pipipoti.data.database.User
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit
) {
    val context = LocalContext.current

    // Inicializamos la base de datos
    val db = Room.databaseBuilder(
        context.applicationContext,
        PipipotiDB::class.java,
        "Pipipoti_database"
    ).build()

    val userDao = db.userDao()

    // Estado para almacenar nombre de usuario, contraseña y mensajes de error
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var registerError by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de entrada para el nombre de usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de entrada para la contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de registro
        Button(
            onClick = {
                coroutineScope.launch {
                    registerError = when {
                        username.isBlank() -> "El nombre de usuario no puede estar vacío."
                        password.isBlank() -> "La contraseña no puede estar vacía."
                        userDao.getUserByUsername(username) != null -> "El usuario ya existe."
                        else -> {
                            userDao.insertUser(User(name = username, password = password))
                            onRegisterSuccess()
                            null
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar error si hay algún problema
        registerError?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
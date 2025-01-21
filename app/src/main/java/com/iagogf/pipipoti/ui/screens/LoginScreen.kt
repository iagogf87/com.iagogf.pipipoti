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
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit, // Callback para cuando el inicio de sesión sea exitoso
    onNavigateToRegister: () -> Unit // Callback para navegar a la pantalla de registro
                                //Unit es que no devuelven nada "algo parecido a void"
) {
    val context = LocalContext.current  // Creamos variable para acceder al applicationContext
                                        // ya que no estamos en el activity y no tenemos acceso directo
    // Inicializamos la base de datos
    val db = Room.databaseBuilder(
        context.applicationContext,
        PipipotiDB::class.java,
        "Pipipoti_database"
    ).build()

    val userDao = db.userDao()

    // Variables de estado para almacenar el nombre de usuario y contraseña
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Variable de estado para manejar errores de inicio de sesión
    val loginError = remember { mutableStateOf<String?>(null) }

    // CoroutineScope para manejar tareas suspendidas
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de entrada para el usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it }, // Actualiza el estado del nombre de usuario
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de entrada para la contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it }, // Actualiza el estado de la contraseña
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(), // Oculta la contraseña
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de inicio de sesión
        Button(
            onClick = {
                coroutineScope.launch {
                    loginError.value = null // Resetea el error al iniciar la acción
                    try {
                        // Busca si existe un usuario con el nombre ingresado
                        val user = userDao.getUserByUsername(username)
                        if (user != null && user.password == password) {
                            // Si el usuario y la contraseña coinciden, llama a onLoginSuccess
                            onLoginSuccess()
                        } else {
                            // Si no coincide, muestra un error
                            loginError.value = "Usuario o contraseña incorrectos"
                        }
                    } catch (e: Exception) {
                        // Manejo de excepciones durante la consulta
                        loginError.value = "Error al iniciar sesión"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión") // Texto del botón
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para navegar al registro
        TextButton(
            onClick = { onNavigateToRegister() }, // Callback para la navegación
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("¿No tienes cuenta? Regístrate") // Texto del botón
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Muestra un error si las credenciales son incorrectas
        loginError.value?.let {
            Text(
                text = it, // Texto del error
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
package com.iagogf.pipipoti.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iagogf.pipipoti.R
import com.iagogf.pipipoti.ui.navigation.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PipiPotiScaffold(
    navController: NavController, //Pasamos el navController correctamente
    onAddEvent: () -> Unit, //Callback para añadir evento
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            // Aquí definimos la TopAppBar que se ve en todas las pantallas
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column {
                            Text(
                                text = "PipiPoti",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(
                                text = "Control de esfínteres",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* No hace nada */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.caca2),
                            contentDescription = "Icono de caca",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onAddEvent) { // Botón "+"
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Añadir evento",
                            modifier = Modifier.size(36.dp),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = { BottomNavBar(navController) }, // Barra inferior que se repite en todas las pantallas
        content = content // Este es el contenido de cada pantalla que se pasa aquí
    )
}


package com.iagogf.pipipoti.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iagogf.pipipoti.R
import com.iagogf.pipipoti.ui.viewmodels.EventoViewModel
import com.iagogf.pipipoti.ui.navigation.PipiPotiScreen

@Composable
fun EventoScreen(
    navController: NavController,
    viewModel: EventoViewModel = hiltViewModel()
) {
    var tipoSeleccionado by remember { mutableStateOf("Pis") }
    var resultadoSeleccionado by remember { mutableStateOf("Conseguido") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.statusBars), // 🔥 Evita que quede debajo de la barra superior
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
    ) {

        Text(
            text = "Vamos a añadir un evento:",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Opciones de tipo (Pis / Caca)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Pis", "Caca").forEach { opcion ->
                Button(
                    onClick = { tipoSeleccionado = opcion },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (tipoSeleccionado == opcion) MaterialTheme.colorScheme.primary else Color.Gray,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .height(50.dp) //Botón cuadrado
                ) {
                    Text(text = opcion)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Opciones de resultado (Conseguido / No conseguido)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Conseguido", "No conseguido").forEach { opcion ->
                Button(
                    onClick = { resultadoSeleccionado = opcion },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (resultadoSeleccionado == opcion) MaterialTheme.colorScheme.primary else Color.Gray,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                        .height(50.dp) //Botón cuadrado
                ) {
                    Text(text = opcion)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de añadir evento
        Button(
            onClick = {
                viewModel.insertarEvento(tipoSeleccionado, resultadoSeleccionado) {
                    navController.navigate(PipiPotiScreen.Resumen.route) // Volver a ResumenScreen
                }
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .width(200.dp)
                .height(60.dp)
        ) {
            Text(text = "Añadir")
        }

        // Imagen caca y pis feliz
        Image(
            painter = painterResource(id = R.drawable.cp), // Carga la imagen desde drawable
            contentDescription = "Caca y pis feliz",
            modifier = Modifier
                .fillMaxWidth() // Ocupa el ancho disponible
                .height(300.dp) // Altura fija
                .padding(bottom = 16.dp) // Margen inferior
        )
    }
}

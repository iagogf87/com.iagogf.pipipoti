package com.iagogf.pipipoti.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iagogf.pipipoti.R

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigateToBath: () -> Unit,
    onNavigateToEvento: () -> Unit,
    onNavigateToResumen: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Asegura que ocupe el espacio disponible
            .padding(16.dp) // Margen interno para los elementos
            .windowInsetsPadding(WindowInsets.statusBars) // Evita que quede detrás de la barra superior
    ) {
        item {
            // Texto de bienvenida
            Text(
                text = "Bienvenido a PipiPoti. Gestiona el control de esfínteres de forma visual y divertida.",
                style = MaterialTheme.typography.headlineSmall, // Estilo del texto
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            // Imagen del niño
            Image(
                painter = painterResource(id = R.drawable.nino1), // Carga la imagen desde drawable
                contentDescription = "Imagen de un niño feliz",
                modifier = Modifier
                    .fillMaxWidth() // Ocupa el ancho disponible
                    .height(300.dp) // Altura fija
                    .padding(bottom = 16.dp) // Margen inferior
            )
        }

        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = onNavigateToBath,
                    modifier = Modifier
                        .fillMaxWidth(0.6f) // ✅ 60% del ancho
                        .height(56.dp)
                ) {
                    Text(text = "Ir al baño", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp)) // Espaciado entre listas
        }

        item {
            // Lista de premios personalizados
            Text(
                text = "Lista de premios personalizados:",
                style = MaterialTheme.typography.titleMedium, // Estilo del texto
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Elementos de la lista de premios personalizados
        items(listOf("Pelota", "Carrito", "Muñeco")) { item ->
            Text(
                text = item,
                style = MaterialTheme.typography.bodyLarge, // Estilo de cada elemento
                modifier = Modifier.padding(vertical = 4.dp) // Margen vertical
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre listas
        }

        item {
            // Lista de premios por defecto
            Text(
                text = "Lista de premios por defecto:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Elementos de la lista de premios por defecto
        items(listOf("Colorear", "Ruleta")) { item ->
            Text(
                text = item,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 4.dp) // Margen vertical
            )
        }
    }
}

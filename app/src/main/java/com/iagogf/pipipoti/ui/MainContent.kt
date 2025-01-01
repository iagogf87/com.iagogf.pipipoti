package com.iagogf.pipipoti.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iagogf.pipipoti.R

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit, // Callback para navegar a LoginScreen
    onNavigateToRegister: () -> Unit // Callback para navegar a RegisterScreen
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize() // Ocupa el espacio disponible
            .padding(16.dp) // Margen interno
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
            // Botones de navegación
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Ocupa el ancho disponible
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly // Espaciado entre botones
            ) {
                Button(
                    onClick = onNavigateToLogin // Navegar a LoginScreen
                ) {
                    Text(text = "Iniciar Sesión")
                }

                Spacer(modifier = Modifier.width(16.dp)) // Espacio entre botones

                Button(
                    onClick = onNavigateToRegister // Navegar a RegisterScreen
                ) {
                    Text(text = "Registrarse")
                }
            }
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


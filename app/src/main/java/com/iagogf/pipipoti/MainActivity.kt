package com.iagogf.pipipoti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment

// Clase principal de la aplicación
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Renderiza la aplicación usando Jetpack Compose
            PipiPotiApp()
        }
    }
}

// Función principal de la aplicación
@Composable
fun PipiPotiApp() {
    // Envuelve el contenido en el scaffold personalizado
    PipiPotiScaffold {
        MainContent()
    }
}

// Scaffold personalizado con TopAppBar y BottomAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PipiPotiScaffold(content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, // Alinea los elementos verticalmente
                        modifier = Modifier.fillMaxWidth() // Ocupa el ancho disponible
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.caca1), // Icono cargado desde drawable
                            contentDescription = "Icono de caca",
                            tint = MaterialTheme.colorScheme.onPrimary, // Color del icono
                            modifier = Modifier
                                .size(40.dp) // Tamaño del icono
                                .padding(end = 8.dp) // Espaciado a la derecha
                        )
                        Column {
                            Text(
                                text = "PipiPoti",
                                style = MaterialTheme.typography.headlineSmall, // Estilo del título principal
                                color = MaterialTheme.colorScheme.onPrimary // Color del texto
                            )
                            Text(
                                text = "Control de esfínteres",
                                style = MaterialTheme.typography.bodySmall, // Estilo del subtítulo
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Color de fondo de la barra
                    titleContentColor = MaterialTheme.colorScheme.onPrimary // Color del contenido
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer // Color de fondo de la BottomAppBar
            ) {
                Text(
                    text = "PipiPoti © 2024",
                    modifier = Modifier
                        .padding(16.dp) // Espaciado interno del texto
                        .fillMaxWidth(), // Ocupa el ancho disponible
                    style = MaterialTheme.typography.bodyMedium // Estilo del texto
                )
            }
        }
    ) { padding ->
        // PaddingValues para manejar los márgenes entre las barras y el contenido
        MainContent(modifier = Modifier.padding(padding))
    }
}

// Contenido principal de la aplicación
@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize() // Ocupa el espacio disponible
            .padding(16.dp) // Margen interno de la columna
    ) {
        // Texto de bienvenida
        Text(
            text = "Bienvenido a PipiPoti. Gestiona el control de esfínteres de forma visual y divertida.",
            style = MaterialTheme.typography.headlineSmall // Estilo del texto
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre elementos

        // Imagen del niño
        Image(
            painter = painterResource(id = R.drawable.nino1), // Carga la imagen desde drawable
            contentDescription = "Imagen de un niño feliz",
            modifier = Modifier
                .fillMaxWidth() // Ocupa el ancho disponible
                .height(300.dp) // Altura de la imagen
                .padding(8.dp) // Espaciado interno de la imagen
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre elementos

        // Lista de premios personalizados
        Text(
            text = "Lista de premios personalizados:",
            style = MaterialTheme.typography.titleMedium // Estilo del texto
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth() // Ocupa el ancho disponible
                .padding(bottom = 5.dp) // Espacio adicional debajo de la lista
        ) {
            items(listOf("Pelota", "Carrito", "Muñeco")) { item ->
                Text(
                    text = item,
                    style = MaterialTheme.typography.bodyLarge, // Estilo del texto de cada elemento
                    modifier = Modifier.padding(8.dp) // Espaciado interno de cada elemento
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre elementos

        // Lista de premios por defecto
        Text(
            text = "Lista de premios por defecto:",
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth() // Ocupa el ancho disponible
                .weight(1f) // Toma el espacio restante en la pantalla
        ) {
            items(listOf("Colorear", "Ruleta")) { item ->
                Text(
                    text = item,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp) // Espaciado interno de cada elemento
                )
            }
        }
    }
}

// Vista previa en el editor
@Preview(showBackground = true)
@Composable
fun PreviewPipiPotiApp() {
    PipiPotiApp()
}

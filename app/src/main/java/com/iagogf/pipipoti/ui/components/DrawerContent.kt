package com.iagogf.pipipoti.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//Diseño y acciones del menú lateral

@Composable
fun DrawerContent(
    onNavigateToMain: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(200.dp), // Ajusto el ancho para ocupar el trozo que quiero de la pantalla
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Menú",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.onPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = onNavigateToMain) {
                Text(
                    text = "Inicio",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

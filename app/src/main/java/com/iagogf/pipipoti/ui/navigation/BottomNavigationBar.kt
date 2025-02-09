package com.iagogf.pipipoti.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.iagogf.pipipoti.R

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 8.dp
    ) {
        listOf(
            PipiPotiScreen.Main to R.drawable.ic_home,
            PipiPotiScreen.Bath to R.drawable.ic_bath,
            PipiPotiScreen.Resumen to R.drawable.ic_resumen
        ).forEach { (screen, icon) ->
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate(screen.route) },// Navega a la ruta seleccionada
                icon = {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = screen.route,
                        modifier = Modifier.size(48.dp),
                        tint = Color.White
                    )
                },
                label = { null } // Elimina el texto debajo del icono
            )
        }
    }
}

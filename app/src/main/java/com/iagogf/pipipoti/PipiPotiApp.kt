package com.iagogf.pipipoti.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.iagogf.pipipoti.navigation.NavGraph
import com.iagogf.pipipoti.ui.components.DrawerContent
import com.iagogf.pipipoti.ui.components.PipiPotiScaffold
import kotlinx.coroutines.launch

// Función principal de la aplicación

@Composable
fun PipiPotiApp() {
    // Creamos un controlador de navegación para gestionar las rutas en la app
    val navController = rememberNavController()
    // Creamos un estado para el menú lateral (Drawer), inicializado como cerrado
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    // CoroutineScope para manejar operaciones suspendidas (suspend), como abrir/cerrar el menú lateral
    val coroutineScope = rememberCoroutineScope()

    // ModalNavigationDrawer gestiona el menú lateral de la aplicación
    ModalNavigationDrawer(
        // Estado del menú lateral
        drawerState = drawerState,
        // Contenido del menú lateral
        drawerContent = {
            DrawerContent(
                onNavigateToMain = {
                    // Cuando se selecciona "Inicio", cierra el menú lateral
                    coroutineScope.launch { drawerState.close() }
                    // Navega a la pantalla principal (main) reiniciando el stack de navegación
                    navController.navigate("main") {
                        popUpTo("main") { inclusive = true } // Limpia el historial de navegación
                    }
                }
            )
        },
        // Color de sombra detrás del menú lateral cuando está abierto
        scrimColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) // Ajusta la opacidad
    ) {
        // PipiPotiScaffold - estructura general de la app (barra superior, barra inferior, contenido principal)
        PipiPotiScaffold(
            drawerState = drawerState // Pasa el estado del menú lateral
        ) { paddingValues -> // Contenido principal con padding proporcionado por el Scaffold
            // Carga la navegación principal de la app (NavGraph)
            NavGraph(
                navController = navController, // Controlador de navegación
                paddingValues = paddingValues // Padding para ajustar el contenido
            )
        }
    }
}


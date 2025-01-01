package com.iagogf.pipipoti

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.iagogf.pipipoti.data.database.AppDatabase
import com.iagogf.pipipoti.data.repository.UserRepository
import com.iagogf.pipipoti.navigation.NavGraph
import com.iagogf.pipipoti.ui.components.DrawerContent
import com.iagogf.pipipoti.ui.components.PipiPotiScaffold
import kotlinx.coroutines.launch

// Función principal de la aplicación

@Composable
fun PipiPotiApp() {
    val context = LocalContext.current
    val userRepository = UserRepository(AppDatabase.getDatabase(context).userDao())
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onNavigateToMain = {
                    coroutineScope.launch { drawerState.close() }
                    navController.navigate("main") {
                        popUpTo("main") { inclusive = true }
                    }
                }
            )
        },
        scrimColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) // Color de sombra ajustado
    ) {
        PipiPotiScaffold(
            drawerState = drawerState
        ) { paddingValues ->
            NavGraph(
                navController = navController,
                paddingValues = paddingValues,
                userRepository = userRepository
            )
        }
    }
}


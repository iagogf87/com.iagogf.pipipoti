package com.iagogf.pipipoti.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.iagogf.pipipoti.ui.components.PipiPotiScaffold

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    PipiPotiScaffold(
        navController = navController,
        onAddEvent = { navController.navigate(PipiPotiScreen.Evento.route) }
    ) { innerPadding ->
        NavGraph(navController = navController, paddingValues = innerPadding)
    }
}


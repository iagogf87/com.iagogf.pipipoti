package com.iagogf.pipipoti.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iagogf.pipipoti.ui.screens.*

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = PipiPotiScreen.Main.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(PipiPotiScreen.Main.route) {
            MainScreen(
                onNavigateToBath = { navController.navigate(PipiPotiScreen.Bath.route) },
                onNavigateToEvento = { navController.navigate(PipiPotiScreen.Evento.route) },
                onNavigateToResumen = { navController.navigate(PipiPotiScreen.Resumen.route) }
            )
        }
        composable(PipiPotiScreen.Bath.route) {
            BathScreen(
                onNavigateToEvento = { navController.navigate(PipiPotiScreen.Evento.route) }
            )
        }
        composable(PipiPotiScreen.Evento.route) {
            EventoScreen(navController)
        }
        composable(PipiPotiScreen.Resumen.route) {
            ResumenScreen(navController)
        }
    }
}


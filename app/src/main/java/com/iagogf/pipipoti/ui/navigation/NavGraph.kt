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
                onNavigateToBath = {
                    navController.navigate(PipiPotiScreen.Bath.route) {
                        popUpTo(PipiPotiScreen.Main.route) { inclusive = true }
                        launchSingleTop = true
                        restoreState = false
                    }
                },
                onNavigateToEvento = {
                    navController.navigate(PipiPotiScreen.Evento.route) {
                        popUpTo(PipiPotiScreen.Main.route) { inclusive = true }
                        launchSingleTop = true
                        restoreState = false//Borra la pila, pero mantiene MainScreen
                    }
                },
                onNavigateToResumen = {
                    navController.navigate(PipiPotiScreen.Resumen.route) {
                        popUpTo(PipiPotiScreen.Main.route) { inclusive = true }
                        launchSingleTop = true
                        restoreState = false//Borra la pila, pero mantiene MainScreen
                    }
                }
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


package com.iagogf.pipipoti.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iagogf.pipipoti.ui.screens.MainContent
import com.iagogf.pipipoti.ui.screens.LoginScreen
import com.iagogf.pipipoti.ui.screens.RegisterScreen
import com.iagogf.pipipoti.ui.screens.BathScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainContent(
                modifier = Modifier.padding(paddingValues),
                onNavigateToLogin = { navController.navigate("login") },
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToBath = { navController.navigate("bath") }
            )
        }
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.popBackStack() },
                onNavigateToRegister = { navController.navigate("register") }
            )
        }
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.popBackStack() }
            )
        }
        composable("bath") {
            BathScreen() // Mostrar BathScreen
        }
    }
}

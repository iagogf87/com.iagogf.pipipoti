package com.iagogf.pipipoti.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iagogf.pipipoti.data.repository.UserRepository
import com.iagogf.pipipoti.ui.MainContent
import com.iagogf.pipipoti.ui.screens.LoginScreen
import com.iagogf.pipipoti.ui.RegisterScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    userRepository: UserRepository
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainContent(
                modifier = Modifier.padding(paddingValues),
                onNavigateToLogin = { navController.navigate("login") },
                onNavigateToRegister = { navController.navigate("register") }
            )
        }
        composable("login") {
            LoginScreen(
                userRepository = userRepository,
                onLoginSuccess = { navController.popBackStack() },
                onNavigateToRegister = { navController.navigate("register") }
            )
        }
        composable("register") {
            RegisterScreen(
                userRepository = userRepository,
                onRegisterSuccess = { navController.popBackStack() }
            )
        }
    }
}

package com.example.medidorapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medidorapp.ui.FormularioMedicionScreen
import com.example.medidorapp.ui.HomeScreen
import com.example.medidorapp.ui.MedicionesListScreen
import com.example.medidorapp.viewmodel.MedidorViewModel

@Composable
fun Navigation(viewModel: MedidorViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onRegisterClick = { navController.navigate("form") },
                onViewListClick = { navController.navigate("list") }
            )
        }
        composable("list") {
            MedicionesListScreen(viewModel) {
                navController.popBackStack()
            }
        }
        composable("form") {
            FormularioMedicionScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

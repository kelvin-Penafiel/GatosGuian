package com.example.gatosguian.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gatosguian.view.GatosViewModel
import com.example.gatosguian.view.MainScreen
import com.example.gatosguian.view.SplashScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController=navController,
        startDestination =AppScreens.SplashScreen.route
    ){
       composable(AppScreens.SplashScreen.route){
           SplashScreen(navController)
       }
        composable(AppScreens.MainScreen.route){
            MainScreen(viewModel = GatosViewModel(), navController)
        }
    }
}
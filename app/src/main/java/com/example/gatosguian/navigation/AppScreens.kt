package com.example.gatosguian.navigation



sealed class AppScreens(val route: String) {
    //object SplashScreen: AppScreens(route = "splash_screen")
    //object MainScreen: AppScreens(route = "main_screen")


    data object SplashScreen: AppScreens("splash_screen")
    data object MainScreen: AppScreens("main")
    //data object LoginScreen: AppScreens("login")
    data object LoginScreen: AppScreens("login")
   // data object PedidoScreen: AppScreens("pedido")
    data object NewProductScreen: AppScreens("product")
    data object Camera: AppScreens("camera")
}
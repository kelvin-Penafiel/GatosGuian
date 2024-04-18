package com.example.gatosguian.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gatosguian.navigation.AppScreens

sealed class BottomNavItem(
    var route: String,
    var unselectedIcon: ImageVector,
    var selectedIcon: ImageVector,
    var title: String
) {

    data object SplashScreen: BottomNavItem(
        route = "splas_screen",
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        title = "Inicio"
    )
    data object Home: BottomNavItem (
        route = "home",
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        title = "Inicio"
    )
    data object Favourites: BottomNavItem (
        route = "favourites",
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        title = "Favoritos"
    )
    data object Perfil: BottomNavItem (
        route = "perfil",
        unselectedIcon = Icons.Outlined.AccountCircle,
        selectedIcon = Icons.Filled.AccountCircle,
        title = "Perfil"
    )
}
package com.example.gatosguian.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.gatosguian.view.Fotos
import com.example.gatosguian.view.Gatos
import com.example.gatosguian.view.GatosViewModel
import com.example.gatosguian.view.ImagenViewModel
import com.example.gatosguian.view.Opiniones
import com.example.gatosguian.view.TextViewModel

sealed class ItemTabs(
    var title: String,
    var iconSelected: ImageVector,
    var iconUnSelected: ImageVector,
    var screen: @Composable() (viewModel: GatosViewModel, viewModelText: TextViewModel, viewModelImage: ImagenViewModel, navHostController: NavHostController) -> Unit
) {
    object tab_gatos : ItemTabs(
        "Razas de Gatos",
        Icons.Filled.Favorite,
        Icons.Filled.FavoriteBorder,
        {viewModel, viewModelText, viewModelImage, navHostController -> Gatos(viewModel = viewModel, viewModelText = viewModelText, viewModelImage = viewModelImage, navHostController = navHostController) }
    )

    object tab_juguetes : ItemTabs(
        "Opiniones",
        Icons.Filled.Info,
        Icons.Filled.Info,
        {viewModel, viewModelText, viewModelImage, navHostController -> Opiniones(viewModel= viewModel, viewModelText = viewModelText, viewModelImage = viewModelImage, navHostController = navHostController) }
    )
    object tab_veterinarios : ItemTabs(
        "Fotos",
        Icons.Filled.AccountBox,
        Icons.Filled.AccountBox,
        {viewModel, viewModelText, viewModelImage, navHostController -> Fotos(viewModel= viewModel, viewModelText = viewModelText, viewModelImage = viewModelImage, navHostController = navHostController) }
    )
}
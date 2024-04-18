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
import com.example.gatosguian.view.Opiniones

sealed class ItemTabs(
    var title: String,
    var iconSelected: ImageVector,
    var iconUnSelected: ImageVector,
    var screen: @Composable() (viewModel: GatosViewModel, navHostController: NavHostController) -> Unit
) {
    object tab_gatos : ItemTabs(
        "Razas de Gatos",
        Icons.Filled.Favorite,
        Icons.Filled.FavoriteBorder,
        {viewModel, navHostController -> Gatos(viewModel = viewModel, navHostController = navHostController) }
    )

    object tab_juguetes : ItemTabs(
        "Opiniones",
        Icons.Filled.Info,
        Icons.Filled.Info,
        {viewModel, navHostController -> Opiniones(navHostController = navHostController) }
    )
    object tab_veterinarios : ItemTabs(
        "Fotos",
        Icons.Filled.AccountBox,
        Icons.Filled.AccountBox,
        {viewModel, navHostController -> Fotos(navHostController = navHostController) }
    )
}
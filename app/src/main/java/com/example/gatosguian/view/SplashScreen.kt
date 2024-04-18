package com.example.gatosguian.view

import android.window.SplashScreen
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.savedstate.SavedStateRegistryController
import com.example.gatosguian.R
import com.example.gatosguian.navigation.AppScreens
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController){
    LaunchedEffect(key1 = true) {
        delay(5000)
        navController.popBackStack()
        navController.navigate(AppScreens.MainScreen.route)
    }
    Splash()

    /*Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd) {
        //Text(text ="V0.01" , fontSize =10.sp)
        Text(text ="V0.011" , modifier = Modifier.padding(10.sp))
    }*/
}



@Composable
fun Splash(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo GatosGuian" )

        Text(text = "Espere cargando...")
    }




}

@Preview(showBackground = true,
    showSystemUi = true)

@Composable
fun SplashScreenPreview(){
    //SplashScreen()
}

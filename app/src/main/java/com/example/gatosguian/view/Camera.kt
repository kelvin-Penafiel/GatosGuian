package com.example.gatosguian.view

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.example.gatosguian.navigation.AppScreens
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File
import java.util.concurrent.Executor

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Camera(navHostController: NavHostController) {
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val context = LocalContext.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }
    val lifecilcly = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val executor = ContextCompat.getMainExecutor(context)
                takePicture(cameraController, executor, navHostController)
            }) {
                Text(text = "Foto")
            }
        }) {
        if (permissionState.status.isGranted) {
            CameraController(cameraController, lifecilcly, modifier = Modifier.padding(it))
        } else {
            Text(text = "Permiso Denegado", modifier = Modifier.padding(it))
        }
    }
}

private fun takePicture(cameraController: LifecycleCameraController, executor: Executor, navHostController: NavHostController){
    val file = File.createTempFile("foto",".jpg")
    val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
    cameraController.takePicture(outputDirectory, executor, object: ImageCapture.OnImageSavedCallback{
        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            cameraController.unbind()//cierra la camara
            val savedUri = outputFileResults.savedUri ?: Uri.fromFile(file)
            println("ruta imagen: "+outputFileResults.savedUri)
            val encodedUri = Uri.encode(savedUri.toString())

            navHostController.navigate("${AppScreens.NewProductScreen.route}/$encodedUri")
            //*  navHostController.navigate("product/$savedUri")
            //navHostController.navigate("product")
        }

        override fun onError(exception: ImageCaptureException) {

            println("error al tomar foto")
        }

    })
}

@Composable
fun CameraController(
    cameraController: LifecycleCameraController,
    lifecicly: LifecycleOwner,
    modifier: Modifier
) {

    cameraController.bindToLifecycle(lifecicly)
    //Text(text = "Permiso Concedido")
    AndroidView(modifier = modifier, factory = { context ->
        val previewView = PreviewView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
        }
        previewView.controller = cameraController
        previewView
    })
}
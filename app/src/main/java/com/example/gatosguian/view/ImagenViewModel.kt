package com.example.gatosguian.view

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.example.gatosguian.data.ImagensRepository

import com.example.gatosguian.model.Imagen
import com.example.gatosguian.model.ImagenResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ImagenViewModel: ViewModel(){
    private val imagesRepository = ImagensRepository()
    private val allImagen = mutableStateListOf<Imagen>()
    private val _filteredImagen = MutableStateFlow<List<Imagen>>(emptyList())
    val filteredImagen: StateFlow<List<Imagen>> = _filteredImagen

    fun getImagenById(ImagenId: Int): Imagen? {
        return allImagen.find { it.id == ImagenId } // Suponiendo que gatos tiene una propiedad "id" que es String
    }
    init {
        fetchImagen()
    }

    fun fetchImagen() {
        viewModelScope.launch {
            val response = imagesRepository.getImagens()
            val imagensResponse = response.map { imagenResponse ->
                Imagen(
                    imagenResponse.id,
                    imagenResponse.url,
                    imagenResponse.width,
                    imagenResponse.height

                )
            }
            allImagen.addAll(imagensResponse)
            _filteredImagen.value = allImagen.toList() // Al inicio, los productos filtrados serán todos los gatos disponibles
        }
    }

    fun filterProducts(query: String) {
        val lowercaseQuery = query.lowercase()
        _filteredImagen.value = allImagen.filter { imagen ->
            imagen.url.lowercase().contains(lowercaseQuery) ||
                    imagen.url.lowercase().contains(lowercaseQuery)
        }
    }
}
package com.example.gatosguian.view

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gatosguian.data.CatsRepository
import com.example.gatosguian.model.Cat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GatosViewModel: ViewModel(){
    private val catsRepository = CatsRepository()
    private val allGatos = mutableStateListOf<Cat>()
    private val _filteredGatos = MutableStateFlow<List<Cat>>(emptyList())
    val filteredGatos: StateFlow<List<Cat>> = _filteredGatos

    fun getGatoById(catId: Int): Cat? {
        return allGatos.find { it.id == catId } // Suponiendo que Product tiene una propiedad "id" que es String
    }
    init {
        fetchGatos()
    }

    fun fetchGatos() {
        viewModelScope.launch {
            val response = catsRepository.getCats()
            val catsResponse = response.map { catResponse ->
                Cat(
                    catResponse.id,
                    catResponse.name,
                    catResponse.origin,
                    catResponse.temperament,
                    catResponse.image
                )
            }
            allGatos.addAll(catsResponse)
            _filteredGatos.value = allGatos.toList() // Al inicio, los productos filtrados serán todos los productos disponibles
        }
    }

    fun filterProducts(query: String) {
        val lowercaseQuery = query.lowercase()
        _filteredGatos.value = allGatos.filter { cat ->
            cat.name.lowercase().contains(lowercaseQuery) ||
                    cat.name.lowercase().contains(lowercaseQuery)
        }
    }
}
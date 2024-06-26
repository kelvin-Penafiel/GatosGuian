package com.example.gatosguian.view

import androidx.compose.runtime.mutableStateListOf
import androidx.core.provider.FontsContractCompat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.gatosguian.data.TextsRepository

import com.example.gatosguian.model.Text
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TextViewModel: ViewModel(){
   private val textsRepository = TextsRepository()
    private val allText= mutableStateListOf<Text>()
    private val _filteredText = MutableStateFlow<List<Text>>(emptyList())
    val filteredText: StateFlow<List<Text>> = _filteredText

    fun getGatoById(textId: String): Text? {
        return allText.find { it.text == textId } // Suponiendo que gatos tiene una propiedad "id" que es String
    }
    init {
        fetchText()
    }

    fun fetchText() {
        viewModelScope.launch {
            val response = textsRepository.getTexts()
            val textsResponse = response.map { textResponse ->
                Text(
                    //textResponse.id,
                    textResponse.text,
                    textResponse.type,
                    textResponse.updatedAt,
                    textResponse.createdAt
                )
            }
            allText.addAll(textsResponse)
            _filteredText.value = allText.toList() // Al inicio, los productos filtrados serán todos los gatos disponibles
        }
    }

    fun filterProducts(query: String) {
        val lowercaseQuery = query.lowercase()
        _filteredText.value = allText.filter { text ->
            text.text.lowercase().contains(lowercaseQuery) ||
                    text.text.lowercase().contains(lowercaseQuery)
        }
    }
}
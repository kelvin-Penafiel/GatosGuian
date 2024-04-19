package com.example.gatosguian.data

import com.example.gatosguian.model.TextResponse
import com.example.gatosguian.service.RetrofitInstance


class TextsRepository {

    private val textsService = RetrofitInstance.textsService

    suspend fun getTexts(): List<TextResponse> {
       return textsService.getTexts()
    }
}
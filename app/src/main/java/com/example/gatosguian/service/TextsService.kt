package com.example.gatosguian.service

import com.example.gatosguian.model.TextsResponse
import com.example.gatosguian.model.TextResponse
import retrofit2.http.GET

interface TextsService {
    @GET("facts")
    suspend fun  getTexts(): List<TextResponse>
}
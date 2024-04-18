package com.example.gatosguian.service

import com.example.gatosguian.model.ImagenResponse
import retrofit2.http.GET

interface ImagensService {
    @GET("search?limit=10")
    suspend fun  getImagens(): List<ImagenResponse>
}
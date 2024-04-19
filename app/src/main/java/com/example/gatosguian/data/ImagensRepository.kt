package com.example.gatosguian.data

import com.example.gatosguian.model.ImagenResponse
import com.example.gatosguian.service.RetrofitInstance


class ImagensRepository {

    private val imagensService = RetrofitInstance.imagensService

    suspend fun getImagens(): List<ImagenResponse> {
        return imagensService.getImagens()
    }
}
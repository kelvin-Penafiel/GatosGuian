package com.example.gatosguian.data

import com.example.gatosguian.model.CatResponse
import com.example.gatosguian.service.RetrofitInstance

class CatsRepository {
    private val catsService = RetrofitInstance.catsService

    suspend fun getCats(): List<CatResponse> {
        return catsService.getCats()
    }
}
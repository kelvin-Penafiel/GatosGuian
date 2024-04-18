package com.example.gatosguian.service

import com.example.gatosguian.model.CatResponse
import com.example.gatosguian.model.CatsResponse
import retrofit2.http.GET

interface CatsService {
    @GET("cats")
    suspend fun  getCats(): List<CatResponse>
}
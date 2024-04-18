package com.example.gatosguian.service

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitInstance {
    private  const val BASE_URL = "https://freetestapi.com/api/v1/"
    private const val BASE_URL_TEXT = "https://cat-fact.herokuapp.com/" //"https://cat-fact.herokuapp.com/facts/random?animal_type=cat&amount=10"
    private const val BASE_URL_IMAGEN= "https://api.thecatapi.com/v1/images/search?limit=100"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private val retrofitText: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_TEXT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    private val retrofitImagen: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_IMAGEN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val catsService: CatsService by lazy {
        retrofit.create(CatsService::class.java)
    }


    val textsService: TextsService by lazy {
        retrofitText.create(TextsService::class.java)
    }


    class ResponseInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val response = chain.proceed(request)

            // Si la respuesta es un código 200, imprime en la consola
            if (response.code == 200) {
                println("La respuesta fue exitosa: ${response.code}")
            }

            return response
        }
    }
}
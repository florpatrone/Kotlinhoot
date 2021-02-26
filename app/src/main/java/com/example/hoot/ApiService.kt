package com.example.hoot

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    companion object {
        val BASE_URL: String = "http://3.141.216.104:8080/api/v1/"
    }

    @GET("preguntas")
    suspend fun getResult():Response<List<Pregunta>>

}
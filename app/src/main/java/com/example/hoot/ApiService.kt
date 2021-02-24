package com.example.hoot

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        //val BASE_URL: String = "https://run.mocky.io/v3/f6027d09-9c43-4ea3-bedf-0041a8003fad/"
        val BASE_URL: String = "http://3.141.216.104:8080/api/v1/"
        //val BASE_URL: String = "http://127.0.0.1:8080/api/v1/"
    }

    @GET("preguntas")
    suspend fun getResult():Response<List<Pregunta>>

}
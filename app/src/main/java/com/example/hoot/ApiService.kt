package com.example.hoot

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        val BASE_URL: String = "https://run.mocky.io/v3/f6027d09-9c43-4ea3-bedf-0041a8003fad/"
    }

    @GET("search")
    suspend fun getResult(
        //@Query("q")
        //query:String?
    ):Response<List<Pregunta>>

}
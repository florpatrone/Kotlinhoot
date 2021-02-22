package com.example.hoot


import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

object Constants {
    var JUGADOR_UNO: Jugador = Jugador("")
    var JUGADOR_DOS: Jugador = Jugador("")
    const val TOTAL_PREGUNTAS: String = "total_questions"

    private val client = OkHttpClient()

    fun fetchJson() {
        val url = "https://run.mocky.io/v3/f6027d09-9c43-4ea3-bedf-0041a8003fad"
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val body = response.body!!.string()

                    val gson = GsonBuilder().create()
                    val preguntas = gson.fromJson(body, Array<Pregunta>::class.java)
                }
            }
        })
    }


    fun getQuestions(): ArrayList<com.example.hoot.Pregunta> {
        val listaPreguntas = ArrayList<com.example.hoot.Pregunta>()

        val preg1 = Pregunta(
            1,
            "choice",
            3,
            "Cuales de las siguientes son obras de Ray Bradbury?",
            listOf(
                Respuesta("Fahrenheit 451", true),
                Respuesta("Cronicas Marcianas", true),
                Respuesta("Bovedas de Acero", false),
                Respuesta("Remedio para melancolicos", true),
                Respuesta("El fin de la infancia", false)
            )
        )
        val preg2 = Pregunta(
            2,
            "choice",
            1,
            "Cuales de las siguientes bandas no son inglesas?",
            listOf(
                Respuesta("Pink Floyd", false),
                Respuesta("Queen", false),
                Respuesta("Metallica", true),
                Respuesta("Black Sabbath", false),
                Respuesta("DragonForce", false)
            )
        )
        val preg3 = Pregunta(
            3,
            "choice",
            3,
            "Cuales de los siguientes son lenguajes de programacion?",
            listOf(
                Respuesta("Kotlin", true),
                Respuesta("CSS", false),
                Respuesta("Brainfuck", true),
                Respuesta("HTML", false),
                Respuesta("Oz", true)
            )
        )
        val preg4 = Pregunta(
            4,
            "VF",
            1,
            "2 + 2 = 4?",
            listOf(
                Respuesta("Verdadero", true),
                Respuesta("Falso", false)
            )
        )

        listaPreguntas.add(preg1)
        listaPreguntas.add(preg4)
        listaPreguntas.add(preg2)
        listaPreguntas.add(preg3)
        return listaPreguntas
    }

}
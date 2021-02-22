package com.example.hoot


import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

object Constants {
    var JUGADOR_UNO: Jugador = Jugador("")
    var JUGADOR_DOS: Jugador = Jugador("")
    const val TOTAL_QUESTIONS: String = "total_questions"

    private val client = OkHttpClient()

    data class Pregunta(
        val tipoPregunta: String,
        val enunciado: String,
        val id: Long,
        val respuestas: List<Respuesta> = listOf(),
        val cantidadCorrecta: Int
    )


    data class Respuesta(
        val respuesta: String,
        val isTrue: Boolean
    )

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

        val que1 = Pregunta(
            1,
            "choice",
            3,
            "Cuales de las siguientes son obras de Ray Bradbury?",
            listOf(
                com.example.hoot.Respuesta("Fahrenheit 451", true),
                com.example.hoot.Respuesta("Cronicas Marcianas", true),
                com.example.hoot.Respuesta("Bovedas de Acero", false),
                com.example.hoot.Respuesta("Remedio para melancolicos", true),
                com.example.hoot.Respuesta("El fin de la infancia", false)
            )
        )
        val que2 = Pregunta(
            2,
            "choice",
            1,
            "Cuales de las siguientes bandas no son inglesas?",
            listOf(
                com.example.hoot.Respuesta("Pink Floyd", false),
                com.example.hoot.Respuesta("Queen", false),
                com.example.hoot.Respuesta("Metallica", true),
                com.example.hoot.Respuesta("Black Sabbath", false),
                com.example.hoot.Respuesta("DragonForce", false)
            )
        )
        val que3 = Pregunta(
            3,
            "choice",
            3,
            "Cuales de los siguientes son lenguajes de programacion?",
            listOf(
                com.example.hoot.Respuesta("Kotlin", true),
                com.example.hoot.Respuesta("CSS", false),
                com.example.hoot.Respuesta("Brainfuck", true),
                com.example.hoot.Respuesta("HTML", false),
                com.example.hoot.Respuesta("Oz", true)
            )
        )

        listaPreguntas.add(que1)
        listaPreguntas.add(que2)
        listaPreguntas.add(que3)
        return listaPreguntas
    }

}
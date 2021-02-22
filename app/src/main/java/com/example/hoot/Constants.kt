package com.example.hoot


import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

object Constants {
    const val USER_NAME_ONE: String = "user_name_one"
    const val USER_NAME_TWO: String = "user_name_two"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS_ONE: String = "correct_answers_one"
    const val CORRECT_ANSWERS_TWO: String = "correct_answers_two"

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


    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,
            "choice",
            3,
            "Cuales de las siguientes son obras de Ray Bradbury?",
            listOf(
                Answer("Fahrenheit 451", true),
                Answer("Cronicas Marcianas", true),
                Answer("Bovedas de Acero", false),
                Answer("Remedio para melancolicos", true),
                Answer("El fin de la infancia", false)
            )
        )
        val que2 = Question(
            2,
            "choice",
            1,
            "Cuales de las siguientes bandas no son inglesas?",
            listOf(
                Answer("Pink Floyd", false),
                Answer("Queen", false),
                Answer("Metallica", true),
                Answer("Black Sabbath", false),
                Answer("DragonForce", false)
            )
        )
        val que3 = Question(
            3,
            "choice",
            3,
            "Cuales de los siguientes son lenguajes de programacion?",
            listOf(
                Answer("Kotlin", true),
                Answer("CSS", false),
                Answer("Brainfuck", true),
                Answer("HTML", false),
                Answer("Oz", true)
            )
        )

        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        return questionsList
    }

}
package com.example.hoot


import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

object Constants {
    var JUGADOR_UNO: Jugador = Jugador("")
    var JUGADOR_DOS: Jugador = Jugador("")
    var LISTA: ArrayList<Pregunta>? = null
    const val TOTAL_PREGUNTAS: String = "total_questions"



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
            "vof",
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
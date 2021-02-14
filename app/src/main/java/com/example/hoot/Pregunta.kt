package com.example.hoot

data class PreguntaChoice(
    val id: Int,
    val pregunta: String,
    val opcionUno: String,
    val opcionDos: String,
    val opcionTres: String,
    val opcionCuatro: String,
    val opcionCinco: String,
    val respuesta: ArrayList<Int>
)
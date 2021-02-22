package com.example.hoot

data class Pregunta(
    val id: Long,
    val tipoPregunta: String,
    val cantidadCorrecta: Int,
    val enunciado: String,
    val respuestas: List<Respuesta>
)

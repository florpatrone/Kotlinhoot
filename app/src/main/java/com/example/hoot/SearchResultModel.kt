package com.example.hoot

data class SearchResultModel (
    val `data`: Data?,
    val status: String?
){

    data class Data(
        val preguntas: List<Pregunta?>?
    )

}
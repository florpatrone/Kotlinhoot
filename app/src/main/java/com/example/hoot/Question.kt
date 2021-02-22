package com.example.hoot

data class Question(
    val id: Long,
    val type: String,
    val correctAnswers: Int,
    val question: String,
    val answers: List<Answer>
)

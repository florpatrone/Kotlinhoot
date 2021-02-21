package com.example.hoot

data class Question(
    val id: Int,
    val type: String,
    val correctAnswer: Int,
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val optionFive: String
)
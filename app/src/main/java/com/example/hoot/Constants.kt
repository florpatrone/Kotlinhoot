package com.example.hoot


object Constants {
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,
            "Cuales de las siguientes son obras de Ray Bradbury?",
            "Fahrenheit 451",
            "Cronicas Marcianas",
            "Bovedas de Acero",
            "Remedio para melancolicos",
            "El fin de la infancia",1
        )
        val que2 = Question(
            2,
            "Cuales de las siguientes bandas no son inglesas?",
            "Pink Floyd",
            "Queen",
            "Metallica",
            "Black Sabbath",
            "DragonForce",3
        )
        val que3 = Question(
            3,
            "Cuales de los siguientes son lenguajes de programacion?",
            "Kotlin",
            "CSS",
            "Brainfuck",
            "HTML",
            "Oz",2
        )

        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        return questionsList
    }

}
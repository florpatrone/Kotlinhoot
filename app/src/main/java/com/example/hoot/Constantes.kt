package com.example.hoot


object Constantes {
    fun obtenerPreguntas(): ArrayList<PreguntaChoice> {
        val listaPreguntas = ArrayList<PreguntaChoice>()

        val preg1 = PreguntaChoice(
            1,
            "Cuales de las siguientes son obras de Ray Bradbury?",
            "Fahrenheit 451",
            "Cronicas Marcianas",
            "Bovedas de Acero",
            "Remedio para melancolicos",
            "El fin de la infancia",
            arrayListOf(1, 2, 4)
        )
        val preg2 = PreguntaChoice(
            2,
            "Cuales de las siguientes bandas no son inglesas?",
            "Pink Floyd",
            "Queen",
            "Metallica",
            "Black Sabbath",
            "DragonForce",
            arrayListOf(3)
        )
        val preg3 = PreguntaChoice(
            3,
            "Cuales de los siguientes son lenguajes de programacion?",
            "Kotlin",
            "CSS",
            "Brainfuck",
            "HTML",
            "Oz",
            arrayListOf(1, 3, 5)
        )

        listaPreguntas.add(preg1)
        listaPreguntas.add(preg2)
        listaPreguntas.add(preg3)
        return listaPreguntas
    }

}
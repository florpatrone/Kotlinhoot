package com.example.hoot

class ComparadorRespuestas {

    fun compararRespuestas(preguntaActual: Question, respuestasElegidas: List<Answer>): Int {

        var cantidadIncorrectas: Int = 0
        var cantidadCorrecta: Int = 0
        respuestasElegidas.forEach{ respuesta ->
            if(!respuesta.isTrue) {
                cantidadIncorrectas++
            }
            else cantidadCorrecta++
        }

        if(preguntaActual.correctAnswers == cantidadCorrecta && (cantidadIncorrectas == 0)) {
            return 1
        }
        else return -1

    }
}
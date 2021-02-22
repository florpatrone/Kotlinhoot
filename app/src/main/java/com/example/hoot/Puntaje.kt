package com.example.hoot


class Puntaje {

    var puntos: Int = 0
    var factor: Int = 1

    fun agregarMultiplicador(multiplicador: Int) {
        factor = multiplicador
    }

    fun sumarPuntos(puntos: Int) {
        this.puntos += puntos * factor
        factor = 1
    }

    fun getPuntaje(): Int {
        return puntos
    }


}
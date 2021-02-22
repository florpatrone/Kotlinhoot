package com.example.hoot

class Jugador(private val nombre: String) {

    var puntaje: Puntaje = Puntaje()
    //lateinit var jugadorSiguiente: Jugador


    fun agregarMultiplicador(factor: Int) {
        puntaje.agregarMultiplicador(factor)
    }

    fun sumarPuntos(puntaje: Int) {
        this.puntaje.sumarPuntos(puntaje)
    }

    fun getPuntaje(): Int {
        return puntaje.getPuntaje()
    }

    fun getNombre(): String {
        return nombre
    }

}
package com.example.hoot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class PreguntasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preguntas)

        val listaPreguntas = Constantes.obtenerPreguntas()
        Log.i("Tamanio preguntas", "${listaPreguntas.size}")
    }
}
package com.example.hoot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_preguntas.*

class PreguntasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preguntas)

        val listaPreguntas = Constantes.obtenerPreguntas()
        Log.i("Tamanio preguntas", "${listaPreguntas.size}")

        val posActual = 1
        val pregunta: PreguntaChoice? = listaPreguntas[posActual - 1]

        progress_bar.progress = posActual
        tv_progress.text = "$posActual" + "/" + progress_bar.max
        tv_question.text = pregunta!!.pregunta
        tv_option_one.text = pregunta.opcionUno
        tv_option_two.text = pregunta.opcionDos
        tv_option_three.text = pregunta.opcionTres
        tv_option_four.text = pregunta.opcionCuatro
        tv_option_five.text = pregunta.opcionCinco
    }
}
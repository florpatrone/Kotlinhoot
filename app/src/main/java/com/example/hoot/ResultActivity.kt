package com.example.hoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val usernameOne = Constants.JUGADOR_UNO.getNombre()
        val usernameTwo = Constants.JUGADOR_DOS.getNombre()
        val puntajeUno = Constants.JUGADOR_UNO.getPuntaje()
        val puntajeDos = Constants.JUGADOR_DOS.getPuntaje()

        if (puntajeUno > puntajeDos) {
            tv_name.text = "GANADOR: $usernameOne"
        } else if (puntajeUno < puntajeDos) {
            tv_name.text = "GANADOR: $usernameTwo"
        } else {
            tv_name.text = "EMPATE"
        }

        tv_score_one.text = "$usernameOne - Puntaje: $puntajeUno"
        tv_score_two.text = "$usernameTwo - Puntaje: $puntajeDos"

        btn_finish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
package com.example.hoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var apiService: ApiService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiService = ApiProvider.createService(ApiService::class.java)

        btn_start.setOnClickListener {
            if (et_name_one.text.toString().isEmpty() or et_name_two.text.toString().isEmpty()) {
                Toast.makeText(this, "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                Constants.JUGADOR_UNO = Jugador(et_name_one.text.toString())
                Constants.JUGADOR_DOS = Jugador(et_name_two.text.toString())

                searchData()
                startActivity(intent)
                finish()
            }
        }

    }


    private fun searchData() =
        runBlocking {

            val lista: Response<List<Pregunta>> =
                async { apiService?.getResult() }.await()!!

            Constants.LISTA_PREGUNTAS = lista.body() as ArrayList<Pregunta>
        }
}
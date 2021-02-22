package com.example.hoot

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var mPosActual: Int = 1
    private var mListaPreguntas: ArrayList<Pregunta>? = null
    private var mOpcionesElegidas = mutableSetOf<Int>()
    private var mTipoPregunta: String? = null
    private var mJugadorActivo: Jugador = Constants.JUGADOR_UNO
    private var mJugadorSiguiente: Jugador = Constants.JUGADOR_DOS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        mListaPreguntas = Constants.getQuestions()
        //mQuestionsList = Constants.fetchJson()
        setQuestion()


        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        if (mTipoPregunta == "choice") {
            tv_option_three.setOnClickListener(this)
            tv_option_four.setOnClickListener(this)
            tv_option_five.setOnClickListener(this)
        }
        btn_submit.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(tv_option_one, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(tv_option_two, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(tv_option_three, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(tv_option_four, 4)
            }
            R.id.tv_option_five -> {
                selectedOptionView(tv_option_five, 5)
            }
            R.id.btn_submit -> {
                if (mOpcionesElegidas.isEmpty()) {
                    mPosActual++

                    when {
                        mPosActual <= mListaPreguntas!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            //intent.putExtra(Constants.USER_NAME_ONE, mUserNameOne)
                            //intent.putExtra(Constants.USER_NAME_TWO, mUserNameTwo)
                            //intent.putExtra(Constants.CORRECT_ANSWERS_ONE, mCorrectAnswersOne)
                            //intent.putExtra(Constants.CORRECT_ANSWERS_TWO, mCorrectAnswersTwo)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mListaPreguntas!!.size)
                            startActivity(intent)
                        }
                    }
                } else {
                    val pregunta = mListaPreguntas!![mPosActual - 1]
                    val respuestasElegidas = getAnswers()
                    val comparador = ComparadorRespuestas()
                    val puntaje = comparador.compararRespuestas(pregunta, respuestasElegidas)

                    mJugadorActivo.sumarPuntos(puntaje)

                    if (mPosActual == mListaPreguntas!!.size) {
                        btn_submit.text = "TERMINAR"
                    } else {
                        btn_submit.text = "SIGUIENTE"
                    }
                    mOpcionesElegidas.clear()
                    val aux = mJugadorActivo
                    mJugadorActivo = mJugadorSiguiente
                    mJugadorSiguiente = aux
                }
            }
        }
    }

    private fun getAnswers(): List<Respuesta> {
        val pregunta = mListaPreguntas!![mPosActual - 1]
        val respuestasElegidas = pregunta.respuestas.filter { a: Respuesta ->
            mOpcionesElegidas.contains(pregunta.respuestas.indexOf(a) + 1)
        }
        return respuestasElegidas
    }


    private fun setQuestion() {

        val pregunta = mListaPreguntas!![mPosActual - 1]
        mTipoPregunta = pregunta.tipoPregunta

        defaultOptionsView()

        if (mPosActual == mListaPreguntas!!.size) {
            btn_submit.text = "TERMINAR"
        } else {
            btn_submit.text = "ENVIAR"
        }

        progress_bar.progress = mPosActual
        tv_progress.text = "$mPosActual" + "/" + progress_bar.max
        tv_question.text = pregunta.enunciado
        tv_option_one.text = pregunta.respuestas[0].respuesta
        tv_option_two.text = pregunta.respuestas[1].respuesta
        if (mTipoPregunta == "choice") {
            tv_option_three.text = pregunta.respuestas[2].respuesta
            tv_option_four.text = pregunta.respuestas[3].respuesta
            tv_option_five.text = pregunta.respuestas[4].respuesta
        }

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        if (mOpcionesElegidas.contains(selectedOptionNum)) {
            unselectedOptionView(tv, selectedOptionNum)
            return
        }
        mOpcionesElegidas.add(selectedOptionNum)

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background =
            ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun unselectedOptionView(tv: TextView, selectedOptionNum: Int) {
        mOpcionesElegidas.remove(selectedOptionNum)

        tv.setTextColor(Color.parseColor("#7A8089"))
        tv.typeface = Typeface.DEFAULT
        tv.background =
            ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
    }

    private fun defaultOptionsView() {
        val opciones = ArrayList<TextView>()

        opciones.add(0, tv_option_one)
        opciones.add(1, tv_option_two)

        if (mTipoPregunta == "choice") {
            opciones.add(2, tv_option_three)
            opciones.add(3, tv_option_four)
            opciones.add(4, tv_option_five)
        }

        for (opcion in opciones) {
            opcion.setTextColor(Color.parseColor("#7A8089"))
            opcion.typeface = Typeface.DEFAULT
            opcion.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
        }
    }
}
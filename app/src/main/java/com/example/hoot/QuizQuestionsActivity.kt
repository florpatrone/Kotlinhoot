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
    private var mCurrentPos: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptions = mutableSetOf<Int>()
    private var mCorrectAnswersOne: Int = 0
    private var mCorrectAnswersTwo: Int = 0
    private var mUserNameOne: String? = null
    private var mUserNameTwo: String? = null
    private var mQuestionType: String? = null
    private var mActiveUser: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        mUserNameTwo = intent.getStringExtra(Constants.USER_NAME_TWO)
        mUserNameOne = intent.getStringExtra(Constants.USER_NAME_ONE)

        mQuestionsList = Constants.getQuestions()
        //mQuestionsList = Constants.fetchJson()
        setQuestion()


        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        if (mQuestionType == "choice") {
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
                if (mSelectedOptions.isEmpty()) {
                    mCurrentPos++

                    when {
                        mCurrentPos <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME_ONE, mUserNameOne)
                            intent.putExtra(Constants.USER_NAME_TWO, mUserNameTwo)
                            intent.putExtra(Constants.CORRECT_ANSWERS_ONE, mCorrectAnswersOne)
                            intent.putExtra(Constants.CORRECT_ANSWERS_TWO, mCorrectAnswersTwo)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                        }
                    }
                } else {
                    val question = mQuestionsList!![mCurrentPos - 1]
                    val choosenAnswers = getAnswers()
                    val comparador = ComparadorRespuestas()
                    val score = comparador.compararRespuestas(question, choosenAnswers)

                    if (mActiveUser == 1) {
                        mCorrectAnswersOne += score
                    } else if (mActiveUser == 2) {
                        mCorrectAnswersTwo += score
                    }

                    if (mCurrentPos == mQuestionsList!!.size) {
                        btn_submit.text = "TERMINAR"
                    } else {
                        btn_submit.text = "SIGUIENTE"
                    }
                    mSelectedOptions.clear()
                    if (mActiveUser == 1) {
                        mActiveUser = 2
                    } else if (mActiveUser == 2) {
                        mActiveUser = 1
                    }
                }
            }
        }
    }

    private fun getAnswers(): List<Answer> {
        val question = mQuestionsList!![mCurrentPos-1]
        val choosenAnswers = question.answers.filter { a: Answer ->
            mSelectedOptions.contains(question.answers.indexOf(a)+1) }
        return choosenAnswers
    }


    private fun answerView(answer: Int, drawableView: Int) {
        if (mQuestionType == "choice") {
            when (answer) {
                1 -> {
                    tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
                }
                2 -> {
                    tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
                }
                3 -> {
                    tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
                }
                4 -> {
                    tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
                }
                5 -> {
                    tv_option_five.background = ContextCompat.getDrawable(this, drawableView)
                }
            }
        } else if (mQuestionType == "VF") {
            when (answer) {
                1 -> {
                    tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
                }
                2 -> {
                    tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
                }
            }
        }
    }

    private fun setQuestion() {

        val question = mQuestionsList!![mCurrentPos - 1]
        mQuestionType = question.type

        defaultOptionsView()

        if (mCurrentPos == mQuestionsList!!.size) {
            btn_submit.text = "TERMINAR"
        } else {
            btn_submit.text = "ENVIAR"
        }

        progress_bar.progress = mCurrentPos
        tv_progress.text = "$mCurrentPos" + "/" + progress_bar.max
        tv_question.text = question.question
        tv_option_one.text = question.answers[0].answer
        tv_option_two.text = question.answers[1].answer
        if (mQuestionType == "choice") {
            tv_option_three.text = question.answers[2].answer
            tv_option_four.text = question.answers[3].answer
            tv_option_five.text = question.answers[4].answer
        }

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        if (mSelectedOptions.contains(selectedOptionNum)) {
            unselectedOptionView(tv, selectedOptionNum)
            return
        }
        mSelectedOptions.add(selectedOptionNum)

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background =
            ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun unselectedOptionView(tv: TextView, selectedOptionNum: Int) {
        mSelectedOptions.remove(selectedOptionNum)

        tv.setTextColor(Color.parseColor("#7A8089"))
        tv.typeface = Typeface.DEFAULT
        tv.background =
            ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        options.add(0, tv_option_one)
        options.add(1, tv_option_two)

        if (mQuestionType == "choice") {
            options.add(2, tv_option_three)
            options.add(3, tv_option_four)
            options.add(4, tv_option_five)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
        }
    }
}
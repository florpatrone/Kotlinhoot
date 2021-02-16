package com.example.hoot

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPos: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPos: Int = 0
    private var mCorrectAnswers: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        mQuestionsList = Constants.getQuestions()
        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        tv_option_five.setOnClickListener(this)
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
                if (mSelectedOptionPos == 0) {
                    mCurrentPos++

                    when {
                        mCurrentPos <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(this, "Terminaste", Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPos-1)
                    if(question!!.correctAnswer != mSelectedOptionPos){
                        answerView(mSelectedOptionPos, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPos == mQuestionsList!!.size){
                        btn_submit.text = "TERMINAR"
                    }else{
                        btn_submit.text = "SIGUIENTE"
                    }
                    mSelectedOptionPos = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
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
    }

    private fun setQuestion() {

        val question = mQuestionsList!![mCurrentPos - 1]

        defaultOptionsView()

        if (mCurrentPos == mQuestionsList!!.size){
            btn_submit.text = "TERMINAR"
        }else{
            btn_submit.text = "ENVIAR"
        }

        progress_bar.progress = mCurrentPos
        tv_progress.text = "$mCurrentPos" + "/" + progress_bar.max
        tv_question.text = question.question
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
        tv_option_five.text = question.optionFive

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPos = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background =
            ContextCompat.getDrawable(this, R.drawable.selected_default_option_border_bg)
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)
        options.add(4, tv_option_five)

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
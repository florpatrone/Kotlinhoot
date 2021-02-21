package com.example.hoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val usernameOne = intent.getStringExtra(Constants.USER_NAME_ONE)
        val usernameTwo = intent.getStringExtra(Constants.USER_NAME_TWO)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswersOne = intent.getIntExtra(Constants.CORRECT_ANSWERS_ONE, 0)
        val correctAnswersTwo =  intent.getIntExtra(Constants.CORRECT_ANSWERS_TWO, 0)

        if (correctAnswersOne > correctAnswersTwo) {
            tv_name.text = "GANADOR: $usernameOne"
        } else if (correctAnswersOne < correctAnswersTwo) {
            tv_name.text = "GANADOR: $usernameTwo"
        } else {
            tv_name.text = "EMPATE"
        }

        tv_score_one.text = "$usernameOne: $correctAnswersOne correctas de $totalQuestions"
        tv_score_two.text = "$usernameTwo: $correctAnswersTwo correctas de $totalQuestions"

        btn_finish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
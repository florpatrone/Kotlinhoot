package com.example.hoot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start.setOnClickListener {
            if (et_name_one.text.toString().isEmpty() or et_name_two.text.toString().isEmpty()) {
                Toast.makeText(this, "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME_ONE, et_name_one.text.toString())
                intent.putExtra(Constants.USER_NAME_TWO, et_name_two.text.toString())
                startActivity(intent)
                finish()
            }
        }

    }
}
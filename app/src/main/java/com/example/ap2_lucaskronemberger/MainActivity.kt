package com.example.ap2_lucaskronemberger

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btStartQuiz: Button = findViewById(R.id.bt_start_quiz)

        btStartQuiz.setOnClickListener {
            val intent = Intent(this, QuizDigitalActivity::class.java)
            startActivity(intent)
        }
    }
}
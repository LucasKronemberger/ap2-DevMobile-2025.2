package com.example.ap2_lucaskronemberger

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RotinaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotina)

        val digitalScore = intent.getIntExtra("PONTOS_DIGITAL", 0)

        if (savedInstanceState == null) {
            val fragment = HabitosFragment()
            val args = Bundle()
            args.putInt("PONTOS_DIGITAL", digitalScore)
            fragment.arguments = args

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
        }
    }
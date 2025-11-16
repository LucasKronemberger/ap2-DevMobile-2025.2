package com.example.ap2_lucaskronemberger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AmbienteActivity : AppCompatActivity() {

    private var scoreFromPreviousStep: Int = 0



    private lateinit var Passo3: TextView
    private lateinit var Nivel: TextView
    private lateinit var seekbar: SeekBar
    private lateinit var Temperatura: RadioGroup
    private lateinit var btNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ambiente)

        scoreFromPreviousStep = intent.getIntExtra("PONTOS_TOTAIS", 0)

        Passo3 = findViewById(R.id.passo3)
        Nivel = findViewById(R.id.nivel)
        seekbar = findViewById(R.id.seekbar)
        Temperatura = findViewById(R.id.temperatura_quarto)
        btNext = findViewById(R.id.bt_next)


        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Nivel.text = "Nível: $progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        btNext.setOnClickListener {
            collectDataAndProceed()
        }
    }

    private fun collectDataAndProceed() {
        var score = scoreFromPreviousStep
        var isFormValid = true

        val niveldabarra = seekbar.progress
        when {
            niveldabarra >= 8 -> score += 10 // Ótimo
            niveldabarra >= 5 -> score += 5  // Bom
            else -> score += 0                // Ruim
        }

        when (Temperatura.checkedRadioButtonId) {
            R.id.temp_confortavel -> score += 10
            R.id.temp_quente -> score += 0
            R.id.temp_frio -> score += 0
            else -> {
                isFormValid = false // Validação
                Toast.makeText(this, "Por favor, selecione a temperatura", Toast.LENGTH_SHORT).show()
            }
        }

        if (isFormValid) {
            val intent = Intent(this, ResultadoActivity::class.java)
            intent.putExtra("PONTUACAO_FINAL", score)
            startActivity(intent)

            finish()
        }
    }
}
package com.example.ap2_lucaskronemberger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizDigitalActivity : AppCompatActivity() {

    // declarando as views para usar o find depois
    private lateinit var tempoDeTela: RadioGroup
    private lateinit var notificacaoSilenciada: Switch
    private lateinit var btNext: Button
    private lateinit var usoCelularManha: Switch
    private lateinit var modoNoturno: Switch
    private lateinit var checarMadruga: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_digital)

        // adicionando conteudo nas variáveis
        tempoDeTela = findViewById(R.id.tempo_de_tela)
        notificacaoSilenciada = findViewById(R.id.notificacao_silenciada)
        btNext = findViewById(R.id.bt_next)
        usoCelularManha = findViewById(R.id.uso_celular_acordar)
        modoNoturno = findViewById(R.id.modo_noturno_luz_azul)
        checarMadruga = findViewById(R.id.checar_madruga)

        btNext.setOnClickListener {
            collectDataAndProceed()
        }
    }


    // pontuação das perguntas
    private fun collectDataAndProceed() {
        var score = 0
        var isFormValid = true // check de resposta preenchida

        // pergunta 1
        when (tempoDeTela.checkedRadioButtonId) {
            R.id.tela1 -> score += 10
            R.id.tela2 -> score += 7
            R.id.tela3 -> score += 3
            R.id.tela4 -> score += 0
            else -> {
                isFormValid = false
                Toast.makeText(this, "Por favor, responda a pergunta 1", Toast.LENGTH_SHORT).show() //pop up
            }
        }

        // pergunta 2
        if (isFormValid) {
            if (notificacaoSilenciada.isChecked) score += 5
        }

        // pergunta 3
        if (isFormValid) {
            if (usoCelularManha.isChecked) {
                score += 0
            } else {
                score += 5
            }
        }

        //pergunta 4
        if (isFormValid) {
            if (modoNoturno.isChecked) score += 5
        }

        //pergunta 5
        if (isFormValid) {
            when (checarMadruga.checkedRadioButtonId) {
                R.id.madruga_1 -> score += 10
                R.id.madruga_2 -> score += 5
                R.id.madruga_3 -> score += 0
                else -> {
                    isFormValid = false
                    Toast.makeText(this, "Por favor, responda a pergunta 5", Toast.LENGTH_SHORT).show()
                }
            }
        }

        if (isFormValid) { // so passa de tela se tiver respota 1 e 5 marcada
            val intent = Intent(this, RotinaActivity::class.java)
            intent.putExtra("PONTOS_DIGITAL", score) // enchendo a mochila de pontos para a proxima tela
            startActivity(intent)

            println("Pontuação Digital Total Calculada: $score")
        }
    }
}
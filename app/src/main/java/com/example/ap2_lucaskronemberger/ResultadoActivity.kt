package com.example.ap2_lucaskronemberger

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat // Import necessário para cores

class ResultadoActivity : AppCompatActivity() {


    private lateinit var class_resultado: TextView
    private lateinit var pontos: TextView
    private lateinit var resultado_recomendacao: TextView
    private lateinit var share: Button
    private lateinit var restart: Button

    private var finalScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)


        finalScore = intent.getIntExtra("PONTUACAO_FINAL", 0)


        initializeViews()


        displayResult(finalScore)


        setupListeners()
    }

    private fun initializeViews() {

        class_resultado = findViewById(R.id.class_resultado)
        pontos = findViewById(R.id.score)
        resultado_recomendacao = findViewById(R.id.resultado_recomendacao)
        share = findViewById(R.id.share)
        restart = findViewById(R.id.restart)

    }


    private fun displayResult(score: Int) {

        pontos.text = "Pontuação Total: $score"


        val classification: String
        val recommendation: String
        val classificationColor: Int

        when {
            score > 80 -> {
                classification = "Qualidade do Sono: Ótima"
                recommendation = "Parabéns! Seus hábitos de sono e bem-estar digital são excelentes. Continue assim!"
                classificationColor = ContextCompat.getColor(this, R.color.cor_otima)
            }
            score > 50 -> {
                classification = "Qualidade do Sono: Boa"
                recommendation = "Você está no caminho certo. Tente melhorar o ambiente do seu quarto (mais escuro e silencioso) e reduzir o uso de telas 1h antes de dormir."
                classificationColor = ContextCompat.getColor(this, R.color.cor_boa)
            }
            score > 30 -> {
                classification = "Qualidade do Sono: Regular"
                recommendation = "Seu sono pode melhorar. Foque em criar uma rotina: evite cafeína à tarde, silencie o celular e tente dormir no mesmo horário."
                classificationColor = ContextCompat.getColor(this, R.color.cor_regular)
            }
            else -> {
                classification = "Qualidade do Sono: Crítica"
                recommendation = "Seu sono precisa de atenção. O uso de telas e a falta de rotina estão afetando seu descanso. Pequenas mudanças, como silenciar o celular à noite, farão uma grande diferença."
                classificationColor = ContextCompat.getColor(this, R.color.cor_critica)
            }
        }

        class_resultado.text = classification
        class_resultado.setTextColor(classificationColor)
        resultado_recomendacao.text = recommendation
    }

    private fun setupListeners() {


        share.setOnClickListener {

            val shareText = "Minha pontuação no Quiz de Bem-Estar Digital foi $finalScore. Classificação: ${class_resultado.text}"


            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND // Ação de enviar
                putExtra(Intent.EXTRA_TEXT, shareText) // O texto
                type = "text/plain" // O tipo de conteúdo
            }


            val shareIntent = Intent.createChooser(sendIntent, "Compartilhar resultado via...")
            startActivity(shareIntent)
        }


        restart.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(intent)
            finish()
        }
    }
}
package com.example.ap2_lucaskronemberger

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner

class HabitosFragment : Fragment() {

    private var initialScore: Int = 0


    private lateinit var spinner: Spinner
    private lateinit var cafe_noite: CheckBox
    private lateinit var exercicio_noite: CheckBox
    private lateinit var btNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            initialScore = it.getInt("PONTOS_DIGITAL", 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_habitos, container, false)
        spinner = view.findViewById(R.id.spinner)
        cafe_noite = view.findViewById(R.id.uso_cafeina_noite)
        exercicio_noite = view.findViewById(R.id.exercicio_noite)
        btNext = view.findViewById(R.id.bt_next_habits)

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_hora,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        btNext.setOnClickListener {
            collectDataAndProceed()
        }

        return view
    }

    private fun collectDataAndProceed() {
        var score = initialScore
        val selectedIndex = spinner.selectedItemPosition
        val hoursPonto = when (selectedIndex) {
            3 -> 15
            2 -> 10
            1 -> 5
            0 -> 0
            else -> 0
        }

        score += hoursPonto

        if (cafe_noite.isChecked) {
            score -= 5
        } else {
            score += 5
        }

        if (exercicio_noite.isChecked) {
            score -= 5
        } else {
            score += 5
        }

        val intent = Intent(requireContext(), AmbienteActivity::class.java)
        intent.putExtra("PONTOS_TOTAIS", score)
        startActivity(intent)

        println("Pontuação Parcial calculada: $score")
    }
}
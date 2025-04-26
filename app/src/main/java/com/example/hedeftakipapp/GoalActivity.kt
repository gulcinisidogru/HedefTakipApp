package com.example.hedeftakipapp

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GoalActivity : AppCompatActivity() {

    private lateinit var buttonAddGoal: Button
    private lateinit var linearLayoutGoals: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        val textViewTitle: TextView = findViewById(R.id.textViewTitle)
        buttonAddGoal = findViewById(R.id.buttonAddGoal)
        linearLayoutGoals = findViewById(R.id.linearLayoutGoals)

        textViewTitle.text = "Hedef Ekle"

        // Örnek hedefler
        val goals = listOf("Hedef 1", "Hedef 2", "Hedef 3")

        // Hedefleri LinearLayout içine dinamik olarak ekliyoruz
        for (goal in goals) {
            val goalButton = Button(this)
            goalButton.text = goal
            goalButton.setPadding(12, 12, 12, 12)
            goalButton.setOnClickListener {
                // Burada tıklanan hedef ile ilgili işlem yapılabilir
            }
            linearLayoutGoals.addView(goalButton)
        }

        // Yeni hedef ekleme butonuna tıklama işlemi
        buttonAddGoal.setOnClickListener {
            // Burada kullanıcı yeni bir hedef ekleyebilir
        }
    }
}

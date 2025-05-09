package com.example.hedeftakipapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GoalsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        val linearLayoutGoals = findViewById<LinearLayout>(R.id.linearLayoutGoals)

        GoalData.getGoals().forEach { goal ->
            val textView = TextView(this).apply {
                text = "${goal.name}\n${goal.description}"
                textSize = 16f
                setPadding(32, 32, 32, 32)
            }
            linearLayoutGoals.addView(textView)
        }
    }
}
package com.example.hedeftakipapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonViewGoals = findViewById<Button>(R.id.buttonViewGoals)
        val buttonAddNewGoal = findViewById<Button>(R.id.buttonAddNewGoal)

        buttonViewGoals.setOnClickListener {
            startActivity(Intent(this, GoalsActivity::class.java))
        }

        buttonAddNewGoal.setOnClickListener {
            startActivity(Intent(this, GoalActivity::class.java))
        }
    }
}
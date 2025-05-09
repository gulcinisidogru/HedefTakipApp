package com.example.hedeftakipapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GoalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        val editTextGoalName = findViewById<EditText>(R.id.editTextGoalName)
        val editTextGoalDescription = findViewById<EditText>(R.id.editTextGoalDescription)
        val buttonAddGoal = findViewById<Button>(R.id.buttonAddGoal)
        val linearLayoutGoals = findViewById<LinearLayout>(R.id.linearLayoutGoals)

        buttonAddGoal.setOnClickListener {
            val name = editTextGoalName.text.toString()
            val description = editTextGoalDescription.text.toString()

            if (name.isNotEmpty() && description.isNotEmpty()) {
                val newGoal = Goal(GoalData.getGoals().size + 1, name, description)
                GoalData.addGoal(newGoal)

                val textView = TextView(this).apply {
                    text = "$name\n$description"
                    textSize = 16f
                    setPadding(32, 32, 32, 32)
                }
                linearLayoutGoals.addView(textView)

                editTextGoalName.text.clear()
                editTextGoalDescription.text.clear()

                Toast.makeText(this, "Hedef eklendi!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
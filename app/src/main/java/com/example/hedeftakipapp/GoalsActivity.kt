package com.example.hedeftakipapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GoalsActivity : AppCompatActivity() {

    // 1. Adapter'ı direkt başlat (lateinit kullanmadan)
    private val suggestedHabitsAdapter = SuggestedHabitsAdapter(
        onAddClick = { habit ->
            addHabitToMainList(habit)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        val editTextHabitName = findViewById<EditText>(R.id.editTextHabitName)
        val editTextHabitDescription = findViewById<EditText>(R.id.editTextHabitDescription)
        val buttonAddHabit = findViewById<Button>(R.id.buttonAddHabit)
        val recyclerViewSuggestedHabits = findViewById<RecyclerView>(R.id.recyclerViewSuggestedHabits)

        // 2. RecyclerView ayarları
        recyclerViewSuggestedHabits.apply {
            layoutManager = LinearLayoutManager(this@GoalsActivity)
            adapter = suggestedHabitsAdapter
            setHasFixedSize(true)
        }

        // 3. Başlangıç verilerini yükle
        loadSuggestedHabits()

        buttonAddHabit.setOnClickListener {
            val name = editTextHabitName.text.toString().trim()
            val description = editTextHabitDescription.text.toString().trim()

            if (name.isNotEmpty() && description.isNotEmpty()) {
                addNewHabit(name, description)
                editTextHabitName.text.clear()
                editTextHabitDescription.text.clear()
            } else {
                Toast.makeText(this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadSuggestedHabits()
    }

    private fun loadSuggestedHabits() {
        val existingHabits = HabitManager.getHabits().filter { !it.suggested }.map { it.name }
        val suggested = HabitManager.getHabits()
            .filter { it.suggested && it.name !in existingHabits }

        suggestedHabitsAdapter.updateList(suggested)
    }

    private fun addNewHabit(name: String, description: String) {
        val newId = HabitManager.getHabits().maxOfOrNull { it.id }?.plus(1) ?: 1
        val newHabit = Habit(
            id = newId,
            name = name,
            description = description,
            suggested = false
        )

        HabitManager.addHabit(newHabit)
        Toast.makeText(this, "Alışkanlık eklendi!", Toast.LENGTH_SHORT).show()
        loadSuggestedHabits() // Listeyi yenile
    }

    private fun addHabitToMainList(habit: Habit) {
        val newId = HabitManager.getHabits().maxOfOrNull { it.id }?.plus(1) ?: 1
        val newHabit = habit.copy(
            id = newId,
            suggested = false
        )

        HabitManager.addHabit(newHabit)
        Toast.makeText(this, "${habit.name} alışkanlıklara eklendi!", Toast.LENGTH_SHORT).show()
        loadSuggestedHabits() // Listeyi yenile
    }
}
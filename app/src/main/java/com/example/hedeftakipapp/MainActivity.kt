package com.example.hedeftakipapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val goalsList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        val goalSection = findViewById<LinearLayout>(R.id.goalSection)
        val editTextNewGoal = findViewById<EditText>(R.id.editTextNewGoal)
        val buttonAddGoal = findViewById<Button>(R.id.buttonAddGoal)
        val linearLayoutGoalsList = findViewById<LinearLayout>(R.id.linearLayoutGoalsList)

        // Hedef ekleme alanı başlangıçta görünmesin
        goalSection.visibility = View.GONE

        // Giriş yap butonu
        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email == "user@example.com" && password == "password") {
                Toast.makeText(this, "Giriş başarılı!", Toast.LENGTH_SHORT).show()
                goalSection.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "Hatalı e-posta veya şifre", Toast.LENGTH_SHORT).show()
            }
        }

        // Kayıt ol butonu (şimdilik sadece toast)
        buttonRegister.setOnClickListener {
            Toast.makeText(this, "Kayıt ekranı henüz aktif değil.", Toast.LENGTH_SHORT).show()
        }

        // Hedef ekleme
        buttonAddGoal.setOnClickListener {
            val newGoal = editTextNewGoal.text.toString().trim()

            if (newGoal.isNotEmpty()) {
                goalsList.add(newGoal)

                val goalTextView = TextView(this)
                goalTextView.text = newGoal
                goalTextView.textSize = 16f
                goalTextView.setPadding(16, 8, 16, 8)

                linearLayoutGoalsList.addView(goalTextView)

                editTextNewGoal.text.clear()
                Toast.makeText(this, "Hedef eklendi!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Hedef boş olamaz!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

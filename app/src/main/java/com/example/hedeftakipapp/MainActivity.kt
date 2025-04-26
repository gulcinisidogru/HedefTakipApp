package com.example.hedeftakipapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Listeyi tutmak için bir değişken
    private val goalsList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Butonları buluyoruz
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        // Yeni hedef eklemek için alanları buluyoruz
        val editTextNewGoal = findViewById<EditText>(R.id.editTextNewGoal)
        val buttonAddGoal = findViewById<Button>(R.id.buttonAddGoal)
        val linearLayoutGoalsList = findViewById<LinearLayout>(R.id.linearLayoutGoalsList)

        // Yeni hedefi ekleme butonuna tıklama olayını ekliyoruz
        buttonAddGoal.setOnClickListener {
            val newGoal = editTextNewGoal.text.toString()

            if (newGoal.isNotEmpty()) {
                // Hedefi listeye ekliyoruz
                goalsList.add(newGoal)

                // Listede hedefi ekliyoruz
                val goalTextView = TextView(this)
                goalTextView.text = newGoal
                goalTextView.textSize = 16f
                goalTextView.setPadding(16, 8, 16, 8)

                // Listede gösteriyoruz
                linearLayoutGoalsList.addView(goalTextView)

                // EditText alanını temizliyoruz
                editTextNewGoal.text.clear()

                // Kullanıcıya mesaj gösteriyoruz
                Toast.makeText(this, "Hedef başarıyla eklendi!", Toast.LENGTH_SHORT).show()
            } else {
                // Hedef boşsa uyarı gösteriyoruz
                Toast.makeText(this, "Hedef boş olamaz!", Toast.LENGTH_SHORT).show()
            }
        }

        // Giriş yap butonuna tıklama olayını ekliyoruz
        buttonLogin.setOnClickListener {
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()

            // Burada giriş işlemi yapılacak (örneğin, doğru mail ve şifre kontrolü)
            if (email == "user@example.com" && password == "password") {
                // Giriş başarılıysa GoalActivity'ye geçiş
                val intent = Intent(this, GoalActivity::class.java)
                startActivity(intent)
            } else {
                // Hatalı giriş durumunda kullanıcıya mesaj gösterelim
                Toast.makeText(this, "Hatalı e-posta veya şifre", Toast.LENGTH_SHORT).show()
            }
        }

        // Kayıt ol butonuna tıklama olayını ekliyoruz
        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

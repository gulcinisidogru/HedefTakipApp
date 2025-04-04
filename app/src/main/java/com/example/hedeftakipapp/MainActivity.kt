package com.example.hedeftakipapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email == "admin@example.com" && password == "1234") {
                Toast.makeText(this, "Hoş geldin!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "E-posta veya şifre hatalı!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

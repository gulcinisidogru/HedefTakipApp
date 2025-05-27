// LoginActivity.kt (Yeni oluşturulacak veya güncellenecek)
package com.example.hedeftakipapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView // TextView ekledik
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // activity_login.xml dosyasını oluşturmalısınız

        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val registerLink = findViewById<TextView>(R.id.textViewRegister) // Yeni eklenen TextView

        loginButton.setOnClickListener {
            val enteredEmail = emailEditText.text.toString()
            val enteredPassword = passwordEditText.text.toString()

            // SharedPreferences'tan kayıtlı bilgileri al
            val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val savedEmail = sharedPref.getString("user_email", null)
            val savedPassword = sharedPref.getString("user_password", null)

            if (enteredEmail == savedEmail && enteredPassword == savedPassword && savedEmail != null) {
                Toast.makeText(this, "Giriş Başarılı!", Toast.LENGTH_SHORT).show()
                // Giriş başarılıysa MainActivity'ye yönlendir
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // LoginActivity'yi kapat
            } else {
                Toast.makeText(this, "E-posta veya şifre yanlış!", Toast.LENGTH_SHORT).show()
            }
        }

        // Kayıt olma linkine tıklama
        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
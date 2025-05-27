// RegisterActivity.kt
package com.example.hedeftakipapp

import android.content.Context
import android.content.Intent // Intent ekledik
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nameEditText = findViewById<EditText>(R.id.editTextName)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val registerButton = findViewById<Button>(R.id.buttonRegister)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (name.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                // SharedPreferences ile kullanıcı bilgilerini kaydet
                val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                with (sharedPref.edit()) {
                    putString("user_email", email)
                    putString("user_password", password)
                    // İsterseniz adı da kaydedebilirsiniz
                    putString("user_name", name)
                    apply() // Değişiklikleri uygula
                }

                Toast.makeText(this, "Kayıt Başarılı: $name", Toast.LENGTH_SHORT).show()
                // Kayıt başarılı olduğunda LoginActivity'ye geri dön
                finish() // RegisterActivity'yi kapat
            } else {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
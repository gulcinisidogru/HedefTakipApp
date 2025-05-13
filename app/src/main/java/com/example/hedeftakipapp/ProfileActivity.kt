package com.example.hedeftakipapp

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val progressContainer = findViewById<LinearLayout>(R.id.progressContainer)
        val habits = listOf(
            "Cilt Bakımı" to 15, // 15/21 gün tamamlandı
            "Kitap Okuma" to 7
        )

        habits.forEach { (habitName, daysCompleted) ->
            // Alışkanlık adı TextView'ı
            val habitView = TextView(this).apply {
                text = habitName
                textSize = 18f
                setPadding(0, 16, 0, 8)
            }

            // ProgressBar oluşturma
            val progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal).apply {
                max = 21
                setProgress(daysCompleted, true) // Düzeltme burada!
                setBackgroundColor(Color.LTGRAY)
            }

            // View'leri ekleme
            progressContainer.addView(habitView)
            progressContainer.addView(progressBar)
        }
    }
}
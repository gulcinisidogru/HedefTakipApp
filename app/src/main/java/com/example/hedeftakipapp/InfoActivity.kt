package com.example.hedeftakipapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this).apply {
            text = "Uygulama Bilgisi\n\n21 gün kuralına göre alışkanlık takip uygulaması."
            textSize = 18f
        }
        setContentView(textView)
    }
}
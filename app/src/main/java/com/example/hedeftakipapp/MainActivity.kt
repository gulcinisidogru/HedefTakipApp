package com.example.hedeftakipapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar ve Menü Ayarları
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(android.R.drawable.ic_menu_more)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navView)

        // Menü Tıklamaları
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_profile -> startActivity(Intent(this, ProfileActivity::class.java))
                R.id.menu_settings -> startActivity(Intent(this, SettingsActivity::class.java))
                R.id.menu_info -> startActivity(Intent(this, InfoActivity::class.java))
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Örnek Alışkanlık Kartları Ekle
        addHabitCards()
    }

    private fun addHabitCards() {
        val habitsContainer = findViewById<LinearLayout>(R.id.habitsContainer)
        val habits = listOf(
            "Her gün cilt bakımı yap",
            "Günde 10 sayfa kitap oku",
            "30 dakika yürüyüş yap"
        )

        habits.forEach { habit ->
            val card = TextView(this).apply {
                text = habit
                textSize = 18f
                setPadding(32, 32, 32, 32)
                setBackgroundColor(Color.parseColor("#E3F2FD"))
                elevation = 8f
            }
            habitsContainer.addView(card)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }
}
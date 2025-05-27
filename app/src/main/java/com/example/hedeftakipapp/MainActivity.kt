package com.example.hedeftakipapp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var habitsContainer: LinearLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View'ları bağla
        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.navView)
        habitsContainer = findViewById(R.id.habitsContainer)

        // Toolbar ayarları
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ActionBarDrawerToggle oluştur ve bağla
        toggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                supportActionBar?.title = "Menü"
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                supportActionBar?.title = "Alışkanlıklarım"
            }
        }

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // NavigationView tıklamaları
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_profile -> startActivity(Intent(this, ProfileActivity::class.java))
                R.id.menu_settings -> startActivity(Intent(this, SettingsActivity::class.java))
                R.id.menu_info -> startActivity(Intent(this, InfoActivity::class.java))
                R.id.menu_habits -> startActivity(Intent(this, GoalsActivity::class.java))
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Alışkanlıkları göster
        displayHabits()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Hamburger menü tıklamasını yönet
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        displayHabits()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayHabits() {
        habitsContainer.removeAllViews()

        HabitManager.getHabits().filter { !it.suggested }.forEach { habit ->
            val habitCard = LayoutInflater.from(this).inflate(R.layout.item_habit_card, habitsContainer, false)

            val habitNameTextView: TextView = habitCard.findViewById(R.id.textViewHabitName)
            val habitDescriptionTextView: TextView = habitCard.findViewById(R.id.textViewHabitDescription)
            val daysContainer: LinearLayout = habitCard.findViewById(R.id.daysContainer)
            val buttonRemoveHabit: Button = habitCard.findViewById(R.id.buttonRemoveHabit)

            habitNameTextView.text = habit.name
            habitDescriptionTextView.text = habit.description

            // 21 günlük ilerleme
            daysContainer.removeAllViews()
            val today = LocalDate.now()
            for (i in 0 until 21) {
                val dayView = TextView(this).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(4, 0, 4, 0)
                    }
                    textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics)
                    setPadding(8, 8, 8, 8)
                    text = "${i + 1}"
                    val dateForDay = today.minusDays((20 - i).toLong())

                    when {
                        habit.completedDays.contains(dateForDay) -> {
                            setBackgroundColor(Color.parseColor("#8BC34A"))
                            setTextColor(Color.WHITE)
                        }
                        dateForDay.isBefore(today) || dateForDay.isEqual(today) -> {
                            setBackgroundColor(Color.parseColor("#FFCCBC"))
                            setTextColor(Color.BLACK)
                        }
                        else -> {
                            setBackgroundColor(Color.parseColor("#E0E0E0"))
                            setTextColor(Color.BLACK)
                        }
                    }

                    setOnClickListener {
                        HabitManager.toggleHabitCompletion(habit.id, dateForDay)
                        displayHabits()
                        Toast.makeText(this@MainActivity, "Gün ${i + 1} durumu güncellendi!", Toast.LENGTH_SHORT).show()
                    }
                }
                daysContainer.addView(dayView)
            }

            buttonRemoveHabit.setOnClickListener {
                HabitManager.removeHabit(habit)
                Toast.makeText(this, "${habit.name} kaldırıldı.", Toast.LENGTH_SHORT).show()
                displayHabits()
            }

            habitsContainer.addView(habitCard)
        }
    }
}
package com.example.hedeftakipapp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
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

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var habitsContainer: LinearLayout
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. View'ları bağla
        habitsContainer = findViewById(R.id.habitsContainer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.navView)

        // 2. Toolbar ve Drawer ayarla
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 3. ActionBarDrawerToggle oluştur
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ).apply {
            isDrawerSlideAnimationEnabled = true
            isDrawerIndicatorEnabled = true
        }
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // 4. NavigationView tıklamaları
        navView.setNavigationItemSelectedListener(this)

        // 5. Alışkanlıkları göster
        displayHabits()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.menu_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.menu_habits -> {
                // Zaten ana sayfadayız, sadece drawer'ı kapat
            }
            R.id.menu_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            R.id.menu_info -> {
                startActivity(Intent(this, InfoActivity::class.java))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayHabits() {
        habitsContainer.removeAllViews()

        val habits = HabitManager.getHabits().filter { !it.suggested }

        if (habits.isEmpty()) {
            findViewById<TextView>(R.id.textEmptyState).visibility = View.VISIBLE
            return
        } else {
            findViewById<TextView>(R.id.textEmptyState).visibility = View.GONE
        }

        habits.forEach { habit ->
            val habitCard = LayoutInflater.from(this).inflate(R.layout.item_habit_card, habitsContainer, false)

            val habitNameTextView: TextView = habitCard.findViewById(R.id.textViewHabitName)
            val habitDescriptionTextView: TextView = habitCard.findViewById(R.id.textViewHabitDescription)
            val daysContainer: LinearLayout = habitCard.findViewById(R.id.daysContainer)
            val motivationTextView: TextView = habitCard.findViewById(R.id.textMotivation)
            val buttonRemoveHabit: Button = habitCard.findViewById(R.id.buttonRemoveHabit)

            habitNameTextView.text = habit.name
            habitDescriptionTextView.text = habit.description

            daysContainer.removeAllViews()
            val today = LocalDate.now()
            val motivationMessages = resources.getStringArray(R.array.motivation_messages)

            for (i in 0 until 21) {
                val dayView = TextView(this).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        resources.getDimensionPixelSize(R.dimen.day_circle_size),
                        resources.getDimensionPixelSize(R.dimen.day_circle_size)
                    ).apply {
                        marginEnd = 4.dpToPx()
                    }

                    gravity = Gravity.CENTER
                    text = (i + 1).toString()
                    textSize = 12f
                    setBackgroundResource(R.drawable.circle_background)

                    val dayDate = today.minusDays((20 - i).toLong())
                    updateDayAppearance(this, habit, dayDate)

                    setOnClickListener {
                        val updatedHabit = HabitManager.toggleHabitCompletion(habit.id, dayDate)
                        updateDayAppearance(this, updatedHabit, dayDate)

                        val randomMessage = motivationMessages.random()
                        motivationTextView.text = randomMessage
                        motivationTextView.visibility = View.VISIBLE

                        Toast.makeText(
                            this@MainActivity,
                            "Gün ${i + 1} - $randomMessage",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                daysContainer.addView(dayView)
            }

            buttonRemoveHabit.setOnClickListener {
                HabitManager.removeHabit(habit)
                displayHabits()
                Toast.makeText(this, "${habit.name} kaldırıldı", Toast.LENGTH_SHORT).show()
            }

            habitsContainer.addView(habitCard)
        }
    }

    private fun updateDayAppearance(dayView: TextView, habit: Habit, date: LocalDate) {
        val today = LocalDate.now()

        when {
            habit.completedDates.contains(date) -> {
                dayView.setBackgroundResource(R.drawable.circle_completed)
                dayView.setTextColor(Color.WHITE)
            }
            date.isBefore(today) || date.isEqual(today) -> {
                dayView.setBackgroundResource(R.drawable.circle_missed)
                dayView.setTextColor(Color.BLACK)
            }
            else -> {
                dayView.setBackgroundResource(R.drawable.circle_upcoming)
                dayView.setTextColor(Color.BLACK)
            }
        }
    }

    private fun Int.dpToPx(): Int = (this * resources.displayMetrics.density).toInt()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
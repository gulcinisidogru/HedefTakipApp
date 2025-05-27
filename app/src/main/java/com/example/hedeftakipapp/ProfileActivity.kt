package com.example.hedeftakipapp


import HabitsAdapter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hedeftakipapp.HabitManager
import com.example.hedeftakipapp.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var habitsAdapter: HabitsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // RecyclerView ayarları
        habitsAdapter = HabitsAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewHabits)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ProfileActivity)
            adapter = habitsAdapter
            setHasFixedSize(true)
        }

        // Profil bilgilerini yükle
        loadProfileData()
    }

    override fun onResume() {
        super.onResume()
        loadHabits() // Her aktiviteye dönüldüğünde alışkanlıkları yenile
    }

    private fun loadProfileData() {
        // Profil bilgilerini yükleme kodu
        val nameTextView = findViewById<TextView>(R.id.textViewName)
        nameTextView.text = "Kullanıcı Adı" // Gerçek veriyle değiştirin
    }

    private fun loadHabits() {
        val habits = HabitManager.getHabits()
            .filter { !it.suggested } // Sadece önerilmeyen alışkanlıklar
            .sortedBy { it.name }

        habitsAdapter.submitList(habits)
    }
}
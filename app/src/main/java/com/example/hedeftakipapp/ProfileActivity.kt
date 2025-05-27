package com.example.hedeftakipapp

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate

class ProfileActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val totalHabitsTextView = findViewById<TextView>(R.id.textViewTotalHabits)
        val totalCompletedDaysTextView = findViewById<TextView>(R.id.textViewTotalCompletedDays)
        val currentChapterTextView = findViewById<TextView>(R.id.textViewCurrentChapter)
        val achievementsContainer = findViewById<LinearLayout>(R.id.achievementsContainer)
        val progressContainer = findViewById<LinearLayout>(R.id.progressContainer)

        val habits = HabitManager.getHabits().filter { !it.suggested }

        // İstatistikleri güncelle
        totalHabitsTextView.text = "Toplam Alışkanlık Sayısı: ${habits.size}"

        var totalCompletedDays = 0
        habits.forEach { habit ->
            totalCompletedDays += habit.completedDays.size
        }
        totalCompletedDaysTextView.text = "Toplam Tamamlanan Gün: $totalCompletedDays"

        // Basit Chapter hesaplaması (Örnek)
        val currentChapter = totalCompletedDays / 10 + 1 // Her 10 gün bir chapter
        currentChapterTextView.text = "Mevcut Chapter: $currentChapter"


        // Başarımları listele (Örnek başarımlar)
        achievementsContainer.removeAllViews()
        val achievements = getAchievements(totalCompletedDays, habits.size)
        if (achievements.isEmpty()) {
            val noAchievementText = TextView(this).apply {
                text = "Henüz bir başarınız bulunmamaktadır. Alışkanlıklarınızı tamamlamaya devam edin!"
                // textSize hatası düzeltildi
                textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16f, resources.displayMetrics)
                setPadding(0, 8, 0, 0)
            }
            achievementsContainer.addView(noAchievementText)
        } else {
            achievements.forEach { achievement ->
                val achievementTextView = TextView(this).apply {
                    text = "• $achievement"
                    // textSize hatası düzeltildi
                    textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16f, resources.displayMetrics)
                    setPadding(0, 4, 0, 4)
                }
                achievementsContainer.addView(achievementTextView)
            }
        }


        // Her bir alışkanlık için ilerleme çubuğu
        progressContainer.removeAllViews()
        if (habits.isEmpty()) {
            val noHabitText = TextView(this).apply {
                text = "Henüz eklenmiş bir alışkanlığınız yok."
                // textSize hatası düzeltildi
                textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16f, resources.displayMetrics)
                setPadding(0, 8, 0, 0)
            }
            progressContainer.addView(noHabitText)
        } else {
            habits.forEach { habit -> // <-- Düzeltme burada yapıldı
                // Alışkanlık adı TextView'ı
                val habitView = TextView(this).apply {
                    text = habit.name // <-- habitName yerine habit.name kullanıldı
                    textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18f, resources.displayMetrics)
                    setPadding(0, 16, 0, 8)
                    setTextColor(Color.BLACK)
                }

                // ProgressBar oluşturma
                val progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal).apply {
                    max = 21
                    setProgress(habit.completedDays.size, true)
                    progressDrawable.setColorFilter(
                        Color.parseColor("#4CAF50"), android.graphics.PorterDuff.Mode.SRC_IN) // Yeşil
                }

                // İlerleme yüzdesi/gün sayısı
                val progressText = TextView(this).apply {
                    text = "${habit.completedDays.size}/21 Gün" // <-- completedDays yerine habit.completedDays.size kullanıldı
                    textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14f, resources.displayMetrics)
                    setTextColor(Color.DKGRAY)
                    setPadding(0, 4, 0, 0)
                }

                // View'leri ekleme
                progressContainer.addView(habitView)
                progressContainer.addView(progressBar)
                progressContainer.addView(progressText)
            }
        }
    }

    private fun getAchievements(totalCompletedDays: Int, totalHabits: Int): List<String> {
        val achievements = mutableListOf<String>()

        if (totalCompletedDays >= 21) {
            achievements.add("21 Gün Kuralı Ustası! İlk alışkanlığınızı tamamladınız.")
        }
        if (totalCompletedDays >= 100) {
            achievements.add("Yüz Günlük Alışkanlık Maratoncusu!")
        }
        if (totalHabits >= 3) {
            achievements.add("Çoklu Alışkanlık Başlangıcı: En az 3 alışkanlığınız var.")
        }
        if (totalHabits >= 5) {
            achievements.add("Alışkanlık Koleksiyoncusu: 5 veya daha fazla alışkanlığınız var.")
        }
        return achievements
    }
}
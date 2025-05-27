package com.example.hedeftakipapp

import java.time.LocalDate

object HabitManager {
    private val _habits = mutableListOf(
        Habit(1, "Her gün su iç", "Günde 2 litre su içmeyi hedefle.", false, mutableListOf(LocalDate.now(), LocalDate.now().minusDays(1))),
        Habit(2, "Sabah yürüyüşü yap", "Her sabah 30 dakika yürü.", false, mutableListOf(LocalDate.now().minusDays(2), LocalDate.now().minusDays(3))),
        Habit(3, "Kitap oku", "Günde 10 sayfa kitap oku.", false, mutableListOf(LocalDate.now(), LocalDate.now().minusDays(1), LocalDate.now().minusDays(5))),
        Habit(4, "Meditasyon yap", "Her akşam 10 dakika meditasyon.", true, mutableListOf()) // Suggested habit
    )

    fun getHabits(): List<Habit> = _habits.toList()

    fun addHabit(habit: Habit) {
        // Yeni bir habit eklerken ID'yi manuel olarak atamak yerine,
        // mevcut habitlerin en yüksek ID'sine 1 ekleyerek benzersiz bir ID oluşturmak daha iyi olacaktır.
        // Bu örnek için sabit bir ID kullanılıyor.
        _habits.add(habit)
    }

    fun markHabitCompleted(habitId: Int, date: LocalDate) {
        _habits.find { it.id == habitId }?.let { habit ->
            if (!habit.completedDays.contains(date)) {
                habit.completedDays.add(date)
            }
        }
    }

    // Yeni eklenen fonksiyon: Bir alışkanlığın belirli bir günkü tamamlanma durumunu aç/kapa
    fun toggleHabitCompletion(habitId: Int, date: LocalDate) {
        _habits.find { it.id == habitId }?.let { habit ->
            if (habit.completedDays.contains(date)) {
                habit.completedDays.remove(date) // Eğer tamamlanmışsa kaldır
            } else {
                habit.completedDays.add(date) // Tamamlanmamışsa ekle
            }
        }
    }

    // Yeni eklenen fonksiyon: Bir alışkanlığı listeden kaldır
    fun removeHabit(habit: Habit) {
        _habits.remove(habit)
    }
}
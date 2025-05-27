package com.example.hedeftakipapp

import java.time.LocalDate

object HabitManager {
    private val _habits = mutableListOf(
        Habit(1, "Her gün su iç", "Günde 2 litre su içmeyi hedefle.",
            completedDates = mutableSetOf(LocalDate.now(), LocalDate.now().minusDays(1))),
        Habit(2, "Sabah yürüyüşü yap", "Her sabah 30 dakika yürü.",
            completedDates = mutableSetOf(LocalDate.now().minusDays(2), LocalDate.now().minusDays(3))),
        Habit(3, "Kitap oku", "Günde 10 sayfa kitap oku.",
            completedDates = mutableSetOf(LocalDate.now(), LocalDate.now().minusDays(1), LocalDate.now().minusDays(5))),
        Habit(4, "Meditasyon yap", "Her akşam 10 dakika meditasyon.",
            suggested = true) // Suggested habit
    )

    fun getHabits(): List<Habit> = _habits.toList()

    fun addHabit(habit: Habit) {
        val newId = _habits.maxOfOrNull { it.id }?.plus(1) ?: 1
        _habits.add(habit.copy(id = newId))
    }

    // Yeni ve düzeltilmiş fonksiyon: Güncellenmiş habit'i döndürür
    fun toggleHabitCompletion(habitId: Int, date: LocalDate): Habit {
        return _habits.find { it.id == habitId }?.let { habit ->
            val newCompletedDates = if (habit.completedDates.contains(date)) {
                habit.completedDates - date
            } else {
                habit.completedDates + date
            }

            val updatedHabit = habit.copy(completedDates = newCompletedDates.toMutableSet())
            _habits.replaceAll { if (it.id == habitId) updatedHabit else it }
            updatedHabit // Güncellenmiş habit'i döndür
        } ?: throw IllegalArgumentException("Habit with id $habitId not found")
    }

    fun removeHabit(habit: Habit) {
        _habits.removeIf { it.id == habit.id }
    }
}
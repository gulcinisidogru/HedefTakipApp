package com.example.hedeftakipapp

object GoalData {
    private val _goals = mutableListOf(
        Goal(1, "Kotlin Öğren", "Her gün 1 saat çalış."),
        Goal(2, "Spor Yap", "Haftada 3 gün spor.")
    )

    fun getGoals(): List<Goal> = _goals.toList()

    fun addGoal(goal: Goal) {
        _goals.add(goal)
    }
}
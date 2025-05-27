package com.example.hedeftakipapp

import java.time.LocalDate

data class Habit(
    val id: Int,
    val name: String,
    val description: String,
    val suggested: Boolean = false,
    val completedDays: MutableList<LocalDate> = mutableListOf()
)
package com.example.hedeftakipapp

import java.time.LocalDate

data class Habit(
    val id: Int,
    val name: String,
    val description: String,
    val completedDates: Set<LocalDate> = emptySet(),
    val suggested: Boolean = false
)




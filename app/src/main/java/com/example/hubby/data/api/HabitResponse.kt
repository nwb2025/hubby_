package com.example.hubby.data.api


data class HabitResponse
(
    val count: Int,
    val date: Int,
    val description: String,
    val frequency: Int,
    val priority: Int,
    val title: String,
    val type: Int
)
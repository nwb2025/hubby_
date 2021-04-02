package com.example.hubby.data.api


data class HabitResponse
(
    val count: Int,
    val date: String,
    val description: String,
    val frequency: Int,
    val priority: Int,
    val title: String,
    val type: Int,
    val done_dates : ArrayList<String>
)
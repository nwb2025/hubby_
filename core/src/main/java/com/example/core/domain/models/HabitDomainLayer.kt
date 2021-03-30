package com.example.core.domain.models

data  class HabitDomainLayer(
    val id : Int,
    val count: Int,
    val date: Int,
    val description: String,
    val frequency: Int,
    val priority: Int,
    val name: String,
    val type: Int
)
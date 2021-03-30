package com.example.hubby.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit
(
        @PrimaryKey( autoGenerate = true) val id : Int,
        val count: Int,
        val date: Int,
        val description: String,
        val frequency: Int,
        val priority: Int,
        val name: String,
        @ColumnInfo(name = "type") val type: Int
)
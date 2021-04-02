package com.example.hubby.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.core.domain.models.HabitDomainLayer
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    // it's like a data source
    // we return a live data object so we dont need to explicitally execute it in the background - it will be by itself
    @Query("SELECT * FROM habits")
    fun getAll() : Flow<List <HabitDomainLayer>>


    @Query("SELECT * FROM habits WHERE type = :type")
    fun getHabitsByType(type:String) : Flow<List<HabitDomainLayer>>

    @Insert
    suspend fun addHabit(habit : Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("UPDATE habits SET done_dates = :done_dates WHERE id = :id")
    suspend fun updateDoneDates(done_dates:String , id:Int )
}
package com.example.core.data

import com.example.core.domain.models.HabitDomainLayer
import kotlinx.coroutines.flow.Flow


interface HabitDataSource
{

    suspend fun add(habit: HabitDomainLayer)
    fun getAll() : Flow<List<HabitDomainLayer>>
    fun getByType(type:String) : Flow<List<HabitDomainLayer>>
    suspend fun remove(habit: HabitDomainLayer)

}

// TODO: here will be another interface HabitRemoteSource - for retrofit
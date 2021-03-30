package com.example.hubby.frameworks


import com.example.core.data.HabitDataSource
import com.example.core.domain.models.HabitDomainLayer
import com.example.hubby.data.db.Dao
import kotlinx.coroutines.flow.Flow

class RoomHabitDataSource ( private val dao: Dao?) : HabitDataSource
{
    private val mapper = HabitDBMapper()

    override suspend fun add(habit: HabitDomainLayer)
    {
        dao?.addHabit(mapper.mapToEntity(habit))
    }

    override fun getAll() : Flow<List<HabitDomainLayer>> =  dao!!.getAll()


    override fun getByType(type: String): Flow<List<HabitDomainLayer>>  = dao?.getHabitsByType(type)!!

    override suspend fun remove(habit: HabitDomainLayer)
    {
        dao?.deleteHabit(mapper.mapToEntity(habit))
    }
}
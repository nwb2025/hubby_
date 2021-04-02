package com.example.hubby.frameworks


import com.example.core.data.HabitDataSource
import com.example.core.domain.models.HabitDomainLayer
import com.example.hubby.data.api.RetrofitInstance
import com.example.hubby.utils.Constants
import kotlinx.coroutines.flow.Flow

class RetrofitHabitDataSource (private val retroApi: RetrofitInstance)  : HabitDataSource
{
    private val mapper = HabitRetrofitMapper()

    override suspend fun add(habit: HabitDomainLayer)  = retroApi.api.putHabit(Constants.API_KEY, mapper.mapToEntity(habit) )

    override fun getAll(): Flow<List<HabitDomainLayer>> {
        return retroApi.api.getHabits(Constants.API_KEY)
    }

    override fun getByType(type: String): Flow<List<HabitDomainLayer>> {
        TODO("Not yet implemented")
    }

    override suspend fun remove(habit: HabitDomainLayer) {
        TODO("Not yet implemented")
    }

    override suspend fun updateDoneDates(doneDates:String, id: Int) {
        TODO("Not yet implemented")
    }

}
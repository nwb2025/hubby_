package com.example.hubby.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.interactors.local_db.*
import com.example.core.interactors.retorfit.PutHabit
import com.example.hubby.frameworks.HabitDBMapper
import com.example.hubby.frameworks.HabitRetrofitMapper

class ViewModelFactory (private val getHabits: GetAllHabits,
                        private val getHabitsByType: GetByType,
                        private  val addHabit: AddHabit,
                        private val deleteH: DeleteHabit,
                        private val getHabitsFromServer:GetAllHabits,
                        private val putHabit: PutHabit,
                        private val updateDoneDates: UpdateDoneDates,
                        private val mapper:HabitDBMapper,
                        private val mapperApi: HabitRetrofitMapper) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if ( modelClass.isAssignableFrom(HabitViewModel::class.java ))
        {
            return  HabitViewModel(
                getHabits,
                getHabitsByType,
                addHabit,
                deleteH,
                getHabitsFromServer,
                putHabit,
                updateDoneDates,
                mapper,
                mapperApi) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }



}
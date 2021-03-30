package com.example.hubby.presentation.viewmodels

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.core.domain.models.HabitDomainLayer


import com.example.core.interactors.local_db.AddHabit
import com.example.core.interactors.local_db.DeleteHabit
import com.example.core.interactors.local_db.GetAllHabits
import com.example.core.interactors.local_db.GetByType
import com.example.core.interactors.retorfit.PutHabit
import com.example.hubby.data.api.HabitResponse
import com.example.hubby.data.db.Habit
import com.example.hubby.frameworks.HabitDBMapper
import com.example.hubby.frameworks.HabitRetrofitMapper
import kotlinx.coroutines.launch

// here we use use-cases to process the ui actions
class  HabitViewModel ( private val getHabits:GetAllHabits,
                        private val getHabitsByType: GetByType,
                        private  val addHabit: AddHabit,
                        private val deleteH: DeleteHabit,
                        private val getHabitsFromServer:GetAllHabits,
                        private val putHabit:PutHabit,
                        private val mapper:HabitDBMapper,
                        private val mapperApi:HabitRetrofitMapper)  : ViewModel() , Observable
{
    val good_habits : LiveData<List<HabitDomainLayer>?> = getHabitsByType("0").asLiveData()
    val bad_habits : LiveData<List<HabitDomainLayer>?>  = getHabitsByType("1").asLiveData()
    // TODO: and here we transform it to the Habit Model

    // From DB ROOM
    val habits: LiveData<List<HabitDomainLayer>> = getHabits().asLiveData()
   // val habits : LiveData<List<HabitDomainLayer>> = getHabitsFromServer().asLiveData()

    @Bindable
    val name = MutableLiveData<String>()

    @Bindable
    val desc = MutableLiveData<String>()

    @Bindable
    val goodH = MutableLiveData<Boolean?>()

    @Bindable
    val badH = MutableLiveData<Boolean?>()

    @Bindable
    val count = MutableLiveData<String>()

    @Bindable
    val frequency  = MutableLiveData<String>()


    fun insertHabit()
    {
        viewModelScope.launch {
            val des = desc.value.toString() // description
            val n = name.value.toString() // name
            val date  =  System.currentTimeMillis().toInt() // date

            // TODO: must be fixed !
            val freq = frequency.value?.toInt()
            val count =  count.value?.toInt()

            if( goodH.value  == true )
            {
                putHabit(mapperApi.mapFromEntity(HabitResponse(count!!, date, des,freq!!,1,n,0)))
                addHabit(mapper.mapFromEntity(Habit(0, count!!, date, des, freq!!, 1, n, 0)))
            }

            else if ( badH.value == true )
            {
                putHabit(mapperApi.mapFromEntity(HabitResponse(count!!, date, des,freq!!,1,n,1)))
                addHabit(mapper.mapFromEntity(Habit(0, count!!, date, des, freq!!, 1, n, 1 )))
            }

            else
            {
                Log.i("Error", "You must specify the habit type!")
            }
        }

        name.value = null
        desc.value = null
        badH.value = null
        goodH.value = null
        frequency.value = null
        count.value = null
    }

    fun deleteHabit(habit: Habit)
    {
        viewModelScope.launch {
            deleteH( mapper.mapFromEntity(habit))
        }
    }

    /*fun getAll() {
        viewModelScope.launch {
            habits.value = mapper.mapToEntityList(getHabits()!!.value!!)

        }

    }*/
    /*fun getHabitsFromServer()
    {
        viewModelScope.launch{
            val resp = repo?.getHabitsFromServer()
            response.value = resp
        }
    }*/



    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}
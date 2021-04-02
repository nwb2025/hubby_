package com.example.hubby.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hubby.databinding.ActivityAddHabitBinding
import com.example.hubby.data.db.AppDataBase
import com.example.core.data.HabitDataSource
import com.example.core.data.HabitRepository
import com.example.core.interactors.local_db.*
import com.example.core.interactors.retorfit.PutHabit
import com.example.hubby.presentation.viewmodels.HabitViewModel
import com.example.hubby.R
import com.example.hubby.data.api.RetrofitInstance
import com.example.hubby.data.db.Dao
import com.example.hubby.frameworks.HabitDBMapper
import com.example.hubby.frameworks.HabitRetrofitMapper
import com.example.hubby.frameworks.RetrofitHabitDataSource
import com.example.hubby.frameworks.RoomHabitDataSource
import com.example.hubby.presentation.viewmodels.ViewModelFactory

class Activity_AddHabit : AppCompatActivity()
{
    private var db : AppDataBase? = null
    private var hDao : Dao? = null
    private var repo: HabitRepository? = null
    private lateinit var binding : ActivityAddHabitBinding
    private var viewModel: HabitViewModel? = null
    private var dataSource: RoomHabitDataSource? = null
    private var repoRetrofit : HabitRepository? = null
    private var dataSourceRetrofit : RetrofitHabitDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {


        super.onCreate(savedInstanceState)
        
        binding = DataBindingUtil.setContentView(this , R.layout.activity_add_habit)

        // From local DB
        db = AppDataBase.getAppDB( context =  this)
        hDao = db?.habitDao()
        dataSource = RoomHabitDataSource(hDao)
        repo = HabitRepository(dataSource as HabitDataSource)

        // From Retrofit Service
        dataSourceRetrofit = RetrofitHabitDataSource(RetrofitInstance)
        repoRetrofit = HabitRepository( dataSourceRetrofit as HabitDataSource)

        val factory = ViewModelFactory (
            GetAllHabits(repo as HabitRepository),
            GetByType(repo as HabitRepository),
            AddHabit(repo as HabitRepository),
            DeleteHabit(repo as HabitRepository),
            GetAllHabits(repoRetrofit as HabitRepository),
            PutHabit(repoRetrofit as HabitRepository),
            UpdateDoneDates(repo as HabitRepository),
            HabitDBMapper() ,
            HabitRetrofitMapper() )

        viewModel = ViewModelProvider(this, factory).get(HabitViewModel::class.java)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        viewModel?.habits?.observe( this , Observer {
            Log.i("habits", it.toString())
        })

    }

    override fun finish() {
        super.finish()
       // overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}
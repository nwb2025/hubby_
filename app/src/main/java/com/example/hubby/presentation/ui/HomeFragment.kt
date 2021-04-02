package com.example.hubby.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.core.data.HabitDataSource
import com.example.core.data.HabitRepository
import com.example.core.interactors.local_db.*
import com.example.core.interactors.retorfit.PutHabit
import com.example.hubby.R
import com.example.hubby.data.api.RetrofitInstance
import com.example.hubby.data.db.AppDataBase
import com.example.hubby.data.db.Dao
import com.example.hubby.databinding.FragmenthomeBinding

import com.example.hubby.frameworks.HabitDBMapper
import com.example.hubby.frameworks.HabitRetrofitMapper
import com.example.hubby.frameworks.RetrofitHabitDataSource
import com.example.hubby.frameworks.RoomHabitDataSource
import com.example.hubby.presentation.adapters.MyViewPagerAdapter
import com.example.hubby.presentation.adapters.RecyclerView_Adapter
import com.example.hubby.presentation.viewmodels.HabitViewModel
import com.example.hubby.presentation.viewmodels.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment()
{
    private lateinit var binding : FragmenthomeBinding
    private var db : AppDataBase? = null
    private var hDao : Dao? = null
    private var dataSource: RoomHabitDataSource? = null
    private var dataSourceRetrofit : RetrofitHabitDataSource? = null
    private var sharedViewModel: HabitViewModel? = null
    private var repo: HabitRepository? = null
    private var repoRetrofit : HabitRepository? = null
    private  lateinit var myContext:MainActivity
    private lateinit var adapter: MyViewPagerAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?)
    {

        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        // TODO: this place is dangerous
        // From local DB
        // Todo : should be fixed - we shouldnt use Activity COntext here in DB
        db = AppDataBase.getAppDB( context = myContext )
        hDao = db?.habitDao()
        dataSource = RoomHabitDataSource(hDao)
        repo = HabitRepository(dataSource as HabitDataSource)

        // From Retrofit Service
        dataSourceRetrofit = RetrofitHabitDataSource(RetrofitInstance)
        repoRetrofit = HabitRepository( dataSourceRetrofit as HabitDataSource)




        val factory = ViewModelFactory(
            GetAllHabits(repo as HabitRepository),
            GetByType(repo as HabitRepository),
            AddHabit(repo as HabitRepository),
            DeleteHabit(repo as HabitRepository),
            GetAllHabits(repoRetrofit as HabitRepository),
            PutHabit(repoRetrofit as HabitRepository),
            UpdateDoneDates(repo as HabitRepository),
            HabitDBMapper() ,
            HabitRetrofitMapper() )

        sharedViewModel = ViewModelProvider(  myContext, factory).get(HabitViewModel::class.java)



        sharedViewModel?.habits?.observe( this , Observer {
            Log.i("in Home Fragment ", it.toString())

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmenthomeBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews()
    {
        // TODO: should be fixed
        adapter = MyViewPagerAdapter(childFragmentManager)

        adapter.addFragment(Fragment_GoodHabits(),"Хорошие")

        adapter.addFragment(Fragment_badHabits(),"Плохие")


        binding.viewPager.adapter = adapter
       // binding.viewPager.offscreenPageLimit = 2

        binding.tabL.setupWithViewPager(binding.viewPager)


        binding.btnAdd.setOnClickListener() {
            val intent = Intent(context, Activity_AddHabit::class.java)
            startActivity(intent)
            // Trans Animation
            //overridePendingTransition(R.anim.fadein,R.anim.fadeout)
        }
    }

    override fun onAttach(activity: Activity) {
        Log.i("On attach home","on attach")
        myContext = activity as MainActivity
        super.onAttach(activity)
    }


}
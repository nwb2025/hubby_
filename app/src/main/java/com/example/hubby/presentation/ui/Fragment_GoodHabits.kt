package com.example.hubby.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hubby.R
import com.example.hubby.data.db.Habit
import com.example.hubby.presentation.adapters.RecyclerView_Adapter
import com.example.hubby.databinding.FragmentGoodHabitsBinding
import com.example.hubby.frameworks.HabitDBMapper
import com.example.hubby.presentation.viewmodels.HabitViewModel


class Fragment_GoodHabits : Fragment()
{
    private lateinit var binding : FragmentGoodHabitsBinding
    private lateinit var rec_v_adapter: RecyclerView_Adapter
    private val viewModel by activityViewModels <HabitViewModel>()
    private val mapper:HabitDBMapper = HabitDBMapper()


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment__good_habits, container, false)

        initRecView()

        viewModel.good_habits?.observe( viewLifecycleOwner, Observer {
           // TODO:  should be fixed ! it's much better to use mapper in one  place
               // TODO  : and nullable - we shouldn call it like that !!!
          binding.rvHabits.adapter = RecyclerView_Adapter(mapper.mapToEntityList(viewModel.good_habits?.value ?: listOf()) ,{habit: Habit ->  itemClicked(habit) },{ habit:Habit -> doneListener(habit)  } )
       })



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


   private  fun initRecView()
    {
        rec_v_adapter = RecyclerView_Adapter(mapper.mapToEntityList(viewModel.good_habits?.value ?: listOf() ), {habit: Habit ->  itemClicked(habit) }, { habit:Habit -> doneListener(habit) })
        binding.rvHabits.layoutManager = LinearLayoutManager(context)
        binding.rvHabits.adapter = rec_v_adapter
    }

    //callback
    fun itemClicked(habit: Habit) : Boolean{
        val name = habit.name
        viewModel.deleteHabit(habit)
        // TODO : a dialog should appear here
        Toast.makeText(context, " $name was deleted ",Toast.LENGTH_SHORT).show()
        return true
    }

    fun doneListener( habit: Habit) : Unit{
        viewModel.updateDone(habit.done_dates, habit.id)

        Toast.makeText(context, " ${habit.id} was uodated  ",Toast.LENGTH_SHORT).show()
    }
}
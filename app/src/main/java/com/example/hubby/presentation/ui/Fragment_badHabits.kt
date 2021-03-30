package com.example.hubby.presentation.ui

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
import com.example.hubby.databinding.FragmentBadHabitsBinding
import com.example.hubby.frameworks.HabitDBMapper
import com.example.hubby.presentation.viewmodels.HabitViewModel

class Fragment_badHabits : Fragment()
{

    private lateinit var binding : FragmentBadHabitsBinding
    private lateinit var rec_v_adapter: RecyclerView_Adapter
    private val viewModel by activityViewModels <HabitViewModel>()
    private val mapper: HabitDBMapper = HabitDBMapper()


    @Override
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View?
    {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_bad_habits , container, false)
        initRecView()
       viewModel.bad_habits?.observe( viewLifecycleOwner, Observer {
            Log.i("In fragment", it.toString())
            // TODO: should be fixed
            binding.rvHabits.adapter = RecyclerView_Adapter(mapper.mapToEntityList(viewModel.bad_habits?.value  ?: listOf() ),{habit: Habit ->  itemClicked(habit) })
        })
        return binding.root
    }

    @Override
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
    }

    private  fun initRecView()
    {
        // TODO: dont really like the solution with the nullable list
        rec_v_adapter = RecyclerView_Adapter(mapper.mapToEntityList(viewModel.bad_habits.value ?: listOf()), {clickedItem: Habit -> itemClicked(clickedItem)})
        binding.rvHabits.layoutManager = LinearLayoutManager(context)
        binding.rvHabits.adapter = rec_v_adapter
    }

    // callback
    fun itemClicked(habit: Habit):Boolean
    {
        val name = habit.name
        viewModel.deleteHabit(habit)
        Toast.makeText(context, " $name was deleted ",Toast.LENGTH_SHORT).show()
        return true
    }
}


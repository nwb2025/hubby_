package com.example.hubby.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hubby.R
import com.example.hubby.databinding.RecViewLayoutBinding
import com.example.hubby.data.db.Habit

class RecyclerView_Adapter (private val habitList:List<Habit>?,
                            private val clickListener:(Habit) -> Boolean) :
        RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder>()
{
    inner class ViewHolder(val binding:RecViewLayoutBinding) : RecyclerView.ViewHolder( binding.root )
    {
        fun bind(habit: Habit?,
                 clickListener:(Habit) -> Boolean)
        {
            binding.habitName.text = habit?.name
            binding.duration.text = habit?.count.toString() + " раза в " + habit?.frequency + " дня"
            binding.cardview.setOnLongClickListener( View.OnLongClickListener {
                clickListener(habit!!)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding:RecViewLayoutBinding = DataBindingUtil.inflate(inflater,R.layout.rec_view_layout,parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount():Int
    {
        // TODO : should be fixed
        var a = 0
        try{
            a = habitList!!.size
        }catch (e:Exception){

        }
        return a
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        // just a different way to bind the data
        holder.bind(habitList?.get(position), clickListener)
    }


}
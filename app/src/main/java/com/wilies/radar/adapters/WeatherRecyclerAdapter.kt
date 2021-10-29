package com.wilies.radar.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wilies.radar.data.models.Weather
import com.wilies.radar.databinding.HomeScreenRecyclerItemBinding
import com.wilies.radar.utils.Utility

class WeatherRecyclerAdapter: RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder>() {
    private var data = listOf<Weather>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherItem = data[position]
        holder.bind(weatherItem)
    }

    override fun getItemCount():Int {
        return data.size
    }

    fun setList(it: List<Weather>) {
        data = it
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: HomeScreenRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherItem: Weather) {
            binding.weather = weatherItem
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = HomeScreenRecyclerItemBinding.inflate(inflater, parent, false)

                return ViewHolder(binding)
            }

        }
    }
}
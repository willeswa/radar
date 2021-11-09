package com.wilies.radar.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wilies.radar.databinding.ForecastRecyclerItemBinding
import com.wilies.radar.domain.DailyWeatherDomain

class ForecastRecyclerAdapter: RecyclerView.Adapter<ForecastRecyclerAdapter.ViewHolder>() {


    private var data = listOf<DailyWeatherDomain>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = data[position]
        holder.bind(weather)
    }

    override fun getItemCount() = data.size

    fun setList(list: List<DailyWeatherDomain>) {
        Log.i("THEEEE MIGHT GOAL", ""+list.size)
        data = list
    }

    class ViewHolder(private val binding: ForecastRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: DailyWeatherDomain) {
            binding.weather = weather
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ForecastRecyclerItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
}
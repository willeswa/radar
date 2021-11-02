package com.wilies.radar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wilies.radar.data.models.DailyWeather
import com.wilies.radar.databinding.ForecastRecyclerItemBinding

class ForecastRecyclerAdapter: RecyclerView.Adapter<ForecastRecyclerAdapter.ViewHolder>() {


    private var data = listOf<DailyWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = data[position]
        holder.bind(weather)
    }

    override fun getItemCount() = data.size

    fun setList(list: List<DailyWeather>) {
        data = list
    }

    class ViewHolder(private val binding: ForecastRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: DailyWeather) {
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
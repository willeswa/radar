package com.wilies.radar.dailyweatherscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wilies.radar.R
import com.wilies.radar.adapters.WeatherRecyclerAdapter
import com.wilies.radar.databinding.FragmentDailyWeatherBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DailyWeatherFragment : Fragment() {
    private val viewModel: DailyWeatherViewModel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDailyWeatherBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_daily_weather,
            container,
            false)

        val adapter = WeatherRecyclerAdapter()

        binding.dailyweatherRecycler.adapter = adapter

        binding.lifecycleOwner = this

        binding.viewmodel = viewModel



        return binding.root
    }


}
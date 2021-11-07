package com.wilies.radar.ui.dailyweatherscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.wilies.radar.MainViewModel
import com.wilies.radar.R
import com.wilies.radar.adapters.DailyWeatherRecyclerAdapter
import com.wilies.radar.databinding.FragmentDailyWeatherBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DailyWeatherFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDailyWeatherBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_daily_weather,
            container,
            false)

        val adapter = DailyWeatherRecyclerAdapter()

        binding.dailyweatherRecycler.adapter = adapter

        binding.lifecycleOwner = this

        binding.viewmodel = viewModel

        binding.navigateToForecast.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }



        return binding.root
    }


}
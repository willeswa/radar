package com.wilies.radar.ui.forecastscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.wilies.radar.R
import com.wilies.radar.adapters.ForecastRecyclerAdapter
import com.wilies.radar.MainViewModel
import com.wilies.radar.databinding.FragmentForecastWeatherBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ForecastWeatherFragment : Fragment() {

    val viewModel: ForecastScreenViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentForecastWeatherBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_forecast_weather, container, false)

        val adapter = ForecastRecyclerAdapter()
        binding.forecastRecycler.adapter = adapter

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel


        val appBarConfig = AppBarConfiguration(findNavController().graph)
        binding.forecastToolbar.setupWithNavController(findNavController(), appBarConfig)
        return binding.root
    }


}
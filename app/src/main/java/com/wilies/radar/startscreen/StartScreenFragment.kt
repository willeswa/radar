package com.wilies.radar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wilies.radar.R
import com.wilies.radar.databinding.FragmentStartScreenBinding

class StartScreen : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentStartScreenBinding.inflate(inflater, container, false)
        binding.getStarted.setOnClickListener{
            findNavController().navigate(R.id.action_startScreen_to_FirstFragment)
        }
        return binding.root
    }
}
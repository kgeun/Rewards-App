package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.databinding.FragmentSplashBinding
import com.bskyb.skyrewards.view.SRWBaseFragment

class SRWSplashFragment : SRWBaseFragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        moveToStart()
        return binding.root
    }

    fun moveToStart() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(
                R.id.splash_to_start,
                null,
                null,
                FragmentNavigatorExtras(binding.introAppLogoImg to "app_logo")
            )
        }, 1000)
    }
}
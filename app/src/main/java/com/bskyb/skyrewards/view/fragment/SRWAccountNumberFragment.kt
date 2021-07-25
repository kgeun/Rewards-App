package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.databinding.FragmentSplashBinding
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.databinding.FragmentAccountNumberBinding
import com.bskyb.skyrewards.databinding.FragmentChannelBinding
import com.bskyb.skyrewards.utils.SRWConstants
import com.bskyb.skyrewards.view.adapters.SRWChannelAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SRWAccountNumberFragment : Fragment() {

    private lateinit var binding: FragmentAccountNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountNumberBinding.inflate(inflater, container, false)
        setListener()

        SRWAnalytics.sendView("SRWIntroFragment")
        return binding.root
    }

    fun setListener() {
        binding.backBtnText.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.nextBtnText.setOnClickListener {
            // ToDo
        }
    }

}
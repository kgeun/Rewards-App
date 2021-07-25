package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.databinding.FragmentSplashBinding
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.databinding.FragmentChannelBinding
import com.bskyb.skyrewards.utils.SRWConstants
import com.bskyb.skyrewards.view.adapters.SRWChannelAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SRWChannelFragment : Fragment() {

    private lateinit var binding: FragmentChannelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChannelBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.no_transition)

        binding.apply {
            adapter = SRWChannelAdapter(binding.root as ViewGroup, SRWConstants.channelList.toList())
        }

        SRWAnalytics.sendView("SRWIntroFragment")

        return binding.root
    }

//    fun moveToStart() {
//        findNavController().navigate(R.id.splash_to_start, null, null, FragmentNavigatorExtras(binding.topLogoImg to "app_logo"))
//    }
}
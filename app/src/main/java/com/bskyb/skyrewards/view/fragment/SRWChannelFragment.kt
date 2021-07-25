package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.databinding.FragmentChannelBinding
import com.bskyb.skyrewards.utils.SRWAnimations
import com.bskyb.skyrewards.utils.SRWConstants
import com.bskyb.skyrewards.view.adapters.SRWChannelAdapter

class SRWChannelFragment : Fragment() {
    private lateinit var binding: FragmentChannelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChannelBinding.inflate(inflater, container, false)

        binding.apply {
            adapter = SRWChannelAdapter(binding.root as ViewGroup, SRWConstants.channelList.toList())
        }

        setListener()

        SRWAnalytics.sendView("SRWIntroFragment")
        return binding.root
    }

    private fun setListener() {
        binding.backBtnText.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
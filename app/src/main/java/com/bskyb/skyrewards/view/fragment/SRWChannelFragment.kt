package com.bskyb.skyrewards.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.databinding.FragmentChannelBinding
import com.bskyb.skyrewards.utils.SRWConstants
import com.bskyb.skyrewards.utils.SRWUtils
import com.bskyb.skyrewards.view.SRWBaseFragment
import com.bskyb.skyrewards.view.adapters.SRWChannelAdapter

class SRWChannelFragment : SRWBaseFragment() {
    private lateinit var binding: FragmentChannelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentChannelBinding.inflate(inflater, container, false)
        binding.adapter = SRWChannelAdapter(binding.root as ViewGroup, SRWConstants.channelList.toList())

        SRWUtils.hideKeyboard(activity)
        setBtnActions()

        return binding.root
    }

    private fun setBtnActions() {
        binding.backBtnText.setOnClickListener {
            findNavController().popBackStack()
            SRWAnalytics.sendClick("BackBtn_${javaClass.simpleName}")
        }
    }
}
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
import com.bskyb.skyrewards.databinding.FragmentStartBinding


class SRWStartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        setListener()

        SRWAnalytics.sendView("SRWIntroFragment")

        return binding.root
    }

    fun setListener() {
        binding.nextBtnText.setOnClickListener {
            findNavController().navigate(R.id.start_to_channel)
        }
    }
}
package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.databinding.FragmentAccountNumberBinding
import com.bskyb.skyrewards.view.SRWBaseFragment

class SRWAccountNumberFragment : SRWBaseFragment() {

    private lateinit var binding: FragmentAccountNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentAccountNumberBinding.inflate(inflater, container, false)

        setBtnActions()

        SRWAnalytics.sendView("SRWIntroFragment")
        return binding.root
    }

    fun setBtnActions() {
        binding.backBtnText.setOnClickListener {
            findNavController().popBackStack()
            SRWAnalytics.sendClick("BackBtn_${javaClass.simpleName}")
        }

        binding.nextBtnText.setOnClickListener {
            // ToDo
            SRWAnalytics.sendClick("NextBtn_${javaClass.simpleName}")
        }
    }

}
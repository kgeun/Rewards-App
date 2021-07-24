package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.databinding.FragmentIntroBinding
import com.nmp.studygeto.analytics.SRWAnalytics

class SRWIntroFragment : Fragment() {

    lateinit var binding: FragmentIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        super.onCreateView(inflater, container, savedInstanceState)

        setListener()

        SRWAnalytics.sendView("SRWIntroFragment")
//        val mapInfo: Map<String, String> = mapOf("농장이름" to farmData.name, "전화번호" to farmData.phone)
        SRWAnalytics.sendEvent("SRWIntroFragment", null)

        return binding.root
    }

    fun setListener() {
//        binding.root.closeBtn.setOnClickListener {
//            KFPAnalytics.sendView("FarmInfoClosed")
//            dismiss()
//        }
    }
}
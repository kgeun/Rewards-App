package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
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
//        val mapInfo: Map<String, String> = mapOf("농장이름" to farmData.name, "전화번호" to farmData.phone)
//        SRWAnalytics.sendEvent("SRWIntroFragment", null)

        return binding.root
    }

    fun setListener() {
//        binding.root.closeBtn.setOnClickListener {
//            KFPAnalytics.sendView("FarmInfoClosed")
//            dismiss()
//        }
    }
}
package com.bskyb.skyrewards.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bskyb.skyrewards.analytics.SRWAnalytics

open class SRWBaseFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sendAnalytics()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun sendAnalytics() {
        SRWAnalytics.sendView(javaClass.simpleName)
    }
}
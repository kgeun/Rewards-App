package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SRWSplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        moveToStart()

        SRWAnalytics.sendView("SRWIntroFragment")

        Log.i("kglee", "onCreateView")
//        val mapInfo: Map<String, String> = mapOf("농장이름" to farmData.name, "전화번호" to farmData.phone)
//        SRWAnalytics.sendEvent("SRWIntroFragment", null)

        return binding.root
    }

    fun moveToStart() {
//        GlobalScope.launch {
//            delay(1000)
//            findNavController().navigate(R.id.splash_to_start)
//        }
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.splash_to_start, null, null, FragmentNavigatorExtras(binding.introAppLogoImg to "app_logo"))
        }, 1000)
    }
}
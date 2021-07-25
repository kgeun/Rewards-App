package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.databinding.FragmentStartBinding
import com.bskyb.skyrewards.utils.SRWAnimationUtils
import com.bskyb.skyrewards.view.SRWBaseFragment


class SRWStartFragment : SRWBaseFragment() {
    private lateinit var binding: FragmentStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentStartBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        setListener()
        setAnim()

        SRWAnalytics.sendView("SRWIntroFragment")
        return binding.root
    }

    private fun setListener() {
        binding.nextBtnText.setOnClickListener {
            val navBuilder = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_from_right)
                .setExitAnim(R.anim.slide_to_left)
                .setPopEnterAnim(R.anim.slide_from_left)
                .setPopExitAnim(R.anim.fade_out)

            findNavController()
                .navigate(R.id.start_to_channel
                    ,null
                    ,navBuilder.build()
                )

            SRWAnalytics.sendClick("NextBtn_${javaClass.simpleName}")
        }
    }

    private fun setAnim() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.rewardContentScroll.startAnimation(SRWAnimationUtils.getAnim500(R.anim.translate_fade_in, requireContext()))
            binding.nextBtnText.startAnimation(SRWAnimationUtils.getAnim500(android.R.anim.fade_in, requireContext()))
        }, 500)
    }
}
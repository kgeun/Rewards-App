package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.data.model.SRWRewardResult
import com.bskyb.skyrewards.databinding.FragmentResultBinding
import com.bskyb.skyrewards.view.SRWBaseFragment
import com.bskyb.skyrewards.view.viewmodel.SRWMainViewModel
import dagger.hilt.android.AndroidEntryPoint

class SRWResultFragment: SRWBaseFragment() {
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentResultBinding.inflate(inflater, container, false)

        bindData(binding)
        setListener()
        setBackKeyAction()

        return binding.root
    }

    private fun setBackKeyAction() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    goToFirstScreen()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun bindData(binding: FragmentResultBinding) {
        val result: SRWRewardResult = SRWAccountNumberFragmentArgs.fromBundle(requireArguments()).rewardResult
        binding.rewardResult = result
    }

    private fun setListener() {
        binding.exitBtn.setOnClickListener {
            activity?.finish()
            SRWAnalytics.sendClick("ExitBtn_${javaClass.simpleName}")
        }

        binding.homeBtn.setOnClickListener {
            goToFirstScreen()
            SRWAnalytics.sendClick("HomeBtn_${javaClass.simpleName}")
        }
    }

    private fun goToFirstScreen() {
        val navBuilder = NavOptions.Builder()
            .setExitAnim(R.anim.fade_out)
            .setPopExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setEnterAnim(R.anim.fade_in)

        findNavController()
            .navigate(R.id.result_to_start, null, navBuilder.build())
    }
}
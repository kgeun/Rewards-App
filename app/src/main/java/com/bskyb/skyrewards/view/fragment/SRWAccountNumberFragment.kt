package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.databinding.FragmentAccountNumberBinding
import com.bskyb.skyrewards.utils.SRWUtils
import com.bskyb.skyrewards.utils.SkyRewardsEngine_Client
import com.bskyb.skyrewards.view.SRWBaseFragment
import com.bskyb.skyrewards.view.SRWMainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SRWAccountNumberFragment : SRWBaseFragment() {

    private lateinit var binding: FragmentAccountNumberBinding
    val mainViewModel: SRWMainViewModel by viewModels()
    private var nextFlag = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentAccountNumberBinding.inflate(inflater, container, false)

        setBtnActions()
//        observeResult()
        return binding.root
    }

//
//    private fun observeResult() {
//        mainViewModel.myAccountNumber.observe(viewLifecycleOwner) {
//            Log.i("kglee","${mainViewModel.myChannel.value} ${mainViewModel.myAccountNumber.value}")
//            if (mainViewModel.myChannel.value != null &&
//                mainViewModel.myAccountNumber.value != null) {
//                    startSkyEngine()
//            }
//        }
//    }

    private fun setBtnActions() {
        binding.backBtnText.setOnClickListener {
            findNavController().popBackStack()
            SRWAnalytics.sendClick("BackBtn_${javaClass.simpleName}")
        }

        binding.nextBtnText.setOnClickListener {
            enterNumberFinished()
            SRWAnalytics.sendClick("NextBtn_${javaClass.simpleName}")
        }

        binding.accountNumberEditText.setOnEditorActionListener { view, actionId, event ->
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                SRWAnalytics.sendClick("KeyboardDoneBtn_${javaClass.simpleName}")
                enterNumberFinished()
            }
            false
        }
    }

    private fun enterNumberFinished() {
        validateAccountNumber(binding.accountNumberEditText.text.toString())
    }

    private fun validateAccountNumber(accountNumberString: String) {
        if (accountNumberString.length != 12) {
            Toast.makeText(requireContext(), getString(R.string.account_number_validate_fail_message), Toast.LENGTH_SHORT).show()
        } else {
            mainViewModel.myAccountNumber.postValue(SRWUtils.sha256(accountNumberString))

            Log.i("kglee","${mainViewModel.myChannel.value} ${mainViewModel.myAccountNumber.value}")
            nextFlag = true
        }
    }
}
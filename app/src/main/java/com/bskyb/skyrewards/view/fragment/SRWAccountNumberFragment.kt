package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.databinding.FragmentAccountNumberBinding
import com.bskyb.skyrewards.view.SRWBaseFragment
import com.bskyb.skyrewards.view.SRWMainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SRWAccountNumberFragment : SRWBaseFragment() {

    private lateinit var binding: FragmentAccountNumberBinding
    private val mainViewModel: SRWMainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentAccountNumberBinding.inflate(inflater, container, false)

        setBtnActions()
        observeResult()

        SRWAnalytics.sendView("SRWIntroFragment")
        return binding.root
    }

    private fun observeResult() {
        mainViewModel.myAccountNumber.observe(this) {
            Toast.makeText(activity, "accountNuber : ${it}", Toast.LENGTH_SHORT).show()
        }
    }

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
            Toast.makeText(activity, "Please enter all 12-digit account numbers.", Toast.LENGTH_SHORT).show()
        } else {
            mainViewModel.myAccountNumber.postValue(accountNumberString)
        }
    }
}
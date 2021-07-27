package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.data.model.SRWCustomerData
import com.bskyb.skyrewards.data.model.SRWRewardResult
import com.bskyb.skyrewards.data.persistance.SRWMainDao
import com.bskyb.skyrewards.databinding.FragmentAccountNumberBinding
import com.bskyb.skyrewards.service.client.SRWSkyClientEngine
import com.bskyb.skyrewards.view.SRWBaseFragment
import com.bskyb.skyrewards.view.viewmodel.SRWMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SRWAccountNumberFragment : SRWBaseFragment() {

    @Inject lateinit var mainDao: SRWMainDao
    private lateinit var binding: FragmentAccountNumberBinding
    val mainViewModel: SRWMainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentAccountNumberBinding.inflate(inflater, container, false)

        setBtnActions()
        observeResult()
        return binding.root
    }

    private fun observeResult() {
        mainViewModel.customerData.observe(viewLifecycleOwner) {
            if (it?.accountNumber != null) {
                startSkyEngine(it)
            }
        }

        mainViewModel.rewardResult.observe(viewLifecycleOwner) {
            if (it != null) {
                goToResultFragment(it)
            }
        }
    }

    private fun goToResultFragment(result: SRWRewardResult) {
        val navBuilder = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_from_right)
            .setExitAnim(R.anim.slide_to_left)
            .setPopEnterAnim(R.anim.slide_from_left)
            .setPopExitAnim(R.anim.fade_out)

        findNavController()
            .navigate(
                SRWAccountNumberFragmentDirections.accountToResult(result)
                ,navBuilder.build())
    }

    private fun startSkyEngine(customerData: SRWCustomerData) {
        mainViewModel.viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val rewardResult =
                        SRWSkyClientEngine(customerData, requireContext()).getRewardResult()
                    mainDao.insertRewardResult(rewardResult!!)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
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
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
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

        try {
            if (accountNumberString.length != 12) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.account_number_validate_fail_message),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val customerData = mainViewModel.customerData.value
                customerData?.accountNumber = accountNumberString
                insertCustomerData(customerData!!)
            }
        } catch (e: NullPointerException) {
            // Check if customerData is null
            e.printStackTrace()
        }
    }

    private fun insertCustomerData(customerData: SRWCustomerData) {
        mainViewModel.viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainDao.insertCustomerData(customerData)
            }
        }
    }
}
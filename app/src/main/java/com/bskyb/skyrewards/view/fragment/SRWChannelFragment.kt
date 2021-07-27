package com.bskyb.skyrewards.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.data.model.SRWChannel
import com.bskyb.skyrewards.data.model.SRWCustomerData
import com.bskyb.skyrewards.data.persistance.SRWMainDao
import com.bskyb.skyrewards.databinding.FragmentChannelBinding
import com.bskyb.skyrewards.constants.SRWConstants
import com.bskyb.skyrewards.utils.SRWUtils
import com.bskyb.skyrewards.view.SRWBaseFragment
import com.bskyb.skyrewards.view.adapters.SRWChannelAdapter
import com.bskyb.skyrewards.view.viewmodel.SRWMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SRWChannelFragment : SRWBaseFragment() {

    private lateinit var binding: FragmentChannelBinding
    val mainViewModel: SRWMainViewModel by viewModels()
    @Inject
    lateinit var mainDao: SRWMainDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentChannelBinding.inflate(inflater, container, false)
        binding.adapter =
            SRWChannelAdapter(binding.root as ViewGroup, SRWConstants.channelOrder.toList())
        SRWUtils.hideKeyboard(activity)
        setBtnActions()
        return binding.root
    }

    private fun setBtnActions() {
        binding.backBtnText.setOnClickListener {
            findNavController().popBackStack()
            SRWAnalytics.sendClick("BackBtn_${javaClass.simpleName}")
        }
    }

    fun insertChannel(item: SRWChannel) {
        var customerData: SRWCustomerData? = mainViewModel.customerData.value
        if (customerData == null) {
            customerData = SRWCustomerData()
        }
        customerData.channelId = item.channelId
        customerData.timeStamp = System.currentTimeMillis()

        mainViewModel.viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mainDao.insertCustomerData(customerData)
            }
        }
    }
}
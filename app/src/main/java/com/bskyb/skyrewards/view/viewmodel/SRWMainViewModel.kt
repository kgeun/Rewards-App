package com.bskyb.skyrewards.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bskyb.skyrewards.data.persistance.SRWMainDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SRWMainViewModel @Inject constructor(private val mainDao: SRWMainDao) : ViewModel() {
    val customerData = mainDao.getCustomerData()
    val rewardResult = mainDao.getRewardResult()
}
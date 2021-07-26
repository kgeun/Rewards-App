package com.bskyb.skyrewards.view

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bskyb.skyrewards.data.constant.SRWChannelType
import com.bskyb.skyrewards.data.model.SRWChannel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SRWMainViewModel @Inject constructor() : ViewModel() {
    val myChannel = MutableLiveData<Int>()
    val myAccountNumber = MutableLiveData<String>()

}
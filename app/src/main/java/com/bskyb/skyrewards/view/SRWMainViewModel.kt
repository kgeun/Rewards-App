package com.bskyb.skyrewards.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bskyb.skyrewards.data.model.SRWChannel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SRWMainViewModel @Inject constructor() : ViewModel() {
    val myChannel = MutableLiveData<SRWChannel>()
    val myAccountNumber = MutableLiveData<String>()
}
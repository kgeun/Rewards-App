package com.bskyb.skyrewards.service.rewards_service

import android.content.Context
import android.util.Log
import com.bskyb.skyrewards.data.model.SRWChannel
import java.lang.Long.parseLong

class SRWSkyRewardsEngine(val myChannel: Int, private val encodedAccountNumber: String) {
    fun engineProcess(): Int {
        val extracted = parseLong(encodedAccountNumber.substring(0,2), 16)
        Log.i("kglee", "extracted : " + extracted)

        return extracted.toInt()
    }
}
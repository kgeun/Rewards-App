package com.bskyb.skyrewards.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.data.model.SRWChannel
import com.bskyb.skyrewards.service.rewards_service.SRWRewardsService
import java.lang.Thread.sleep

class SkyRewardsEngine_Client(val myChannel: Int, val encodedAccountNumber: String, val context: Context)  {

    fun startService() {
        Log.i("kglee","SkyRewardsEngine_Client Started!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        Log.i("kglee","$myChannel $encodedAccountNumber")

        if (!checkRewardsServiceStatus()) {
            Toast.makeText(context, R.string.rewards_service_offline_try_restart, Toast.LENGTH_SHORT).show()
        } else {
            (SRWRewardsService.Helper.srwService as SRWRewardsService).engineProcess(encodedAccountNumber, myChannel)
        }
    }

    fun checkRewardsServiceStatus(): Boolean {
        return if (SRWRewardsService.Helper.isBounded) {
            true
        } else {
            SRWRewardsService.Helper.bindService(context)
            sleep(100)
            SRWRewardsService.Helper.isBounded
        }
    }

}
package com.bskyb.skyrewards.service.eligibility_service

import android.content.Context
import android.widget.Toast
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.data.model.SRWChannel
import java.lang.Thread.sleep

class SRWSkyEligibilityEngine(val myChannel: SRWChannel, val myAccountNumber: String, val context: Context)  {

    fun startService() {
        if (!checkRewardsServiceStatus()) {
            Toast.makeText(context, R.string.rewards_service_offline_try_restart, Toast.LENGTH_SHORT).show()
        } else {

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
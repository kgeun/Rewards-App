package com.bskyb.skyrewards.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.data.model.SRWChannel
import com.bskyb.skyrewards.data.model.SRWCustomerData
import com.bskyb.skyrewards.service.rewards_service.SRWRewardsService
import java.lang.Thread.sleep

class SkyRewardsClientEngine(customerData: SRWCustomerData, val context: Context)  {

    fun startService() {
        if (!checkRewardsServiceStatus()) {
            Toast.makeText(context, R.string.rewards_service_offline_try_restart, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "DSDFDFSFDFDFD", Toast.LENGTH_SHORT).show()
            val result = (SRWRewardsService.Helper.srwService as SRWRewardsService).engineProcess("318aee3fed8c9d040d35a7fc1fa776fb31303833aa2de885354ddf3d44d8fb69", 3)
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
package com.bskyb.skyrewards.service.client

import android.content.Context
import android.widget.Toast
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.SRWApplication
import com.bskyb.skyrewards.constants.enums.SRWServiceResult
import com.bskyb.skyrewards.data.model.SRWCustomerData
import com.bskyb.skyrewards.data.model.SRWRewardResult
import com.bskyb.skyrewards.service.rewards_service.SRWRewardsService
import com.bskyb.skyrewards.service.util.SRWEngineUtil
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.lang.Thread.sleep


class SRWSkyClientEngine(val customerData: SRWCustomerData, val context: Context) {

    fun getRewardResult(): SRWRewardResult? {
        return if (!checkRewardsServiceStatus()) {
            Toast.makeText(
                context,
                R.string.rewards_service_offline_try_restart,
                Toast.LENGTH_SHORT
            ).show()
            createRewardsServiceFailure()
        } else {
            val rewardsService = SRWRewardsService.Helper.srwService as SRWRewardsService
            val rewardsResult: ByteArray = rewardsService.serviceProcess(customerDataByteArray())
            SRWEngineUtil.parseToRewardResult(rewardsResult)
        }
    }

    private fun customerDataByteArray(): ByteArray {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<SRWCustomerData> = moshi.adapter(SRWCustomerData::class.java)
        val customerDataToJson = jsonAdapter.toJson(customerData)
        return customerDataToJson.toByteArray()
    }

    private fun createRewardsServiceFailure(): SRWRewardResult {
        return SRWRewardResult(
            resultCode = SRWServiceResult.RESULTS_SERVICE_FAILURE.resultCode,
            timestamp = System.currentTimeMillis(),
            messageTitle = SRWApplication.instance.getString(R.string.server_failure_title),
            messageDescription = SRWApplication.instance.getString(R.string.server_failure_description),
            imageUrl = R.drawable.img_exclamination_mark
        )
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
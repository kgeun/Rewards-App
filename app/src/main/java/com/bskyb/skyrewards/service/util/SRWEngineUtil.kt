package com.bskyb.skyrewards.service.util

import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.SRWApplication
import com.bskyb.skyrewards.constants.SRWConstants
import com.bskyb.skyrewards.constants.enums.SRWServiceResult
import com.bskyb.skyrewards.data.model.network.SRWCustomerData
import com.bskyb.skyrewards.data.model.network.SRWRewardResult
import com.squareup.moshi.JsonAdapter

import com.squareup.moshi.Moshi

object SRWEngineUtil {

    fun parseToCustomerData(rawData: ByteArray): SRWCustomerData? {
        val customerDataJson = rawData.decodeToString()
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<SRWCustomerData> = moshi.adapter(SRWCustomerData::class.java)
        return jsonAdapter.fromJson(customerDataJson)
    }

    fun parseToRewardResult(rawData: ByteArray): SRWRewardResult? {
        val rewardResultJson = rawData.decodeToString()
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<SRWRewardResult> = moshi.adapter(SRWRewardResult::class.java)
        return jsonAdapter.fromJson(rewardResultJson)
    }

    fun makeByteArrayWithNegativeResultCode(resultCode: Int): ByteArray {
        val resultObject = makeNegativeRewardResult(resultCode)
        return rewardResultToJson(resultObject).encodeToByteArray()
    }

    fun makeNegativeRewardResult(resultCode: Int)
        = SRWRewardResult (
            resultCode = resultCode,
            timestamp = System.currentTimeMillis(),
            messageTitle = SRWConstants.serviceResultMap[resultCode]?.title,
            messageDescription = SRWConstants.serviceResultMap[resultCode]?.message,
            imageUrl = SRWConstants.serviceResultMap[resultCode]?.imageUrl
        )

    fun makeByteArrayWithEligibleCustomer(customerData: SRWCustomerData): ByteArray =
        rewardResultToJson(makeResultEligibleCustomer(customerData)).encodeToByteArray()

    fun makeResultEligibleCustomer(
        customerData: SRWCustomerData
    ): SRWRewardResult {
        try {
            val reward = SRWConstants.eligibleChannelsToRewardsMap[customerData.channelId]!!
            val messageDescription: String =
                SRWApplication.instance.getString(R.string.eligible_description_1) + " " +
                        reward.title + " " + SRWApplication.instance.getString(R.string.eligible_description_2)

            return SRWRewardResult(
                resultCode = SRWServiceResult.CUSTOMER_ELIGIBLE.resultCode,
                timestamp = System.currentTimeMillis(),
                messageTitle = SRWApplication.instance.getString(R.string.eligible_title),
                messageDescription = messageDescription,
                imageUrl = reward.rewardImgUrl
            )
        } catch (e: Exception) {
            // If an invalid channel ID is entered
            e.printStackTrace()
            return makeNegativeRewardResult(SRWServiceResult.RESULTS_SERVICE_FAILURE.resultCode)
        }
    }

    private fun rewardResultToJson(result: SRWRewardResult): String {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<SRWRewardResult> =
            moshi.adapter(SRWRewardResult::class.java)

        return jsonAdapter.toJson(result)
    }
}
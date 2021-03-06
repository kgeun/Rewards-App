package com.bskyb.skyrewards.service.rewards_service

import com.bskyb.skyrewards.constants.SRWConstants
import com.bskyb.skyrewards.constants.enums.SRWServiceResult
import com.bskyb.skyrewards.data.model.network.SRWCustomerData
import com.bskyb.skyrewards.service.SRWSkyEngine
import com.bskyb.skyrewards.service.eligibility_service.SRWEligibilityService
import com.bskyb.skyrewards.service.util.SRWEngineUtil

class SRWSkyRewardsEngine(private val rawData: ByteArray) : SRWSkyEngine {

    override fun engineProcess(): ByteArray {
        try {
            // 1. Before processing, parse raw data
            val customerData: SRWCustomerData =
                SRWEngineUtil.parseToCustomerData(rawData) ?: throw NullPointerException()

            // 2. If channel is news or kids it returns ineligible
            if (!checkRewardChannel(customerData)) {
                return SRWEngineUtil
                    .makeByteArrayWithNegativeResultCode(SRWServiceResult.CUSTOMER_INELIGIBLE.resultCode)
            }

            // 3. if Other channels, check EligibilityService alive and pass account number to EligibilityService
            if (!SRWEligibilityService.Helper.isBounded)
                return SRWEngineUtil
                    .makeByteArrayWithNegativeResultCode(SRWServiceResult.ELIGIBILITY_SERVICE_FAILURE.resultCode)

            val eligibilityService = SRWEligibilityService.Helper.srwService as SRWEligibilityService
            return eligibilityService.serviceProcess(rawData)

        } catch (e: Exception) {
            e.printStackTrace()
            return SRWEngineUtil
                .makeByteArrayWithNegativeResultCode(SRWServiceResult.RESULTS_SERVICE_FAILURE.resultCode)
        }
    }

    private fun checkRewardChannel(customerData: SRWCustomerData): Boolean {
        return SRWConstants.eligibleChannelsToRewardsMap.keys.contains(customerData.channelId)
    }
}
package com.bskyb.skyrewards.service.rewards_service

import com.bskyb.skyrewards.data.constant.SRWChannelType
import com.bskyb.skyrewards.data.constant.SRWServiceResult
import com.bskyb.skyrewards.service.SRWSkyEngine
import com.bskyb.skyrewards.service.eligibility_service.SRWEligibilityService
import com.bskyb.skyrewards.utils.SRWUtils

class SRWSkyRewardsEngine(val encodedAccountNumber: String, val myChannel: Int): SRWSkyEngine{

    override fun engineProcess(): Int {
        // 1. 5% chance to determine server failure
        if (SRWUtils.random100() < 5)
            return SRWServiceResult.TECHNICAL_FAILURE_ERROR1.outputId

        // 2. If channel is news or kids it returns ineligible
        if (myChannel == SRWChannelType.KIDS.channelId ||
                myChannel == SRWChannelType.NEWS.channelId) {
            return SRWServiceResult.CUSTOMER_INELIGIBLE.outputId
        }

        // 3. if Other channels, pass account number to EligibilityService
        if (!SRWEligibilityService.Helper.isBounded)
            return SRWServiceResult.TECHNICAL_FAILURE_ERROR2.outputId

        return (SRWEligibilityService.Helper.srwService as SRWEligibilityService).engineProcess(encodedAccountNumber)

    }
}
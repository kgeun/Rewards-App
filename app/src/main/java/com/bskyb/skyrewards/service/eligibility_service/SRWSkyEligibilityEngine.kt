package com.bskyb.skyrewards.service.eligibility_service

import com.bskyb.skyrewards.data.enums.SRWServiceResult
import com.bskyb.skyrewards.service.SRWSkyEngine
import com.bskyb.skyrewards.utils.SRWUtils

class SRWSkyEligibilityEngine(val encodedAccountNumber: String, val myChannel: Int): SRWSkyEngine{
    override fun engineProcess(): Int {
        // 1. 5% chance to determine server failure
        if (SRWUtils.random100() < 7) {
            return SRWServiceResult.TECHNICAL_FAILURE_ERROR2.resultCode
        }

        // 2. Determining whether an account number is valid with its own algorithm
        if (isEligibleAccountNumber(encodedAccountNumber)) {
            return SRWServiceResult.CUSTOMER_ELIGIBLE.resultCode
        } else {
            return SRWServiceResult.INVALID_ACCOUNT_NUMBER_ERROR.resultCode
        }
    }

    fun isEligibleAccountNumber(accountNumber: String): Boolean {
        val first6letters = accountNumber.substring(0,7)
        var alphabetCount = 0

        for (i in 0 until 6) {
            if (first6letters[i].isLetter())
                alphabetCount++
        }

        return alphabetCount <= 3
    }
}
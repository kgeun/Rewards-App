package com.bskyb.skyrewards.service.eligibility_service

import com.bskyb.skyrewards.constants.enums.SRWServiceResult
import com.bskyb.skyrewards.data.model.SRWCustomerData
import com.bskyb.skyrewards.service.SRWSkyEngine
import com.bskyb.skyrewards.service.util.SRWEngineUtil

class SRWSkyEligibilityEngine(val rawData: ByteArray) : SRWSkyEngine {
    override fun engineProcess(): ByteArray {
        try {
            // 1. Before processing, parse raw data
            val customerData: SRWCustomerData =
                SRWEngineUtil.parseToCustomerData(rawData) ?: throw NullPointerException()

            // 2. Determining whether an account number is valid with its own algorithm
            if (isEligibleAccountNumber(
                    customerData.accountNumber ?: throw NullPointerException()
                )
            ) {
                return SRWEngineUtil
                    .makeByteArrayWithEligibleCustomer(customerData)
            } else {
                return SRWEngineUtil
                    .makeByteArrayWithNegativeResultCode(SRWServiceResult.INVALID_ACCOUNT_NUMBER_ERROR.resultCode)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return SRWEngineUtil
                .makeByteArrayWithNegativeResultCode(SRWServiceResult.ELIGIBILITY_SERVICE_FAILURE.resultCode)
        }
    }

    private fun isEligibleAccountNumber(accountNumber: String): Boolean {
        val firstNum = accountNumber[0]
        for (i in 1 until 4) {
            if (firstNum != accountNumber[i]) {
                return false
            }
        }
        return true
    }
}
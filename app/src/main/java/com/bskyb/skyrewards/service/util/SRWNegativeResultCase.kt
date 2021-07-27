package com.bskyb.skyrewards.service.util

import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.SRWApplication
import com.bskyb.skyrewards.constants.enums.SRWServiceResult
import com.bskyb.skyrewards.data.model.SRWRewardResult

object SRWNegativeResultCase {
    fun getResultsServiceFailureCase(timestamp: Boolean = true) = SRWRewardResult(
        resultCode = SRWServiceResult.RESULTS_SERVICE_FAILURE.resultCode,
        timestamp = if (timestamp) System.currentTimeMillis() else 0,
        messageTitle = SRWApplication.instance.getString(R.string.server_failure_title),
        messageDescription = SRWApplication.instance.getString(R.string.server_failure_description),
        imageUrl = R.drawable.img_exclamination_mark
    )

    fun getEligibilityServiceFailureCase(timestamp: Boolean = true) = SRWRewardResult(
        resultCode = SRWServiceResult.ELIGIBILITY_SERVICE_FAILURE.resultCode,
        timestamp = if (timestamp) System.currentTimeMillis() else 0,
        messageTitle = SRWApplication.instance.getString(R.string.server_failure_title),
        messageDescription = SRWApplication.instance.getString(R.string.server_failure_description),
        imageUrl = R.drawable.img_exclamination_mark
    )

    fun getCustomerInEligibleCase(timestamp: Boolean = true) = SRWRewardResult(
        resultCode = SRWServiceResult.CUSTOMER_INELIGIBLE.resultCode,
        timestamp = if (timestamp) System.currentTimeMillis() else 0,
        messageTitle = SRWApplication.instance.getString(R.string.ineligible_title),
        messageDescription = SRWApplication.instance.getString(R.string.ineligible_description),
        imageUrl = R.drawable.img_sad_person
    )

    fun getInvalidAccountNumberCase(timestamp: Boolean = true) = SRWRewardResult(
        resultCode = SRWServiceResult.INVALID_ACCOUNT_NUMBER_ERROR.resultCode,
        timestamp = if (timestamp) System.currentTimeMillis() else 0,
        messageTitle = SRWApplication.instance.getString(R.string.invalid_number_title),
        messageDescription = SRWApplication.instance.getString(R.string.invalid_number_description),
        imageUrl = R.drawable.img_exclamination_mark
    )
}
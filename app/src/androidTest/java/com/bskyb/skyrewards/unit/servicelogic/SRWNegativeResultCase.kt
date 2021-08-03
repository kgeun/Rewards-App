package com.bskyb.skyrewards.unit.servicelogic

import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.SRWApplication
import com.bskyb.skyrewards.constants.enums.SRWServiceResult
import com.bskyb.skyrewards.data.model.network.SRWRewardResult

object SRWNegativeResultCase {

    fun getResultsServiceFailureCase() = SRWRewardResult(
        resultCode = SRWServiceResult.RESULTS_SERVICE_FAILURE.resultCode,
        timestamp = System.currentTimeMillis(),
        messageTitle = SRWApplication.instance.getString(R.string.server_failure_title),
        messageDescription = SRWApplication.instance.getString(R.string.server_failure_description),
        imageUrl = R.drawable.img_exclamination_mark
    )

    fun getEligibilityServiceFailureCase() = SRWRewardResult(
        resultCode = SRWServiceResult.ELIGIBILITY_SERVICE_FAILURE.resultCode,
        timestamp = System.currentTimeMillis(),
        messageTitle = SRWApplication.instance.getString(R.string.server_failure_title),
        messageDescription = SRWApplication.instance.getString(R.string.server_failure_description),
        imageUrl = R.drawable.img_exclamination_mark
    )

    fun getCustomerInEligibleCase() = SRWRewardResult(
        resultCode = SRWServiceResult.CUSTOMER_INELIGIBLE.resultCode,
        timestamp = System.currentTimeMillis(),
        messageTitle = SRWApplication.instance.getString(R.string.ineligible_title),
        messageDescription = SRWApplication.instance.getString(R.string.ineligible_description),
        imageUrl = R.drawable.img_sad_person
    )

    fun getInvalidAccountNumberCase() = SRWRewardResult(
        resultCode = SRWServiceResult.INVALID_ACCOUNT_NUMBER_ERROR.resultCode,
        timestamp = System.currentTimeMillis(),
        messageTitle = SRWApplication.instance.getString(R.string.invalid_number_title),
        messageDescription = SRWApplication.instance.getString(R.string.invalid_number_description),
        imageUrl = R.drawable.img_exclamination_mark
    )
}
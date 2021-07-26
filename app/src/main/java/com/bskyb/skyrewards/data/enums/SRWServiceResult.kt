package com.bskyb.skyrewards.data.enums

enum class SRWServiceResult(val message: String, val resultCode: Int) {
    CUSTOMER_ELIGIBLE("Customer is eligible", 1),
    CUSTOMER_INELIGIBLE("Customer is not eligible", 0),
    TECHNICAL_FAILURE_ERROR1("Service technical failure (ResultsService Failure)", -99999),
    TECHNICAL_FAILURE_ERROR2("Service technical failure (EligibilityService Failure)", -99998),
    INVALID_ACCOUNT_NUMBER_ERROR("Invalid account number error", -1)
}
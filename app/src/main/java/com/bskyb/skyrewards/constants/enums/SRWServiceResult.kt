package com.bskyb.skyrewards.constants.enums

enum class SRWServiceResult(val message: String, val resultCode: Int) {
    CUSTOMER_ELIGIBLE("Customer is eligible", 1),
    CUSTOMER_INELIGIBLE("Customer is not eligible", 0),
    RESULTS_SERVICE_FAILURE("Service technical failure (ResultsService Failure)", -99999),
    ELIGIBILITY_SERVICE_FAILURE("Service technical failure (EligibilityService Failure)", -99998),
    INVALID_ACCOUNT_NUMBER_ERROR("Invalid account number error", -1)
}
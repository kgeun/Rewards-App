package com.bskyb.skyrewards.data.constant

enum class SRWServiceResult(val message: String, val outputId: Int) {
    CUSTOMER_ELIGIBLE("Customer is eligible", 1),
    CUSTOMER_INELIGIBLE("Customer is not eligible", 0),
    TECHNICAL_FAILURE_ERROR("Service technical failure", -99999),
    INVALID_ACCOUNT_NUMBER_ERROR("Invalid account number error", -1)
}
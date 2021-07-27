package com.bskyb.skyrewards.constants.enums

import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.SRWApplication

enum class SRWServiceResult(val resultCode: Int, val title: String, val message: String, val imageUrl: Int) {
    CUSTOMER_ELIGIBLE (
        1,
        SRWApplication.instance.getString(R.string.eligible_title),
        "",
        0),
    CUSTOMER_INELIGIBLE (
        0,
        SRWApplication.instance.getString(R.string.ineligible_title),
        SRWApplication.instance.getString(R.string.ineligible_description),
        R.drawable.img_sad_person),
    RESULTS_SERVICE_FAILURE (
        -99999,
        SRWApplication.instance.getString(R.string.server_failure_title),
        SRWApplication.instance.getString(R.string.server_failure_description),
        R.drawable.img_exclamination_mark),
    ELIGIBILITY_SERVICE_FAILURE (
        -99998,
        SRWApplication.instance.getString(R.string.server_failure_title),
        SRWApplication.instance.getString(R.string.server_failure_description),
        R.drawable.img_exclamination_mark),
    INVALID_ACCOUNT_NUMBER_ERROR (
        -10,
        SRWApplication.instance.getString(R.string.invalid_number_title),
        SRWApplication.instance.getString(R.string.invalid_number_description),
        R.drawable.img_exclamination_mark)
}
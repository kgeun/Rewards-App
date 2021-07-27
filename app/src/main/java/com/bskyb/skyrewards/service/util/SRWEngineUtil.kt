package com.bskyb.skyrewards.service.util

import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.SRWApplication
import com.bskyb.skyrewards.data.enums.SRWChannelType
import com.bskyb.skyrewards.data.enums.SRWRewardType
import com.bskyb.skyrewards.data.enums.SRWServiceResult
import com.bskyb.skyrewards.data.model.SRWCustomerData
import com.bskyb.skyrewards.data.model.SRWRewardResult
import com.squareup.moshi.JsonAdapter

import com.squareup.moshi.Moshi

object SRWEngineUtil {

    fun parseToCustomerData(rawData: ByteArray): SRWCustomerData? {
        val customerDataJson = rawData.decodeToString()
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<SRWCustomerData> = moshi.adapter(SRWCustomerData::class.java)
        return jsonAdapter.fromJson(customerDataJson)
    }

    fun parseToRewardResult(rawData: ByteArray): SRWRewardResult? {
        val rewardResultJson = rawData.decodeToString()
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<SRWRewardResult> = moshi.adapter(SRWRewardResult::class.java)
        return jsonAdapter.fromJson(rewardResultJson)
    }

    fun makeByteArrayWithNegativeResultCode(resultCode: Int): ByteArray {
        val resultObject =
            when (resultCode) {
                SRWServiceResult.RESULTS_SERVICE_FAILURE.resultCode,
                SRWServiceResult.ELIGIBILITY_SERVICE_FAILURE.resultCode
                 -> SRWRewardResult(
                    resultCode = resultCode,
                    timestamp = System.currentTimeMillis(),
                    messageTitle = SRWApplication.instance.getString(R.string.server_failure_title),
                    messageDescription = SRWApplication.instance.getString(R.string.server_failure_description),
                    imageUrl = R.drawable.img_exclamination_mark
                )
                SRWServiceResult.CUSTOMER_INELIGIBLE.resultCode -> SRWRewardResult(
                    resultCode = resultCode,
                    timestamp = System.currentTimeMillis(),
                    messageTitle = SRWApplication.instance.getString(R.string.ineligible_title),
                    messageDescription = SRWApplication.instance.getString(R.string.ineligible_description),
                    imageUrl = R.drawable.img_sad_person
                )
                SRWServiceResult.INVALID_ACCOUNT_NUMBER_ERROR.resultCode -> SRWRewardResult(
                    resultCode = resultCode,
                    timestamp = System.currentTimeMillis(),
                    messageTitle = SRWApplication.instance.getString(R.string.invalid_number_title),
                    messageDescription = SRWApplication.instance.getString(R.string.invalid_number_description),
                    imageUrl = R.drawable.img_exclamination_mark
                )
                else -> SRWRewardResult()
            }
        return rewardResultToJson(resultObject).encodeToByteArray()
    }

    fun makeByteArrayWithEligibleCustomer(customerData: SRWCustomerData): ByteArray {
        var imageUrl: Int = 0
        var prize = ""
        when (customerData.channelId) {
            SRWChannelType.MOVIE.channelId -> {
                imageUrl = R.drawable.img_pirates_of_caribbean
                prize = SRWRewardType.PIRATES_OF_THE_CARIBBEAN_COLLECTION.title
            }
            SRWChannelType.SPORTS.channelId -> {
                imageUrl = R.drawable.img_champions_league
                prize = SRWRewardType.CHAMPIONS_LEAGUE_FINAL_TICKET.title
            }
            SRWChannelType.MUSIC.channelId -> {
                imageUrl = R.drawable.img_microphone
                prize = SRWRewardType.KARAOKE_PRO_MICROPHONE.title
            }
        }

        var messageDescription: String = SRWApplication.instance.getString(R.string.eligible_description_1) + " " +
                                             prize + " " + SRWApplication.instance.getString(R.string.eligible_description_2)

        val resultObject = SRWRewardResult(
            resultCode = SRWServiceResult.CUSTOMER_ELIGIBLE.resultCode,
            timestamp = System.currentTimeMillis(),
            messageTitle = SRWApplication.instance.getString(R.string.eligible_title),
            messageDescription = messageDescription,
            imageUrl = imageUrl
        )
        return rewardResultToJson(resultObject).encodeToByteArray()
    }

    private fun rewardResultToJson(result: SRWRewardResult): String {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<SRWRewardResult> =
            moshi.adapter(SRWRewardResult::class.java)

        return jsonAdapter.toJson(result)
    }
}
package com.bskyb.skyrewards.unit.servicelogic

import com.bskyb.skyrewards.constants.enums.SRWChannelType
import com.bskyb.skyrewards.data.model.network.SRWCustomerData

object CustomerCases {

    // Valid Account Number Cases
    val `Customer_who_subscribed_sports_channel_and_have_valid_account_number` = SRWCustomerData(
        "111111111111",
        SRWChannelType.SPORTS.channelId,
        System.currentTimeMillis()
    )

    val `Customer_who_subscribed_kids_channel_and_have_valid_account_number` = SRWCustomerData(
        "111111111111",
        SRWChannelType.KIDS.channelId,
        System.currentTimeMillis()
    )

    val `Customer_who_subscribed_music_channel_and_have_valid_account_number` = SRWCustomerData(
        "111111111111",
        SRWChannelType.MUSIC.channelId,
        System.currentTimeMillis()
    )

    val `Customer_who_subscribed_news_channel_and_have_valid_account_number` = SRWCustomerData(
        "111111111111",
        SRWChannelType.NEWS.channelId,
        System.currentTimeMillis()
    )

    val `Customer_who_subscribed_movie_channel_and_have_valid_account_number` = SRWCustomerData(
        "111111111111",
        SRWChannelType.MOVIE.channelId,
        System.currentTimeMillis()
    )


    // Invalid Account Number Cases
    val `Customer_who_subscribed_sports_channel_and_have_invalid_account_number` = SRWCustomerData(
        "211111111111",
        SRWChannelType.SPORTS.channelId,
        System.currentTimeMillis()
    )

    val `Customer_who_subscribed_kids_channel_and_have_invalid_account_number` = SRWCustomerData(
        "211111111111",
        SRWChannelType.KIDS.channelId,
        System.currentTimeMillis()
    )

    val `Customer_who_subscribed_music_channel_and_have_invalid_account_number` = SRWCustomerData(
        "211111111111",
        SRWChannelType.MUSIC.channelId,
        System.currentTimeMillis()
    )

    val `Customer_who_subscribed_news_channel_and_have_invalid_account_number` = SRWCustomerData(
        "211111111111",
        SRWChannelType.NEWS.channelId,
        System.currentTimeMillis()
    )

    val `Customer_who_subscribed_movie_channel_and_have_invalid_account_number` = SRWCustomerData(
        "211111111111",
        SRWChannelType.MOVIE.channelId,
        System.currentTimeMillis()
    )
}
package com.bskyb.skyrewards.utils

import com.bskyb.skyrewards.data.constant.SRWChannelType
import com.bskyb.skyrewards.data.constant.SRWEligibilityServiceOutput
import com.bskyb.skyrewards.data.model.SRWChannel

object SRWConstants {
    const val ANIM_DURATION: Long = 800
    val channelList = arrayOf<SRWChannel>(
        SRWChannel(
            SRWChannelType.SPORTS,
            SRWChannelType.SPORTS.channelId,
            SRWChannelType.SPORTS.title,
            SRWChannelType.SPORTS.imageId
        ),
        SRWChannel(
            SRWChannelType.KIDS,
            SRWChannelType.KIDS.channelId,
            SRWChannelType.KIDS.title,
            SRWChannelType.KIDS.imageId
        ),
        SRWChannel(
            SRWChannelType.MUSIC,
            SRWChannelType.MUSIC.channelId,
            SRWChannelType.MUSIC.title,
            SRWChannelType.MUSIC.imageId
        ),
        SRWChannel(
            SRWChannelType.NEWS,
            SRWChannelType.NEWS.channelId,
            SRWChannelType.NEWS.title,
            SRWChannelType.NEWS.imageId
        ),
        SRWChannel(
            SRWChannelType.MOVIE,
            SRWChannelType.MOVIE.channelId,
            SRWChannelType.MOVIE.title,
            SRWChannelType.MOVIE.imageId
        ))
}
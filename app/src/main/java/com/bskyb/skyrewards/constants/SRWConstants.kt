package com.bskyb.skyrewards.constants

import com.bskyb.skyrewards.constants.enums.SRWChannelType
import com.bskyb.skyrewards.constants.enums.SRWRewardType
import com.bskyb.skyrewards.data.model.SRWChannel

object SRWConstants {
    const val ANIM_DURATION: Long = 800

    val channelList = arrayOf(
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
        )
    )

    val channelsToRewards = hashMapOf(
        SRWChannelType.SPORTS.channelId to SRWRewardType.CHAMPIONS_LEAGUE_FINAL_TICKET,
        SRWChannelType.MUSIC.channelId to SRWRewardType.KARAOKE_PRO_MICROPHONE,
        SRWChannelType.MOVIE.channelId to SRWRewardType.PIRATES_OF_THE_CARIBBEAN_COLLECTION
    )
}
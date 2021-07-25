package com.bskyb.skyrewards.data.model

import com.bskyb.skyrewards.data.constant.SRWChannelType

data class SRWChannel(
    val channelType: SRWChannelType,
    val channelId: Int,
    val channelTitle: String,
    val drawableId: Int
)

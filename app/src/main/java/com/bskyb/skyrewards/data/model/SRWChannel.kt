package com.bskyb.skyrewards.data.model

import com.bskyb.skyrewards.constants.enums.SRWChannelType

data class SRWChannel(
    val channelType: SRWChannelType? = null,
    val channelId: Int? = 0,
    val channelTitle: String? = null,
    val drawableId: Int? = 0
)

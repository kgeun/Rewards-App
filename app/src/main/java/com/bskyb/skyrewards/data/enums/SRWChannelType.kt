package com.bskyb.skyrewards.data.enums

import com.bskyb.skyrewards.R

enum class SRWChannelType(val title: String, val channelId: Int, val imageId: Int) {
    SPORTS("Sky Sports", 1, R.drawable.sports),
    KIDS("Sky Kids", 2, R.drawable.kids),
    MUSIC("Sky Music", 3, R.drawable.music),
    NEWS("Sky News", 4, R.drawable.news),
    MOVIE("Sky Movie", 5, R.drawable.movies)
}
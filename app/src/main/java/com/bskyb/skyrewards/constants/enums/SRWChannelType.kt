package com.bskyb.skyrewards.constants.enums

import com.bskyb.skyrewards.R

enum class SRWChannelType(val title: String, val channelId: Int, val imageId: Int) {
    SPORTS("Sky Sports", 1, R.drawable.img_sports),
    KIDS("Sky Kids", 2, R.drawable.img_kids),
    MUSIC("Sky Music", 3, R.drawable.img_music),
    NEWS("Sky News", 4, R.drawable.img_news),
    MOVIE("Sky Movie", 5, R.drawable.img_movies)
}
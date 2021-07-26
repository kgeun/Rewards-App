package com.bskyb.skyrewards.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "rewardResult")
@Parcelize
@JsonClass(generateAdapter = true)
data class SRWRewardResult(
    @field:Json(name = "resultCode") val resultCode: Int? = null,
    @field:Json(name = "messageTitle") val messageTitle: String? = null,
    @field:Json(name = "messageDescription") val messageDescription: String? = null,
    @field:Json(name = "imageUrl") val imageUrl: Int? = 0,
    @PrimaryKey @field:Json(name = "timestamp") val timestamp: Long? = 0
) : Parcelable
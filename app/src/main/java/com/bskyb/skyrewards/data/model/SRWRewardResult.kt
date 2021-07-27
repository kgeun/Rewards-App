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
    @field:Json(name = "resultCode") var resultCode: Int? = null,
    @field:Json(name = "messageTitle") var messageTitle: String? = null,
    @field:Json(name = "messageDescription") var messageDescription: String? = null,
    @field:Json(name = "imageUrl") var imageUrl: Int? = 0,
    @PrimaryKey @field:Json(name = "timestamp") var timestamp: Long? = 0
) : Parcelable
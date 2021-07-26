package com.bskyb.skyrewards.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName = "customerData")
@Parcelize
@JsonClass(generateAdapter = true)
data class SRWCustomerData(
    @field:Json(name = "accountNumber") var accountNumber: String? = null,
    @field:Json(name = "channelId") var channelId: Int? = 0,
    @PrimaryKey @field:Json(name = "timeStamp") var timeStamp: Long? = 0L
) : Parcelable
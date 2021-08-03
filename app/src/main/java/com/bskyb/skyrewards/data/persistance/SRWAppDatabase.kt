package com.bskyb.skyrewards.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bskyb.skyrewards.data.model.network.SRWCustomerData
import com.bskyb.skyrewards.data.model.network.SRWRewardResult

@Database(
    entities = [SRWCustomerData::class, SRWRewardResult::class],
    version = 1,
    exportSchema = false
)
abstract class SRWAppDatabase : RoomDatabase() {
    abstract fun SRWMainDao(): SRWMainDao
}
package com.bskyb.skyrewards.data.persistance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bskyb.skyrewards.data.model.SRWCustomerData
import com.bskyb.skyrewards.data.model.SRWRewardResult

@Dao
interface SRWMainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRewardResult(rewardResult: SRWRewardResult)

    @Query("SELECT * FROM RewardResult ORDER BY timestamp DESC LIMIT 1")
    fun getRewardResult(): LiveData<SRWRewardResult>

    @Query("SELECT * FROM RewardResult ORDER BY timestamp DESC LIMIT 1")
    fun getRewardResultSync(): SRWRewardResult

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomerData(customerData: SRWCustomerData)

    @Query("SELECT * FROM customerData ORDER BY timestamp DESC LIMIT 1")
    fun getCustomerData(): LiveData<SRWCustomerData>

    @Query("SELECT * FROM customerData ORDER BY timestamp DESC LIMIT 1")
    fun getCustomerDataSync(): SRWCustomerData

    @Query("DELETE FROM RewardResult")
    fun truncateRewardResult()

    @Query("DELETE FROM customerData")
    fun truncateCustomerData()
}
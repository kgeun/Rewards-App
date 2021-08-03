package com.bskyb.skyrewards.data.persistance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bskyb.skyrewards.data.model.network.SRWCustomerData
import com.bskyb.skyrewards.data.model.network.SRWRewardResult

@Dao
interface SRWMainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRewardResult(rewardResult: SRWRewardResult)

    @Query("SELECT * FROM RewardResult ORDER BY timestamp DESC LIMIT 1")
    fun getRewardResult(): LiveData<SRWRewardResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomerData(customerData: SRWCustomerData)

    @Query("SELECT * FROM customerData ORDER BY timestamp DESC LIMIT 1")
    fun getCustomerData(): LiveData<SRWCustomerData>

    @Query("DELETE FROM RewardResult")
    fun truncateRewardResult()

    @Query("DELETE FROM customerData")
    fun truncateCustomerData()
}
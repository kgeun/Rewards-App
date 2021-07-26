package com.bskyb.skyrewards.data.repository

import com.bskyb.skyrewards.data.persistance.SRWMainDao
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SRWMainRepository @Inject constructor(
    private val mainDao: SRWMainDao
): SRWBaseRepository {
    fun getRewardsResult() = mainDao.getRewardResult()
    fun getCustomerData() = mainDao.getCustomerData()
}
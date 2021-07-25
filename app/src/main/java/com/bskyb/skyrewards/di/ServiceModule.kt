package com.bskyb.skyrewards.di

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.bskyb.skyrewards.service.RewardsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import javax.inject.Singleton

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {
    var mRewardBound = false
    var mEligibilityBound = false

    val NOT_SET_YET = 0
    val SERVICE_CONNECTED = 1
    val SERVICE_DISCONNECTED = -1

    @Provides
    @Singleton
    fun provideRewardsService(context: Context): RewardsService {
        lateinit var mRewardsService: RewardsService

        var flag = NOT_SET_YET
        context.bindService(Intent(context, RewardsService::class.java), object : ServiceConnection {
                override fun onServiceConnected(className: ComponentName, service: IBinder) {
                    flag = SERVICE_CONNECTED
                    val rewardsBinder = service as RewardsService.LocalBinder
                    mRewardsService = rewardsBinder.getService()
                    mRewardBound = true
                }

                override fun onServiceDisconnected(arg0: ComponentName) {
                    flag = SERVICE_DISCONNECTED
                    mRewardBound = false
                }
        }, Context.BIND_AUTO_CREATE)

        while (flag == NOT_SET_YET) {}

        return mRewardsService
    }
}
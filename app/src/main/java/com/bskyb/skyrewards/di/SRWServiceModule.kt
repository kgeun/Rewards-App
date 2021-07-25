package com.bskyb.skyrewards.di

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.bskyb.skyrewards.service.SRWRewardsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import javax.inject.Singleton

@Module
@InstallIn(ServiceComponent::class)
object SRWServiceModule {
    var mRewardBound = false
    var mEligibilityBound = false

    val NOT_SET_YET = 0
    val SERVICE_CONNECTED = 1
    val SERVICE_DISCONNECTED = -1

    @Provides
    @Singleton
    fun provideRewardsService(context: Context): SRWRewardsService {
        lateinit var mRewardsService: SRWRewardsService

        var flag = NOT_SET_YET
        context.bindService(Intent(context, SRWRewardsService::class.java), object : ServiceConnection {
                override fun onServiceConnected(className: ComponentName, service: IBinder) {
                    flag = SERVICE_CONNECTED
                    val rewardsBinder = service as SRWRewardsService.LocalBinder
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
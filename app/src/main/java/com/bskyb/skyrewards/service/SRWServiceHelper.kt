package com.bskyb.skyrewards.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import javax.inject.Singleton

@Module
@InstallIn(ServiceComponent::class)
object SRWServiceHelper {

    lateinit var rewardsService: RewardsService
    lateinit var eligibilityService: EligibilityService
    var isServiceBounded = false

    // Defines callbacks for service binding, passed to bindService()
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val rewardsBinder = service as RewardsService.LocalBinder
            rewardsService = rewardsBinder.getService()
            val eligibilityBinder = service as EligibilityService.LocalBinder
            eligibilityService = eligibilityBinder.getService()
            isServiceBounded = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            isServiceBounded = false
        }
    }

    @Provides
    @Singleton
    fun getRewardsServices(): RewardsService = rewardsService

    @Provides
    @Singleton
    fun getEligibilityServices(): EligibilityService = eligibilityService

    fun startServices(context: Context) {
        Intent(context, RewardsService::class.java).also { intent ->
            context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

        Intent(context, EligibilityService::class.java).also { intent ->
            context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    fun stopServices(context: Context) {
        context.unbindService(connection)
        isServiceBounded = false
    }
}
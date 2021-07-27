package com.bskyb.skyrewards.service.rewards_service

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.service.SRWService
import com.bskyb.skyrewards.service.SRWServiceHelper
import com.bskyb.skyrewards.service.eligibility_service.SRWEligibilityService
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SRWRewardsService: SRWService() {
    private val binder = LocalBinder()

    override fun serviceProcess(rawData: ByteArray): ByteArray {
        return SRWSkyRewardsEngine(rawData).engineProcess()
    }

    // Responsibility of the RewardsService to invoke and release the Eligibility
    override fun onBind(intent: Intent): IBinder {
        SRWEligibilityService.Helper.bindService(this)
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        SRWEligibilityService.Helper.stopService(this)
        return super.onUnbind(intent)
    }

    inner class LocalBinder : Binder() {
        fun getService(): SRWRewardsService = this@SRWRewardsService
    }

    object Helper: SRWServiceHelper {
        override lateinit var srwService: Service
        override lateinit var connection: ServiceConnection
        override var isBounded = false

        override fun bindService(context: Context) {
            // Defines callbacks for service binding, passed to bindService()
            connection = object: ServiceConnection {
                override fun onServiceConnected(className: ComponentName, service: IBinder) {
                    srwService = (service as LocalBinder).getService()
                    isBounded = true
                    Toast.makeText(context, R.string.rewards_service_online, Toast.LENGTH_SHORT).show()
                }

                override fun onServiceDisconnected(componentName: ComponentName) {
                    isBounded = false
                    Toast.makeText(context, R.string.rewards_service_offline, Toast.LENGTH_SHORT).show()
                }
            }

            context.bindService(Intent(context, SRWRewardsService::class.java), connection, Context.BIND_AUTO_CREATE)
        }

        override fun stopService(context: Context) {
            context.unbindService(connection)
            isBounded = false
        }
    }
}
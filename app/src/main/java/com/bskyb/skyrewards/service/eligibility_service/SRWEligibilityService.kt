package com.bskyb.skyrewards.service.eligibility_service

import android.annotation.SuppressLint
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import com.bskyb.skyrewards.service.SRWService
import com.bskyb.skyrewards.service.SRWServiceHelper
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SRWEligibilityService : SRWService() {
    private val binder = LocalBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun serviceProcess(rawData: ByteArray): ByteArray {
        return SRWSkyEligibilityEngine(rawData).engineProcess()
    }

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): SRWEligibilityService = this@SRWEligibilityService
    }

    @SuppressLint("StaticFieldLeak")
    object Helper : SRWServiceHelper {
        override lateinit var srwService: Service
        override lateinit var connection: ServiceConnection
        override var isBounded = false

        override fun bindService(context: Context) {
            // Defines callbacks for service binding, passed to bindService()
            connection = object : ServiceConnection {
                override fun onServiceConnected(className: ComponentName, service: IBinder) {
                    srwService = (service as LocalBinder).getService()
                    isBounded = true
                }

                override fun onServiceDisconnected(componentName: ComponentName) {
                    isBounded = false
                }
            }

            context.bindService(
                Intent(context, SRWEligibilityService::class.java),
                connection,
                Context.BIND_AUTO_CREATE
            )
        }

        override fun stopService(context: Context) {
            context.unbindService(connection)
            isBounded = false
        }
    }
}
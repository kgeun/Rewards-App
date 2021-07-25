package com.bskyb.skyrewards.service

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import com.bskyb.skyrewards.SRWApplication
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SRWEligibilityService: Service() {
    private val binder = LocalBinder()
    private val mGenerator = Random()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    /** method for clients  */
    val randomNumber: Int
        get() = mGenerator.nextInt(100)

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): SRWEligibilityService = this@SRWEligibilityService
    }

    object Helper {
        lateinit var eligibilityService: SRWEligibilityService
        private lateinit var connection: ServiceConnection
        var isBounded = false

        fun bindService(context: Context) {
            // Defines callbacks for service binding, passed to bindService()
            connection = object: ServiceConnection {
                override fun onServiceConnected(className: ComponentName, service: IBinder) {
                    eligibilityService = (service as SRWEligibilityService.LocalBinder).getService()
                    isBounded = true
                }

                override fun onServiceDisconnected(arg0: ComponentName) {
                    isBounded = false
                }
            }

            context.bindService(Intent(context, SRWEligibilityService::class.java), connection, Context.BIND_AUTO_CREATE)
        }

        fun stopService(context: Context) {
            context.unbindService(connection)
            isBounded = false
        }
    }
}
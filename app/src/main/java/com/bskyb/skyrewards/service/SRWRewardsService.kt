package com.bskyb.skyrewards.service

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import com.bskyb.skyrewards.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SRWRewardsService : Service() {
    private val binder = LocalBinder()
    private val mGenerator = Random()

    override fun onBind(intent: Intent): IBinder {
        // Responsibility of the RewardsService to invoke and release the Eligibility
        SRWEligibilityService.Helper.bindService(this)

        Toast.makeText(this, "eligibility : ${SRWEligibilityService.Helper.isBounded}", Toast.LENGTH_SHORT).show()

        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        SRWEligibilityService.Helper.stopService(this)

        Toast.makeText(this, "eligibility : ${SRWEligibilityService.Helper.isBounded}", Toast.LENGTH_SHORT).show()

        return super.onUnbind(intent)
    }

    /** method for clients  */
    val randomNumber: Int
        get() = mGenerator.nextInt(100)

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): SRWRewardsService = this@SRWRewardsService
    }

    object Helper {
        lateinit var rewardsService: SRWRewardsService
        private lateinit var connection: ServiceConnection
        var isBounded = false

        fun bindService(context: Context) {
            // Defines callbacks for service binding, passed to bindService()
            connection = object: ServiceConnection {
                override fun onServiceConnected(className: ComponentName, service: IBinder) {
                    rewardsService = (service as SRWRewardsService.LocalBinder).getService()
                    isBounded = true
                    Toast.makeText(context, context.getString(R.string.rewards_service_online), Toast.LENGTH_SHORT).show()
                }

                override fun onServiceDisconnected(arg0: ComponentName) {
                    isBounded = false
                    Toast.makeText(context, context.getString(R.string.rewards_service_offline), Toast.LENGTH_SHORT).show()
                }
            }

            context.bindService(Intent(context, SRWRewardsService::class.java), connection, Context.BIND_AUTO_CREATE)
        }

        fun stopService(context: Context) {
            context.unbindService(connection)
            isBounded = false
        }
    }
}
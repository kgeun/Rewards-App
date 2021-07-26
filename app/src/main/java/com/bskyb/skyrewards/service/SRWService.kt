package com.bskyb.skyrewards.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

abstract class SRWService : Service() {

    abstract override fun onBind(intent: Intent): IBinder?
    abstract fun engineProcess(encodedAccountNumber: String, myChannel: Int = 0): Int

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }
}
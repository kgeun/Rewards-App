package com.bskyb.skyrewards.service

import android.app.Service
import android.content.Context
import android.content.ServiceConnection

interface SRWServiceHelper {
    var srwService: Service
    var connection: ServiceConnection
    var isBounded: Boolean

    fun bindService(context: Context)
    fun stopService(context: Context)
}
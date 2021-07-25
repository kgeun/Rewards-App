package com.bskyb.skyrewards.service

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import java.util.*

interface SRWServiceHelper {
    var srwService: Service
    var connection: ServiceConnection
    var isBounded: Boolean

    fun bindService(context: Context)
    fun stopService(context: Context)
}
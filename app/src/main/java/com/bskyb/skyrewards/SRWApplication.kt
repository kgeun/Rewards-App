package com.bskyb.skyrewards

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bskyb.skyrewards.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SRWApplication : Application() {
    companion object {
        lateinit var instance: SRWApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this@SRWApplication
    }
}
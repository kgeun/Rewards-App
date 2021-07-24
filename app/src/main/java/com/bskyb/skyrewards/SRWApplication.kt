package com.bskyb.skyrewards

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bskyb.skyrewards.R

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
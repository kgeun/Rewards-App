package com.bskyb.skyrewards.view

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.data.model.SRWRewardResult
import com.bskyb.skyrewards.databinding.ActivityMainBinding
import com.bskyb.skyrewards.service.rewards_service.SRWRewardsService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class SRWMainActivity: AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var statusbarHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setTransparentStatusBar()
        sendAnalytics()
    }

    override fun onStart() {
        super.onStart()
        initStatusBarPadding()
        SRWRewardsService.Helper.bindService(this)
    }

    override fun onStop() {
        super.onStop()
        SRWRewardsService.Helper.stopService(this)
    }

    private fun setTransparentStatusBar() {
        // Basic status bar Height
        statusbarHeight = resources.getDimensionPixelSize(R.dimen.statusbar_height)
        val decorView: View = window.decorView

        // Setting transparent status bar
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        } else {
            val wic = WindowInsetsControllerCompat(window, decorView)
            wic.isAppearanceLightStatusBars = true
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    private fun initStatusBarPadding() {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusbarHeight = resources.getDimensionPixelSize(resourceId)
        }

        val statusbarView : View?  = findViewById<View>(R.id.statusbarView)
        statusbarView?.visibility = View.VISIBLE
        statusbarView?.layoutParams?.height = statusbarHeight
    }

    private fun sendAnalytics() {
        SRWAnalytics.sendView(javaClass.simpleName)
    }
}
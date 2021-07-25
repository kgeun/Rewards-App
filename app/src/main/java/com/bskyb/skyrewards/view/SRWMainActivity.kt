package com.bskyb.skyrewards.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.databinding.ActivityMainBinding

open class SRWMainActivity: AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var statusbarHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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

        sendAnalytics()
    }

    override fun onStart() {
        super.onStart()
        initStatusBarPadding()
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
package com.bskyb.skyrewards.view.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.bskyb.skyrewards.R

open class SRWBaseActivity : AppCompatActivity() {
    var statusbarHeight = 0

    fun Int.dp2px(context: Context) :Int {
        val scale = context.resources.displayMetrics.density
        return (this * scale).toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        statusbarHeight = resources.getDimensionPixelSize(R.dimen.statusbar_height)
        val decorView: View = window.decorView

        // Set custom status bar
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        } else {
            val wic = WindowInsetsControllerCompat(window, decorView)
            wic.isAppearanceLightStatusBars = true
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
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
}
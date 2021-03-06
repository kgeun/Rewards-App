package com.bskyb.skyrewards.analytics

import android.os.Bundle
import com.bskyb.skyrewards.SRWApplication
import com.google.firebase.analytics.FirebaseAnalytics

object SRWAnalytics {
    private val firebaseAnalytics: FirebaseAnalytics =
        FirebaseAnalytics.getInstance(SRWApplication.instance.applicationContext)

    fun sendView(categoryCode: String) {
        try {
            val bundle = Bundle()
            bundle.putString("CategoryCode", categoryCode)
            firebaseAnalytics.logEvent("VIEW", bundle)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun sendClick(categoryCode: String) {
        try {
            val bundle = Bundle()
            bundle.putString("CategoryCode", categoryCode)
            firebaseAnalytics.logEvent("CLICK", bundle)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun sendEvent(tag: String, mapData: Map<String, String>) {
        try {
            val bundle = Bundle()
            for (key in mapData.keys) {
                bundle.putString(key, mapData[key])
            }
            firebaseAnalytics.logEvent(tag, bundle)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
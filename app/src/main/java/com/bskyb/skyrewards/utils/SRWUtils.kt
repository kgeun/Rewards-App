package com.bskyb.skyrewards.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.common.hash.Hashing
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

object SRWUtils {

    fun hideKeyboard(activity: Activity?) {
        val imm: InputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity?.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun sha256(input :String): String = Hashing.sha256()
        .hashString(input, StandardCharsets.UTF_8)
        .toString()
}
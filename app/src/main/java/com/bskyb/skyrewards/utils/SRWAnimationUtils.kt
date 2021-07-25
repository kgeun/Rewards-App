package com.bskyb.skyrewards.utils

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils

object SRWAnimationUtils {
    fun getAnim400(animation: Int, context: Context): Animation {
        val ani400: Animation = AnimationUtils.loadAnimation(
            context,
            animation
        )
        ani400.duration = 400
        ani400.fillAfter = true

        return ani400
    }

    fun getAnim500(animation: Int, context: Context): Animation {
        val ani500: Animation = AnimationUtils.loadAnimation(
            context,
            animation
        )
        ani500.duration = 500
        ani500.fillAfter = true

        return ani500
    }

    fun getAnimCustomSpeed(duration: Long, animation: Int, context: Context): Animation {
        val aniC: Animation = AnimationUtils.loadAnimation(
            context,
            animation
        )
        aniC.duration = duration
        aniC.fillAfter = true

        return aniC
    }

    fun getAnimStandard(animation: Int, context: Context): Animation {
        val aniS: Animation = AnimationUtils.loadAnimation(
            context,
            animation
        )
        aniS.duration = SRWConstants.ANIM_DURATION
        aniS.isFillEnabled = true
        aniS.fillAfter = true

        return aniS
    }
}
package com.bskyb.skyrewards.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

object ViewBinding {
    @JvmStatic
    @BindingAdapter("setDrawableId")
    fun loadImageDrawable(view: ImageView, drawableId: Int) {
        Glide.with(view.context)
            .load(drawableId)
            .into(view)
    }
}
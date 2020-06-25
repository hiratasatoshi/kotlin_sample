package com.github.hiratasatoshi.sample.aaccontributors.presentation.view

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

object ImageViewExt {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {
        Picasso.get().load(url).into(this)
    }

}
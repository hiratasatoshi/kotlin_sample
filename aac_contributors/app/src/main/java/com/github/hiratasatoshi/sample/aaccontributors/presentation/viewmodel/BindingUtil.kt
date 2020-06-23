package com.github.hiratasatoshi.sample.aaccontributors.presentation.viewmodel

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingUtil {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {
        Log.d("aaa", "bbb $url")
        Picasso.get().load(url).into(this)
    }
}
package com.github.hiratasatoshi.sample.aaccontributors.presentation.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageViewExt {
    // 引数で指定されたURLの画像をImageViewに表示する
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {
        url ?: return
        Picasso.get().load(url).into(this)
    }
}
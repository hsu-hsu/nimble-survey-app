package com.nimble.android.features.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter(
    value = ["backgroundImage", "imageInHd"],
    requireAll = false)
fun bindBackgroundImage(imageView: ImageView, imageUrl: String, imageInHd: Boolean = true) {
    val newImageUrl = if(imageInHd ) imageUrl+ "l" else imageUrl
    Glide.with(imageView.context)
        .load(newImageUrl)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)

}
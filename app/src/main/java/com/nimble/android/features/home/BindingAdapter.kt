package com.nimble.android.features.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nimble.android.api.response.survey.Survey

@BindingAdapter("backgroundImage")
fun bindBackgroundImage(imageView: ImageView, imageUrl: String) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)

}
package com.nimble.android.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.nimble.android.api.response.survey.Survey

@BindingAdapter("backgroundImage")
fun bindBackgroundImage(imageView: ImageView, imageUrl: String) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)

}
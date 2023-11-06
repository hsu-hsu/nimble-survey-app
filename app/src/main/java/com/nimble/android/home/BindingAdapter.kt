package com.nimble.android.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.nimble.android.api.response.survey.Survey

@BindingAdapter("backgroundImage")
fun bindImagePictureOfDay(imageView: ImageView, data: Survey) {

    data?.let {

            Glide.with(imageView.context)
                .load(it.attributes.coverImageUrl)
                .into(imageView)

    }
}
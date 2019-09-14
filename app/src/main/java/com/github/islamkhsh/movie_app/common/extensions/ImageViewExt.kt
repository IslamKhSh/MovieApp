package com.github.islamkhsh.movie_app.common.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.Nullable
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.github.islamkhsh.movie_app.BuildConfig
import com.github.islamkhsh.movie_app.R
import com.github.islamkhsh.movie_app.common.Constants
import com.github.islamkhsh.movie_app.common.GlideApp


fun ImageView.loadImg(
    @Nullable photo: String,
    @DrawableRes placeHolder: Int = R.drawable.ic_vertical_place_holder,
    @DrawableRes error: Int = placeHolder
) {

    val myOptions = RequestOptions()
        .override(this.width, this.height)
        .fitCenter()

    GlideApp.with(context!!)
        .load("${BuildConfig.IMG_BASE_URL}w185$photo")
        .placeholder(placeHolder)
        .error(error)
        .fallback(error)
        .apply(myOptions)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun ImageView.loadImgBanner(
    @Nullable photo: String,
    @DrawableRes placeHolder: Int = R.drawable.ic_place_holder,
    @DrawableRes error: Int = placeHolder
) {

    val myOptions = RequestOptions()
        .override(this.width, this.height)
        .fitCenter()

    GlideApp.with(context!!)
        .load("${BuildConfig.IMG_BASE_URL}w500$photo")
        .placeholder(placeHolder)
        .error(error)
        .fallback(error)
        .apply(myOptions)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}


fun ImageView.loadThumbnail(
    @Nullable video: String,
    @DrawableRes placeHolder: Int = R.drawable.ic_place_holder,
    @DrawableRes error: Int = placeHolder
) {

    val myOptions = RequestOptions()
        .override(this.width, this.height)
        .fitCenter()

    GlideApp.with(context!!)
        .load(Constants.YOUTUBE_THUMBNAIL_URL.replace("VIDEO_ID", video))
        .placeholder(placeHolder)
        .error(error)
        .fallback(error)
        .apply(myOptions)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

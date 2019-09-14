package com.github.islamkhsh.movie_app.ui

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.github.islamkhsh.movie_app.R
import com.github.islamkhsh.movie_app.common.extensions.loadImg
import com.github.islamkhsh.movie_app.common.extensions.loadImgBanner
import com.github.islamkhsh.movie_app.common.extensions.loadThumbnail
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter(value = ["imageUrl", "imageBannerUrl"], requireAll = false)
fun setImageUrl(imageView: ImageView, imageUrl: String?, imageBannerUrl: String?) {

    imageUrl?.run { imageView.loadImg(this) }
    imageBannerUrl?.run { imageView.loadImgBanner(this) }
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter(value = ["year", "rate"], requireAll = false)
fun setText(textView: TextView, date: String?, rate: Float?) {

    date?.run {
        val serverFormat = SimpleDateFormat("yyyy-MM-dd", Locale("en"))
        val displayFormat = SimpleDateFormat("yyyy")

        val date = serverFormat.parse(this)
        textView.text = displayFormat.format(date)
    }

    rate?.run { textView.text = NumberFormat.getInstance().format(this) }
}

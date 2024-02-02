package com.emirozturk.country.util
import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emirozturk.country.R

fun ImageView.getImageFromUrl(url: String?) {
    val options = RequestOptions()
        .placeholder(getProgressBarAsPlaceholder(this.context))
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun getProgressBarAsPlaceholder(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl") //Xml'de çalışır hale geldi
fun downloadUrl(imageView: ImageView, url: String?) {
    imageView.getImageFromUrl(url)
}
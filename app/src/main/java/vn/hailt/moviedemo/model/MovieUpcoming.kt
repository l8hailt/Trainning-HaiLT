package vn.hailt.moviedemo.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import vn.hailt.moviedemo.api.W500_IMAGE_BASE_URL

data class MovieUpcoming(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("backdrop_path")
    val backdropPath: String
) {

    companion object {
        @JvmStatic
        @BindingAdapter("android:backdrop")
        fun loadImage(view: ImageView, url: String) {
            Glide.with(view.context)
                .load(W500_IMAGE_BASE_URL + url)
                .into(view)
        }
    }
}
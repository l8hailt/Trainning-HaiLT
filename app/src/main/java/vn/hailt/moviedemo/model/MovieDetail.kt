package vn.hailt.moviedemo.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import vn.hailt.moviedemo.api.W500_IMAGE_BASE_URL

data class MovieDetail(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("poster_path")
    val posterPath: String
) {
    companion object {
        @JvmStatic
        @BindingAdapter("android:poster")
        fun loadImage(view: ImageView, url: String?) {
            Glide.with(view.context)
                .load(W500_IMAGE_BASE_URL + url)
                .into(view)
        }
    }
}
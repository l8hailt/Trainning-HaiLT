package vn.hailt.moviedemo.model

import com.google.gson.annotations.SerializedName

data class MovieSimilar(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overView: String,
    @SerializedName("title")
    val title: String
)
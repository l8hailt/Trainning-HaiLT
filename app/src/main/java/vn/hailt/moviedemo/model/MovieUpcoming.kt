package vn.hailt.moviedemo.model

import com.google.gson.annotations.SerializedName

data class MovieUpcoming(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String
)
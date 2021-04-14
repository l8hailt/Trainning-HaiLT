package vn.hailt.moviedemo.model

import com.google.gson.annotations.SerializedName

data class ResponseMovieUpcoming(
    @SerializedName("results")
    val results: List<MovieUpcoming>
)
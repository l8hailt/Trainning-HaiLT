package vn.hailt.moviedemo.model

import com.google.gson.annotations.SerializedName

data class GetMovieVideoResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieSimilar>
)
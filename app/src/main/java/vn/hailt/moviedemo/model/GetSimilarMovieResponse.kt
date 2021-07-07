package vn.hailt.moviedemo.model

import com.google.gson.annotations.SerializedName

data class GetSimilarMovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<MovieVideo>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
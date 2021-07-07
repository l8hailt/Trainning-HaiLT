package vn.hailt.moviedemo.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import vn.hailt.moviedemo.model.GetMovieVideoResponse
import vn.hailt.moviedemo.model.GetSimilarMovieResponse
import vn.hailt.moviedemo.model.MovieDetail
import vn.hailt.moviedemo.model.ResponseMovieUpcoming

interface ApiService {

    @GET("movie/upcoming")
    suspend fun getMovieUpcoming(@Query("api_key") apiKey: String): ResponseMovieUpcoming

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): MovieDetail

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): GetMovieVideoResponse

    @GET("/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): GetSimilarMovieResponse


}
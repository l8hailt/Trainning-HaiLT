package vn.hailt.moviedemo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiBuilder {

    companion object {
        private val apiService: ApiService? = null

        fun getInstance(): ApiService {
            if (apiService != null) {
                return apiService
            }
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
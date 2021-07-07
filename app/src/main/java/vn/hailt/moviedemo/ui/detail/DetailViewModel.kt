package vn.hailt.moviedemo.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import vn.hailt.moviedemo.MainActivity
import vn.hailt.moviedemo.api.ApiBuilder

class DetailViewModel : ViewModel() {

    fun getMovieDetail(movieId: String) =
        liveData(Dispatchers.IO) {
            try {
                emit(ApiBuilder.getInstance().getMovieDetail(movieId, MainActivity.API_KEY))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    fun getMovieVideos(movieId: String) = liveData(Dispatchers.IO) {
        try {
            emit(ApiBuilder.getInstance().getMovieVideos(movieId, MainActivity.API_KEY))

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
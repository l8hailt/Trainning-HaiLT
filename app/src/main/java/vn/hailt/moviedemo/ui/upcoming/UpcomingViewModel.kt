package vn.hailt.moviedemo.ui.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import vn.hailt.moviedemo.MainActivity
import vn.hailt.moviedemo.api.ApiBuilder

class UpcomingViewModel : ViewModel() {

    fun getMovieUpcoming() =
        liveData(Dispatchers.IO) {
            try {
                emit(ApiBuilder.getInstance().getMovieUpcoming(MainActivity.API_KEY))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
}
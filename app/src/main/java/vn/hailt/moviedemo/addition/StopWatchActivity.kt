package vn.hailt.moviedemo.addition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import vn.hailt.moviedemo.R
import vn.hailt.moviedemo.databinding.ActivityBrigdeBinding
import vn.hailt.moviedemo.databinding.ActivityStopWatchBinding

class StopWatchActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityStopWatchBinding>(
                this,
                R.layout.activity_stop_watch
            )

        handler = Handler(Looper.getMainLooper())


    }
}
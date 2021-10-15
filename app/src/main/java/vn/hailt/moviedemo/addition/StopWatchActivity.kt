package vn.hailt.moviedemo.addition

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import vn.hailt.moviedemo.R
import vn.hailt.moviedemo.databinding.ActivityStopWatchBinding
import java.util.*
import java.util.concurrent.TimeUnit


class StopWatchActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private var isRunning: Boolean = true
    private val interval = 10L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityStopWatchBinding>(
                this,
                R.layout.activity_stop_watch
            )

        handler = Handler(Looper.getMainLooper())

        var time = 0L

        var thread: Thread? = null

        binding.btnStart.setOnClickListener {
            isRunning = true
            if (thread == null) {
                thread = Thread {
                    while (isRunning) {
                        try {
                            Thread.sleep(interval)
                            time += interval
                            handler.post {
//                                binding.tvTime.text = (time.toDouble() / 1000).toString()
//                                binding.tvTime.text =
//                                    "${convert(time)}:${(time.toDouble() / 1000)}"
                                binding.tvTime.text = convert(time)
                            }
                        } catch (e: Exception) {
                            Log.e("TAG", "onCreate: ${e.message}")
                        }
                    }
                }
                thread?.start()
            }
        }

        binding.btnPause.setOnClickListener {
            isRunning = false
            thread?.interrupt()
            thread = null
        }

        binding.btnStop.setOnClickListener {
            time = 0L
            binding.tvTime.text = convert(time)
            isRunning = false
            thread?.interrupt()
            thread = null
        }

    }

    private fun convert(millis: Long): String {
        return String.format(
            Locale.getDefault(),
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1),
            TimeUnit.MILLISECONDS.toMillis(millis) % TimeUnit.SECONDS.toMillis(1) / 10
        )
    }

}
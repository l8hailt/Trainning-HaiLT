package vn.hailt.moviedemo.addition

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.webkit.URLUtil
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import vn.hailt.moviedemo.R
import vn.hailt.moviedemo.databinding.ActivityDownloadBinding
import java.net.MalformedURLException
import java.net.URL


class DownloadActivity : AppCompatActivity() {

    private lateinit var progressReceiver: BroadcastReceiver
    val progressObs: ObservableField<Int> = ObservableField()
    val isDownloading: ObservableField<Boolean> = ObservableField()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDownloadBinding>(
            this,
            R.layout.activity_download
        )

        binding.act = this

        binding.btnDownload.setOnClickListener {
            isDownloading.set(true)
            downloadFile(binding.edtUrl.text.toString().trim())
        }

        progressReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.let {
                    val progress = it.getIntExtra("PROGRESS", 0)
                    Log.e("TAG", "onReceive: $progress")
                    progressObs.set(progress)
                    isDownloading.set(progress != 100)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this).registerReceiver(
            progressReceiver,
            IntentFilter("ACTION_UPDATE_PROGRESS")
        )
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(progressReceiver)
    }

    private fun downloadFile(inputUrl: String) {
        var url: URL? = null
        var file = ""
        try {
            //url = new URL ("http://www.cs.uwyo.edu/~seker/courses/4730/example/pic.jpg");

            //for a bigger file: http://www.nasa.gov/images/content/206402main_jsc2007e113280_hires.jpg
            //for reason I don't understand, I can't download it from nasa on the phone... weird...
            // url = new URL("http://www.nasa.gov/images/content/206402main_jsc2007e113280_hires.jpg");
//            url =
//                URL("http://www.cs.uwyo.edu/~seker/courses/4730/example/206402main_jsc2007e113280_hires.jpg")
            url = URL(inputUrl)

            file = URLUtil.guessFileName(url.file, null, null)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        Intent(this, FileDownloadService::class.java).also { downloadIntent ->
            downloadIntent.putExtra("FILE", file)
            downloadIntent.putExtra("URL", url)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                startForegroundService(downloadIntent)
            } else {
                startService(downloadIntent)
            }
        }
    }


}
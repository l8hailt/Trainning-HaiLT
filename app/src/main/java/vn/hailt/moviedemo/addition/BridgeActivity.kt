package vn.hailt.moviedemo.addition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import vn.hailt.moviedemo.MainActivity
import vn.hailt.moviedemo.R
import vn.hailt.moviedemo.databinding.ActivityBrigdeBinding

class BridgeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityBrigdeBinding>(this, R.layout.activity_brigde)

        binding.btn1.setOnClickListener {
            Intent(this@BridgeActivity, MainActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btn2.setOnClickListener {
            Intent(this@BridgeActivity, StopWatchActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btn3.setOnClickListener {
            Intent(this@BridgeActivity, DownloadActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}
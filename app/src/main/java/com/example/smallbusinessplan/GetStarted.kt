package com.example.smallbusinessplan

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.ads.Classes.Strategies.bannerAds
import com.example.ads.Classes.Strategies.interstitialAds
import com.example.smallbusinessplan.Activities.Main
import com.example.smallbusinessplan.Extensions.gone
import com.example.smallbusinessplan.Extensions.visible
import com.example.smallbusinessplan.Utils.NetworkUtils
import com.example.smallbusinessplan.Utils.NetworkUtils.isNetworkAvailable
import java.util.concurrent.TimeUnit


class GetStarted : AppCompatActivity() {
    private lateinit var button:Button
    private lateinit var progressBar:ProgressBar
    private lateinit var bannerAd:FrameLayout
    private lateinit var countDownTimer:CountDownTimer

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        supportActionBar?.hide()
        button=findViewById(R.id.button)
        progressBar=findViewById(R.id.progressBar)
        bannerAd=findViewById(R.id.bannerad_getStarted)
        button.setOnClickListener {
            val intent = Intent(this , Main::class.java)
            startActivity(intent)
        }
        button.gone()
        progressBar.visible()
        countDownTimer=object : CountDownTimer(TimeUnit.SECONDS.toMillis(5), TimeUnit.SECONDS.toMillis(1)){
            override fun onFinish() {
                button.visible()
                progressBar.gone()
                if (isNetworkAvailable(this@GetStarted)) {
                    bannerAd.visible()
                    bannerAds(this@GetStarted, bannerAd, "FULL_BANNER")
                } else {
                    bannerAd.gone()
                }
            }

            override fun onTick(p0: Long) {
                Log.d("Tag","Counter:$p0")
            }
        }
        countDownTimer.start()
    }
}
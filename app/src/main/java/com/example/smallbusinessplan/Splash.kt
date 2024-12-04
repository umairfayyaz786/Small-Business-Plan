package com.example.smallbusinessplan

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import java.util.concurrent.TimeUnit

class Splash : AppCompatActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash)
        window?.decorView?.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_VISIBLE or View.SYSTEM_UI_FLAG_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT
        Handler(Looper.myLooper()!!).postDelayed({
            startNewActivity(GetStarted::class.java, true, null)

        }, TimeUnit.SECONDS.toMillis(5))

    }

    private fun startNewActivity(className: Class<*>, closed: Boolean, bundle: Bundle?) {
        val intent = Intent(this, className)
        bundle?.let {
            intent.putExtras(it)
        }
        startActivity(intent)
        if (closed) {
            finish()
        }

    }
}
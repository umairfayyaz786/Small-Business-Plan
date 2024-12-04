package com.example.ads.Classes.Utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    fun networkState(context: Context): Boolean {
        val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connection.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
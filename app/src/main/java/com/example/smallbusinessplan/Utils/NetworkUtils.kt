package com.example.smallbusinessplan.Utils



import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager


object NetworkUtils {
    @SuppressLint("MissingPermission")
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return networkInfo!=null && networkInfo.isConnectedOrConnecting
    }
}
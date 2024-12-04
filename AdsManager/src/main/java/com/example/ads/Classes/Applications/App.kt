package com.example.ads.Classes.Applications

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.ads.Classes.Models.SharedModel
import com.example.ads.Classes.Strategies.initCheckAds
import com.example.ads.Classes.Utils.AppConstants
import com.example.ads.Classes.Utils.NetworkUtil
import com.example.ads.Classes.Utils.SharedPreference
import com.example.ads.Classes.getRemoteValue
import com.example.ads.Classes.initAppLovinSDK
import com.example.ads.Classes.initSDK
import com.example.ads.Classes.remoteConfiguration
import com.example.ads.R
import com.google.android.gms.ads.*
import com.google.android.gms.ads.appopen.AppOpenAd
import java.util.*

class App : Application(), Application.ActivityLifecycleCallbacks, LifecycleObserver {
    @SuppressLint("StaticFieldLeak")
    companion object {
        @SuppressLint("StaticFieldLeak")
        var sharedPreference: SharedPreference? = null
        private lateinit var appOpenAdManager: AppOpenAdManager
        fun showAdIfAvailable(
            activity: Activity,
            onShowAdCompleteListener: OnShowAdCompleteListener
        ) {
            appOpenAdManager.showAdIfAvailable(activity, onShowAdCompleteListener)
        }
    }

    private var currentActivity: Activity? = null
    private val LOG_TAG = "Tag"
    private var appOpenAd: AppOpenAd? = null
    private var isLoadingAd = false
    override fun onCreate() {
        super.onCreate()
        try {
            sharedPreference = SharedPreference(context = this)
            if (NetworkUtil.networkState(this)) {
                remoteConfiguration()
                getRemoteValue(this)
                initSDK(this)
                initAppLovinSDK(this)
                initCheckAds()
                registerActivityLifecycleCallbacks(this)
                ProcessLifecycleOwner.get().lifecycle.addObserver(this)
                appOpenAdManager = AppOpenAdManager()
            } else {
                Log.d("Tag", "onCreate: Network Required!")
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        appOpenAdManager.showAdIfAvailable(currentActivity!!)
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
        if (!appOpenAdManager.isShowingAd) {
            currentActivity = activity
        }
    }


    override fun onActivityResumed(p0: Activity) {
        currentActivity = null
        appOpenAd=null
        isLoadingAd=false
        Log.d(LOG_TAG, "onActivityResumed Resume")
    }

    override fun onActivityPaused(p0: Activity) {
        currentActivity = null
        appOpenAd=null
        isLoadingAd=false
        Log.d(LOG_TAG, "onActivityPaused Pause")
    }

    override fun onActivityStopped(p0: Activity) {
        Log.d(LOG_TAG, "onActivityStopped Stopped")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        Log.d(LOG_TAG, "onActivitySaveInstanceState Save ")
    }

    override fun onActivityDestroyed(p0: Activity) {
        currentActivity = null
        appOpenAd=null
        isLoadingAd=false
        Log.d(LOG_TAG, "onActivityDestroyed Destroyed")
    }


    interface OnShowAdCompleteListener {
        fun onShowAdComplete()
    }

    inner class AppOpenAdManager {
        private val appOpenID =
            if (sharedPreference != null && !(sharedPreference!!.getString(
                    AppConstants.ADMOB_APP_OPEN_ADS_ID
                )
                    .isNullOrBlank() || sharedPreference!!.getString(AppConstants.ADMOB_APP_OPEN_ADS_ID)
                    .isNullOrEmpty())
            ) {
                sharedPreference!!.getString(AppConstants.ADMOB_APP_OPEN_ADS_ID)!!
            } else {
                AppConstants.ADMOB_APP_OPEN_ADS_ID_DEFAULT
            }
        var isShowingAd = false
        private var loadTime: Long = 0
        fun loadAd(context: Context) {
            if (isLoadingAd || isAdAvailable()) {
                return
            }

            isLoadingAd = true
            val request = AdRequest.Builder().build()
            AppOpenAd.load(
                context,
                appOpenID,
                request,
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                object : AppOpenAd.AppOpenAdLoadCallback() {
                    override fun onAdLoaded(ad: AppOpenAd) {
                        appOpenAd = ad
                        isLoadingAd = false
                        loadTime = Date().time
                        Log.d(LOG_TAG, "onAdLoaded.")

                    }

                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        isLoadingAd = false
                        Log.d(LOG_TAG, "onAdFailedToLoad: " + loadAdError.message)
                    }
                }
            )
        }

        private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
            val dateDifference: Long = Date().time - loadTime
            val numMilliSecondsPerHour: Long = 3600000
            return dateDifference < numMilliSecondsPerHour * numHours
        }

        private fun isAdAvailable(): Boolean {
            return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
        }

        fun showAdIfAvailable(activity: Activity) {
            showAdIfAvailable(
                activity,
                object : OnShowAdCompleteListener {
                    override fun onShowAdComplete() {
                        // Empty because the user will go back to the activity that shows the ad.
                        Log.d("Tag", "onShowAdComplete: ")
                    }
                }
            )
        }

        fun showAdIfAvailable(
            activity: Activity,
            onShowAdCompleteListener: OnShowAdCompleteListener
        ) {
            if (isShowingAd) {
                Log.d(LOG_TAG, "The app open ad is already showing.")
                return
            }
            if (!isAdAvailable()) {
                Log.d(LOG_TAG, "The app open ad is not ready yet.")
                onShowAdCompleteListener.onShowAdComplete()
                loadAd(activity)
                return
            }

            Log.d(LOG_TAG, "Will show ad.")

            appOpenAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    // Set the reference to null so isAdAvailable() returns false.
                    appOpenAd = null
                    isShowingAd = false
                    Log.d(LOG_TAG, "onAdDismissedFullScreenContent.")
                    onShowAdCompleteListener.onShowAdComplete()
                    loadAd(activity)
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    appOpenAd = null
                    isShowingAd = false
                    Log.d(LOG_TAG, "onAdFailedToShowFullScreenContent: " + adError.message)
                    onShowAdCompleteListener.onShowAdComplete()
                    loadAd(activity)
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(LOG_TAG, "onAdShowedFullScreenContent.")
                    Toast.makeText(activity, "onAdShowedFullScreenContent", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            isShowingAd = true
            appOpenAd!!.show(activity)
        }
    }
}
package com.example.ads.Classes.Strategies

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.ads.Classes.*
import com.example.ads.Classes.Applications.App
import com.example.ads.Classes.Utils.AppConstants
import java.util.*
import java.util.concurrent.TimeUnit


private const val TAG = "Tag"

private var adsVisible: Int? = null
private var adsStrategy: Int? = null
private lateinit var countDownTimer: CountDownTimer

private fun showMessage(msg: String, context: Context) {
//    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    Log.d(TAG, "$msg ")
}

// init Key
fun Application.initCheckAds() {
    adsVisible =
        if (App.sharedPreference != null && App.sharedPreference!!.getLong(AppConstants.ADS_VISIBLE_KEY) > -1) {
            App.sharedPreference!!.getLong(AppConstants.ADS_VISIBLE_KEY)
        } else {
            AppConstants.ADS_VISIBLE
        }
    adsStrategy =
        if (App.sharedPreference != null && App.sharedPreference!!.getLong(AppConstants.ADS_STRATEGY_KEY) > -1) {
            App.sharedPreference!!.getLong(AppConstants.ADS_STRATEGY_KEY)
        } else {
            AppConstants.ADS_STRATEGY
        }
}

// Banner Ads
fun AppCompatActivity.bannerAds(
    activity: Activity,
    containerView: FrameLayout,
    bannerSize: String? = null,
    timer: Long = 0
) {
    initBanner(
        activity = activity,
        containerView = containerView,
        bannerSize = bannerSize
    )
    try {
        if (adsVisible != null && adsVisible == 0) {
            if (timer >= 0) {
                countDownTimer = object :
                    CountDownTimer(TimeUnit.SECONDS.toMillis(timer), TimeUnit.SECONDS.toMillis(1)) {
                    override fun onFinish() {
                        adsStrategy?.let {
                            when (it) {
                                AppConstants.ADMOB_ADS -> {
                                    Log.d(TAG, "bannerAds: admob Ads")
                                    strategyBannerAds()
                                }
                                AppConstants.APPLOVIN_ADS -> {
                                    Log.d(TAG, "bannerAds: applovin Ads")
                                    strategyBannerMaxAds(
                                        activity = activity,
                                        containerView = containerView,
                                        bannerSize = bannerSize
                                    )
                                }
                                else -> {
                                    if (nativeAdView != null) {
                                        Log.d(TAG, "bannerAds: applovin Ads: $bannerAdsChecker")
                                        strategyBannerMaxAds(
                                            activity = activity,
                                            containerView = containerView,
                                            bannerSize = bannerSize
                                        )
                                    } else {
                                        Log.d(TAG, "bannerAds: admob Ads: $bannerAdsChecker")
                                        strategyBannerAds()
                                    }
                                }
                            }
                        }
                    }

                    override fun onTick(p0: Long) {
                        Log.d(TAG, "onTick: ${(p0 / 1000) + 1}")
                    }
                }
                countDownTimer.start()
            } else {
                adsStrategy?.let {
                    when (it) {
                        AppConstants.ADMOB_ADS -> {
                            Log.d(TAG, "bannerAds: admob Ads")
                            strategyBannerAds()
                        }
                        AppConstants.APPLOVIN_ADS -> {
                            Log.d(TAG, "bannerAds: applovin Ads")
                            strategyBannerMaxAds(
                                activity = activity,
                                containerView = containerView,
                                bannerSize = bannerSize
                            )
                        }
                        else -> {
                            if (nativeAdView != null) {
                                Log.d(TAG, "bannerAds: applovin Ads: $bannerAdsChecker")
                                strategyBannerMaxAds(
                                    activity = activity,
                                    containerView = containerView,
                                    bannerSize = bannerSize
                                )
                            } else {
                                Log.d(TAG, "bannerAds: admob Ads: $bannerAdsChecker")
                                strategyBannerAds()
                            }
                        }
                    }
                }
            }
        } else {
            showMessage(msg = "ads is not visible", context = activity)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun Fragment.bannerAds(
    activity: Activity,
    containerView: FrameLayout,
    bannerSize: String? = null,
    timer: Long = 0
) {
    initBanner(
        activity = activity,
        containerView = containerView,
        bannerSize = bannerSize
    )
    try {
        if (adsVisible != null && adsVisible == 0) {
            if (timer >= 0) {
                countDownTimer = object :
                    CountDownTimer(TimeUnit.SECONDS.toMillis(timer), TimeUnit.SECONDS.toMillis(1)) {
                    override fun onFinish() {
                        adsStrategy?.let {
                            when (it) {
                                AppConstants.ADMOB_ADS -> {
                                    Log.d(TAG, "bannerAds: admob Ads")
                                    strategyBannerAds()
                                }
                                AppConstants.APPLOVIN_ADS -> {
                                    Log.d(TAG, "bannerAds: applovin Ads")
                                    strategyBannerMaxAds(
                                        activity = activity,
                                        containerView = containerView,
                                        bannerSize = bannerSize
                                    )
                                }
                                else -> {
                                    if (nativeAdView != null) {
                                        Log.d(TAG, "bannerAds: applovin Ads: $bannerAdsChecker")
                                        strategyBannerMaxAds(
                                            activity = activity,
                                            containerView = containerView,
                                            bannerSize = bannerSize
                                        )
                                    } else {
                                        Log.d(TAG, "bannerAds: admob Ads: $bannerAdsChecker")
                                        strategyBannerAds()
                                    }
                                }
                            }
                        }
                    }

                    override fun onTick(p0: Long) {
                        Log.d(TAG, "onTick: ${(p0 / 1000) + 1}")
                    }
                }
                countDownTimer.start()
            } else {
                adsStrategy?.let {
                    when (it) {
                        AppConstants.ADMOB_ADS -> {
                            Log.d(TAG, "bannerAds: admob Ads")
                            strategyBannerAds()
                        }
                        AppConstants.APPLOVIN_ADS -> {
                            Log.d(TAG, "bannerAds: applovin Ads")
                            strategyBannerMaxAds(
                                activity = activity,
                                containerView = containerView,
                                bannerSize = bannerSize
                            )
                        }
                        else -> {
                            if (nativeAdView != null) {
                                Log.d(TAG, "bannerAds: applovin Ads: $bannerAdsChecker")
                                strategyBannerMaxAds(
                                    activity = activity,
                                    containerView = containerView,
                                    bannerSize = bannerSize
                                )
                            } else {
                                Log.d(TAG, "bannerAds: admob Ads: $bannerAdsChecker")
                                strategyBannerAds()
                            }
                        }
                    }
                }
            }
        } else {
            showMessage(msg = "ads is not visible", context = activity)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

// Interstitial Ads
fun AppCompatActivity.interstitialAds(activity: Activity, timer: Long = 0) {
    try {
        if (adsVisible != null && adsVisible == 0) {
            maxInterstitialAdsInit(activity = activity)
            if (timer > 0) {
                countDownTimer = object :
                    CountDownTimer(TimeUnit.SECONDS.toMillis(timer), TimeUnit.SECONDS.toMillis(1)) {
                    override fun onFinish() {
                        adsStrategy?.let {
                            when (it) {
                                AppConstants.ADMOB_ADS -> {
                                    Log.d(TAG, "bannerAds: admob Ads")
                                    strategyInterstitialAds(activity = activity)
                                }
                                AppConstants.APPLOVIN_ADS -> {
                                    Log.d(TAG, "bannerAds: applovin Ads")
                                    strategyMaxInterstitialAds(activity = activity)
                                }
                                else -> {
                                    if (mInterstitialAd != null) {
                                        Log.d(TAG, "bannerAds: admob Ads")
                                        strategyInterstitialAds(activity = activity)
                                    } else {
                                        Log.d(TAG, "bannerAds: applovin Ads")
                                        strategyMaxInterstitialAds(activity = activity)
                                    }
                                }
                            }
                        }
                    }

                    override fun onTick(p0: Long) {
                        Log.d(TAG, "onTick: ${(p0 / 1000) + 1}")
                    }
                }
                countDownTimer.start()
            } else {
                adsStrategy?.let {
                    when (it) {
                        AppConstants.ADMOB_ADS -> {
                            Log.d(TAG, "bannerAds: admob Ads")
                            strategyInterstitialAds(activity = activity)
                        }
                        AppConstants.APPLOVIN_ADS -> {
                            Log.d(TAG, "bannerAds: applovin Ads")
                            strategyMaxInterstitialAds(activity = activity)
                        }
                        else -> {
                            if (mInterstitialAd != null) {
                                Log.d(TAG, "bannerAds: admob Ads")
                                strategyInterstitialAds(activity = activity)
                            } else {
                                Log.d(TAG, "bannerAds: applovin Ads")
                                strategyMaxInterstitialAds(activity = activity)
                            }
                        }
                    }
                }
            }
        } else {
            showMessage(msg = "ads is not visible", context = activity)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun Fragment.interstitialAds(activity: Activity, timer: Long = 0) {
    try {
        if (adsVisible != null && adsVisible == 0) {
            maxInterstitialAdsInit(activity = activity)
            if (timer > 0) {
                countDownTimer = object :
                    CountDownTimer(TimeUnit.SECONDS.toMillis(timer), TimeUnit.SECONDS.toMillis(1)) {
                    override fun onFinish() {
                        adsStrategy?.let {
                            when (it) {
                                AppConstants.ADMOB_ADS -> {
                                    Log.d(TAG, "bannerAds: admob Ads")
                                    strategyInterstitialAds(activity = activity)
                                }
                                AppConstants.APPLOVIN_ADS -> {
                                    Log.d(TAG, "bannerAds: applovin Ads")
                                    strategyMaxInterstitialAds(activity = activity)
                                }
                                else -> {
                                    if (mInterstitialAd != null) {
                                        Log.d(TAG, "bannerAds: admob Ads")
                                        strategyInterstitialAds(activity = activity)
                                    } else {
                                        Log.d(TAG, "bannerAds: applovin Ads")
                                        strategyMaxInterstitialAds(activity = activity)
                                    }
                                }
                            }
                        }
                    }

                    override fun onTick(p0: Long) {
                        Log.d(TAG, "onTick: ${(p0 / 1000) + 1}")
                    }
                }
                countDownTimer.start()
            } else {
                adsStrategy?.let {
                    when (it) {
                        AppConstants.ADMOB_ADS -> {
                            Log.d(TAG, "bannerAds: admob Ads")
                            strategyInterstitialAds(activity = activity)
                        }
                        AppConstants.APPLOVIN_ADS -> {
                            Log.d(TAG, "bannerAds: applovin Ads")
                            strategyMaxInterstitialAds(activity = activity)
                        }
                        else -> {
                            if (mInterstitialAd != null) {
                                Log.d(TAG, "bannerAds: admob Ads")
                                strategyInterstitialAds(activity = activity)
                            } else {
                                Log.d(TAG, "bannerAds: applovin Ads")
                                strategyMaxInterstitialAds(activity = activity)
                            }
                        }
                    }
                }
            }
        } else {
            showMessage(msg = "ads is not visible", context = activity)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


fun AppCompatActivity.nativeAds(
    activity: Activity,
    adFrame: FrameLayout,
    checkBox: CheckBox? = null,
    timer: Long = 0
) {
    try {
        if (adsVisible != null && adsVisible == 0) {
            strategyMaxNativeADS(
                activity = activity,
                containerView = adFrame
            )
            if (timer > 0) {
                countDownTimer = object :
                    CountDownTimer(TimeUnit.SECONDS.toMillis(timer), TimeUnit.SECONDS.toMillis(1)) {
                    override fun onFinish() {
                        adsStrategy?.let {
                            when (it) {
                                AppConstants.ADMOB_ADS -> {
                                    Log.d(TAG, "nativeAds: admob ads")
                                    strategyNativeADS(
                                        activity = activity,
                                        adFrame = adFrame,
                                        checkBox = checkBox
                                    )
                                }
                                AppConstants.APPLOVIN_ADS -> {
                                    Log.d(TAG, "nativeAds: applovin ads")
                                    showNativeADS()
                                }
                                else -> {
                                    if (currentNativeAd != null) {
                                        Log.d(TAG, "nativeAds: admob ads")
                                        strategyNativeADS(
                                            activity = activity,
                                            adFrame = adFrame,
                                            checkBox = checkBox
                                        )
                                    } else {
                                        Log.d(TAG, "nativeAds: applovin ads")
                                        showNativeADS()
                                    }
                                }
                            }
                        }
                    }

                    override fun onTick(p0: Long) {
                        Log.d(TAG, "onTick: ${(p0 / 1000) + 1}")
                    }
                }
                countDownTimer.start()
            } else {
                adsStrategy?.let {
                    when (it) {
                        AppConstants.ADMOB_ADS -> {
                            Log.d(TAG, "nativeAds: admob ads")
                            strategyNativeADS(
                                activity = activity,
                                adFrame = adFrame,
                                checkBox = checkBox
                            )
                        }
                        AppConstants.APPLOVIN_ADS -> {
                            Log.d(TAG, "nativeAds: applovin ads")
                            showNativeADS()
                        }
                        else -> {
                            if (currentNativeAd != null) {
                                Log.d(TAG, "nativeAds: admob ads")
                                strategyNativeADS(
                                    activity = activity,
                                    adFrame = adFrame,
                                    checkBox = checkBox
                                )
                            } else {
                                Log.d(TAG, "nativeAds: applovin ads")
                                showNativeADS()
                            }
                        }
                    }
                }
            }

        } else {
            showMessage(msg = "ads is not visible", context = activity)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun Fragment.nativeAds(
    activity: Activity,
    adFrame: FrameLayout,
    checkBox: CheckBox? = null,
    timer: Long = 0
) {
    try {
        if (adsVisible != null && adsVisible == 0) {
            strategyMaxNativeADS(
                activity = activity,
                containerView = adFrame
            )
            if (timer > 0) {
                countDownTimer = object :
                    CountDownTimer(TimeUnit.SECONDS.toMillis(timer), TimeUnit.SECONDS.toMillis(1)) {
                    override fun onFinish() {
                        adsStrategy?.let {
                            when (it) {
                                AppConstants.ADMOB_ADS -> {
                                    Log.d(TAG, "nativeAds: admob ads")
                                    strategyNativeADS(
                                        activity = activity,
                                        adFrame = adFrame,
                                        checkBox = checkBox
                                    )
                                }
                                AppConstants.APPLOVIN_ADS -> {
                                    Log.d(TAG, "nativeAds: applovin ads")
                                    showNativeADS()
                                }
                                else -> {
                                    if (currentNativeAd != null) {
                                        Log.d(TAG, "nativeAds: admob ads")
                                        strategyNativeADS(
                                            activity = activity,
                                            adFrame = adFrame,
                                            checkBox = checkBox
                                        )
                                    } else {
                                        Log.d(TAG, "nativeAds: applovin ads")
                                        showNativeADS()
                                    }
                                }
                            }
                        }
                    }

                    override fun onTick(p0: Long) {
                        Log.d(TAG, "onTick: ${(p0 / 1000) + 1}")
                    }
                }
                countDownTimer.start()
            } else {
                adsStrategy?.let {
                    when (it) {
                        AppConstants.ADMOB_ADS -> {
                            Log.d(TAG, "nativeAds: admob ads")
                            strategyNativeADS(
                                activity = activity,
                                adFrame = adFrame,
                                checkBox = checkBox
                            )
                        }
                        AppConstants.APPLOVIN_ADS -> {
                            Log.d(TAG, "nativeAds: applovin ads")
                            showNativeADS()
                        }
                        else -> {
                            if (currentNativeAd != null) {
                                Log.d(TAG, "nativeAds: admob ads")
                                strategyNativeADS(
                                    activity = activity,
                                    adFrame = adFrame,
                                    checkBox = checkBox
                                )
                            } else {
                                Log.d(TAG, "nativeAds: applovin ads")
                                showNativeADS()
                            }
                        }
                    }
                }
            }

        } else {
            showMessage(msg = "ads is not visible", context = activity)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun AppCompatActivity.rewardAds(
    activity: Activity,
    timer: Long = 0
) {
    try {
        if (adsVisible != null && adsVisible == 0) {
            initRewardMaxADS(activity = activity)
            if (timer > 0) {
                countDownTimer = object :
                    CountDownTimer(TimeUnit.SECONDS.toMillis(timer), TimeUnit.SECONDS.toMillis(1)) {
                    override fun onFinish() {
                        adsStrategy?.let {
                            when (it) {
                                AppConstants.ADMOB_ADS -> {
                                    Log.d(TAG, "rewardAds: admob Ads")
                                    strategyRewardAds(activity = activity)
                                }
                                AppConstants.APPLOVIN_ADS -> {
                                    Log.d(TAG, "rewardAds: applovin Ads")
                                    strategyMaxRewardAds(activity = activity)
                                }
                                else -> {
                                    if (mRewardedAd != null) {
                                        Log.d(TAG, "rewardAds: admob Ads")
                                        strategyRewardAds(activity = activity)
                                    } else {
                                        Log.d(TAG, "rewardAds: applovin Ads")
                                        strategyMaxRewardAds(activity = activity)
                                    }
                                }
                            }
                        }
                    }

                    override fun onTick(p0: Long) {
                        Log.d(TAG, "onTick: ${(p0 / 1000) + 1}")
                    }
                }
                countDownTimer.start()
            } else {
                adsStrategy?.let {
                    when (it) {
                        AppConstants.ADMOB_ADS -> {
                            Log.d(TAG, "rewardAds: admob Ads")
                            strategyRewardAds(activity = activity)
                        }
                        AppConstants.APPLOVIN_ADS -> {
                            Log.d(TAG, "rewardAds: applovin Ads")
                            strategyMaxRewardAds(activity = activity)
                        }
                        else -> {
                            if (mRewardedAd != null) {
                                Log.d(TAG, "rewardAds: admob Ads")
                                strategyRewardAds(activity = activity)
                            } else {
                                Log.d(TAG, "rewardAds: applovin Ads")
                                strategyMaxRewardAds(activity = activity)
                            }
                        }
                    }
                }
            }
        } else {
            showMessage(msg = "ads is not visible", context = activity)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun Fragment.rewardAds(
    activity: Activity,
    timer: Long = 0
) {
    try {
        if (adsVisible != null && adsVisible == 0) {
            initRewardMaxADS(activity = activity)
            if (timer > 0) {
                countDownTimer = object :
                    CountDownTimer(TimeUnit.SECONDS.toMillis(timer), TimeUnit.SECONDS.toMillis(1)) {
                    override fun onFinish() {
                        adsStrategy?.let {
                            when (it) {
                                AppConstants.ADMOB_ADS -> {
                                    Log.d(TAG, "rewardAds: admob Ads")
                                    strategyRewardAds(activity = activity)
                                }
                                AppConstants.APPLOVIN_ADS -> {
                                    Log.d(TAG, "rewardAds: applovin Ads")
                                    strategyMaxRewardAds(activity = activity)
                                }
                                else -> {
                                    if (mRewardedAd != null) {
                                        Log.d(TAG, "rewardAds: admob Ads")
                                        strategyRewardAds(activity = activity)
                                    } else {
                                        Log.d(TAG, "rewardAds: applovin Ads")
                                        strategyMaxRewardAds(activity = activity)
                                    }
                                }
                            }
                        }
                    }

                    override fun onTick(p0: Long) {
                        Log.d(TAG, "onTick: ${(p0 / 1000) + 1}")
                    }
                }
                countDownTimer.start()
            } else {
                adsStrategy?.let {
                    when (it) {
                        AppConstants.ADMOB_ADS -> {
                            Log.d(TAG, "rewardAds: admob Ads")
                            strategyRewardAds(activity = activity)
                        }
                        AppConstants.APPLOVIN_ADS -> {
                            Log.d(TAG, "rewardAds: applovin Ads")
                            strategyMaxRewardAds(activity = activity)
                        }
                        else -> {
                            if (mRewardedAd != null) {
                                Log.d(TAG, "rewardAds: admob Ads")
                                strategyRewardAds(activity = activity)
                            } else {
                                Log.d(TAG, "rewardAds: applovin Ads")
                                strategyMaxRewardAds(activity = activity)
                            }
                        }
                    }
                }
            }
        } else {
            showMessage(msg = "ads is not visible", context = activity)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun AppCompatActivity.rewardInterstitialAds(
    activity: Activity,
    fragmentManager: FragmentManager,
    timer: Long = 0
) {
    try {
        if (adsVisible != null && adsVisible == 0) {
            if (timer > 0) {
                countDownTimer = object :
                    CountDownTimer(TimeUnit.SECONDS.toMillis(timer), TimeUnit.SECONDS.toMillis(1)) {
                    override fun onFinish() {
                        adsStrategy?.let {
                            when (it) {
                                AppConstants.ADMOB_ADS -> {
                                    Log.d(TAG, "bannerStrategyAds: ")
                                    strategyRewardInterstitialADS(
                                        activity = activity,
                                        fragmentManager = fragmentManager
                                    )
                                }
                                AppConstants.APPLOVIN_ADS -> {
                                    showMessage(msg = "AppLovin Ads", context = activity)
                                }
                                else -> {
                                    showMessage(msg = "custom Ads Checker", context = activity)
                                }
                            }
                        }
                    }

                    override fun onTick(p0: Long) {
                        Log.d(TAG, "onTick: ${(p0 / 1000) + 1}")
                    }
                }
                countDownTimer.start()
            } else {
                adsStrategy?.let {
                    when (it) {
                        AppConstants.ADMOB_ADS -> {
                            Log.d(TAG, "bannerStrategyAds: ")
                            strategyRewardInterstitialADS(
                                activity = activity,
                                fragmentManager = fragmentManager
                            )
                        }
                        AppConstants.APPLOVIN_ADS -> {
                            showMessage(msg = "AppLovin Ads", context = activity)
                        }
                        else -> {
                            showMessage(msg = "custom Ads Checker", context = activity)
                        }
                    }
                }
            }
        } else {
            showMessage(msg = "ads is not visible", context = activity)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Fragment.rewardInterstitialAds(
    activity: Activity,
    fragmentManager: FragmentManager,
    timer: Long = 0
) {
    try {
        if (adsVisible != null && adsVisible == 0) {
            if (timer > 0) {
                countDownTimer = object :
                    CountDownTimer(TimeUnit.SECONDS.toMillis(timer), TimeUnit.SECONDS.toMillis(1)) {
                    override fun onFinish() {
                        adsStrategy?.let {
                            when (it) {
                                AppConstants.ADMOB_ADS -> {
                                    Log.d(TAG, "bannerStrategyAds: ")
                                    strategyRewardInterstitialADS(
                                        activity = activity,
                                        fragmentManager = fragmentManager
                                    )
                                }
                                AppConstants.APPLOVIN_ADS -> {
                                    showMessage(msg = "AppLovin Ads", context = activity)
                                }
                                else -> {
                                    showMessage(msg = "custom Ads Checker", context = activity)
                                }
                            }
                        }
                    }

                    override fun onTick(p0: Long) {
                        Log.d(TAG, "onTick: ${(p0 / 1000) + 1}")
                    }
                }
                countDownTimer.start()
            } else {
                adsStrategy?.let {
                    when (it) {
                        AppConstants.ADMOB_ADS -> {
                            Log.d(TAG, "bannerStrategyAds: ")
                            strategyRewardInterstitialADS(
                                activity = activity,
                                fragmentManager = fragmentManager
                            )
                        }
                        AppConstants.APPLOVIN_ADS -> {
                            showMessage(msg = "AppLovin Ads", context = activity)
                        }
                        else -> {
                            showMessage(msg = "custom Ads Checker", context = activity)
                        }
                    }
                }
            }
        } else {
            showMessage(msg = "ads is not visible", context = activity)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun AppCompatActivity.appOpenAds(
    activity: Activity,
    className: Class<*>,
    closed: Boolean,
    bundle: Bundle?
) {
    try {
        if (adsVisible != null && adsVisible == 0) {
            Log.d(TAG, "appOpenAds: Interstitial :${AppConstants.INTERSTITIAL_ADS_OPEN}")
            val application = application as? App
            if (application == null) {
                Log.d("Tag", "Failed to cast application to MyApplication.")
                startNewActivity(activity, className, closed, bundle)
                return
            }
            if (App.sharedPreference != null && AppConstants.INTERSTITIAL_ADS_OPEN) {
                Log.d(TAG, "appOpenAds: Interstitial Ads is loaded")
            } else {
                Log.d(TAG, "appOpenAds: Interstitial Ads is not loaded")
                App.showAdIfAvailable(activity, object : App.OnShowAdCompleteListener {
                    override fun onShowAdComplete() {
                        startNewActivity(activity, className, closed, bundle)
                    }
                })
            }
        } else {
            showMessage(msg = "ads is not visible", context = activity)
            startNewActivity(activity, className, closed, bundle)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

private fun startNewActivity(
    context: Context,
    className: Class<*>,
    closed: Boolean,
    bundle: Bundle?
) {
    val intent = Intent(context, className)
    bundle?.let {
        intent.putExtras(it)
    }
    context.startActivity(intent)
    if (closed) {
        (context as Activity).finish()
    }
}
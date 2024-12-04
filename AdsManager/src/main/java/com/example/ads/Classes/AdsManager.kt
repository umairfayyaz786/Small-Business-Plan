package com.example.ads.Classes

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.ads.Classes.Applications.App
import com.example.ads.Classes.Applications.App.Companion.sharedPreference
import com.example.ads.Classes.Utils.AppConstants
import com.google.android.gms.ads.*
import com.example.ads.databinding.AdUnifiedBinding
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import java.util.concurrent.TimeUnit

var mInterstitialAd: InterstitialAd? = null
private var adRequest = AdRequest.Builder().build()
var currentNativeAd: NativeAd? = null
private const val GAME_COUNTER_TIME = 10L
private const val GAME_OVER_REWARD = 1
private const val TAG = "Tag"
private var coinCount: Int = 0
private var countDownTimer: CountDownTimer? = null
private var gameOver = false
private var gamePaused = false
private var isLoadingAds = false
private var rewardedInterstitialAd: RewardedInterstitialAd? = null
private var timeRemaining: Long = 0L
var mRewardedAd: RewardedAd? = null
var bannerAdsChecker: Boolean = false
private var adView: AdView? = null


// AdMob SDK init
fun Application.initSDK(context: Context) {
    MobileAds.initialize(context) {}
    initInterstitialADS(context = context)
    loadReward(context = context)
}

// Message Show
private fun showMessage(msg: String, context: Context) {
//    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    Log.d("Tag", "$msg")
}

// Banner Ads Show
fun strategyBannerAds() {
    adView?.loadAd(adRequest)
}

// Banner Ads init
fun initBanner(
    activity: Activity,
    containerView: FrameLayout,
    bannerSize: String? = null
) {
    val size = if (bannerSize != null && (bannerSize == "FULL_BANNER")) {
        AdSize.FULL_BANNER
    } else if (bannerSize != null && (bannerSize == "LARGE_BANNER")) {
        AdSize.LARGE_BANNER
    } else if (bannerSize != null && (bannerSize == "FULL_BANNER")) {
        AdSize.FULL_BANNER
    } else if (bannerSize != null && (bannerSize == "LEADERBOARD")) {
        AdSize.LEADERBOARD
    } else if (bannerSize != null && (bannerSize == "WIDE_SKYSCRAPER")) {
        AdSize.WIDE_SKYSCRAPER
    } else if (bannerSize != null && (bannerSize == "FLUID")) {
        AdSize.FLUID
    } else if (bannerSize != null && (bannerSize == "MEDIUM_RECTANGLE")) {
        AdSize.MEDIUM_RECTANGLE
    } else if (bannerSize != null && (bannerSize == "INVALID")) {
        AdSize.INVALID
    } else if (bannerSize != null && (bannerSize == "SEARCH")) {
        AdSize.SEARCH
    } else if (bannerSize != null && (bannerSize == "zza")) {
        AdSize.zza
    } else {
        AdSize.BANNER
    }
    adView = AdView(activity)
    adView?.setAdSize(size)
    adView?.adUnitId =
        if (sharedPreference != null && !(sharedPreference!!.getString(AppConstants.ADMOB_BANNER_ADS_ID)
                .isNullOrBlank() || sharedPreference!!.getString(AppConstants.ADMOB_BANNER_ADS_ID)
                .isNullOrEmpty())
        ) {
            sharedPreference!!.getString(AppConstants.ADMOB_BANNER_ADS_ID)!!
        } else {
            AppConstants.ADMOB_BANNER_ADS_ID_DEFAULT
        }
    val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT
    )
    containerView.addView(adView, params)
    adView?.adListener = object : AdListener() {
        override fun onAdClicked() {
            // Code to be executed when the user clicks on an ad.

            showMessage(msg = " Banner onAdClicked", context = activity)
        }

        override fun onAdClosed() {
            // Code to be executed when the user is about to return
            // to the app after tapping on an ad.
            showMessage(msg = "Banner onAdClosed", context = activity)
        }

        override fun onAdFailedToLoad(adError: LoadAdError) {
            bannerAdsChecker = false
            // Code to be executed when an ad request fails.
            showMessage(msg = "Banner onAdFailedToLoad:${adError.message}", context = activity)
        }

        override fun onAdImpression() {
            // Code to be executed when an impression is recorded
            // for an ad.
            showMessage(msg = "Banner onAdImpression", context = activity)
        }

        override fun onAdLoaded() {
            // Code to be executed when an ad finishes loading.
            bannerAdsChecker = true
            showMessage(msg = "Banner onAdLoaded", context = activity)
        }

        override fun onAdOpened() {
            // Code to be executed when an ad opens an overlay that
            // covers the screen.
            showMessage(msg = "Banner onAdOpened", context = activity)
        }
    }
}


// Load Interstitial Ads
private fun initInterstitialADS(context: Context) {
    val interstitialKey =
        if (sharedPreference != null && !(sharedPreference!!.getString(AppConstants.ADMOB_INTERSTITIAL_ADS_ID)
                .isNullOrBlank() || sharedPreference!!.getString(AppConstants.ADMOB_INTERSTITIAL_ADS_ID)
                .isNullOrEmpty())
        ) {
            sharedPreference!!.getString(AppConstants.ADMOB_INTERSTITIAL_ADS_ID)!!
        } else {
            AppConstants.ADMOB_INTERSTITIAL_ADS_ID_DEFAULT
        }
    InterstitialAd.load(
        context,
        interstitialKey,
        adRequest,
        object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                showMessage(msg = adError.toString(), context = context)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                showMessage(msg = "Ad was loaded.", context = context)
                mInterstitialAd = interstitialAd
            }
        })
}

// Show Interstitial Ads
fun strategyInterstitialAds(activity: Activity) {
    showInterstitialAds(activity = activity)
}

private fun showInterstitialAds(activity: Activity) {
    if (mInterstitialAd != null) {
        mInterstitialAd?.show(activity)
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                showMessage(msg = "Ad was clicked.", context = activity)
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                showMessage(msg = "Ad dismissed fullscreen content.", context = activity)
                mInterstitialAd = null
                AppConstants.INTERSTITIAL_ADS_OPEN=true
                sharedPreference?.let { it.setBoolean(AppConstants.CHECK_INTERSTITIAL_ADS_IS_LOADED, true) }
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                super.onAdFailedToShowFullScreenContent(p0)
                showMessage(msg = "Ad failed to show fullscreen content.", context = activity)
                mInterstitialAd = null
                AppConstants.INTERSTITIAL_ADS_OPEN=true
                sharedPreference?.let {
                    it.setBoolean(AppConstants.CHECK_INTERSTITIAL_ADS_IS_LOADED, true)
                }
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                showMessage(msg = "Ad recorded an impression.", context = activity)
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                AppConstants.INTERSTITIAL_ADS_OPEN=true
                sharedPreference?.let {
                    it.setBoolean(AppConstants.CHECK_INTERSTITIAL_ADS_IS_LOADED, true)
                }
                showMessage(msg = "Ad showed fullscreen content.", context = activity)
            }
        }
    } else {
        sharedPreference?.let {
            AppConstants.INTERSTITIAL_ADS_OPEN=false
            it.setBoolean(AppConstants.CHECK_INTERSTITIAL_ADS_IS_LOADED, false)
        }
        showMessage(msg = "The interstitial ad wasn't ready yet.", context = activity)
    }
}


// Native Ads show
fun strategyNativeADS(activity: Activity, adFrame: FrameLayout, checkBox: CheckBox?) {
    val nativeADSID =
        if (sharedPreference != null && !(sharedPreference!!.getString(AppConstants.ADMOB_NATIVE_ADS_ID)
                .isNullOrBlank() || sharedPreference!!.getString(AppConstants.ADMOB_NATIVE_ADS_ID)
                .isNullOrEmpty())
        ) {
            sharedPreference!!.getString(AppConstants.ADMOB_NATIVE_ADS_ID)!!
        } else {
            AppConstants.ADMOB_NATIVE_ADS_ID_DEFAULT
        }

    val builder = AdLoader.Builder(activity, nativeADSID)
    builder.forNativeAd { nativeAd ->
        var activityDestroyed = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            activityDestroyed = activity.isDestroyed
        }
        if (activityDestroyed || activity.isFinishing || activity.isChangingConfigurations) {
            nativeAd.destroy()
            return@forNativeAd
        }
        currentNativeAd?.destroy()
        currentNativeAd = nativeAd
        val unifiedAdBinding = AdUnifiedBinding.inflate(activity.layoutInflater)
        populateNativeAdView(
            nativeAd = nativeAd,
            unifiedAdBinding = unifiedAdBinding,
            activity = activity
        )
        adFrame.removeAllViews()
        adFrame.addView(unifiedAdBinding.root)
    }
    checkBox?.let {
        val videoOptions =
            VideoOptions.Builder().setStartMuted(it.isChecked).build()

        val adOptions = NativeAdOptions.Builder().setVideoOptions(videoOptions).build()

        builder.withNativeAdOptions(adOptions)

    }

    val adLoader = builder.withAdListener(object : AdListener() {
        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
            val error =
                """domain: ${loadAdError.domain}, code: ${loadAdError.code}, message: ${loadAdError.message}   """"
            showMessage(msg = "Failed to load native ad with error $error", context = activity)
        }
    }
    ).build()
    adLoader.loadAd(AdRequest.Builder().build())
}

// Native Ads View
private fun populateNativeAdView(
    nativeAd: NativeAd,
    unifiedAdBinding: AdUnifiedBinding,
    activity: Activity
) {
    val nativeAdView = unifiedAdBinding.root
    nativeAdView.mediaView = unifiedAdBinding.adMedia
    nativeAdView.headlineView = unifiedAdBinding.adHeadline
    nativeAdView.bodyView = unifiedAdBinding.adBody
    nativeAdView.callToActionView = unifiedAdBinding.adCallToAction
    nativeAdView.iconView = unifiedAdBinding.adAppIcon
    nativeAdView.priceView = unifiedAdBinding.adPrice
    nativeAdView.starRatingView = unifiedAdBinding.adStars
    nativeAdView.storeView = unifiedAdBinding.adStore
    nativeAdView.advertiserView = unifiedAdBinding.adAdvertiser
    unifiedAdBinding.adHeadline.text = nativeAd.headline
    nativeAd.mediaContent?.let { unifiedAdBinding.adMedia.setMediaContent(it) }
    if (nativeAd.body == null) {
        unifiedAdBinding.adBody.visibility = View.INVISIBLE
    } else {
        unifiedAdBinding.adBody.visibility = View.VISIBLE
        unifiedAdBinding.adBody.text = nativeAd.body
    }

    if (nativeAd.callToAction == null) {
        unifiedAdBinding.adCallToAction.visibility = View.INVISIBLE
    } else {
        unifiedAdBinding.adCallToAction.visibility = View.VISIBLE
        unifiedAdBinding.adCallToAction.text = nativeAd.callToAction
    }

    if (nativeAd.icon == null) {
        unifiedAdBinding.adAppIcon.visibility = View.GONE
    } else {
        unifiedAdBinding.adAppIcon.setImageDrawable(nativeAd.icon?.drawable)
        unifiedAdBinding.adAppIcon.visibility = View.VISIBLE
    }

    if (nativeAd.price == null) {
        unifiedAdBinding.adPrice.visibility = View.INVISIBLE
    } else {
        unifiedAdBinding.adPrice.visibility = View.VISIBLE
        unifiedAdBinding.adPrice.text = nativeAd.price
    }
    if (nativeAd.store == null) {
        unifiedAdBinding.adStore.visibility = View.INVISIBLE
    } else {
        unifiedAdBinding.adStore.visibility = View.VISIBLE
        unifiedAdBinding.adStore.text = nativeAd.store
    }
    if (nativeAd.starRating == null) {
        unifiedAdBinding.adStars.visibility = View.INVISIBLE
    } else {
        unifiedAdBinding.adStars.rating = nativeAd.starRating!!.toFloat()
        unifiedAdBinding.adStars.visibility = View.VISIBLE
    }
    if (nativeAd.advertiser == null) {
        unifiedAdBinding.adAdvertiser.visibility = View.INVISIBLE
    } else {
        unifiedAdBinding.adAdvertiser.text = nativeAd.advertiser
        unifiedAdBinding.adAdvertiser.visibility = View.VISIBLE
    }
    nativeAdView.setNativeAd(nativeAd)
    val vc = nativeAd.mediaContent?.videoController
    if (vc != null && vc.hasVideoContent()) {
        vc.videoLifecycleCallbacks =
            object : VideoController.VideoLifecycleCallbacks() {
                @SuppressLint("SetTextI18n")
                override fun onVideoEnd() {
                    showMessage(msg = "Video status: Video playback has ended.", context = activity)
                    super.onVideoEnd()
                }
            }
    } else {
        showMessage(msg = "Video status: Ad does not contain a video asset.", context = activity)
    }

}

// Reward Ads init
private fun loadReward(context: Context) {
    val rewardADSID =
        if (sharedPreference != null && !(sharedPreference!!.getString(AppConstants.ADMOB_REWARD_ADS_ID)
                .isNullOrBlank() || sharedPreference!!.getString(AppConstants.ADMOB_REWARD_ADS_ID)
                .isNullOrEmpty())
        ) {
            sharedPreference!!.getString(AppConstants.ADMOB_REWARD_ADS_ID)!!
        } else {
            AppConstants.ADMOB_REWARD_ADS_ID_DEFAULT
        }
    RewardedAd.load(
        context,
        rewardADSID,
        adRequest,
        object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError.toString())
                mRewardedAd = null
            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                Log.d(TAG, "Ad was loaded.")
                mRewardedAd = rewardedAd
            }
        })
}


// Show Reward Ads
fun strategyRewardAds(activity: Activity) {
    if (mRewardedAd != null) {
        mRewardedAd?.show(activity, OnUserEarnedRewardListener() {
            fun onUserEarnedReward(rewardItem: RewardItem) {
                var rewardAmount = rewardItem.amount
                var rewardType = rewardItem.type
                showMessage(msg = "User earned the reward.", context = activity)
            }
        })
        mRewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                showMessage(msg = "Ad was clicked.", context = activity)
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time
                showMessage(msg = "Ad dismissed fullscreen content.", context = activity)
                mRewardedAd = null
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                super.onAdFailedToShowFullScreenContent(p0)
                showMessage(msg = "Ad failed to show fullscreen content.", context = activity)
                mRewardedAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                showMessage(msg = "Ad recorded an impression.", context = activity)
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                showMessage(msg = "Ad showed fullscreen content.", context = activity)
            }
        }
    } else {
        showMessage(msg = "The rewarded ad wasn't ready yet.", context = activity)
    }
}

// Reward Interstitial ADS
fun strategyRewardInterstitialADS(activity: Activity, fragmentManager: FragmentManager) {
    if (rewardedInterstitialAd == null && !isLoadingAds) {
        loadRewardedInterstitialAd(activity = activity)
    }
    createTimer(GAME_COUNTER_TIME, activity = activity, fragmentManager = fragmentManager)
    gamePaused = false
    gameOver = false

}

// Reward Interstitial Ads init
private fun loadRewardedInterstitialAd(activity: Activity) {
    val rewardADSID =
        if (sharedPreference != null && !(sharedPreference!!.getString(AppConstants.ADMOB_REWARD_INTERSTITIAL_ADS_ID)
                .isNullOrBlank() || sharedPreference!!.getString(AppConstants.ADMOB_REWARD_INTERSTITIAL_ADS_ID)
                .isNullOrEmpty())
        ) {
            sharedPreference!!.getString(AppConstants.ADMOB_REWARD_INTERSTITIAL_ADS_ID)!!
        } else {
            AppConstants.ADMOB_REWARD_INTERSTITIAL_ADS_ID_DEFAULT
        }
    if (rewardedInterstitialAd == null) {
        isLoadingAds = true
        RewardedInterstitialAd.load(
            activity,
            rewardADSID,
            adRequest,
            object : RewardedInterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    super.onAdFailedToLoad(adError)
                    Log.d(TAG, "onAdFailedToLoad: ${adError.message}")
                    isLoadingAds = false
                    rewardedInterstitialAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedInterstitialAd) {
                    super.onAdLoaded(rewardedAd)
                    Log.d(TAG, "Ad was loaded.")
                    rewardedInterstitialAd = rewardedAd
                    isLoadingAds = false
                }
            }
        )
    }
}

@SuppressLint("SetTextI18n")
private fun createTimer(time: Long, activity: Activity, fragmentManager: FragmentManager) {
    countDownTimer?.cancel()
    countDownTimer = object : CountDownTimer(time * 1000, 50) {

        override fun onTick(timerLong: Long) {
            timeRemaining = timerLong / 1000 + 1
//            binding.timer.text = "seconds remaining: $timeRemaining"
            Log.d(TAG, "seconds remaining: $timeRemaining")
//            showMessage(msg = "seconds remaining: $timeRemaining", activity)
        }

        override fun onFinish() {
//            binding.timer.text = "The game has ended!"
            showMessage(msg = "The game has ended!", activity)
            addCoins(coins = GAME_OVER_REWARD, activity = activity)
//            binding.retryButton.visibility = View.VISIBLE
            gameOver = true
            if (rewardedInterstitialAd == null) {
                Log.d(
                    TAG,
                    "The game is over but the rewarded interstitial ad wasn't ready yet."
                )
                return
            }
            Log.d(TAG, "The rewarded interstitial ad is ready.")
            val rewardAmount = rewardedInterstitialAd!!.rewardItem.amount
            val rewardType = rewardedInterstitialAd!!.rewardItem.type
            introduceVideoAd(
                rewardAmount,
                rewardType,
                fragmentManager = fragmentManager,
                activity = activity
            )
        }
    }
    countDownTimer?.start()
}

@SuppressLint("SetTextI18n")
private fun addCoins(coins: Int, activity: Activity) {
    coinCount += coins
//    binding.coinCountText.text = "Coins: $coinCount"
    showMessage(msg = "Coins: $coinCount", activity)
}

private fun introduceVideoAd(
    rewardAmount: Int,
    rewardType: String,
    fragmentManager: FragmentManager,
    activity: Activity
) {
    val dialog = AdDialogFragment.newInstance(rewardAmount, rewardType)
    dialog.setAdDialogInteractionListener(
        object : AdDialogFragment.AdDialogInteractionListener {
            override fun onShowAd() {
                Log.d(TAG, "The rewarded interstitial ad is starting.")
                showRewardedVideo(activity = activity)
            }

            override fun onCancelAd() {
                Log.d(TAG, "The rewarded interstitial ad was skipped before it starts.")
            }
        }
    )
    dialog.show(fragmentManager, "AdDialogFragment")
}

private fun showRewardedVideo(activity: Activity) {
    if (rewardedInterstitialAd == null) {
        Log.d(TAG, "The rewarded interstitial ad wasn't ready yet.")
        return
    }

    rewardedInterstitialAd!!.fullScreenContentCallback =
        object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d(TAG, "Ad was dismissed.")
                rewardedInterstitialAd = null
                loadRewardedInterstitialAd(activity = activity)
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                Log.d(TAG, "Ad failed to show.")
                rewardedInterstitialAd = null
            }

            override fun onAdShowedFullScreenContent() {
                Log.d(TAG, "Ad showed fullscreen content.")
            }
        }

    rewardedInterstitialAd?.show(activity) { rewardItem ->
        addCoins(coins = rewardItem.amount, activity = activity)
        Log.d("TAG", "User earned the reward.")
    }
}

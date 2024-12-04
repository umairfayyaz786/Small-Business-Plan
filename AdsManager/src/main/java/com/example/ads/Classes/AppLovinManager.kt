package com.example.ads.Classes

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustAdRevenue
import com.adjust.sdk.AdjustConfig
import com.applovin.adview.*
import com.applovin.mediation.*
import com.applovin.mediation.ads.MaxAdView
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.mediation.ads.MaxRewardedAd
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder
import com.applovin.sdk.*
import com.example.ads.Classes.Applications.App.Companion.sharedPreference
import com.example.ads.Classes.Utils.AppConstants
import com.example.ads.R
import java.util.concurrent.TimeUnit
import kotlin.math.min
import kotlin.math.pow

private lateinit var appLovinSDK: AppLovinSdk
var interstitialAdDialog: AppLovinInterstitialAdDialog? = null
private var incentivizedInterstitial: AppLovinIncentivizedInterstitial? = null
private lateinit var interstitialAd: MaxInterstitialAd
private var retryAttempt = 0.0
private lateinit var rewardedAd: MaxRewardedAd
private lateinit var nativeAdLoader: MaxNativeAdLoader
private var nativeAd: MaxAd? = null

@SuppressLint("StaticFieldLeak")
private lateinit var adView: MaxAdView

@SuppressLint("StaticFieldLeak")
lateinit var nativeAdView: MaxNativeAdView
private var TAG = "Tag"

// init AppLovin SDK
fun Application.initAppLovinSDK(context: Context) {
    AppLovinSdk.getInstance(context).mediationProvider = AppLovinMediationProvider.MAX
    appLovinSDK = AppLovinSdk.getInstance(context)
    AppLovinSdk.initializeSdk(context) {}
//    AppLovinSdk.getInstance(context).showMediationDebugger()
    interstitialADS(context = context)
    initRewardAds(context = context)
}

// Message Show
private fun showMessage(msg: String, context: Context) {
//    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    Log.d(TAG, "$msg")
}


// AppLovin Interstitial Ads init
private fun interstitialADS(context: Context) {
    val interstitialADSID =
        if (sharedPreference != null && !(sharedPreference!!.getString(AppConstants.APPLOVIN_INTERSTITIAL_ADS_ID)
                .isNullOrBlank() || sharedPreference!!.getString(AppConstants.APPLOVIN_INTERSTITIAL_ADS_ID)
                .isNullOrEmpty())
        ) {
            sharedPreference!!.getString(AppConstants.APPLOVIN_INTERSTITIAL_ADS_ID)
        } else {
            AppConstants.APPLOVIN_INTERSTITIAL_ADS_ID_DEFAULT
        }
    interstitialAdDialog = AppLovinInterstitialAd.create(appLovinSDK, context)
    appLovinSDK.adService.loadNextAdForZoneId(
        interstitialADSID,
        object : AppLovinAdLoadListener {
            override fun adReceived(ad: AppLovinAd?) {
                showMessage(msg = "adReceived", context = context)
            }

            override fun failedToReceiveAd(errorCode: Int) {
                showMessage(msg = "failedToReceiveAd", context = context)
            }
        })
    interstitialAdDialog?.setAdDisplayListener(object : AppLovinAdDisplayListener {
        override fun adDisplayed(ad: AppLovinAd?) {
            showMessage(msg = "adDisplayed", context = context)
        }

        override fun adHidden(ad: AppLovinAd?) {
            showMessage(msg = "adHidden", context = context)
        }
    })
    interstitialAdDialog?.setAdClickListener { showMessage("adClicked", context = context) }
    interstitialAdDialog?.setAdVideoPlaybackListener(object :
        AppLovinAdVideoPlaybackListener {
        override fun videoPlaybackBegan(ad: AppLovinAd?) {
            showMessage(msg = "videoPlaybackBegan", context = context)
        }

        override fun videoPlaybackEnded(
            ad: AppLovinAd?,
            percentViewed: Double,
            fullyWatched: Boolean
        ) {

            showMessage(msg = "videoPlaybackEnded", context = context)
        }
    })
}


// AppLovin Interstitial Ads Show
fun strategyInterstitialAppLovinAds() {
    interstitialAdDialog?.show()
}

// AppLovin Banner ADS
fun strategyAppLovinBannerAds(
    activity: Activity,
    containerView: LinearLayout,
    bannerSize: String? = null
) {
    appLovinBannerInit(
        activity = activity,
        containerView = containerView,
        bannerSize = bannerSize
    )
}

private fun appLovinBannerInit(
    activity: Activity,
    containerView: LinearLayout,
    bannerSize: String? = null
) {
    val bannerADSID =
        if (sharedPreference != null && !(sharedPreference!!.getString(AppConstants.APPLOVIN_BANNER_ADS_ID)
                .isNullOrBlank() || sharedPreference!!.getString(AppConstants.APPLOVIN_BANNER_ADS_ID)
                .isNullOrEmpty())
        ) {
            sharedPreference!!.getString(AppConstants.APPLOVIN_BANNER_ADS_ID)
        } else {
            AppConstants.APPLOVIN_BANNER_ADS_ID_DEFAULT
        }
    val adSize = if (bannerSize != null && (bannerSize == "LEADER")) {
        AppLovinAdSize.LEADER
    } else if (bannerSize != null && (bannerSize == "CROSS_PROMO")) {
        AppLovinAdSize.CROSS_PROMO
    } else if (bannerSize != null && (bannerSize == "MREC")) {
        AppLovinAdSize.MREC
    } else if (bannerSize != null && (bannerSize == "INTERSTITIAL")) {
        AppLovinAdSize.INTERSTITIAL
    } else if (bannerSize != null && (bannerSize == "NATIVE")) {
        AppLovinAdSize.NATIVE
    } else {
        AppLovinAdSize.BANNER
    }
    val adView = AppLovinAdView(adSize, bannerADSID, activity)
    adView.setAdLoadListener(object : AppLovinAdLoadListener {
        override fun adReceived(ad: AppLovinAd?) {
            showMessage(msg = "adReceived", context = activity)
        }

        override fun failedToReceiveAd(errorCode: Int) {
            showMessage(msg = "failedToReceiveAd", context = activity)
        }
    })
    adView.setAdDisplayListener(object : AppLovinAdDisplayListener {
        override fun adDisplayed(ad: AppLovinAd?) {
            showMessage(msg = "adDisplayed", context = activity)
        }

        override fun adHidden(ad: AppLovinAd?) {
            showMessage(msg = "adHidden", context = activity)
        }
    })
    adView.setAdViewEventListener(object : AppLovinAdViewEventListener {
        override fun adOpenedFullscreen(ad: AppLovinAd?, adView: AppLovinAdView?) {
            showMessage(msg = "adOpenedFullscreen", context = activity)
        }

        override fun adClosedFullscreen(ad: AppLovinAd?, adView: AppLovinAdView?) {
            showMessage(msg = "adClosedFullscreen", context = activity)
        }

        override fun adLeftApplication(ad: AppLovinAd?, adView: AppLovinAdView?) {
            showMessage(msg = "adLeftApplication", context = activity)
        }

        override fun adFailedToDisplay(
            ad: AppLovinAd?,
            adView: AppLovinAdView?,
            code: AppLovinAdViewDisplayErrorCode?
        ) {
            showMessage(msg = "adFailedToDisplay", context = activity)
        }
    })
    adView.setAdClickListener { showMessage("adClicked", context = activity) }
    val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT
    )
    containerView.addView(adView, params)
    // Load an ad!
    adView.loadNextAd()

}

// AppLovin Reward Ads init
private fun initRewardAds(context: Context) {
    val bannerADSID =
        if (sharedPreference != null && !(sharedPreference!!.getString(AppConstants.APPLOVIN_REWARD_ADS_ID)
                .isNullOrBlank() || sharedPreference!!.getString(AppConstants.APPLOVIN_REWARD_ADS_ID)
                .isNullOrEmpty())
        ) {
            sharedPreference!!.getString(AppConstants.APPLOVIN_REWARD_ADS_ID)
        } else {
            AppConstants.APPLOVIN_REWARD_ADS_ID_DEFAULT
        }
    incentivizedInterstitial =
        AppLovinIncentivizedInterstitial.create(context).apply {
            preload(object : AppLovinAdLoadListener {
                override fun adReceived(ad: AppLovinAd?) {
                    showMessage(msg = "adFailedToDisplay", context = context)
                }

                override fun failedToReceiveAd(errorCode: Int) {
                    showMessage(msg = "adFailedToDisplay", context = context)
                }
            })
        }
}

// AppLovin Reward Ads show
fun strategyRewardAppLovinAds(activity: Activity) {
    incentivizedInterstitial?.show(activity, object : AppLovinAdRewardListener {
        override fun userRewardRejected(ad: AppLovinAd?, response: MutableMap<String, String>?) {
            showMessage(msg = "userRewardRejected", context = activity)
        }

        override fun userOverQuota(ad: AppLovinAd?, response: MutableMap<String, String>?) {
            showMessage(msg = "userOverQuota", context = activity)
        }

        override fun validationRequestFailed(ad: AppLovinAd?, errorCode: Int) {
            showMessage(msg = "validationRequestFailed", context = activity)
        }

        override fun userRewardVerified(ad: AppLovinAd?, response: MutableMap<String, String>?) {
            showMessage(msg = "userRewardVerified", context = activity)
        }
    }, object : AppLovinAdVideoPlaybackListener {
        override fun videoPlaybackBegan(ad: AppLovinAd?) {
            showMessage(msg = "videoPlaybackBegan", context = activity)
        }

        override fun videoPlaybackEnded(
            ad: AppLovinAd?,
            percentViewed: Double,
            fullyWatched: Boolean
        ) {
            showMessage(msg = "videoPlaybackEnded", context = activity)
        }
    }, object : AppLovinAdDisplayListener {
        override fun adDisplayed(ad: AppLovinAd?) {
            showMessage(msg = "adDisplayed", context = activity)
        }

        override fun adHidden(ad: AppLovinAd?) {
            showMessage(msg = "adHidden", context = activity)
        }
    }
    ) { showMessage(msg = "adClicked", context = activity) }
}

// Max Banner Ads
fun strategyBannerMaxAds(
    activity: Activity,
    containerView: FrameLayout,
    bannerSize: String? = null
) {
    Log.d(TAG, "showMaxBannerAds: function")
    val bannerADSID =
        if (sharedPreference != null && !(sharedPreference!!.getString(AppConstants.MAX_BANNER_ADS_ID)
                .isNullOrBlank() || sharedPreference!!.getString(AppConstants.MAX_BANNER_ADS_ID)
                .isNullOrEmpty())
        ) {
            sharedPreference!!.getString(AppConstants.MAX_BANNER_ADS_ID)
        } else {
            AppConstants.MAX_BANNER_ADS_ID_DEFAULT
        }
    val adSize = if (bannerSize != null && (bannerSize == "CROSS_PROMO")) {
        MaxAdFormat.CROSS_PROMO
    } else if (bannerSize != null && (bannerSize == "LEADER")) {
        MaxAdFormat.LEADER
    } else if (bannerSize != null && (bannerSize == "MREC")) {
        MaxAdFormat.MREC
    } else if (bannerSize != null && (bannerSize == "NATIVE")) {
        MaxAdFormat.NATIVE
    } else if (bannerSize != null && (bannerSize == "INTERSTITIAL")) {
        MaxAdFormat.INTERSTITIAL
    } else if (bannerSize != null && (bannerSize == "REWARDED")) {
        MaxAdFormat.REWARDED
    } else if (bannerSize != null && (bannerSize == "REWARDED_INTERSTITIAL")) {
        MaxAdFormat.REWARDED_INTERSTITIAL
    } else {
        MaxAdFormat.BANNER
    }
    adView = MaxAdView(bannerADSID, adSize, activity)
    adView.setListener(object : MaxAdViewAdListener {
        override fun onAdClicked(ad: MaxAd?) {
            showMessage(msg = "max banner onAdClicked", context = activity)
        }

        override fun onAdCollapsed(ad: MaxAd?) {
            showMessage(msg = "max banner onAdCollapsed", context = activity)
        }

        override fun onAdDisplayed(ad: MaxAd?) {
            showMessage(msg = "max banner onAdDisplayed", context = activity)
        }

        override fun onAdExpanded(ad: MaxAd?) {
            showMessage(msg = "max banner onAdExpanded", context = activity)
        }

        override fun onAdHidden(ad: MaxAd?) {
            showMessage(msg = "max banner onAdHidden", context = activity)
        }

        override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
            showMessage(msg = "max banner onAdDisplayFailed", context = activity)
        }

        override fun onAdLoaded(ad: MaxAd?) {
            showMessage(msg = "max banner onAdLoaded", context = activity)
        }

        override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
            showMessage(msg = "max banner onAdLoadFailed", context = activity)
        }
    })
    adView.setRevenueListener {
        val adjustAdRevenue = AdjustAdRevenue(AdjustConfig.AD_REVENUE_APPLOVIN_MAX)
        adjustAdRevenue.setRevenue(it?.revenue, "USD")
        adjustAdRevenue.setAdRevenueNetwork(it?.networkName)
        adjustAdRevenue.setAdRevenueUnit(it?.adUnitId)
        adjustAdRevenue.setAdRevenuePlacement(it?.placement)
        Adjust.trackAdRevenue(adjustAdRevenue)
    }
    val width = ViewGroup.LayoutParams.MATCH_PARENT

    // Banner height on phones and tablets is 50 and 90, respectively
    val heightPx = activity.resources.getDimensionPixelSize(R.dimen.fab_margin)

    adView.layoutParams = FrameLayout.LayoutParams(width, heightPx)
    adView.setBackgroundColor(Color.BLACK)
    // Set background or background color for banners to be fully functional
    containerView.addView(adView)
    // Load the ad
    adView.loadAd()
    adView.startAutoRefresh()
}


// Max Interstitial Ads init
fun maxInterstitialAdsInit(activity: Activity) {
    val interstitialADSID =
        if (sharedPreference != null && !(sharedPreference!!.getString(AppConstants.MAX_INTERSTITIAL_ADS_ID)
                .isNullOrBlank() || sharedPreference!!.getString(AppConstants.MAX_INTERSTITIAL_ADS_ID)
                .isNullOrEmpty())
        ) {
            sharedPreference!!.getString(AppConstants.MAX_INTERSTITIAL_ADS_ID)
        } else {
            AppConstants.MAX_INTERSTITIAL_ADS_ID_DEFAULT
        }
    interstitialAd = MaxInterstitialAd(interstitialADSID, activity)

    interstitialAd.setListener(object : MaxAdListener {
        override fun onAdHidden(ad: MaxAd?) {
            showMessage(msg = "max inter onAdHidden", context = activity)
        }

        override fun onAdClicked(ad: MaxAd?) {
            showMessage(msg = "max inter onAdHidden", context = activity)
        }

        override fun onAdDisplayed(ad: MaxAd?) {
            sharedPreference?.let {
                it.setBoolean(AppConstants.CHECK_INTERSTITIAL_ADS_IS_LOADED, true)
            }
            showMessage(msg = "max inter onAdClicked", context = activity)
        }

        override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
            sharedPreference?.let {
                it.setBoolean(AppConstants.CHECK_INTERSTITIAL_ADS_IS_LOADED, false)
            }
            showMessage(msg = "max inter onAdDisplayFailed", context = activity)
        }

        override fun onAdLoaded(ad: MaxAd?) {
            showMessage(msg = "max inter onAdLoaded", context = activity)
        }

        override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
            sharedPreference?.let {
                it.setBoolean(AppConstants.CHECK_INTERSTITIAL_ADS_IS_LOADED, false)
            }
            showMessage(msg = "max inter onAdLoadFailed", context = activity)
        }
    })
    interstitialAd.setRevenueListener { ad ->
        val adjustAdRevenue = AdjustAdRevenue(AdjustConfig.AD_REVENUE_APPLOVIN_MAX)
        adjustAdRevenue.setRevenue(ad?.revenue, "USD")
        adjustAdRevenue.setAdRevenueNetwork(ad?.networkName)
        adjustAdRevenue.setAdRevenueUnit(ad?.adUnitId)
        adjustAdRevenue.setAdRevenuePlacement(ad?.placement)
        Adjust.trackAdRevenue(adjustAdRevenue)
    }

    // Load the first ad.
    interstitialAd.loadAd()
}

// Max Interstitial Ads show
fun strategyMaxInterstitialAds(activity: Activity) {
    if (interstitialAd.isReady) {
        Log.d(TAG, "strategyMaxInterstitialAds: Loaded")
        interstitialAd.showAd()
    }
}

// Max Reward ADS init
fun initRewardMaxADS(activity: Activity) {
    val rewardADSID =
        if (sharedPreference != null && !(sharedPreference!!.getString(AppConstants.MAX_REWARD_ADS_ID)
                .isNullOrBlank() || sharedPreference!!.getString(AppConstants.MAX_REWARD_ADS_ID)
                .isNullOrEmpty())
        ) {
            sharedPreference!!.getString(AppConstants.MAX_REWARD_ADS_ID)
        } else {
            AppConstants.MAX_REWARD_ADS_ID_DEFAULT
        }

    rewardedAd = MaxRewardedAd.getInstance(rewardADSID, activity)

    rewardedAd.setListener(object : MaxRewardedAdListener {
        override fun onAdLoaded(ad: MaxAd?) {
            showMessage(msg = "onAdLoaded", context = activity)
            retryAttempt = 0.0
        }

        override fun onAdClicked(ad: MaxAd?) {
            showMessage(msg = "onAdClicked", context = activity)
        }

        override fun onAdDisplayed(ad: MaxAd?) {
            showMessage(msg = "onAdDisplayed", context = activity)
        }

        override fun onAdHidden(ad: MaxAd?) {
            showMessage(msg = "onAdHidden", context = activity)
            rewardedAd.loadAd()
        }

        override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
            showMessage(msg = "onAdLoadFailed", context = activity)
            retryAttempt++
            val delayMillis = TimeUnit.SECONDS.toMillis(2.0.pow(min(6.0, retryAttempt)).toLong())
            Handler(Looper.myLooper()!!).postDelayed({ rewardedAd.loadAd() }, delayMillis)
        }

        override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
            showMessage(msg = "onAdDisplayFailed", context = activity)
            rewardedAd.loadAd()
        }

        override fun onRewardedVideoCompleted(ad: MaxAd?) {
            showMessage(msg = "onRewardedVideoCompleted", context = activity)
        }

        override fun onRewardedVideoStarted(ad: MaxAd?) {
            showMessage(msg = "onRewardedVideoStarted", context = activity)
        }

        override fun onUserRewarded(ad: MaxAd?, reward: MaxReward?) {
            showMessage(msg = "onUserRewarded", context = activity)
        }
    })
    rewardedAd.setRevenueListener { ad ->
        showMessage(msg = "setRevenueListener", context = activity)
        val adjustAdRevenue = AdjustAdRevenue(AdjustConfig.AD_REVENUE_APPLOVIN_MAX)
        adjustAdRevenue.setRevenue(ad?.revenue, "USD")
        adjustAdRevenue.setAdRevenueNetwork(ad?.networkName)
        adjustAdRevenue.setAdRevenueUnit(ad?.adUnitId)
        adjustAdRevenue.setAdRevenuePlacement(ad?.placement)

        Adjust.trackAdRevenue(adjustAdRevenue)
    }

    rewardedAd.loadAd()
}

// Max Reward ADS Show
fun strategyMaxRewardAds(activity: Activity) {
    if (rewardedAd.isReady) {
        rewardedAd.showAd()
    }
}

// Max Native Ads Show

fun strategyMaxNativeADS(activity: Activity, containerView: FrameLayout) {
    val nativeADSID =
        if (sharedPreference != null && !(sharedPreference!!.getString(AppConstants.MAX_Native_ADS_ID)
                .isNullOrBlank() || sharedPreference!!.getString(AppConstants.MAX_Native_ADS_ID)
                .isNullOrEmpty())
        ) {
            sharedPreference!!.getString(AppConstants.MAX_Native_ADS_ID)
        } else {
            AppConstants.MAX_Native_ADS_ID_DEFAULT
        }
    nativeAdView = createNativeAdView(activity = activity)
    nativeAdLoader = MaxNativeAdLoader(nativeADSID, activity)
    nativeAdLoader.setRevenueListener { ad ->
        val adjustAdRevenue = AdjustAdRevenue(AdjustConfig.AD_REVENUE_APPLOVIN_MAX)
        adjustAdRevenue.setRevenue(ad?.revenue, "USD")
        adjustAdRevenue.setAdRevenueNetwork(ad?.networkName)
        adjustAdRevenue.setAdRevenueUnit(ad?.adUnitId)
        adjustAdRevenue.setAdRevenuePlacement(ad?.placement)
        Adjust.trackAdRevenue(adjustAdRevenue)
    }

    nativeAdLoader.setNativeAdListener(object : MaxNativeAdListener() {
        override fun onNativeAdLoaded(nativeAdView: MaxNativeAdView?, ad: MaxAd) {
            showMessage(msg = "onNativeAdLoaded", context = activity)
            // Cleanup any pre-existing native ad to prevent memory leaks.
            if (nativeAd != null) {
                nativeAdLoader.destroy(nativeAd)
            }

            // Save ad for cleanup.
            nativeAd = ad

            // Add ad view to view.
            containerView.removeAllViews()
            containerView.addView(nativeAdView)
        }

        override fun onNativeAdLoadFailed(adUnitId: String, error: MaxError) {
            showMessage(msg = "onNativeAdLoadFailed", context = activity)
        }

        override fun onNativeAdClicked(ad: MaxAd) {
            showMessage(msg = "onNativeAdClicked", context = activity)
        }
    })
}

private fun createNativeAdView(activity: Activity): MaxNativeAdView {
    val binder: MaxNativeAdViewBinder =
        MaxNativeAdViewBinder.Builder(R.layout.native_custom_ad_view)
            .setTitleTextViewId(R.id.title_text_view)
            .setBodyTextViewId(R.id.body_text_view)
            .setAdvertiserTextViewId(R.id.advertiser_textView)
            .setIconImageViewId(R.id.icon_image_view)
            .setMediaContentViewGroupId(R.id.media_view_container)
            .setOptionsContentViewGroupId(R.id.options_view)
            .setCallToActionButtonId(R.id.cta_button)
            .build()
    return MaxNativeAdView(binder, activity)
}

fun showNativeADS() {
    nativeAdLoader.loadAd(nativeAdView)
}




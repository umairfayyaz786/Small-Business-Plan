package com.example.ads.Classes

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.ads.Classes.Applications.App
import com.example.ads.Classes.Models.SharedModel
import com.example.ads.Classes.Utils.AppConstants
import com.example.ads.R
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


@SuppressLint("StaticFieldLeak")
private var remoteConfiguration: FirebaseRemoteConfig? = null

fun Application.remoteConfiguration() {
    remoteConfiguration = FirebaseRemoteConfig.getInstance()
    val remoteSetting = FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(4).build()
    remoteConfiguration!!.setConfigSettingsAsync(remoteSetting)
    remoteConfiguration!!.setDefaultsAsync(R.xml.ads_default_value)
}

fun Application.getRemoteValue(context: Context) {
    remoteConfiguration!!.fetchAndActivate().addOnCompleteListener { task ->
        if (task.isComplete) {
            // ADMOB value
            val ADMOB_APP_ID: String = remoteConfiguration!!.getString(AppConstants.ADMOB_APP_ID)
            val ADMOB_INTERSTITIAL_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.ADMOB_INTERSTITIAL_ADS_ID)
            val ADMOB_BANNER_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.ADMOB_BANNER_ADS_ID)
            val ADMOB_NATIVE_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.ADMOB_NATIVE_ADS_ID)
            val ADMOB_REWARD_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.ADMOB_REWARD_ADS_ID)
            val ADMOB_REWARD_INTERSTITIAL_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.ADMOB_REWARD_INTERSTITIAL_ADS_ID)
            val ADMOB_APP_OPEN_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.ADMOB_APP_OPEN_ADS_ID)
             // Applovin value
            val APPLOVIN_SDK_KEY: String =
                remoteConfiguration!!.getString(AppConstants.APPLOVIN_SDK_KEY)
            val APPLOVIN_INTERSTITIAL_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.APPLOVIN_INTERSTITIAL_ADS_ID)
            val APPLOVIN_BANNER_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.APPLOVIN_BANNER_ADS_ID)
            val APPLOVIN_REWARD_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.APPLOVIN_REWARD_ADS_ID)
            // max value
            val MAX_INTERSTITIAL_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.MAX_INTERSTITIAL_ADS_ID)
            val MAX_BANNER_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.MAX_BANNER_ADS_ID)
            val MAX_Native_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.MAX_Native_ADS_ID)
            val MAX_REWARD_ADS_ID: String =
                remoteConfiguration!!.getString(AppConstants.MAX_REWARD_ADS_ID)

            val adsVisible= remoteConfiguration!!.getLong(AppConstants.ADS_VISIBLE_KEY)
            val adsStrategy= remoteConfiguration!!.getLong(AppConstants.ADS_STRATEGY_KEY)
            Log.d("Tag", "getRemoteValue: $adsStrategy ")

            writeValue(
                sharedModel = SharedModel(
                    adMobAppKey = ADMOB_APP_ID,
                    adMobBannerID = ADMOB_BANNER_ADS_ID,
                    adMobInterstitialID = ADMOB_INTERSTITIAL_ADS_ID,
                    adMobRewardID = ADMOB_REWARD_ADS_ID,
                    adMobRewardInterstitialID = ADMOB_REWARD_INTERSTITIAL_ADS_ID,
                    adMobNativeID = ADMOB_NATIVE_ADS_ID,
                    adMobAppOpenID = ADMOB_APP_OPEN_ADS_ID,
                    appLovinSDKKey = APPLOVIN_SDK_KEY,
                    appLovinInterstitialID = APPLOVIN_INTERSTITIAL_ADS_ID,
                    appLovinBannerID = APPLOVIN_BANNER_ADS_ID,
                    appLovinRewardID = APPLOVIN_REWARD_ADS_ID,
                    maxInterstitialID = MAX_INTERSTITIAL_ADS_ID,
                    maxBannerID = MAX_BANNER_ADS_ID,
                    maxNativeID = MAX_Native_ADS_ID,
                    maxRewardID = MAX_REWARD_ADS_ID,
                    adsVisible=adsVisible,
                    adsStrategy=adsStrategy
                )
            )
            Log.d("Tag", "Fetch and activate succeeded:$ADMOB_APP_ID")
        } else {
            Log.d("Tag", "Failed")
        }
    }
}

// Message Show
private fun showMessage(msg: String, context: Context) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

private fun writeValue(sharedModel: SharedModel?) {
    sharedModel?.let {
        App.sharedPreference?.let {
            // AdMob Ads init Keys
            sharedModel.adMobAppKey?.let { adMobAppKey ->
                App.sharedPreference!!.stringValue(AppConstants.ADMOB_APP_ID, adMobAppKey)
                Log.d("Tag", "adMobAppKey")
            }
            sharedModel.adMobBannerID?.let { adMobBannerID ->
                App.sharedPreference!!.stringValue(AppConstants.ADMOB_BANNER_ADS_ID, adMobBannerID)
                Log.d("Tag", "adMobBannerID")
            }
            sharedModel.adMobInterstitialID?.let { adMobInterstitialID ->
                App.sharedPreference!!.stringValue(
                    AppConstants.ADMOB_INTERSTITIAL_ADS_ID,
                    adMobInterstitialID
                )
                Log.d("Tag", "adMobInterstitialID")
            }
            sharedModel.adMobRewardID?.let { adMobRewardID ->
                App.sharedPreference!!.stringValue(AppConstants.ADMOB_REWARD_ADS_ID, adMobRewardID)
                Log.d("Tag", "adMobRewardID")
            }
            sharedModel.adMobRewardInterstitialID?.let { adMobRewardInterstitialID ->
                App.sharedPreference!!.stringValue(
                    AppConstants.ADMOB_REWARD_INTERSTITIAL_ADS_ID,
                    adMobRewardInterstitialID
                )
                Log.d("Tag", "adMobRewardInterstitialID")
            }
            sharedModel.adMobNativeID?.let { adMobNativeID ->
                App.sharedPreference!!.stringValue(AppConstants.ADMOB_NATIVE_ADS_ID, adMobNativeID)
                Log.d("Tag", "adMobNativeID")
            }
            sharedModel.adMobAppOpenID?.let { adMobAppOpenID ->
                App.sharedPreference!!.stringValue(
                    AppConstants.ADMOB_APP_OPEN_ADS_ID,
                    adMobAppOpenID
                )
                Log.d("Tag", "adMobAppOpenID")
            }

            // AppLovin Ads Init Keys

            sharedModel.appLovinSDKKey?.let { appLovinSDKKey ->
                App.sharedPreference!!.stringValue(AppConstants.APPLOVIN_SDK_KEY, appLovinSDKKey)
                Log.d("Tag", "appLovinSDKKey")
            }
            sharedModel.appLovinBannerID?.let { appLovinBannerID ->
                App.sharedPreference!!.stringValue(
                    AppConstants.APPLOVIN_BANNER_ADS_ID,
                    appLovinBannerID
                )
                Log.d("Tag", "appLovinBannerID")
            }
            sharedModel.appLovinInterstitialID?.let { appLovinInterstitialID ->
                App.sharedPreference!!.stringValue(
                    AppConstants.APPLOVIN_INTERSTITIAL_ADS_ID,
                    appLovinInterstitialID
                )
                Log.d("Tag", "appLovinInterstitialID")
            }
            sharedModel.appLovinRewardID?.let { appLovinRewardID ->
                App.sharedPreference!!.stringValue(
                    AppConstants.APPLOVIN_REWARD_ADS_ID,
                    appLovinRewardID
                )
                Log.d("Tag", "appLovinRewardID")
            }

            // Max Ads init Keys

            sharedModel.maxInterstitialID?.let { maxInterstitialID ->
                App.sharedPreference!!.stringValue(
                    AppConstants.MAX_INTERSTITIAL_ADS_ID,
                    maxInterstitialID
                )
                Log.d("Tag", "maxInterstitialID")
            }
            sharedModel.maxBannerID?.let { maxBannerID ->
                App.sharedPreference!!.stringValue(AppConstants.MAX_BANNER_ADS_ID, maxBannerID)
                Log.d("Tag", "maxBannerID")
            }
            sharedModel.maxRewardID?.let { maxRewardID ->
                App.sharedPreference!!.stringValue(AppConstants.MAX_REWARD_ADS_ID, maxRewardID)
                Log.d("Tag", "maxRewardID")
            }
            sharedModel.maxNativeID?.let { maxNativeID ->
                App.sharedPreference!!.stringValue(AppConstants.MAX_Native_ADS_ID, maxNativeID)
                Log.d("Tag", "maxNativeID")
            }

            sharedModel.adsVisible?.let {
                App.sharedPreference!!.setIntValue(AppConstants.ADS_VISIBLE_KEY,it.toInt())
                Log.d("Tag", "adsVisible : $it")
            }
            sharedModel.adsStrategy?.let {
                App.sharedPreference!!.setIntValue(AppConstants.ADS_STRATEGY_KEY,it.toInt())
                Log.d("Tag", "adsStrategy: $it")
            }
        }

    }
}

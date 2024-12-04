package com.example.ads.Classes.Models

data class SharedModel(
    var adMobAppKey:String?=null,
    var adMobBannerID:String?=null,
    var adMobInterstitialID:String?=null,
    var adMobRewardID:String?=null,
    var adMobRewardInterstitialID:String?=null,
    var adMobNativeID:String?=null,
    var adMobAppOpenID:String?=null,
    var appLovinSDKKey:String?=null,
    var appLovinInterstitialID:String?=null,
    var appLovinBannerID:String?=null,
    var appLovinRewardID:String?=null,
    var maxInterstitialID:String?=null,
    var maxBannerID:String?=null,
    var maxNativeID:String?=null,
    var maxRewardID:String?=null,
    var adsVisible:Long?=null,
    var adsStrategy:Long?=null
)
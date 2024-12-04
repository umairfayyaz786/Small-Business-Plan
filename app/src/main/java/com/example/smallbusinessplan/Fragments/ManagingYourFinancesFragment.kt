package com.example.smallbusinessplan.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.example.ads.Classes.Strategies.bannerAds
import com.example.smallbusinessplan.Activities.Main
import com.example.smallbusinessplan.Extensions.gone
import com.example.smallbusinessplan.Extensions.visible
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.Utils.NetworkUtils


class ManagingYourFinancesFragment : Fragment() {
private lateinit var bannerAd:FrameLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_managing_your_finances, container, false)
        bannerAd=view.findViewById(R.id.ManagingBannerAd)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            bannerAd.visible()
            bannerAds(requireActivity(), bannerAd, "SMALL_BANNER")
        } else {
            bannerAd.gone()
        }
        val btnBack3: Button = view.findViewById(R.id.back)
        btnBack3.setOnClickListener {
            val i = Intent(requireContext() , Main::class.java)
            startActivity(i)
        }
    }
}
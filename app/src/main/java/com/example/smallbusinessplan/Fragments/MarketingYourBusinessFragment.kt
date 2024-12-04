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
import com.example.smallbusinessplan.databinding.FragmentManagingYourFinancesBinding
import com.example.smallbusinessplan.databinding.FragmentMarketingYourBusinessBinding


class MarketingYourBusinessFragment : Fragment() {
    private lateinit var binding: FragmentMarketingYourBusinessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMarketingYourBusinessBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            binding.MarketingBannerAd.visible()
            bannerAds(requireActivity(), binding.MarketingBannerAd, "SMALL_BANNER")
        } else {
            binding.MarketingBannerAd.gone()
        }
        binding.back.setOnClickListener {
            val i = Intent(requireContext(), Main::class.java)
            startActivity(i)
        }
    }
}
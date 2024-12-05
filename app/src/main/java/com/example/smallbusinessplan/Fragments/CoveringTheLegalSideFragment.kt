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
import com.example.smallbusinessplan.Activities.FormulasActivity
import com.example.smallbusinessplan.Activities.Main
import com.example.smallbusinessplan.Extensions.FragmentIntent
import com.example.smallbusinessplan.Extensions.gone
import com.example.smallbusinessplan.Extensions.visible
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.Utils.NetworkUtils
import com.example.smallbusinessplan.databinding.FragmentBusinessSalesBinding
import com.example.smallbusinessplan.databinding.FragmentCoveringTheLegalSideBinding


class CoveringTheLegalSideFragment : Fragment() {

    private lateinit var binding: FragmentCoveringTheLegalSideBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoveringTheLegalSideBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            binding.LegalBannerAd.visible()
            bannerAds(requireActivity(), binding.LegalBannerAd, "SMALL_BANNER")
        } else {
            binding.LegalBannerAd.gone()
        }
        binding.back.setOnClickListener {
            FragmentIntent(Main::class)
        }
    }
}
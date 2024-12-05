package com.example.smallbusinessplan.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import com.example.ads.Classes.Strategies.bannerAds
import com.example.smallbusinessplan.Extensions.ActivityIntent
import com.example.smallbusinessplan.Extensions.Calculator
import com.example.smallbusinessplan.Extensions.gone
import com.example.smallbusinessplan.Extensions.visible
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.Utils.SharedPref
import com.example.smallbusinessplan.Utils.AppConstants
import com.example.smallbusinessplan.Utils.NetworkUtils
import com.example.smallbusinessplan.databinding.ActivityMarketBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class Market : AppCompatActivity() {
    private var MainMenu: Menu? = null
    private lateinit var sharedPref: SharedPref
    private lateinit var binding: ActivityMarketBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = Html.fromHtml(getString(R.string.small_market_validity_small))
        sharedPref = SharedPref(this)
        if (NetworkUtils.isNetworkAvailable(this)) {
            binding.MarketBannerAd.visible()
            bannerAds(this, binding.MarketBannerAd, getString(R.string.small_banner))
        } else {
            binding.MarketBannerAd.gone()
        }
        if (sharedPref.getStringValue(AppConstants.GEOGRAPHIC) == AppConstants.YES) {
            binding.Narrowradio.isChecked = true
            binding.Wideradio.isChecked = false
        } else {
            binding.Narrowradio.isChecked = false
            binding.Wideradio.isChecked = true
        }
        binding.Narrowradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.GEOGRAPHIC, AppConstants.YES)
        }
        binding.Wideradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.GEOGRAPHIC, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.POTENTIAL) == AppConstants.YES) {
            binding.Specificradio.isChecked = true
            binding.Everyoneradio.isChecked = false
        } else {
            binding.Specificradio.isChecked = false
            binding.Everyoneradio.isChecked = true
        }
        binding.Specificradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.POTENTIAL, AppConstants.YES)
        }
        binding.Everyoneradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.POTENTIAL, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.PRODUCTS) == AppConstants.YES) {
            binding.Fewradio.isChecked = true
            binding.Manyradio.isChecked = false
        } else {
            binding.Fewradio.isChecked = false
            binding.Manyradio.isChecked = true
        }
        binding.Fewradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPANY, AppConstants.YES)
        }
        binding.Manyradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPANY, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.COMPETITORS) == AppConstants.YES) {
            binding.Lowerradio.isChecked = true
            binding.Higherradio.isChecked = false
        } else {
            binding.Lowerradio.isChecked = false
            binding.Higherradio.isChecked = true
        }
        binding.Lowerradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPETITORS, AppConstants.YES)
        }
        binding.Higherradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPETITORS, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.QUALITY) == AppConstants.YES) {
            this.binding.Similarradio.isChecked = true
            binding.Betterradio.isChecked = false
        } else {
            binding.Similarradio.isChecked = false
            binding.Betterradio.isChecked = true
        }
        binding.Similarradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.QUALITY, AppConstants.YES)
        }
        binding.Betterradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.QUALITY, AppConstants.NO)
        }

        binding.formula.setOnClickListener {
            ActivityIntent(FormulasActivity::class)
        }
        binding.btncalculate.setOnClickListener {
            if (binding.Narrowradio.isChecked && binding.Specificradio.isChecked && binding.Fewradio.isChecked && binding.Lowerradio.isChecked && binding.Similarradio.isChecked) {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    getString(R.string.a_narrow) + getString(R.string.offering) + getString(R.string.providing)
                )
                binding.outputofMarketcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofMarketcalculation.setTextColor(Color.GRAY)
            } else if (binding.Wideradio.isChecked && binding.Specificradio.isChecked && binding.Fewradio.isChecked && binding.Lowerradio.isChecked && binding.Similarradio.isChecked) {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    getString(R.string.targetting) + getString(R.string.offering) + getString(R.string.providing)
                )
                binding.outputofMarketcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofMarketcalculation.setTextColor(Color.GRAY)
            } else if (binding.Wideradio.isChecked && binding.Everyoneradio.isChecked && binding.Manyradio.isChecked && binding.Higherradio.isChecked && binding.Betterradio.isChecked) {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    getString(R.string.combination) + getString(R.string.offering) + getString(R.string.providing)
                )
                binding.outputofMarketcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofMarketcalculation.setTextColor(Color.GRAY)
            } else if (binding.Narrowradio.isChecked && binding.Everyoneradio.isChecked || binding.Specificradio.isChecked && binding.Manyradio.isChecked && binding.Higherradio.isChecked && binding.Betterradio.isChecked) {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    getString(R.string.a_narrow) + getString(R.string.offering) + getString(R.string.providing)
                )
                binding.outputofMarketcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofMarketcalculation.setTextColor(Color.GRAY)
            } else if (binding.Wideradio.isChecked && binding.Specificradio.isChecked && binding.Manyradio.isChecked && binding.Higherradio.isChecked && binding.Betterradio.isChecked) {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    getString(R.string.targetting) + getString(R.string.offering) + getString(R.string.providing)
                )
                binding.outputofMarketcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofMarketcalculation.setTextColor(Color.GRAY)
            } else {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    getString(R.string.targetting) + getString(R.string.providing)
                )
                binding.outputofMarketcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofMarketcalculation.setTextColor(Color.GRAY)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            ActivityIntent(Calculator::class)
        }
        if (item.itemId == R.id.ReviewsAction) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.kachariya.smallbusinessplan")
                )
            )
        }
        if (item.itemId == R.id.QuestionAction) {
            showBottomSheetDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    fun showBottomSheetDialog() {

        val bottomSheetDialog = BottomSheetDialog(this)
        val viewLayout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet, null)
        bottomSheetDialog.setContentView(viewLayout)
        bottomSheetDialog.show()
        val close: TextView = viewLayout.findViewById(R.id.close_bottom_sheet)
        close.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MainMenu = menu
        menuInflater.inflate(R.menu.calculator_activities_items, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onBackPressed() {
        ActivityIntent(Calculator::class)
    }
}
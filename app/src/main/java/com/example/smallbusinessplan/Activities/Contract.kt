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
import com.example.smallbusinessplan.databinding.ActivityContractBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class Contract : AppCompatActivity() {
    private lateinit var bannerAd: FrameLayout
    private lateinit var sharedPref: SharedPref
    private var MainMenu: Menu? = null
    private lateinit var binding: ActivityContractBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContractBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = Html.fromHtml(getString(R.string.small_contract_validity_small))

        sharedPref = SharedPref(this)

        bannerAd = findViewById(R.id.ContractBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, getString(R.string.small_banner))
        } else {
            bannerAd.gone()
        }

        binding.formula.setOnClickListener {
            ActivityIntent(FormulasActivity::class)
        }
        binding.outputofcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
        if (sharedPref.getStringValue(AppConstants.SERVICE) == AppConstants.YES) {
            binding.yesradio1.isChecked = true
            binding.noradio1.isChecked = false
        } else {
            binding.yesradio1.isChecked = false
            binding.noradio1.isChecked = true
        }
        binding.yesradio1.setOnClickListener {
            sharedPref.setStringValue(AppConstants.SERVICE, AppConstants.YES)
        }
        binding.noradio1.setOnClickListener {
            sharedPref.setStringValue(AppConstants.SERVICE, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.AWFULL) == AppConstants.YES) {
            binding.yesradio2.isChecked = true
            binding.noradio2.isChecked = false
        } else {
            binding.yesradio2.isChecked = false
            binding.noradio2.isChecked = true
        }
        binding.yesradio2.setOnClickListener {
            sharedPref.setStringValue(AppConstants.AWFULL, AppConstants.YES)
        }
        binding.noradio2.setOnClickListener {
            sharedPref.setStringValue(AppConstants.AWFULL, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.COMPANY) == AppConstants.YES) {
            binding.yesradio3.isChecked = true
            binding.noradio3.isChecked = false
        } else {
            binding.yesradio3.isChecked = false
            binding.noradio3.isChecked = true
        }
        binding.yesradio3.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPANY, AppConstants.YES)
        }
        binding.noradio3.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPANY, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.OFFERED) == AppConstants.YES) {
            binding.yesradio4.isChecked = true
            binding.noradio4.isChecked = false
        } else {
            binding.yesradio4.isChecked = false
            binding.noradio4.isChecked = true
        }
        binding.yesradio4.setOnClickListener {
            sharedPref.setStringValue(AppConstants.OFFERED, AppConstants.YES)
        }
        binding.noradio4.setOnClickListener {
            sharedPref.setStringValue(AppConstants.OFFERED, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.FRIENDS) == AppConstants.YES) {
            binding.yesradio5.isChecked = true
            binding.noradio5.isChecked = false
        } else {
            binding.yesradio5.isChecked = false
            binding.noradio5.isChecked = true
        }
        binding.yesradio5.setOnClickListener {
            sharedPref.setStringValue(AppConstants.FRIENDS, AppConstants.YES)
        }
        binding.noradio5.setOnClickListener {
            sharedPref.setStringValue(AppConstants.FRIENDS, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.AGREEMENT) == AppConstants.YES) {
            binding.yesradio6.isChecked = true
            binding.noradio6.isChecked = false
        } else {
            binding.yesradio6.isChecked = false
            binding.noradio6.isChecked = true
        }
        binding.yesradio6.setOnClickListener {
            sharedPref.setStringValue(AppConstants.AGREEMENT, AppConstants.YES)
        }
        binding.noradio6.setOnClickListener {
            sharedPref.setStringValue(AppConstants.AGREEMENT, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.DURATION) == AppConstants.YES) {
            binding.yesradio7.isChecked = true
            binding.noradio7.isChecked = false
        } else {
            binding.yesradio7.isChecked = false
            binding.noradio7.isChecked = true
        }
        binding.yesradio7.setOnClickListener {
            sharedPref.setStringValue(AppConstants.DURATION, AppConstants.YES)
        }
        binding.noradio7.setOnClickListener {
            sharedPref.setStringValue(AppConstants.DURATION, AppConstants.NO)
        }

        binding.btncalculate.setOnClickListener {
            if (binding.yesradio1.isChecked && binding.yesradio2.isChecked && binding.yesradio3.isChecked && binding.yesradio4.isChecked && binding.yesradio6.isChecked && binding.yesradio5.isChecked && binding.yesradio7.isChecked) {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    getString(R.string.probably_a_valid_contract_but_may_be_hard_to_enforce)
                )
                binding.outputofcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofcalculation.setTextColor(Color.GRAY)
            } else if (binding.yesradio1.isChecked && binding.yesradio2.isChecked && binding.yesradio3.isChecked && binding.yesradio4.isChecked && binding.yesradio5.isChecked && binding.yesradio5.isChecked || binding.noradio5.isChecked && binding.yesradio7.isChecked || binding.noradio5.isChecked) {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    getString(R.string.probably_a_valid_contract_that_is_enforceable)
                )
                binding.outputofcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofcalculation.setTextColor(Color.GRAY)
            } else {
                sharedPref.setStringValue(AppConstants.RESULT,
                    getString(R.string.probably_invalid_contract))
                binding.outputofcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofcalculation.setTextColor(Color.RED)
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
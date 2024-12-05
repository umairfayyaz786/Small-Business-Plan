package com.example.smallbusinessplan.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.example.ads.Classes.Strategies.bannerAds
import com.example.smallbusinessplan.Extensions.ActivityIntent
import com.example.smallbusinessplan.Extensions.Calculator
import com.example.smallbusinessplan.Extensions.gone
import com.example.smallbusinessplan.Extensions.showToast
import com.example.smallbusinessplan.Extensions.visible
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.Utils.SharedPref
import com.example.smallbusinessplan.Utils.AppConstants
import com.example.smallbusinessplan.Utils.NetworkUtils
import com.example.smallbusinessplan.databinding.ActivityGstVatBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class Gst_Vat : AppCompatActivity() {
    private lateinit var bannerAd: FrameLayout
    private var MainMenu: Menu? = null
    private lateinit var sharedPref: SharedPref
    private lateinit var binding: ActivityGstVatBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGstVatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = Html.fromHtml(getString(R.string.small_gst_vat_small))
        sharedPref = SharedPref(this)

        bannerAd = findViewById(R.id.GstBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, getString(R.string.small_gross_profit_small))
        } else {
            bannerAd.gone()
        }
        binding.tax1.setText(sharedPref.getIntegerValue(AppConstants.TEXT_KEY).toString())
        binding.taxPercent.setText(sharedPref.getIntegerValue(AppConstants.TEXT_KEY).toString())

        binding.calculate.setOnClickListener {
            if (binding.salesNetPrice.text.toString()
                    .isEmpty() || binding.salesNetPrice.text.toString().isBlank()
            ) {
                showToast(getString(R.string.sales_net_price_must_be_required))
                return@setOnClickListener
            }
            if (binding.tax1.text.toString().isEmpty() || binding.tax1.text.toString().isBlank()) {
                showToast(getString(R.string.gst_vst_percent_must_be_required))
                return@setOnClickListener
            }

            val sales_net_pricee = binding.salesNetPrice.text.toString().toDouble()
            val tax11 = binding.tax1.text.toString().toDouble()

            val tax_one = ((sales_net_pricee) * (tax11 / 100))
            binding.tax1Result.text = tax_one.toString()
            sharedPref.setIntegerValue(AppConstants.TEXT_KEY, tax_one)

            val salesgrossprice = sales_net_pricee + tax_one
            binding.salesgrosspriceResult.text = salesgrossprice.toString()
        }

        binding.calculate2.setOnClickListener {
            if (binding.salesGrossProfit.text.toString()
                    .isEmpty() || binding.salesGrossProfit.text.toString().isBlank()
            ) {
                showToast(getString(R.string.sales_gross_price_must_be_required))
                return@setOnClickListener
            }
            if (binding.taxPercent.text.toString().isEmpty() || binding.taxPercent.text.toString()
                    .isBlank()
            ) {
                showToast(getString(R.string.gst_vst_percent_must_be_required))
                return@setOnClickListener
            }
            val sales_gross_profitt = binding.salesGrossProfit.text.toString().toDouble()
            val tax_percentt = binding.taxPercent.text.toString().toDouble()

            val tax_two =
                ((sales_gross_profitt) - (sales_gross_profitt / (1 + (tax_percentt / 100))))
            binding.tax2Result.text = tax_two.toString()
            sharedPref.setIntegerValue(AppConstants.TEXT_KEY, tax_two)

            val salesnetprice = sales_gross_profitt - tax_two
            binding.salesnetpriceResult.text = salesnetprice.toString()
        }
        binding.formula.setOnClickListener {
            ActivityIntent(FormulasActivity::class)
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
}
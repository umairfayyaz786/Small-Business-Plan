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
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.example.ads.Classes.Strategies.bannerAds
import com.example.smallbusinessplan.Extensions.Calculator
import com.example.smallbusinessplan.Extensions.gone
import com.example.smallbusinessplan.Extensions.visible
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.SharedPref
import com.example.smallbusinessplan.Utils.AppConstants
import com.example.smallbusinessplan.Utils.NetworkUtils
import com.example.smallbusinessplan.databinding.ActivityMarkupBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class Markup : AppCompatActivity() {
    private lateinit var bannerAd: FrameLayout
    private var MainMenu: Menu? = null
    private lateinit var sharedPref: SharedPref
    private lateinit var binding: ActivityMarkupBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarkupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = Html.fromHtml("<small>MARKUP</small>")
        sharedPref = SharedPref(this)

        bannerAd = findViewById(R.id.MarkupBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, "SMALL_BANNER")
        } else {
            bannerAd.gone()
        }
        binding.tax.setText(sharedPref.getIntegerValue(AppConstants.TEXT_KEY).toString())
        binding.calculate.setOnClickListener {
            if (binding.costPrice.text.toString().isEmpty() || binding.costPrice.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(this, "Cost Price must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.Markuppercent.text.toString()
                    .isEmpty() || binding.Markuppercent.text.toString().isBlank()
            ) {
                Toast.makeText(this, "Markup % must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.tax.text.toString().isEmpty() || binding.tax.text.toString().isBlank()) {
                Toast.makeText(this, "GST/VST % must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cost_pricee = binding.costPrice.text.toString().toDouble()
            val Markuppercentt = binding.Markuppercent.text.toString().toDouble()
            val taxxx = binding.tax.text.toString().toDouble()
            sharedPref.setIntegerValue(AppConstants.TEXT_KEY, taxxx)
            val salesnetprice = (cost_pricee + (cost_pricee * (Markuppercentt / 100)))
            binding.SalesnetpriceResult.text = salesnetprice.toString()
            val tax = salesnetprice * (taxxx / 100)
            binding.taxResult.text = tax.toString()
            val Marginpercent = ((salesnetprice - cost_pricee) / salesnetprice) * 100
            binding.MarginpercentResult.text = Marginpercent.toString()
            val Grossprofitpercent = (salesnetprice - cost_pricee)
            binding.GrossprofittResult.text = Grossprofitpercent.toString()
            val SalesGrossPrice = (salesnetprice + tax)
            binding.SalesgrosspriceResult.text = SalesGrossPrice.toString()
        }

        binding.formula.setOnClickListener {
            val i = Intent(this, FormulasActivity::class.java)
            startActivity(i)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val i = Intent(this, Calculator::class.java)
            startActivity(i)
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
        val intent = Intent(this, Calculator::class.java)
        startActivity(intent)
    }
}
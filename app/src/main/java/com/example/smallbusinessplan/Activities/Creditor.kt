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
import com.example.smallbusinessplan.Extensions.ActivityIntent
import com.example.smallbusinessplan.Extensions.Calculator
import com.example.smallbusinessplan.Extensions.gone
import com.example.smallbusinessplan.Extensions.showToast
import com.example.smallbusinessplan.Extensions.visible
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.Utils.NetworkUtils
import com.example.smallbusinessplan.databinding.ActivityCreditorBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class Creditor : AppCompatActivity() {
    private lateinit var bannerAd: FrameLayout
    private var MainMenu: Menu? = null
    private lateinit var binding: ActivityCreditorBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = Html.fromHtml(getString(R.string.small_creditor_small))


        bannerAd = findViewById(R.id.CreditorBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, getString(R.string.small_banner))
        } else {
            bannerAd.gone()
        }

        binding.calculate.setOnClickListener {
            if (binding.sales.text.toString().isEmpty() || binding.sales.text.toString()
                    .isBlank()
            ) {
                showToast(getString(R.string.sales_must_be_required))
                return@setOnClickListener
            }
            if (binding.days.text.toString().isEmpty() || binding.days.text.toString().isBlank()) {
                showToast(getString(R.string.supplier_days_credit_must_be_required))
                return@setOnClickListener
            }
            val Cost_of_sales_per_year = binding.sales.text.toString().toDouble()
            val Days_credit = binding.days.text.toString().toDouble()
            val creditors = (((Cost_of_sales_per_year) / 365) * Days_credit)
            binding.creditorResult.text = creditors.toString()
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

    override fun onBackPressed() {
        ActivityIntent(Calculator::class)
    }
}
package com.example.smallbusinessplan.Activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.ads.Classes.Strategies.bannerAds
import com.example.smallbusinessplan.Extensions.ActivityIntent
import com.example.smallbusinessplan.Extensions.Calculator
import com.example.smallbusinessplan.Extensions.gone
import com.example.smallbusinessplan.Extensions.showToast
import com.example.smallbusinessplan.Extensions.visible
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.Utils.NetworkUtils
import com.example.smallbusinessplan.databinding.ActivityCashBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class Cash : AppCompatActivity() {
    private lateinit var bannerAd: FrameLayout
    private var MainMenu: Menu? = null
    private lateinit var binding: ActivityCashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = Html.fromHtml(getString(R.string.small_cash_small))
        bannerAd = findViewById(R.id.CashBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, getString(R.string.small_banner))
        } else {
            bannerAd.gone()
        }

        binding.calculate.setOnClickListener {
            if (binding.saless.text.toString().isEmpty() || binding.saless.text.toString()
                    .isBlank()
            ) {
                showToast(getString(R.string.sales_must_be_required))
                return@setOnClickListener
            }
            if (binding.days.text.toString().isEmpty() || binding.days.text.toString().isBlank()) {
                showToast(getString(R.string.customer_days_credit_must_be_required))
                return@setOnClickListener
            }
            if (binding.CostOfSaless.text.toString()
                    .isEmpty() || binding.CostOfSaless.text.toString().isBlank()
            ) {
                showToast(getString(R.string.cost_of_sales_must_be_required))
                return@setOnClickListener
            }
            if (binding.Expenses.text.toString().isEmpty() || binding.Expenses.text.toString()
                    .isBlank()
            ) {
                showToast(getString(R.string.expenses_must_be_required))
                return@setOnClickListener
            }
            if (binding.SuplierDaysCredit.text.toString()
                    .isEmpty() || binding.SuplierDaysCredit.text.toString().isBlank()
            ) {
                showToast(getString(R.string.supplier_days_credit_must_be_required))
                return@setOnClickListener
            }
            val sales_per_year = binding.saless.text.toString().toDouble()
            val Customer_Days_credit = binding.days.text.toString().toDouble()
            val Cost_of_sales_per_year = binding.CostOfSaless.text.toString().toDouble()
            val Expens = binding.Expenses.text.toString().toDouble()
            val Supplier_days_creditt = binding.SuplierDaysCredit.text.toString().toDouble()


            val Debtors = (((sales_per_year) / 365) * Customer_Days_credit) * -1
            binding.DebtorsResult.text = Debtors.toString()

            val creditors = (((Cost_of_sales_per_year) / 365) * Supplier_days_creditt)
            binding.CreditorResult.text = creditors.toString()

            val OperatingProfit = sales_per_year - Cost_of_sales_per_year - Expens

            val netCash = Debtors + creditors + OperatingProfit
            binding.NetcashResult.text = netCash.toString()
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
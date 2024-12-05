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
import com.example.smallbusinessplan.databinding.ActivityNetProfitBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class NetProfit : AppCompatActivity() {
    private lateinit var bannerAd: FrameLayout
    private var MainMenu: Menu? = null
    private lateinit var binding: ActivityNetProfitBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetProfitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = Html.fromHtml("<small>NET PROFIT</small>")

        bannerAd = findViewById(R.id.NetProfitBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, "SMALL_BANNER")
        } else {
            bannerAd.gone()
        }
        binding.calculate.setOnClickListener {
            if (binding.Saless.text.toString().isEmpty() || binding.Saless.text.toString()
                    .isBlank()
            ) {
                showToast(getString(R.string.sales_must_be_required))
                return@setOnClickListener
            }
            if (binding.COstOfSalesss.text.toString()
                    .isEmpty() || binding.COstOfSalesss.text.toString().isBlank()
            ) {
                showToast(getString(R.string.cost_of_sales_credit_must_be_required))
                return@setOnClickListener
            }
            if (binding.Depreciation.text.toString()
                    .isEmpty() || binding.Depreciation.text.toString().isBlank()
            ) {
                showToast(getString(R.string.depreciation_must_be_required))
                return@setOnClickListener
            }
            if (binding.Expenses.text.toString().isEmpty() || binding.Expenses.text.toString()
                    .isBlank()
            ) {
                showToast(getString(R.string.expenses_must_be_required))
                return@setOnClickListener
            }
            val sales_per_year = binding.Saless.text.toString().toDouble()
            val Cost_of_sales_per_year = binding.COstOfSalesss.text.toString().toDouble()
            val Expense = binding.Expenses.text.toString().toDouble()
            val Dep = binding.Depreciation.text.toString().toDouble()


            val Gross_Profit = sales_per_year - Cost_of_sales_per_year

            val Total_Expenses = Expense + Dep

            val Net_Profitt = Gross_Profit - Total_Expenses
            binding.NetProfit.text = Net_Profitt.toString()

            val Net_Profit_Percentage = (Net_Profitt / sales_per_year) * 100
            binding.NetProfitPercent.text = Net_Profit_Percentage.toString()
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
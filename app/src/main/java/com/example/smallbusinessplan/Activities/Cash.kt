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
import com.example.smallbusinessplan.Extensions.Calculator
import com.example.smallbusinessplan.Extensions.gone
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
        supportActionBar?.title = Html.fromHtml("<small>CASH</small>")


        bannerAd = findViewById(R.id.CashBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, "SMALL_BANNER")
        } else {
            bannerAd.gone()
        }

        binding.calculate.setOnClickListener {
            if (binding.saless.text.toString().isEmpty() || binding.saless.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(this, "Sales must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.days.text.toString().isEmpty() || binding.days.text.toString().isBlank()) {
                Toast.makeText(this, "Customer Days Credit must be required!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (binding.CostOfSaless.text.toString()
                    .isEmpty() || binding.CostOfSaless.text.toString().isBlank()
            ) {
                Toast.makeText(this, "Cost of Sales must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.Expenses.text.toString().isEmpty() || binding.Expenses.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(this, "Expenses must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.SuplierDaysCredit.text.toString()
                    .isEmpty() || binding.SuplierDaysCredit.text.toString().isBlank()
            ) {
                Toast.makeText(this, "Supplier Days Credit must be required!", Toast.LENGTH_SHORT)
                    .show()
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
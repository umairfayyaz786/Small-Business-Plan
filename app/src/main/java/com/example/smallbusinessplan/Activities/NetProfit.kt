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
import com.example.smallbusinessplan.Utils.NetworkUtils
import com.google.android.material.bottomsheet.BottomSheetDialog

class NetProfit : AppCompatActivity() {
    private lateinit var bannerAd:FrameLayout
    private var MainMenu: Menu? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_profit)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title= Html.fromHtml("<small>NET PROFIT</small>")

        bannerAd=findViewById(R.id.NetProfitBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, "SMALL_BANNER")
        } else {
            bannerAd.gone()
        }

        val sales: EditText = findViewById(R.id.Saless)
        val CostOfSales: EditText = findViewById(R.id.COstOfSalesss)
        val Expenses: EditText = findViewById(R.id.Expenses)
        val Depreciation: EditText = findViewById(R.id.Depreciation)
        val Result: Button = findViewById(R.id.calculate)
        val NetProfit: TextView = findViewById(R.id.Net_profit)
        val NetProfitPercent: TextView = findViewById(R.id.Net_profit_percent)

        Result.setOnClickListener {
            if (sales.text.toString().isEmpty() || sales.text.toString().isBlank()){
                Toast.makeText(this, "Sales must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (CostOfSales.text.toString().isEmpty() || CostOfSales.text.toString().isBlank()){
                Toast.makeText(this, "Cost of Sales Credit must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Depreciation.text.toString().isEmpty() || Depreciation.text.toString().isBlank()){
                Toast.makeText(this, "Depreciation must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Expenses.text.toString().isEmpty() || Expenses.text.toString().isBlank()){
                Toast.makeText(this, "Expenses must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val sales_per_year = sales.text.toString().toDouble()
            val Cost_of_sales_per_year = CostOfSales.text.toString().toDouble()
            val Expense = Expenses.text.toString().toDouble()
            val Dep = Depreciation.text.toString().toDouble()


            val Gross_Profit = sales_per_year - Cost_of_sales_per_year

            val Total_Expenses = Expense + Dep

            val Net_Profitt = Gross_Profit - Total_Expenses
            NetProfit.text = Net_Profitt.toString()

            val Net_Profit_Percentage = (Net_Profitt / sales_per_year) * 100
            NetProfitPercent.text = Net_Profit_Percentage.toString()
        }
        val formulaBtn:Button = findViewById(R.id.formula)
        formulaBtn.setOnClickListener {
            val i = Intent(this , FormulasActivity::class.java)
            startActivity(i)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            val i = Intent(this , Calculator::class.java)
            startActivity(i)
        }
        if (item.itemId == R.id.ReviewsAction){
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
    fun showBottomSheetDialog(){

        val bottomSheetDialog = BottomSheetDialog(this)
        val  viewLayout= LayoutInflater.from(this).inflate(R.layout.bottom_sheet,null)
        bottomSheetDialog.setContentView(viewLayout)
        bottomSheetDialog.show()
        val close:TextView = viewLayout.findViewById(R.id.close_bottom_sheet)
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
        val intent = Intent(this , Calculator::class.java)
        startActivity(intent)
    }
}
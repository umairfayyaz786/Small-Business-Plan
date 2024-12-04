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
import com.google.android.material.bottomsheet.BottomSheetDialog

class Cash : AppCompatActivity() {
    private lateinit var bannerAd: FrameLayout
    private var MainMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title= Html.fromHtml("<small>CASH</small>")
//        actionBar?.title = "Cash"
        val saless: EditText = findViewById(R.id.saless)
        val days: EditText = findViewById(R.id.days)
        val CostOfSales: EditText = findViewById(R.id.Cost_of_saless)
        val Expenses: EditText = findViewById(R.id.Expenses)
        val SupplierDaysCredit: EditText = findViewById(R.id.Suplier_Days_credit)
        val Result: Button = findViewById(R.id.calculate)
        val Debtor: TextView = findViewById(R.id.Debtors_result)
        val Creditors: TextView = findViewById(R.id.Creditor_result)
        val NetCash: TextView = findViewById(R.id.Netcash_result)
        bannerAd=findViewById(R.id.CashBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, "SMALL_BANNER")
        } else {
            bannerAd.gone()
        }

        Result.setOnClickListener {
            if (saless.text.toString().isEmpty() || saless.text.toString().isBlank()){
                Toast.makeText(this, "Sales must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (days.text.toString().isEmpty() || days.text.toString().isBlank()){
                Toast.makeText(this, "Customer Days Credit must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (CostOfSales.text.toString().isEmpty() || CostOfSales.text.toString().isBlank()){
                Toast.makeText(this, "Cost of Sales must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Expenses.text.toString().isEmpty() || Expenses.text.toString().isBlank()){
                Toast.makeText(this, "Expenses must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (SupplierDaysCredit.text.toString().isEmpty() || SupplierDaysCredit.text.toString().isBlank()){
                Toast.makeText(this, "Supplier Days Credit must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val sales_per_year = saless.text.toString().toDouble()
            val Customer_Days_credit = days.text.toString().toDouble()
            val Cost_of_sales_per_year = CostOfSales.text.toString().toDouble()
            val Expens = Expenses.text.toString().toDouble()
            val Supplier_days_creditt = SupplierDaysCredit.text.toString().toDouble()


            val Debtors = (((sales_per_year) / 365) * Customer_Days_credit) * -1
            Debtor.text = Debtors.toString()

            val creditors =  (((Cost_of_sales_per_year)/365)* Supplier_days_creditt)
            Creditors.text = creditors.toString()

            val OperatingProfit = sales_per_year - Cost_of_sales_per_year - Expens

            val netCash = Debtors + creditors + OperatingProfit
            NetCash.text = netCash.toString()
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
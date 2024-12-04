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
import com.google.android.material.bottomsheet.BottomSheetDialog

class Gst_Vat : AppCompatActivity() {
    private lateinit var bannerAd:FrameLayout
    private var MainMenu: Menu? = null
    private lateinit var sharedPref: SharedPref

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gst_vat)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title= Html.fromHtml("<small>GST/VAT</small>")

        sharedPref = SharedPref(this)

        bannerAd=findViewById(R.id.GstBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, "SMALL_BANNER")
        } else {
            bannerAd.gone()
        }

        val sales_net_price: EditText = findViewById(R.id.sales_net_price)
        val tax1: EditText = findViewById(R.id.tax1)
        tax1.setText(sharedPref.getIntegerValue(AppConstants.TEXT_KEY).toString())
        val Result: Button = findViewById(R.id.calculate)
        val tax1_result: TextView = findViewById(R.id.tax1_result)
        val salesgrossprice_result: TextView = findViewById(R.id.salesgrossprice_result)

        val sales_gross_profit: EditText = findViewById(R.id.sales_gross_profit)
        val tax_percent: EditText = findViewById(R.id.tax_percent)
        tax_percent.setText(sharedPref.getIntegerValue(AppConstants.TEXT_KEY).toString())
        val Result2: Button = findViewById(R.id.calculate2)
        val tax2_result: TextView = findViewById(R.id.tax2_result)
        val salesnetprice_result: TextView = findViewById(R.id.salesnetprice_result)

        Result.setOnClickListener {
            if (sales_net_price.text.toString().isEmpty() || sales_net_price.text.toString().isBlank()){
                Toast.makeText(this, "Sales Net Price must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (tax1.text.toString().isEmpty() || tax1.text.toString().isBlank()){
                Toast.makeText(this, "GST/VST % must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sales_net_pricee = sales_net_price.text.toString().toDouble()
            val tax11 = tax1.text.toString().toDouble()

            val tax_one = ((sales_net_pricee) * (tax11 / 100))
            tax1_result.text = tax_one.toString()
            sharedPref.setIntegerValue(AppConstants.TEXT_KEY,tax_one)

            val salesgrossprice = sales_net_pricee + tax_one
            salesgrossprice_result.text = salesgrossprice.toString()
        }

        Result2.setOnClickListener {
            if (sales_gross_profit.text.toString().isEmpty() || sales_gross_profit.text.toString().isBlank()){
                Toast.makeText(this, "Sales Gross Price must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (tax_percent.text.toString().isEmpty() || tax_percent.text.toString().isBlank()){
                Toast.makeText(this, "GST/VST % must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val sales_gross_profitt = sales_gross_profit.text.toString().toDouble()
            val tax_percentt = tax_percent.text.toString().toDouble()

            val tax_two = ((sales_gross_profitt) - (sales_gross_profitt / (1 + (tax_percentt / 100))))
            tax2_result.text = tax_two.toString()
            sharedPref.setIntegerValue(AppConstants.TEXT_KEY,tax_two)

            val salesnetprice = sales_gross_profitt - tax_two
            salesnetprice_result.text = salesnetprice.toString()
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
}
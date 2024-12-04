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

class Discount : AppCompatActivity() {
    private lateinit var bannerAd:FrameLayout
    private var MainMenu: Menu? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discount)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title= Html.fromHtml("<small>DISCOUNT</small>")


        val normalprice1: EditText = findViewById(R.id.normalprice1)
        val DiscountPercent: EditText = findViewById(R.id.DiscountPercent)
        val normalprice2: EditText = findViewById(R.id.normalprice2)
        val Discount2: EditText = findViewById(R.id.DiscountPercent2)

        val Discounttv: TextView = findViewById(R.id.Discount)
        val Discount_Price1tv: TextView = findViewById(R.id.Discount_Price2)
        val Discount_Price2tv: TextView = findViewById(R.id.Discount_Price)
        val DiscountPercenttv: TextView = findViewById(R.id.DiscountPercent3)

        val Result1: Button = findViewById(R.id.calculate)
        val Result2: Button = findViewById(R.id.calculate2)
        bannerAd=findViewById(R.id.DiscountBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, "SMALL_BANNER")
        } else {
            bannerAd.gone()
        }
        Result1.setOnClickListener {
            if (normalprice1.text.toString().isEmpty() || normalprice1.text.toString().isBlank()){
                Toast.makeText(this, "Normal Price must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (DiscountPercent.text.toString().isEmpty() || DiscountPercent.text.toString().isBlank()){
                Toast.makeText(this, "Discount % must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val normalprice11 = normalprice1.text.toString().toDouble()
            val DiscountPercent11 = DiscountPercent.text.toString().toDouble()

            val Discount = ((normalprice11) * (DiscountPercent11 / 100))
            Discounttv.text = Discount.toString()
            val Discprice = ((normalprice11) - (normalprice11 * (DiscountPercent11 / 100)))
            Discount_Price1tv.text = Discprice.toString()

        }
        Result2.setOnClickListener {
            if (normalprice2.text.toString().isEmpty() || normalprice2.text.toString().isBlank()){
                Toast.makeText(this, "Normal Price must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Discount2.text.toString().isEmpty() || Discount2.text.toString().isBlank()){
                Toast.makeText(this, "Discount must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val normalprice22 = normalprice2.text.toString().toDouble()
            val Discount22 = Discount2.text.toString().toDouble()

            val Discprice2 = normalprice22 - Discount22
            Discount_Price2tv.text = Discprice2.toString()
            val Discount_percent = ((Discount22 / (normalprice22) * 100))
            DiscountPercenttv.text = Discount_percent.toString()
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
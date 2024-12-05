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
import com.example.smallbusinessplan.databinding.ActivityDiscountBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class Discount : AppCompatActivity() {
    private lateinit var bannerAd:FrameLayout
    private var MainMenu: Menu? = null
    private lateinit var binding: ActivityDiscountBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title= Html.fromHtml(getString(R.string.small_discount_small))

        bannerAd=findViewById(R.id.DiscountBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, getString(R.string.small_banner))
        } else {
            bannerAd.gone()
        }
        binding.calculate.setOnClickListener {
            if (binding.normalprice1.text.toString().isEmpty() || binding.normalprice1.text.toString().isBlank()){
                showToast(getString(R.string.normal_price_must_be_required))
                return@setOnClickListener
            }
            if (binding.DiscountPercent.text.toString().isEmpty() || binding.DiscountPercent.text.toString().isBlank()){
               showToast(getString(R.string.discount_must_be_required))
                return@setOnClickListener
            }
            val normalprice11 = binding.normalprice1.text.toString().toDouble()
            val DiscountPercent11 = binding.DiscountPercent.text.toString().toDouble()

            val Discount = ((normalprice11) * (DiscountPercent11 / 100))
            binding.Discount.text = Discount.toString()
            val Discprice = ((normalprice11) - (normalprice11 * (DiscountPercent11 / 100)))
            binding.DiscountPrice2.text = Discprice.toString()

        }
        binding.calculate2.setOnClickListener {
            if (binding.normalprice2.text.toString().isEmpty() || binding.normalprice2.text.toString().isBlank()){
                showToast(getString(R.string.normal_price_must_be_required))
                return@setOnClickListener
            }
            if (binding.DiscountPercent2.text.toString().isEmpty() || binding.DiscountPercent2.text.toString().isBlank()){
                showToast(getString(R.string.discount_simple_must_be_required))
                return@setOnClickListener
            }
            val normalprice22 = binding.normalprice2.text.toString().toDouble()
            val Discount22 = binding.DiscountPercent2.text.toString().toDouble()

            val Discprice2 = normalprice22 - Discount22
            binding.DiscountPrice.text = Discprice2.toString()
            val Discount_percent = ((Discount22 / (normalprice22) * 100))
            binding.DiscountPercent3.text = Discount_percent.toString()
        }

        binding.formula.setOnClickListener {
            ActivityIntent(FormulasActivity::class)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            ActivityIntent(Calculator::class)
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
        ActivityIntent(Calculator::class)
    }

}
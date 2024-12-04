package com.example.smallbusinessplan.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.TextView
import com.example.ads.Classes.Strategies.bannerAds
import com.example.smallbusinessplan.Extensions.Calculator
import com.example.smallbusinessplan.Extensions.gone
import com.example.smallbusinessplan.Extensions.visible
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.SharedPref
import com.example.smallbusinessplan.Utils.AppConstants
import com.example.smallbusinessplan.Utils.NetworkUtils
import com.google.android.material.bottomsheet.BottomSheetDialog

class Market : AppCompatActivity() {
    private lateinit var bannerAd:FrameLayout
    private var MainMenu: Menu? = null
    private lateinit var sharedPref: SharedPref

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title= Html.fromHtml("<small>MARKET VALIDITY</small>")

        sharedPref = SharedPref(this)

        bannerAd=findViewById(R.id.MarketBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, "SMALL_BANNER")
        } else {
            bannerAd.gone()
        }

        val narrow_yes: RadioButton = findViewById(R.id.Narrowradio)
        val Specific_yes: RadioButton = findViewById(R.id.Specificradio)
        val Few_yes: RadioButton = findViewById(R.id.Fewradio)
        val Lower_yes: RadioButton = findViewById(R.id.Lowerradio)
        val Similar_yes: RadioButton = findViewById(R.id.Similarradio)

        val Wide_No: RadioButton = findViewById(R.id.Wideradio)
        val Everyone_No: RadioButton = findViewById(R.id.Everyoneradio)
        val Many_No: RadioButton = findViewById(R.id.Manyradio)
        val Higher_No: RadioButton = findViewById(R.id.Higherradio)
        val Better_No: RadioButton = findViewById(R.id.Betterradio)

        val btncalculate:Button = findViewById(R.id.btncalculate)
        val Result: TextView = findViewById(R.id.outputofMarketcalculation)

        if (sharedPref.getStringValue(AppConstants.GEOGRAPHIC) == AppConstants.YES) {
            narrow_yes.isChecked = true
            Wide_No.isChecked = false
        } else {
            narrow_yes.isChecked = false
            Wide_No.isChecked = true
        }
        narrow_yes.setOnClickListener {
            sharedPref.setStringValue(AppConstants.GEOGRAPHIC, AppConstants.YES)
        }
        Wide_No.setOnClickListener {
            sharedPref.setStringValue(AppConstants.GEOGRAPHIC, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.POTENTIAL) == AppConstants.YES) {
            Specific_yes.isChecked = true
            Everyone_No.isChecked = false
        } else {
            Specific_yes.isChecked = false
            Everyone_No.isChecked = true
        }
        Specific_yes.setOnClickListener {
            sharedPref.setStringValue(AppConstants.POTENTIAL, AppConstants.YES)
        }
        Everyone_No.setOnClickListener {
            sharedPref.setStringValue(AppConstants.POTENTIAL, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.PRODUCTS) == AppConstants.YES) {
            Few_yes.isChecked = true
            Many_No.isChecked = false
        } else {
            Few_yes.isChecked = false
            Many_No.isChecked = true
        }
        Few_yes.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPANY, AppConstants.YES)
        }
        Many_No.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPANY, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.COMPETITORS) == AppConstants.YES) {
            Lower_yes.isChecked = true
            Higher_No.isChecked = false
        } else {
            Lower_yes.isChecked = false
            Higher_No.isChecked = true
        }
        Lower_yes.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPETITORS, AppConstants.YES)
        }
        Higher_No.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPETITORS, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.QUALITY) == AppConstants.YES) {
            Similar_yes.isChecked = true
            Better_No.isChecked = false
        } else {
            Similar_yes.isChecked = false
            Better_No.isChecked = true
        }
        Similar_yes.setOnClickListener {
            sharedPref.setStringValue(AppConstants.QUALITY, AppConstants.YES)
        }
        Better_No.setOnClickListener {
            sharedPref.setStringValue(AppConstants.QUALITY, AppConstants.NO)
        }
        val formulaBtn:Button = findViewById(R.id.formula)
        formulaBtn.setOnClickListener {
            val i = Intent(this , FormulasActivity::class.java)
            startActivity(i)
        }
        btncalculate.setOnClickListener {
            if (narrow_yes.isChecked && Specific_yes.isChecked && Few_yes.isChecked && Lower_yes.isChecked && Similar_yes.isChecked) {
                sharedPref.setStringValue(AppConstants.RESULT, "A narrow geographic market can be a successful market position for a small business. This is especially the case if there are opportunities to customize product or services to the specific needs of the local market. Larger businesses can find local knowledge difficult to obtain and the cost of adapting to the local market prohibitive.Targetting specific customers can be a successful strategyfor small businesses. It can allow them to adapt their products or services so they are better suited to the needs of their targetted customers. The internet has provided the opportunity for small scale firms to customise their products and services to very focused customer niches and then to service wide geographic areas. Large firms can find it hard to adapt their product and services to very focused customer segments.\n\n" + "Offering few products or services can be successful strategy for small businesses Having a small range allows them to customize their products and services to particular customers and local markets. Larger firms are less likely to be so focused and may find it hard to compete.\n\n"+ "Providing goods or services of a similar quality to competitors at a lower price ca in some circumstances be successful for small businesses. Larger businesses are often better suited to competing on price to a broad market as they can achieve lower costs through scale. However, sma firms maybe able to compete on price where they adapt there product or service to a particular customer segment or geographic area that has been ignored by larger firms. Also opportunities may existfor small firms where labour costs are lov or transportation costs are high.")
                Result.setText(sharedPref.getStringValue(AppConstants.RESULT))
                Result.setTextColor(Color.GRAY)
            } else if (Wide_No.isChecked && Specific_yes.isChecked && Few_yes.isChecked && Lower_yes.isChecked && Similar_yes.isChecked) {
                sharedPref.setStringValue(AppConstants.RESULT, "Targetting specific customers can be a successful strategy for small businesses It can allow them to adapt their products or services so they are better suited to the needs of their targetted customers.The internet has provided the opportunity for small scale firms to customise their products and services to very focused customer niches and then to service wide geographic areas. Large firms can find it hard to adapt their product and services to very focused customer segments.\n\n" + "Offering few products or services can be successful strategy for small businesses Having a small range allows them to customize their products and services to particular customers and local markets.Larger firms are less likely to be so focused and may find it hard to compete.\n\n" + "Providing goods or services of a similar quality to competitors at a lower price ca in some circumstances be successful for small businesses. Larger businesses are often better suited to competing on price to a broad market as they can achieve lower costs through scale. However, sma firms maybe able to compete on price where they adapt there product or service to a particular customer segment or geographic area that has been ignored by larger firms. Also opportunities may exist for small firms where labour costs are lov or transportation costs are high.")
                Result.setText(sharedPref.getStringValue(AppConstants.RESULT))
                Result.setTextColor(Color.GRAY)
            }
            else if (Wide_No.isChecked && Everyone_No.isChecked && Many_No.isChecked && Higher_No.isChecked && Better_No.isChecked) {
                sharedPref.setStringValue(AppConstants.RESULT, "A combination of wide geographic market and trying to sell to everyone is less likely to be a successful strategy for small firms. Large firms are better suited to selling to such customers as they can achieve lower costs from their scale. Small firms are better suited to customising their products or services to suit specific customers or a particular narrow geographic area.\n" + "Offering few products or services can be successful strategy for small businesses Having a small range allows them to customize their products and services to particular customers and local markets. Larger firms are less likely to be so focused and may find it hard to compete.\n" + "Providing goods or services of a similar quality to competitors at a lower price ca in some circumstances be successful for small businesses. Larger businesses are often better suited to competing on price to a broad market as they can achieve lower costs through scale. However, sma firms maybe able to compete on price where they adapt there product or service to a particular customer segment or geographic area that has been ignored by larger firms. Also opportunities may exist for small firms where labour costs are lov or transportation costs are high.")
                Result.setText(sharedPref.getStringValue(AppConstants.RESULT))
                Result.setTextColor(Color.GRAY)
            }
            else if (narrow_yes.isChecked && Everyone_No.isChecked || Specific_yes.isChecked && Many_No.isChecked && Higher_No.isChecked && Better_No.isChecked) {
                sharedPref.setStringValue(AppConstants.RESULT, "A narrow geographic market can be a successful market position for a small business. This is especially the case if there are opportunities to customize product or services to the specific needs of the local market. Larger businesses can find local knowledge difficult to obtain and the cost of adapting to the local market prohibitive.Targetting specific customers can be a successful strategyfor small businesses. It can allow them to adapt their products or services so they are better suited to the needs of their targetted customers. The internet has provided the opportunity for small scale firms to customise their products and services to very focused customer niches and then to service wide geographic areas. Large firms can find it hard to adapt their product and services to very focused customer segments.\n\n" + "Offering few products or services can be successful strategy for small businesses Having a small range allows them to customize their products and services to particular customers and local markets. Larger firms are less likely to be so focused and may find it hard to compete.\n\n"+ "Providing goods or services of a similar quality to competitors at a lower price ca in some circumstances be successful for small businesses. Larger businesses are often better suited to competing on price to a broad market as they can achieve lower costs through scale. However, sma firms maybe able to compete on price where they adapt there product or service to a particular customer segment or geographic area that has been ignored by larger firms. Also opportunities may existfor small firms where labour costs are lov or transportation costs are high.")
                Result.setText(sharedPref.getStringValue(AppConstants.RESULT))
                Result.setTextColor(Color.GRAY)
            }
            else if(Wide_No.isChecked && Specific_yes.isChecked && Many_No.isChecked && Higher_No.isChecked && Better_No.isChecked){
                sharedPref.setStringValue(AppConstants.RESULT, "Targetting specific customers can be a successful strategy for small businesses It can allow them to adapt their products or services so they are better suited to the needs of their targetted customers.The internet has provided the opportunity for small scale firms to customise their products and services to very focused customer niches and then to service wide geographic areas. Large firms can find it hard to adapt their product and services to very focused customer segments.\n\n" + "Offering few products or services can be successful strategy for small businesses Having a small range allows them to customize their products and services to particular customers and local markets.Larger firms are less likely to be so focused and may find it hard to compete.\n\n" + "Providing goods or services of a similar quality to competitors at a lower price ca in some circumstances be successful for small businesses. Larger businesses are often better suited to competing on price to a broad market as they can achieve lower costs through scale. However, sma firms maybe able to compete on price where they adapt there product or service to a particular customer segment or geographic area that has been ignored by larger firms. Also opportunities may exist for small firms where labour costs are lov or transportation costs are high.")
                Result.setText(sharedPref.getStringValue(AppConstants.RESULT))
                Result.setTextColor(Color.GRAY)
            }else {
                sharedPref.setStringValue(AppConstants.RESULT, "Targetting specific customers can be a successful strategy for small businesses It can allow them to adapt their products or services so they are better suited to the needs of their targetted customers.The internet has provided the opportunity for small scale firms to customise their products and services to very focused customer niches and then to service wide geographic areas. Large firms can find it hard to adapt their product and services to very focused customer segments.\n\n" + "Offering few products or services can be successful strategy for small businesses Having a small range allows them to customize their products and services to particular customers and local markets.Larger firms are less likely to be so focused and may find it hard to compete.\n\n" + "Providing goods or services of a similar quality to competitors at a lower price ca in some circumstances be successful for small businesses. Larger businesses are often better suited to competing on price to a broad market as they can achieve lower costs through scale. However, sma firms maybe able to compete on price where they adapt there product or service to a particular customer segment or geographic area that has been ignored by larger firms. Also opportunities may exist for small firms where labour costs are lov or transportation costs are high.")
                Result.setText(sharedPref.getStringValue(AppConstants.RESULT))
                Result.setTextColor(Color.GRAY)
            }
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
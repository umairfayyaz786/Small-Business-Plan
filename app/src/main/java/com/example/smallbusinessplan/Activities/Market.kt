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
import com.example.smallbusinessplan.databinding.ActivityMarketBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class Market : AppCompatActivity() {
    private lateinit var bannerAd: FrameLayout
    private var MainMenu: Menu? = null
    private lateinit var sharedPref: SharedPref
    private lateinit var binding: ActivityMarketBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = Html.fromHtml("<small>MARKET VALIDITY</small>")
        sharedPref = SharedPref(this)

        bannerAd = findViewById(R.id.MarketBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, "SMALL_BANNER")
        } else {
            bannerAd.gone()
        }
        if (sharedPref.getStringValue(AppConstants.GEOGRAPHIC) == AppConstants.YES) {
            binding.Narrowradio.isChecked = true
            binding.Wideradio.isChecked = false
        } else {
            binding.Narrowradio.isChecked = false
            binding.Wideradio.isChecked = true
        }
        binding.Narrowradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.GEOGRAPHIC, AppConstants.YES)
        }
        binding.Wideradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.GEOGRAPHIC, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.POTENTIAL) == AppConstants.YES) {
            binding.Specificradio.isChecked = true
            binding.Everyoneradio.isChecked = false
        } else {
            binding.Specificradio.isChecked = false
            binding.Everyoneradio.isChecked = true
        }
        binding.Specificradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.POTENTIAL, AppConstants.YES)
        }
        binding.Everyoneradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.POTENTIAL, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.PRODUCTS) == AppConstants.YES) {
            binding.Fewradio.isChecked = true
            binding.Manyradio.isChecked = false
        } else {
            binding.Fewradio.isChecked = false
            binding.Manyradio.isChecked = true
        }
        binding.Fewradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPANY, AppConstants.YES)
        }
        binding.Manyradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPANY, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.COMPETITORS) == AppConstants.YES) {
            binding.Lowerradio.isChecked = true
            binding.Higherradio.isChecked = false
        } else {
            binding.Lowerradio.isChecked = false
            binding.Higherradio.isChecked = true
        }
        binding.Lowerradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPETITORS, AppConstants.YES)
        }
        binding.Higherradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPETITORS, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.QUALITY) == AppConstants.YES) {
            this.binding.Similarradio.isChecked = true
            binding.Betterradio.isChecked = false
        } else {
            binding.Similarradio.isChecked = false
            binding.Betterradio.isChecked = true
        }
        binding.Similarradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.QUALITY, AppConstants.YES)
        }
        binding.Betterradio.setOnClickListener {
            sharedPref.setStringValue(AppConstants.QUALITY, AppConstants.NO)
        }

        binding.formula.setOnClickListener {
            val i = Intent(this, FormulasActivity::class.java)
            startActivity(i)
        }
        binding.btncalculate.setOnClickListener {
            if (binding.Narrowradio.isChecked && binding.Specificradio.isChecked && binding.Fewradio.isChecked && binding.Lowerradio.isChecked && binding.Similarradio.isChecked) {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    "A narrow geographic market can be a successful market position for a small business. This is especially the case if there are opportunities to customize product or services to the specific needs of the local market. Larger businesses can find local knowledge difficult to obtain and the cost of adapting to the local market prohibitive.Targetting specific customers can be a successful strategyfor small businesses. It can allow them to adapt their products or services so they are better suited to the needs of their targetted customers. The internet has provided the opportunity for small scale firms to customise their products and services to very focused customer niches and then to service wide geographic areas. Large firms can find it hard to adapt their product and services to very focused customer segments.\n\n" + "Offering few products or services can be successful strategy for small businesses Having a small range allows them to customize their products and services to particular customers and local markets. Larger firms are less likely to be so focused and may find it hard to compete.\n\n" + "Providing goods or services of a similar quality to competitors at a lower price ca in some circumstances be successful for small businesses. Larger businesses are often better suited to competing on price to a broad market as they can achieve lower costs through scale. However, sma firms maybe able to compete on price where they adapt there product or service to a particular customer segment or geographic area that has been ignored by larger firms. Also opportunities may existfor small firms where labour costs are lov or transportation costs are high."
                )
                binding.outputofMarketcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofMarketcalculation.setTextColor(Color.GRAY)
            } else if (binding.Wideradio.isChecked && binding.Specificradio.isChecked && binding.Fewradio.isChecked && binding.Lowerradio.isChecked && binding.Similarradio.isChecked) {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    "Targetting specific customers can be a successful strategy for small businesses It can allow them to adapt their products or services so they are better suited to the needs of their targetted customers.The internet has provided the opportunity for small scale firms to customise their products and services to very focused customer niches and then to service wide geographic areas. Large firms can find it hard to adapt their product and services to very focused customer segments.\n\n" + "Offering few products or services can be successful strategy for small businesses Having a small range allows them to customize their products and services to particular customers and local markets.Larger firms are less likely to be so focused and may find it hard to compete.\n\n" + "Providing goods or services of a similar quality to competitors at a lower price ca in some circumstances be successful for small businesses. Larger businesses are often better suited to competing on price to a broad market as they can achieve lower costs through scale. However, sma firms maybe able to compete on price where they adapt there product or service to a particular customer segment or geographic area that has been ignored by larger firms. Also opportunities may exist for small firms where labour costs are lov or transportation costs are high."
                )
                binding.outputofMarketcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofMarketcalculation.setTextColor(Color.GRAY)
            } else if (binding.Wideradio.isChecked && binding.Everyoneradio.isChecked && binding.Manyradio.isChecked && binding.Higherradio.isChecked && binding.Betterradio.isChecked) {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    "A combination of wide geographic market and trying to sell to everyone is less likely to be a successful strategy for small firms. Large firms are better suited to selling to such customers as they can achieve lower costs from their scale. Small firms are better suited to customising their products or services to suit specific customers or a particular narrow geographic area.\n" + "Offering few products or services can be successful strategy for small businesses Having a small range allows them to customize their products and services to particular customers and local markets. Larger firms are less likely to be so focused and may find it hard to compete.\n" + "Providing goods or services of a similar quality to competitors at a lower price ca in some circumstances be successful for small businesses. Larger businesses are often better suited to competing on price to a broad market as they can achieve lower costs through scale. However, sma firms maybe able to compete on price where they adapt there product or service to a particular customer segment or geographic area that has been ignored by larger firms. Also opportunities may exist for small firms where labour costs are lov or transportation costs are high."
                )
                binding.outputofMarketcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofMarketcalculation.setTextColor(Color.GRAY)
            } else if (binding.Narrowradio.isChecked && binding.Everyoneradio.isChecked || binding.Specificradio.isChecked && binding.Manyradio.isChecked && binding.Higherradio.isChecked && binding.Betterradio.isChecked) {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    "A narrow geographic market can be a successful market position for a small business. This is especially the case if there are opportunities to customize product or services to the specific needs of the local market. Larger businesses can find local knowledge difficult to obtain and the cost of adapting to the local market prohibitive.Targetting specific customers can be a successful strategyfor small businesses. It can allow them to adapt their products or services so they are better suited to the needs of their targetted customers. The internet has provided the opportunity for small scale firms to customise their products and services to very focused customer niches and then to service wide geographic areas. Large firms can find it hard to adapt their product and services to very focused customer segments.\n\n" + "Offering few products or services can be successful strategy for small businesses Having a small range allows them to customize their products and services to particular customers and local markets. Larger firms are less likely to be so focused and may find it hard to compete.\n\n" + "Providing goods or services of a similar quality to competitors at a lower price ca in some circumstances be successful for small businesses. Larger businesses are often better suited to competing on price to a broad market as they can achieve lower costs through scale. However, sma firms maybe able to compete on price where they adapt there product or service to a particular customer segment or geographic area that has been ignored by larger firms. Also opportunities may existfor small firms where labour costs are lov or transportation costs are high."
                )
                binding.outputofMarketcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofMarketcalculation.setTextColor(Color.GRAY)
            } else if (binding.Wideradio.isChecked && binding.Specificradio.isChecked && binding.Manyradio.isChecked && binding.Higherradio.isChecked && binding.Betterradio.isChecked) {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    "Targetting specific customers can be a successful strategy for small businesses It can allow them to adapt their products or services so they are better suited to the needs of their targetted customers.The internet has provided the opportunity for small scale firms to customise their products and services to very focused customer niches and then to service wide geographic areas. Large firms can find it hard to adapt their product and services to very focused customer segments.\n\n" + "Offering few products or services can be successful strategy for small businesses Having a small range allows them to customize their products and services to particular customers and local markets.Larger firms are less likely to be so focused and may find it hard to compete.\n\n" + "Providing goods or services of a similar quality to competitors at a lower price ca in some circumstances be successful for small businesses. Larger businesses are often better suited to competing on price to a broad market as they can achieve lower costs through scale. However, sma firms maybe able to compete on price where they adapt there product or service to a particular customer segment or geographic area that has been ignored by larger firms. Also opportunities may exist for small firms where labour costs are lov or transportation costs are high."
                )
                binding.outputofMarketcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofMarketcalculation.setTextColor(Color.GRAY)
            } else {
                sharedPref.setStringValue(
                    AppConstants.RESULT,
                    "Targetting specific customers can be a successful strategy for small businesses It can allow them to adapt their products or services so they are better suited to the needs of their targetted customers.The internet has provided the opportunity for small scale firms to customise their products and services to very focused customer niches and then to service wide geographic areas. Large firms can find it hard to adapt their product and services to very focused customer segments.\n\n" + "Offering few products or services can be successful strategy for small businesses Having a small range allows them to customize their products and services to particular customers and local markets.Larger firms are less likely to be so focused and may find it hard to compete.\n\n" + "Providing goods or services of a similar quality to competitors at a lower price ca in some circumstances be successful for small businesses. Larger businesses are often better suited to competing on price to a broad market as they can achieve lower costs through scale. However, sma firms maybe able to compete on price where they adapt there product or service to a particular customer segment or geographic area that has been ignored by larger firms. Also opportunities may exist for small firms where labour costs are lov or transportation costs are high."
                )
                binding.outputofMarketcalculation.setText(sharedPref.getStringValue(AppConstants.RESULT))
                binding.outputofMarketcalculation.setTextColor(Color.GRAY)
            }
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
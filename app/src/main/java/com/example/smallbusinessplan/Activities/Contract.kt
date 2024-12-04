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

class Contract : AppCompatActivity() {
    private lateinit var bannerAd:FrameLayout
    private lateinit var sharedPref: SharedPref
    private var MainMenu: Menu? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title= Html.fromHtml("<small>CONTRACT VALIDITY</small>")

        sharedPref = SharedPref(this)
        val yescheck1: RadioButton = findViewById(R.id.yesradio1)
        val yescheck2: RadioButton = findViewById(R.id.yesradio2)
        val yescheck3: RadioButton = findViewById(R.id.yesradio3)
        val yescheck4: RadioButton = findViewById(R.id.yesradio4)
        val yescheck5: RadioButton = findViewById(R.id.yesradio5)
        val yescheck6: RadioButton = findViewById(R.id.yesradio6)
        val yescheck7: RadioButton = findViewById(R.id.yesradio7)

        val nocheck1: RadioButton = findViewById(R.id.noradio1)
        val nocheck2: RadioButton = findViewById(R.id.noradio2)
        val nocheck3: RadioButton = findViewById(R.id.noradio3)
        val nocheck4: RadioButton = findViewById(R.id.noradio4)
        val nocheck5: RadioButton = findViewById(R.id.noradio5)
        val nocheck6: RadioButton = findViewById(R.id.noradio6)
        val nocheck7: RadioButton = findViewById(R.id.noradio7)
        val Output: TextView = findViewById(R.id.outputofcalculation)
        val button: Button = findViewById(R.id.btncalculate)
        val formulaBtn:Button = findViewById(R.id.formula)
        bannerAd=findViewById(R.id.ContractBannerAd)
        if (NetworkUtils.isNetworkAvailable(this)) {
            bannerAd.visible()
            bannerAds(this, bannerAd, "SMALL_BANNER")
        } else {
            bannerAd.gone()
        }

        formulaBtn.setOnClickListener {
            val i = Intent(this , FormulasActivity::class.java)
            startActivity(i)
        }
        Output.setText(sharedPref.getStringValue(AppConstants.RESULT))
        if (sharedPref.getStringValue(AppConstants.SERVICE) == AppConstants.YES) {
            yescheck1.isChecked = true
            nocheck1.isChecked = false
        } else {
            yescheck1.isChecked = false
            nocheck1.isChecked = true
        }
        yescheck1.setOnClickListener {
            sharedPref.setStringValue(AppConstants.SERVICE, AppConstants.YES)
        }
        nocheck1.setOnClickListener {
            sharedPref.setStringValue(AppConstants.SERVICE, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.AWFULL) == AppConstants.YES) {
            yescheck2.isChecked = true
            nocheck2.isChecked = false
        } else {
            yescheck2.isChecked = false
            nocheck2.isChecked = true
        }
        yescheck2.setOnClickListener {
            sharedPref.setStringValue(AppConstants.AWFULL, AppConstants.YES)
        }
        nocheck2.setOnClickListener {
            sharedPref.setStringValue(AppConstants.AWFULL, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.COMPANY) == AppConstants.YES) {
            yescheck3.isChecked = true
            nocheck3.isChecked = false
        } else {
            yescheck3.isChecked = false
            nocheck3.isChecked = true
        }
        yescheck3.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPANY, AppConstants.YES)
        }
        nocheck3.setOnClickListener {
            sharedPref.setStringValue(AppConstants.COMPANY, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.OFFERED) == AppConstants.YES) {
            yescheck4.isChecked = true
            nocheck4.isChecked = false
        } else {
            yescheck4.isChecked = false
            nocheck4.isChecked = true
        }
        yescheck4.setOnClickListener {
            sharedPref.setStringValue(AppConstants.OFFERED, AppConstants.YES)
        }
        nocheck4.setOnClickListener {
            sharedPref.setStringValue(AppConstants.OFFERED, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.FRIENDS) == AppConstants.YES) {
            yescheck5.isChecked = true
            nocheck5.isChecked = false
        } else {
            yescheck5.isChecked = false
            nocheck5.isChecked = true
        }
        yescheck5.setOnClickListener {
            sharedPref.setStringValue(AppConstants.FRIENDS, AppConstants.YES)
        }
        nocheck5.setOnClickListener {
            sharedPref.setStringValue(AppConstants.FRIENDS, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.AGREEMENT) == AppConstants.YES) {
            yescheck6.isChecked = true
            nocheck6.isChecked = false
        } else {
            yescheck6.isChecked = false
            nocheck6.isChecked = true
        }
        yescheck6.setOnClickListener {
            sharedPref.setStringValue(AppConstants.AGREEMENT, AppConstants.YES)
        }
        nocheck6.setOnClickListener {
            sharedPref.setStringValue(AppConstants.AGREEMENT, AppConstants.NO)
        }

        if (sharedPref.getStringValue(AppConstants.DURATION) == AppConstants.YES) {
            yescheck7.isChecked = true
            nocheck7.isChecked = false
        } else {
            yescheck7.isChecked = false
            nocheck7.isChecked = true
        }
        yescheck7.setOnClickListener {
            sharedPref.setStringValue(AppConstants.DURATION, AppConstants.YES)
        }
        nocheck7.setOnClickListener {
            sharedPref.setStringValue(AppConstants.DURATION, AppConstants.NO)
        }

        button.setOnClickListener {
            if (yescheck1.isChecked && yescheck2.isChecked && yescheck3.isChecked && yescheck4.isChecked && yescheck6.isChecked && yescheck5.isChecked && yescheck7.isChecked) {
                sharedPref.setStringValue(AppConstants.RESULT, "Probably a VALID Contract, but may be hard to enforce")
                Output.setText(sharedPref.getStringValue(AppConstants.RESULT))
                Output.setTextColor(Color.GRAY)
            } else if (yescheck1.isChecked && yescheck2.isChecked && yescheck3.isChecked && yescheck4.isChecked && yescheck6.isChecked && yescheck5.isChecked || nocheck5.isChecked && yescheck7.isChecked || nocheck5.isChecked) {
                sharedPref.setStringValue(AppConstants.RESULT, "Probably a VALID Contract that is enforceable")
                Output.setText(sharedPref.getStringValue(AppConstants.RESULT))
                Output.setTextColor(Color.GRAY)
            } else {
                sharedPref.setStringValue(AppConstants.RESULT, "Probably INVALID contract")
                Output.setText(sharedPref.getStringValue(AppConstants.RESULT))
                Output.setTextColor(Color.RED)
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
package com.example.smallbusinessplan

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.smallbusinessplan.R.id.nav_view
import com.example.smallbusinessplan.Utils.SharedPref
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
//    lateinit var toggle: ActionBarDrawerToggle
//    private var MainMenu: Menu? = null
//    private lateinit var navigationView: NavigationView
//
//    companion object {
//        var themValue = 0
//    }
//
//    internal lateinit var sharedpref: SharedPref
//
//    private lateinit var txtsetting1: LinearLayout
//    private lateinit var txtbusinessplan1: LinearLayout
//    private lateinit var txtfinances1: LinearLayout
//    private lateinit var txtlegalside1: LinearLayout
//    private lateinit var txtmarketing1: LinearLayout
//    private lateinit var txtlaunching1: LinearLayout
//    private lateinit var bannerad: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        sharedpref = SharedPref(this)
//        if (sharedpref.getThemState("Theme") >= -1 && sharedpref.getThemState("Theme") == 0) {
//            themeState(theme=sharedpref.getThemState("Theme") )
//        } else if (sharedpref.getThemState("Theme") >= -1 && sharedpref.getThemState("Theme") == 1){
//            themeState(theme=sharedpref.getThemState("Theme") )
//        }else{
//            themeState(theme=sharedpref.getThemState("Theme") )
//        }
//        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.ActionBarColor)))
//        navigationView = findViewById(nav_view)
//        val drawyerLayout: DrawerLayout = findViewById(R.id.drawyerlayout)
//        val navView: NavigationView = findViewById(nav_view)
//
//
//        txtsetting1 = findViewById(R.id.setting)
//        txtbusinessplan1 = findViewById(R.id.business_plan)
//        txtfinances1 = findViewById(R.id.finances)
//        txtlegalside1 = findViewById(R.id.legal_side)
//        txtmarketing1 = findViewById(R.id.marketing)
//        txtlaunching1 = findViewById(R.id.launching)
//        bannerad = findViewById(R.id.bannerad)

//        txtsetting1.setOnClickListener {
//            val intent = Intent(this, SettingOutTheBasics::class.java)
//            startActivity(intent)
//        }
//        txtbusinessplan1.setOnClickListener {
//            val intent = Intent(this, WritingAbusinessPlan::class.java)
//            startActivity(intent)
//        }
//        txtfinances1.setOnClickListener {
//            val intent = Intent(this, ManaginYourFinances::class.java)
//            startActivity(intent)
//        }
//        txtlegalside1.setOnClickListener {
//            val intent = Intent(this, CoveringTheLegalSide::class.java)
//            startActivity(intent)
//        }
//        txtmarketing1.setOnClickListener {
//            val intent = Intent(this, MarketingYourBusiness::class.java)
//            startActivity(intent)
//        }
//        txtlaunching1.setOnClickListener {
//            val intent = Intent(this, LaunchingYourBusiness::class.java)
//            startActivity(intent)
//        }

//        toggle = ActionBarDrawerToggle(this, drawyerLayout, R.string.open, R.string.close)
//        drawyerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//        navigationView.itemIconTintList = null
//
//        navView.setNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.home -> {
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                }
//                R.id.sharemenu -> {
//                    shareItem()
//                }
//                R.id.review -> {
//                    reviews()
//                }
//                R.id.mode1 -> {
//                    Mode()
//                }
//            }
//            true
//        }

//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        MainMenu = menu
//        menuInflater.inflate(R.menu.share, menu)
//        return super.onCreateOptionsMenu(menu)
//
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (toggle.onOptionsItemSelected(item)) {
//            return true
//        }
//        if (item.itemId == R.id.share) {
//            shareItem()
//        }
//        if (item.itemId == R.id.review) {
//            reviews()
//        }
//        if (item.itemId == R.id.mode1) {
//            Mode()
//        }
//        return super.onOptionsItemSelected(item)
//    }

//    private fun shareItem() {
//            val url = "https://play.google.com/store/apps/details?id=com.kachariya.smallbusinessplan"
//            val intent = Intent()
//            intent.action = Intent.ACTION_SEND
//            intent.putExtra(Intent.EXTRA_TEXT, url)
//            intent.type = "text/plain"
//            startActivity(Intent.createChooser(intent, "Share to:"))
//    }

//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    private fun statusColor(color: Int) {
//        val window: Window = window
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.statusBarColor = ContextCompat.getColor(this, color)
//    }
//
//    private fun reviews() {
//        val alertBuilder = AlertDialog.Builder(this)
//        alertBuilder.setTitle("Small Business Plan")
//        alertBuilder.setMessage("If you enjoy using Small Business Plan, would you mind taking a moment to rate it? It won't take more than a minute. Thanks for your support!")
//        alertBuilder.setPositiveButton("RATE") { _, _ ->
////            if (::Adaptar.isFinal){
////                showHideDelete(false)
//
//            startActivity(
//                Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("https://play.google.com/store/apps/details?id=com.kachariya.smallbusinessplan")
//                )
//            )
//
//        }
//        alertBuilder.setNegativeButton("NO, THANKS") { _, _ ->
//
//        }
//        alertBuilder.show()
//    }

//    private fun Mode() {
//        val dialog = Dialog(this)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(true)
//        dialog.setContentView(R.layout.fortheme)
//        val darkmode = dialog.findViewById<RadioButton>(R.id.darkmode)
//        val lightmode = dialog.findViewById<RadioButton>(R.id.lightmode)
//        val defaultmode = dialog.findViewById<RadioButton>(R.id.defaultt)
//        if (sharedpref.getThemState("Theme") >= -1 && sharedpref.getThemState("Theme") == 0) {
//            darkmode.isChecked = true
//            lightmode.isChecked = false
//            defaultmode.isChecked = false
//            themeState(theme = sharedpref.getThemState("Theme"))
//        } else if (sharedpref.getThemState("Theme") >= -1 && sharedpref.getThemState("Theme") == 1) {
//            lightmode.isChecked = true
//            darkmode.isChecked = false
//            defaultmode.isChecked = false
//            themeState(theme = sharedpref.getThemState("Theme"))
//        } else {
//            lightmode.isChecked = false
//            darkmode.isChecked = false
//            defaultmode.isChecked = true
//            themeState(theme = sharedpref.getThemState("Theme"))
//        }
//        darkmode.setOnClickListener {
//            darkmode.isChecked = true
//            lightmode.isChecked = false
//            defaultmode.isChecked = false
//            themeState(theme = 0)
//        }
//        lightmode.setOnClickListener {
//            darkmode.isChecked = false
//            lightmode.isChecked = true
//            defaultmode.isChecked = false
//            themeState(theme = 1)
//        }
//        defaultmode.setOnClickListener {
//            darkmode.isChecked = false
//            lightmode.isChecked = false
//            defaultmode.isChecked = true
//            themeState(theme = 2)
//        }
//        dialog.show()
//    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    @SuppressLint("ResourceAsColor")
//    override fun onBackPressed() {
//        val dialog = Dialog(this)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(true)
//        dialog.setContentView(R.layout.feedback)
//        val btncancel = dialog.findViewById<Button>(R.id.cancel)
//        val btnFeedback = dialog.findViewById<Button>(R.id.feedback)
//        if (sharedpref.getThemState("Theme") >= -1 && sharedpref.getThemState("Theme") == 0) {
//        btncancel.setOnClickListener {
//            dialog.dismiss()
//        }
//        btnFeedback.setOnClickListener {
//            startActivity(
//                Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("https://play.google.com/store/apps/details?id=com.kachariya.smallbusinessplan")
//                )
//            )
//        }
//
//        }
//        dialog.show()
//    }
//        val alertBuilder = AlertDialog.Builder(this)
//        alertBuilder.setTitle("Small Business Plan")
//        alertBuilder.setMessage("If you enjoy using Small Business Plan, would you mind taking a moment to rate it? It won't take more than a minute. Thanks for your support!")
//        alertBuilder.setPositiveButton("RATE") { _, _ ->
//            if (::Adaptar.isFinal){
//                showHideDelete(false)

//            startActivity(
//                Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("https://play.google.com/store/apps/details?id=com.kachariya.smallbusinessplan")
//                )
//            )
//
//        }
//        alertBuilder.setNegativeButton("NO, THANKS") { _, _ ->
//            finishAffinity()
//        }
//        alertBuilder.show()
//    }

//    @SuppressLint("ResourceAsColor")
//    private fun themeState(theme: Int) {
//        when (theme) {
//            0 -> {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                sharedpref.setThemeState("Theme", 0)
//            }
//            1 -> {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                sharedpref.setThemeState("Theme", 1)
//            }
//            else -> {
//                if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.Q){
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
//                }else{
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
//                }
//                sharedpref.setThemeState("Theme", 2)
//
//            }
//        }
    }
}
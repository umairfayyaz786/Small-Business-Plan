package com.example.smallbusinessplan.Activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.smallbusinessplan.Extensions.Calculator
import com.example.smallbusinessplan.Fragments.*
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.SharedPref
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout

class CategoriesActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private var MainMenu: Menu? = null
    private lateinit var navigationView: NavigationView


    companion object {
        var themValue = 0
    }

    internal lateinit var sharedpref: SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)


        val pager = findViewById<ViewPager>(R.id.viewpager)
        val tabs = findViewById<TabLayout>(R.id.tabs)

        val adaptor = Main.MyViewPagerAdaptor(supportFragmentManager)
        adaptor.addfragment(SettingOutBasicsFragment(), "Setting Out Basics")
        adaptor.addfragment(WritingaBusinessPlanFragment(), "Writing a Business Plan")
        adaptor.addfragment(ManagingYourFinancesFragment(), "Managing Your Finances")
        adaptor.addfragment(CoveringTheLegalSideFragment(), "Covering the Legal Side")
        adaptor.addfragment(MarketingYourBusinessFragment(), "Marketing Your Business")
        adaptor.addfragment(LaunchingYourBusinessFragment(), "Launching Your Business")

        pager.adapter = adaptor
        tabs.setupWithViewPager(pager)
        tabs.setScrollPosition(Main.id, 0f, true)
        pager.currentItem = Main.id

        sharedpref = SharedPref(this)
        if (sharedpref.getThemState("Theme") >= -1 && sharedpref.getThemState("Theme") == 0) {
            Main.themeState(themState = sharedpref.getThemState("Theme"))
        } else if (sharedpref.getThemState("Theme") >= -1 && sharedpref.getThemState("Theme") == 1) {
            Main.themeState(themState = sharedpref.getThemState("Theme"))
        } else {
            Main.themeState(themState = sharedpref.getThemState("Theme"))
        }

//        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.ActionBarColor)))
        navigationView = findViewById(R.id.nav_view)
        val drawyerLayout: DrawerLayout = findViewById(R.id.drawyerlayout00)
        val navView: NavigationView = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(this, drawyerLayout, R.string.open, R.string.close)
        drawyerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val intent = Intent(this, Main::class.java)
                    startActivity(intent)
                }
                R.id.sharemenu -> {
                    shareItem()
                }
                R.id.review -> {
                    reviews()
                }
                R.id.mode1 -> {
//                    Mode()
                    Main.Mode(this)
                }
                R.id.calculator -> {
                    calculator()
                }
            }
            true
        }
    }

    ///For View Pager tab layout adaptor////

    class MyViewPagerAdaptor(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()
        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addfragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }


    private fun calculator() {
        val i = Intent(this, Calculator::class.java)
        startActivity(i)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MainMenu = menu
        menuInflater.inflate(R.menu.share, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        if (item.itemId == R.id.calculator) {
            calculator()
        }

        if (item.itemId == R.id.review) {
            reviews()
        }
        if (item.itemId == R.id.mode1) {
//            Mode()
            Main.Mode(this)
        }
        if (item.itemId == R.id.share) {
            shareItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun reviews() {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Small Business Plan")
        alertBuilder.setMessage("If you enjoy using Small Business Plan, would you mind taking a moment to rate it? It won't take more than a minute. Thanks for your support!")
        alertBuilder.setPositiveButton("RATE") { _, _ ->
//            if (::Adaptar.isFinal){
//                showHideDelete(false)

            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.kachariya.smallbusinessplan")
                )
            )

        }
        alertBuilder.setNegativeButton("NO, THANKS") { _, _ ->

        }
        alertBuilder.show()
    }

//    private fun Mode() {
//        val dialog = Dialog(this)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(true)
//        dialog.setContentView(R.layout.fortheme)
//        val darkmode = dialog.findViewById<RadioButton>(R.id.darkmode)
//        val lightmode = dialog.findViewById<RadioButton>(R.id.lightmode)
//        val defaultmode = dialog.findViewById<RadioButton>(R.id.defaultt)
//        val btnOK =dialog.findViewById<Button>(R.id.okselectedtheme)
//        dialog.show()
//        btnOK.setOnClickListener {
//            themeState(themState = themValue)
//            dialog.dismiss()
//        }
//        if (sharedpref.getThemState("Theme") >= -1 && sharedpref.getThemState("Theme") == 0) {
//            darkmode.isChecked = true
//            lightmode.isChecked = false
//            defaultmode.isChecked = false
//            themeState(themState = sharedpref.getThemState("Theme"))
//        } else if (sharedpref.getThemState("Theme") >= -1 && sharedpref.getThemState("Theme") == 1) {
//            lightmode.isChecked = true
//            darkmode.isChecked = false
//            defaultmode.isChecked = false
//            themeState(themState = sharedpref.getThemState("Theme"))
//        } else {
//            lightmode.isChecked = false
//            darkmode.isChecked = false
//            defaultmode.isChecked = true
//            themeState(themState = sharedpref.getThemState("Theme"))
//        }
//        darkmode.setOnClickListener {
//            darkmode.isChecked = true
//            lightmode.isChecked = false
//            defaultmode.isChecked = false
//            themValue =0
//
//        }
//
//        lightmode.setOnClickListener {
//            darkmode.isChecked = false
//            lightmode.isChecked = true
//            defaultmode.isChecked = false
//            themValue =1
//
//        }
//
//        defaultmode.setOnClickListener {
//            darkmode.isChecked = false
//            lightmode.isChecked = false
//            defaultmode.isChecked = true
//            themValue =2
//        }
//
//    }

    private fun shareItem() {
        val url = "https://play.google.com/store/apps/details?id=com.kachariya.smallbusinessplan"
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, url)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "Share to:"))
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun statusColor(color: Int) {
        val window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, color)
    }

//    private fun themeState(themState: Int) {
//        when (themState) {
//            0 -> {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                sharedpref.setThemeState("Theme", 0)
//            }
//            1 -> {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                sharedpref.setThemeState("Theme", 1)
//            }
//            else -> {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
//                }
//                sharedpref.setThemeState("Theme", 2)
//
//            }
//        }
//    }
}
package com.example.smallbusinessplan.Activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.smallbusinessplan.Extensions.ActivityIntent
import com.example.smallbusinessplan.Extensions.Calculator
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.Utils.SharedPref
import com.example.smallbusinessplan.Utils.AppConstants
import com.example.smallbusinessplan.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private var MainMenu: Menu? = null
    private lateinit var sharedPref: SharedPref
    private lateinit var binding: ActivitySettingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.set_your_tax)
        sharedPref = SharedPref(this)
        binding.settax.setText(sharedPref.getIntegerValue(AppConstants.TEXT_KEY).toString())
        binding.save.setOnClickListener {
            val set_Tax = binding.settax.text.toString().toDouble()
            sharedPref.setIntegerValue(AppConstants.TEXT_KEY, set_Tax)
            Toast.makeText(this,
                getString(R.string.gst_vat_value_saved_successfully), Toast.LENGTH_SHORT).show()
        }
        binding.formula.setOnClickListener {
            ActivityIntent(FormulasActivity::class)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            ActivityIntent(Calculator::class)
        }
        if (item.itemId == R.id.setting_reviews) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.kachariya.smallbusinessplan")
                )
            )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MainMenu = menu
        menuInflater.inflate(R.menu.setting_review, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onBackPressed() {
        ActivityIntent(Calculator::class)
    }
}
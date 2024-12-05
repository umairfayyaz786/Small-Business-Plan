package com.example.smallbusinessplan.Extensions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.ads.Classes.Strategies.bannerAds
import com.example.ads.Classes.Strategies.interstitialAds
import com.example.smallbusinessplan.Activities.*
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.Utils.NetworkUtils
import com.example.smallbusinessplan.databinding.ActivityCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder


lateinit var binding: ActivityCalculatorBinding


class Calculator : AppCompatActivity() {
    private var MainMenu: Menu? = null
    private lateinit var bannerAd: FrameLayout

    companion object {
        var buttonState: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (NetworkUtils.isNetworkAvailable(this)) {
            interstitialAds(this)
        }
        supportActionBar?.title = Html.fromHtml("<small>BUSINESS CALCULATOR</small>")
        if (buttonState) {
            binding.fram1.visibility = View.GONE
            binding.fram2.visibility = View.VISIBLE
        } else {
            binding.fram2.visibility = View.GONE
            binding.fram1.visibility = View.VISIBLE
        }
        binding.creditor.setOnClickListener {
            binding.fram1.visibility = View.GONE
            binding.fram2.visibility = View.VISIBLE
            buttonState = true
        }
        binding.more2.setOnClickListener {
            binding.fram2.visibility = View.GONE
            binding.fram1.visibility = View.VISIBLE
            buttonState = false
        }
        binding.br.setOnClickListener {
            ActivityIntent(BreakEven::class)
        }
        binding.Sales.setOnClickListener {
            ActivityIntent(Sales::class)
        }
        binding.cas.setOnClickListener {
            ActivityIntent(Cash::class)
        }
        binding.cont.setOnClickListener {
            ActivityIntent(Contract::class)
        }
        binding.credit.setOnClickListener {
            ActivityIntent(Creditor::class)
        }
        binding.Debtor.setOnClickListener {
            ActivityIntent(Dabtors::class)
        }
        binding.Disc.setOnClickListener {
            ActivityIntent(Discount::class)
        }
        binding.contract.setOnClickListener {
            ActivityIntent(Gross_profit::class)
        }
        binding.margin.setOnClickListener {
            ActivityIntent(Margin::class)
        }
        binding.Market.setOnClickListener {
            ActivityIntent(Market::class)
        }
        binding.Markup.setOnClickListener {
            ActivityIntent(Markup::class)
        }
        binding.Netprofit.setOnClickListener {
            ActivityIntent(NetProfit::class)
        }
        binding.Gst.setOnClickListener {
            ActivityIntent(Gst_Vat::class)
        }
        binding.button.setOnClickListener {
            binding.editTextTextPersonName2.setText("")
            binding.editTextTextPersonName.setText("")
        }
        binding.button18.setOnClickListener {
            binding.editTextTextPersonName2.append("1")
        }
        binding.button15.setOnClickListener {
            binding.editTextTextPersonName2.append("2")
        }
        binding.button16.setOnClickListener {
            binding.editTextTextPersonName2.append("3")
        }
        binding.button14.setOnClickListener {
            binding.editTextTextPersonName2.append("4")
        }
        binding.button11.setOnClickListener {
            binding.editTextTextPersonName2.append("5")
        }
        binding.button12.setOnClickListener {
            binding.editTextTextPersonName2.append("6")
        }
        binding.button10.setOnClickListener {
            binding.editTextTextPersonName2.append("7")
        }
        binding.button7.setOnClickListener {
            binding.editTextTextPersonName2.append("8")
        }
        binding.button8.setOnClickListener {
            binding.editTextTextPersonName2.append("9")
        }
        binding.button20.setOnClickListener {
            binding.editTextTextPersonName2.append("0")
        }
        binding.button19.setOnClickListener {
            try {
                val lastIndex =
                    binding.editTextTextPersonName2.text.toString().toCharArray().size - 1
                val lastChar =
                    binding.editTextTextPersonName2.text.toString().toCharArray()[lastIndex]
                if ((lastChar.compareTo('.') == 0) or (lastChar.compareTo('-') == 0) or (lastChar.compareTo(
                        '+'
                    ) == 0) or (lastChar.compareTo('*') == 0) or (lastChar.compareTo('/') == 0)
                ) {
                    Log.d("Tag", "onCreate: $lastIndex  : $lastChar")
                } else {
                    binding.editTextTextPersonName2.append(".")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        binding.button22.setOnClickListener {
            try {
                val lastIndex =
                    binding.editTextTextPersonName2.text.toString().toCharArray().size - 1
                val lastChar =
                    binding.editTextTextPersonName2.text.toString().toCharArray()[lastIndex]
                if ((lastChar.compareTo('.') == 0) or (lastChar.compareTo('-') == 0) or (lastChar.compareTo(
                        '+'
                    ) == 0) or (lastChar.compareTo('*') == 0) or (lastChar.compareTo('/') == 0)
                ) {
                    Log.d("Tag", "onCreate: $lastIndex  : $lastChar")
                } else {
                    binding.editTextTextPersonName2.append("/")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        binding.button13.setOnClickListener {
            try {
                val lastIndex =
                    binding.editTextTextPersonName2.text.toString().toCharArray().size - 1
                val lastChar =
                    binding.editTextTextPersonName2.text.toString().toCharArray()[lastIndex]
                if ((lastChar.compareTo('.') == 0) or (lastChar.compareTo('-') == 0) or (lastChar.compareTo(
                        '+'
                    ) == 0) or (lastChar.compareTo('*') == 0) or (lastChar.compareTo('/') == 0)
                ) {
                    Log.d("Tag", "onCreate: $lastIndex  : $lastChar")
                } else {
                    binding.editTextTextPersonName2.append("+")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        binding.button9.setOnClickListener {
            try {
                val lastIndex =
                    binding.editTextTextPersonName2.text.toString().toCharArray().size - 1
                val lastChar =
                    binding.editTextTextPersonName2.text.toString().toCharArray()[lastIndex]
                if ((lastChar.compareTo('.') == 0) or (lastChar.compareTo('-') == 0) or (lastChar.compareTo(
                        '+'
                    ) == 0) or (lastChar.compareTo('*') == 0) or (lastChar.compareTo('/') == 0)
                ) {
                    Log.d("Tag", "onCreate: $lastIndex  : $lastChar")
                } else {
                    binding.editTextTextPersonName2.append("-")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                binding.editTextTextPersonName2.append("-")
            }
        }
        binding.button23.setOnClickListener {
            try {
                val lastIndex =
                    binding.editTextTextPersonName2.text.toString().toCharArray().size - 1
                val lastChar =
                    binding.editTextTextPersonName2.text.toString().toCharArray()[lastIndex]
                if ((lastChar.compareTo('.') == 0) or (lastChar.compareTo('-') == 0) or (lastChar.compareTo(
                        '+'
                    ) == 0) or (lastChar.compareTo('*') == 0) or (lastChar.compareTo('/') == 0)
                ) {
                    Log.d("Tag", "onCreate: $lastIndex  : $lastChar")
                } else {
                    binding.editTextTextPersonName2.append("*")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        binding.button6.setOnClickListener {
            binding.editTextTextPersonName2.append("/1.2")
        }
        binding.button5.setOnClickListener {
            binding.editTextTextPersonName2.append("*1.2")
        }

        binding.button21.setOnClickListener {
            if (binding.editTextTextPersonName2.text.toString()
                    .isEmpty() or binding.editTextTextPersonName2.text.toString().isBlank()
            ) {
                showToast("Please Enter Any Expression")
                return@setOnClickListener
            }

            try {
                val expression =
                    ExpressionBuilder(binding.editTextTextPersonName2.text.toString()).build()
                val result = expression.evaluate().toLong()
                val longresult = result
                if (result == longresult) {
                    binding.editTextTextPersonName.setText(longresult.toString())
                    binding.editTextTextPersonName2.text = (longresult.toString())
                } else {
                    binding.editTextTextPersonName.setText(result.toString())
                    binding.editTextTextPersonName2.text = (result.toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showToast("Please Enter Valid Expression")
            }
        }
        binding.button4.setOnClickListener() {
            val v = binding.editTextTextPersonName2.text.toString()
            if (v.isNotEmpty()) {
                binding.editTextTextPersonName2.text = v.dropLast(1)
            }
            binding.editTextTextPersonName.setText("")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val i = Intent(this, Main::class.java)
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
        if (item.itemId == R.id.SettingsAction) {
            ActivityIntent(SettingActivity::class)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MainMenu = menu
        menuInflater.inflate(R.menu.calculator_action_items, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onBackPressed() {
        ActivityIntent(Main::class)
    }
}
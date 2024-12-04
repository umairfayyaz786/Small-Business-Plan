package com.example.smallbusinessplan.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import android.widget.TextView
import com.example.smallbusinessplan.Extensions.Calculator
import com.example.smallbusinessplan.R

class FormulasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulas)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title="Formulas"

        val  Cash:TextView = findViewById(R.id.tv2)
        Cash.movementMethod = LinkMovementMethod.getInstance()

        val  Forcast:TextView = findViewById(R.id.tv10)
        Forcast.movementMethod = LinkMovementMethod.getInstance()

        val  Loan:TextView = findViewById(R.id.tv12)
        Loan.movementMethod = LinkMovementMethod.getInstance()

        val  Margin:TextView = findViewById(R.id.tv14)
        Margin.movementMethod = LinkMovementMethod.getInstance()

        val  Markup:TextView = findViewById(R.id.tv18)
        Markup.movementMethod = LinkMovementMethod.getInstance()

        val  Profit:TextView = findViewById(R.id.tv20)
        Profit.movementMethod = LinkMovementMethod.getInstance()

        val  VatFromNetPrice:TextView = findViewById(R.id.tv24)
        VatFromNetPrice.movementMethod = LinkMovementMethod.getInstance()

        val  VatFromGrossPrice:TextView = findViewById(R.id.tv26)
        VatFromGrossPrice.movementMethod = LinkMovementMethod.getInstance()

        val  Sales:TextView = findViewById(R.id.tv22)
        Sales.movementMethod = LinkMovementMethod.getInstance()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val i = Intent(this, Calculator::class.java)
            startActivity(i)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
        val intent = Intent(this , Calculator::class.java)
        startActivity(intent)
    }
}
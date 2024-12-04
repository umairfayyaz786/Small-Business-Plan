package com.example.smallbusinessplan.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import com.example.smallbusinessplan.Extensions.Calculator
import com.example.smallbusinessplan.databinding.ActivityFormulasBinding

class FormulasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormulasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormulasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Formulas"

        binding.tv2.movementMethod = LinkMovementMethod.getInstance()
        binding.tv10.movementMethod = LinkMovementMethod.getInstance()
        binding.tv12.movementMethod = LinkMovementMethod.getInstance()
        binding.tv14.movementMethod = LinkMovementMethod.getInstance()
        binding.tv18.movementMethod = LinkMovementMethod.getInstance()
        binding.tv20.movementMethod = LinkMovementMethod.getInstance()
        binding.tv24.movementMethod = LinkMovementMethod.getInstance()
        binding.tv26.movementMethod = LinkMovementMethod.getInstance()
        binding.tv22.movementMethod = LinkMovementMethod.getInstance()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val i = Intent(this, Calculator::class.java)
            startActivity(i)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val intent = Intent(this, Calculator::class.java)
        startActivity(intent)
    }
}
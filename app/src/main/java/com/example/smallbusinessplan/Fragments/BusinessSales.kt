package com.example.smallbusinessplan.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.smallbusinessplan.Activities.FormulasActivity
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.databinding.FragmentBusinessSalesBinding

class BusinessSales : Fragment() {

    private lateinit var binding: FragmentBusinessSalesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBusinessSalesBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.calculate.setOnClickListener {
            if (binding.sale1.text.toString().isEmpty() || binding.sale1.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Sales Stage 1 must be required!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (binding.sale2.text.toString().isEmpty() || binding.sale2.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Sales Stage 2 must be required!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (binding.sale3.text.toString().isEmpty() || binding.sale3.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Sales Stage 3 must be required!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (binding.sale4.text.toString().isEmpty() || binding.sale4.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Sales Stage 4 must be required!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (binding.BuyersActual.text.toString()
                    .isEmpty() || binding.BuyersActual.text.toString().isBlank()
            ) {
                Toast.makeText(context, "Actual Buyers must be required!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (binding.BuyersTarget.text.toString()
                    .isEmpty() || binding.BuyersTarget.text.toString().isBlank()
            ) {
                Toast.makeText(context, "Buyers Target must be required!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val Stage1 = binding.sale1.text.toString().toDouble()
            val Stage2 = binding.sale2.text.toString().toDouble()
            val Stage3 = binding.sale3.text.toString().toDouble()
            val Stage4 = binding.sale4.text.toString().toDouble()
            val ActualBuyer = binding.BuyersActual.text.toString().toDouble()
            val TargetBuyer = binding.BuyersTarget.text.toString().toDouble()

            val stage1Covert = (Stage1 / Stage1) * 100
            binding.stage1Result.text = stage1Covert.toString()
            val stage2Covert = (Stage2 / Stage1 * 100)
            binding.stage2Result.text = stage2Covert.toString()
            val stage3Covert = (Stage3 / Stage1 * 100)
            binding.stage3Result.text = stage3Covert.toString()
            val stage4Covert = (Stage4 / Stage1 * 100)
            binding.Stage4Result.text = stage4Covert.toString()

            val ActualBuyerConvert = (ActualBuyer / Stage1 * 100)
            binding.BuyersActualResult.text = ActualBuyerConvert.toString()
            val TargetSt1 = (TargetBuyer / (ActualBuyerConvert / 100))
            binding.Stage21Result.text = TargetSt1.toString()
            val TargetSt2 = (TargetSt1 * (stage2Covert / 100))
            binding.Stage22Result.text = TargetSt2.toString()
            val TargetSt3 = (TargetSt1 * (stage3Covert / 100))
            binding.Stage23Result.text = TargetSt3.toString()
            val TargetSt4 = (TargetSt1 * (stage4Covert / 100))
            binding.Stage24Result.text = TargetSt4.toString()
            val TargetBuyersCal = (TargetSt1 * (ActualBuyerConvert / 100))
            binding.BuyersTargetResult.text = TargetBuyersCal.toString()
        }

        binding.clear.setOnClickListener {
            binding.sale1.setText("")
            binding.sale2.setText("")
            binding.sale3.setText("")
            binding.sale4.setText("")
            binding.BuyersActual.setText("")
            binding.BuyersTarget.setText("")
        }

        binding.formula.setOnClickListener {
            val i = Intent(context, FormulasActivity::class.java)
            startActivity(i)
        }
        // Inflate the layout for this fragment
        return view
    }


}
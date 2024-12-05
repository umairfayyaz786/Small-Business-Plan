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
import com.example.smallbusinessplan.Extensions.FragmentIntent
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.databinding.FragmentRetailSalesBinding
import com.example.smallbusinessplan.databinding.FragmentWritingaBusinessPlanBinding


class RetailSales : Fragment() {
    private lateinit var binding: FragmentRetailSalesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRetailSalesBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.calculate.setOnClickListener {
            if (binding.Lookers.text.toString().isEmpty() || binding.Lookers.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Windows Lookers must be required!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (binding.Visitors.text.toString().isEmpty() || binding.Visitors.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Visitors must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.Enquirers.text.toString().isEmpty() || binding.Enquirers.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Enquirers must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.Enquirers1.text.toString().isEmpty() || binding.Enquirers1.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(
                    context,
                    "Empty Below Enquirers must be required!",
                    Toast.LENGTH_SHORT
                ).show()
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
            val Stage1 = binding.Lookers.text.toString().toDouble()
            val Stage2 = binding.Visitors.text.toString().toDouble()
            val Stage3 = binding.Enquirers.text.toString().toDouble()
            val Stage4 = binding.Enquirers1.text.toString().toDouble()
            val ActualBuyer = binding.BuyersActual.text.toString().toDouble()
            val TargetBuyer = binding.BuyersTarget.text.toString().toDouble()

            val visitorCovert = (Stage1 / Stage1) * 100
            binding.LookersResult.text = visitorCovert.toString()
            val LoginsCovert = (Stage2 / Stage1 * 100)
            binding.VisitorResult.text = LoginsCovert.toString()
            val SHoppersCovert = (Stage3 / Stage1 * 100)
            binding.EnquirersResult.text = SHoppersCovert.toString()
            val SHoppers1Covert = (Stage4 / Stage1 * 100)
            binding.Enquirers1Result.text = SHoppers1Covert.toString()

            val ActualBuyerConvert = (ActualBuyer / Stage1 * 100)
            binding.BuyersActualResult.text = ActualBuyerConvert.toString()
            val TargetSt1 = (TargetBuyer / (ActualBuyerConvert / 100))
            binding.tLookersResult.text = TargetSt1.toString()
            val TargetSt2 = (TargetSt1 * (LoginsCovert / 100))
            binding.tvisitorResult.text = TargetSt2.toString()
            val TargetSt3 = (TargetSt1 * (SHoppersCovert / 100))
            binding.tEnquirersResult.text = TargetSt3.toString()
            val TargetSt4 = (TargetSt1 * (SHoppers1Covert / 100))
            binding.tEnquirers1Result.text = TargetSt4.toString()
            val TargetBuyersCal = (TargetSt1 * (ActualBuyerConvert / 100))
            binding.BuyersTargetResult.text = TargetBuyersCal.toString()
        }

        binding.clear.setOnClickListener {
            binding.Lookers.setText("")
            binding.Visitors.setText("")
            binding.Enquirers.setText("")
            binding.Enquirers1.setText("")
            binding.BuyersActual.setText("")
            binding.BuyersTarget.setText("")
        }
        binding.formula.setOnClickListener {
            FragmentIntent(FormulasActivity::class)
        }
        return view
    }
}
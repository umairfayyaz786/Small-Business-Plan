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
import com.example.smallbusinessplan.databinding.FragmentCoveringTheLegalSideBinding
import com.example.smallbusinessplan.databinding.FragmentEcommerceSalesBinding


class EcommerceSales : Fragment() {

    private lateinit var binding: FragmentEcommerceSalesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEcommerceSalesBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.calculate.setOnClickListener {
            if (binding.visitors.text.toString().isEmpty() || binding.visitors.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Visitors must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.Logins.text.toString().isEmpty() || binding.Logins.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Logins must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.Shoppers.text.toString().isEmpty() || binding.Shoppers.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Shoppers must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.Shoppers1.text.toString().isEmpty() || binding.Shoppers1.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(
                    context,
                    "Empty Below Shoppers must be required!",
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
            val Stage1 = binding.visitors.text.toString().toDouble()
            val Stage2 = binding.Logins.text.toString().toDouble()
            val Stage3 = binding.Shoppers.text.toString().toDouble()
            val Stage4 = binding.Shoppers1.text.toString().toDouble()
            val ActualBuyer = binding.BuyersActual.text.toString().toDouble()
            val TargetBuyer = binding.BuyersTarget.text.toString().toDouble()

            val visitorCovert = (Stage1 / Stage1) * 100
            binding.VisitorsResult.text = visitorCovert.toString()
            val LoginsCovert = (Stage2 / Stage1 * 100)
            binding.LoginsResult.text = LoginsCovert.toString()
            val SHoppersCovert = (Stage3 / Stage1 * 100)
            binding.ShoppersResult.text = SHoppersCovert.toString()
            val SHoppers1Covert = (Stage4 / Stage1 * 100)
            binding.Shoppers1Result.text = SHoppers1Covert.toString()

            val ActualBuyerConvert = (ActualBuyer / Stage1 * 100)
            binding.BuyersActualResult.text = ActualBuyerConvert.toString()
            val TargetSt1 = (TargetBuyer / (ActualBuyerConvert / 100))
            binding.tvisitorsResult.text = TargetSt1.toString()
            val TargetSt2 = (TargetSt1 * (LoginsCovert / 100))
            binding.tLoginsResult.text = TargetSt2.toString()
            val TargetSt3 = (TargetSt1 * (SHoppersCovert / 100))
            binding.tshoppersResult.text = TargetSt3.toString()
            val TargetSt4 = (TargetSt1 * (SHoppers1Covert / 100))
            binding.tshoppers1Result.text = TargetSt4.toString()
            val TargetBuyersCal = (TargetSt1 * (ActualBuyerConvert / 100))
            binding.BuyersTargetResult.text = TargetBuyersCal.toString()
        }

        binding.clear.setOnClickListener {
            binding.visitors.setText("")
            binding.Logins.setText("")
            binding.Shoppers.setText("")
            binding.Shoppers1.setText("")
            binding.BuyersActual.setText("")
            binding.BuyersTarget.setText("")
        }
        binding.formula.setOnClickListener {
            val i = Intent(context, FormulasActivity::class.java)
            startActivity(i)
        }
        return view
    }
}
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
import com.example.smallbusinessplan.databinding.FragmentEcommerceSalesBinding
import com.example.smallbusinessplan.databinding.FragmentFacetoFaceSalesBinding


class FacetoFaceSales : Fragment() {
    private lateinit var binding: FragmentFacetoFaceSalesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFacetoFaceSalesBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.calculate.setOnClickListener {
            if (binding.Leads.text.toString().isEmpty() || binding.Leads.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Leads must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.Pitches.text.toString().isEmpty() || binding.Pitches.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Pitches must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.Quotations.text.toString().isEmpty() || binding.Quotations.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(context, "Quotations must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.Quotations1.text.toString().isEmpty() || binding.Quotations1.text.toString()
                    .isBlank()
            ) {
                Toast.makeText(
                    context,
                    "Empty Below Quotations must be required!",
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
            val Stage1 = binding.Leads.text.toString().toDouble()
            val Stage2 = binding.Pitches.text.toString().toDouble()
            val Stage3 = binding.Quotations.text.toString().toDouble()
            val Stage4 = binding.Quotations1.text.toString().toDouble()
            val ActualBuyer = binding.BuyersActual.text.toString().toDouble()
            val TargetBuyer = binding.BuyersTarget.text.toString().toDouble()

            val visitorCovert = (Stage1 / Stage1) * 100
            binding.LeadsResult.text = visitorCovert.toString()
            val LoginsCovert = (Stage2 / Stage1 * 100)
            binding.PitchesResult.text = LoginsCovert.toString()
            val SHoppersCovert = (Stage3 / Stage1 * 100)
            binding.QuotationsResult.text = SHoppersCovert.toString()
            val SHoppers1Covert = (Stage4 / Stage1 * 100)
            binding.Quotations1Result.text = SHoppers1Covert.toString()

            val ActualBuyerConvert = (ActualBuyer / Stage1 * 100)
            binding.BuyersActualResult.text = ActualBuyerConvert.toString()
            val TargetSt1 = (TargetBuyer / (ActualBuyerConvert / 100))
            binding.tLeadsResult.text = TargetSt1.toString()
            val TargetSt2 = (TargetSt1 * (LoginsCovert / 100))
            binding.tpitchesResult.text = TargetSt2.toString()
            val TargetSt3 = (TargetSt1 * (SHoppersCovert / 100))
            binding.tQuotationsResult.text = TargetSt3.toString()
            val TargetSt4 = (TargetSt1 * (SHoppers1Covert / 100))
            binding.tQuotations1Result.text = TargetSt4.toString()
            val TargetBuyersCal = (TargetSt1 * (ActualBuyerConvert / 100))
            binding.BuyersTargetResult.text = TargetBuyersCal.toString()
        }

        binding.clear.setOnClickListener {
            binding.Leads.setText("")
            binding.Pitches.setText("")
            binding.Quotations.setText("")
            binding.Quotations1.setText("")
            binding.BuyersActual.setText("")
            binding.BuyersTarget.setText("")
        }
        binding.formula.setOnClickListener {
            val i = Intent(context, FormulasActivity::class.java)
            startActivity(i)
        }
        return view
    }
    // Inflate the layout for this fragmen
}
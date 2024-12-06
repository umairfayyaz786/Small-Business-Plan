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
import com.example.smallbusinessplan.Extensions.FragmentShowToast
import com.example.smallbusinessplan.R
import com.example.smallbusinessplan.databinding.FragmentRetailSalesBinding
import com.example.smallbusinessplan.databinding.FragmentTelesalesBinding


class Telesales : Fragment() {
    private lateinit var binding: FragmentTelesalesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTelesalesBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.calculate.setOnClickListener {
            if (binding.Leads.text.toString().isEmpty() || binding.Leads.text.toString()
                    .isBlank()
            ) {
                FragmentShowToast(getString(R.string.leads_must_be_required))
                return@setOnClickListener
            }
            if (binding.Calls.text.toString().isEmpty() || binding.Calls.text.toString()
                    .isBlank()
            ) {
                FragmentShowToast(getString(R.string.calls_must_be_required))
                return@setOnClickListener
            }
            if (binding.Answers.text.toString().isEmpty() || binding.Answers.text.toString()
                    .isBlank()
            ) {
                FragmentShowToast(getString(R.string.answers_must_be_required))
                return@setOnClickListener
            }
            if (binding.Answers1.text.toString().isEmpty() || binding.Answers1.text.toString()
                    .isBlank()
            ) {
                FragmentShowToast(getString(R.string.empty_below_answers_must_be_required))
                return@setOnClickListener
            }
            if (binding.BuyersActual.text.toString()
                    .isEmpty() || binding.BuyersActual.text.toString().isBlank()
            ) {
                FragmentShowToast(getString(R.string.actual_buyers_must_be_required))
                return@setOnClickListener
            }
            if (binding.BuyersTarget.text.toString()
                    .isEmpty() || binding.BuyersTarget.text.toString().isBlank()
            ) {
                FragmentShowToast(getString(R.string.buyers_target_must_be_required))
                return@setOnClickListener
            }
            val Stage1 = binding.Leads.text.toString().toDouble()
            val Stage2 = binding.Calls.text.toString().toDouble()
            val Stage3 = binding.Answers.text.toString().toDouble()
            val Stage4 = binding.Answers1.text.toString().toDouble()
            val ActualBuyer = binding.BuyersActual.text.toString().toDouble()
            val TargetBuyer = binding.BuyersTarget.text.toString().toDouble()

            val visitorCovert = (Stage1 / Stage1) * 100
            binding.LeadsResult.text = visitorCovert.toString()
            val LoginsCovert = (Stage2 / Stage1 * 100)
            binding.CallsResult.text = LoginsCovert.toString()
            val SHoppersCovert = (Stage3 / Stage1 * 100)
            binding.AnswersResult.text = SHoppersCovert.toString()
            val SHoppers1Covert = (Stage4 / Stage1 * 100)
            binding.Answers1Result.text = SHoppers1Covert.toString()

            val ActualBuyerConvert = (ActualBuyer / Stage1 * 100)
            binding.BuyersActualResult.text = ActualBuyerConvert.toString()
            val TargetSt1 = (TargetBuyer / (ActualBuyerConvert / 100))
            binding.tLeadsResult.text = TargetSt1.toString()
            val TargetSt2 = (TargetSt1 * (LoginsCovert / 100))
            binding.tCallsResult.text = TargetSt2.toString()
            val TargetSt3 = (TargetSt1 * (SHoppersCovert / 100))
            binding.tAnswersResult.text = TargetSt3.toString()
            val TargetSt4 = (TargetSt1 * (SHoppers1Covert / 100))
            binding.tAnswers1Result.text = TargetSt4.toString()
            val TargetBuyersCal = (TargetSt1 * (ActualBuyerConvert / 100))
            binding.BuyersTargetResult.text = TargetBuyersCal.toString()
        }

        binding.clear.setOnClickListener {
            binding.Leads.setText("")
            binding.Calls.setText("")
            binding.Answers.setText("")
            binding.Answers1.setText("")
            binding.BuyersActual.setText("")
            binding.BuyersTarget.setText("")
        }
        binding.formula.setOnClickListener {
            FragmentIntent(FormulasActivity::class)
        }
        return view
    }
}
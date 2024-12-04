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


class Telesales : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view:View=inflater.inflate(R.layout.fragment_telesales, container, false)
        var Leads=view.findViewById<EditText>(R.id.Leads)
        var Calls=view.findViewById<EditText>(R.id.Calls)
        var Answers=view.findViewById<EditText>(R.id.Answers)
        var Answers1=view.findViewById<EditText>(R.id.Answers1)
        var BuyersActual=view.findViewById<EditText>(R.id.BuyersActual)
        var BuyersTarget=view.findViewById<EditText>(R.id.BuyersTarget)

        var ConversionLeads=view.findViewById<TextView>(R.id.Leads_result)
        var ConversionCalls=view.findViewById<TextView>(R.id.Calls_result)
        var ConversionAnswers=view.findViewById<TextView>(R.id.Answers_result)
        var ConversionAnswers1=view.findViewById<TextView>(R.id.Answers1_result)
        var ConversionBuyersActual=view.findViewById<TextView>(R.id.BuyersActual_result)

        var TargetLeads=view.findViewById<TextView>(R.id.tLeads_result)
        var TargetCalls=view.findViewById<TextView>(R.id.tCalls_result)
        var TargetAnswers=view.findViewById<TextView>(R.id.tAnswers_result)
        var TargetAnswers1=view.findViewById<TextView>(R.id.tAnswers1_result)
        var TargetBuyersTarget=view.findViewById<TextView>(R.id.BuyersTarget_result)

        var Calculatebtn=view.findViewById<Button>(R.id.calculate)
        var Clearbtn=view.findViewById<Button>(R.id.clear)

        Calculatebtn.setOnClickListener {
            if (Leads.text.toString().isEmpty() || Leads.text.toString().isBlank()){
                Toast.makeText(context, "leads must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Calls.text.toString().isEmpty() || Calls.text.toString().isBlank()){
                Toast.makeText(context, "Calls must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Answers.text.toString().isEmpty() || Answers.text.toString().isBlank()){
                Toast.makeText(context, "Answers must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Answers1.text.toString().isEmpty() || Answers1.text.toString().isBlank()){
                Toast.makeText(context, "Empty Below Answers must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (BuyersActual.text.toString().isEmpty() || BuyersActual.text.toString().isBlank()){
                Toast.makeText(context, "Actual Buyers must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (BuyersTarget.text.toString().isEmpty() || BuyersTarget.text.toString().isBlank()){
                Toast.makeText(context, "Buyers Target must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val Stage1 = Leads.text.toString().toDouble()
            val Stage2 = Calls.text.toString().toDouble()
            val Stage3 = Answers.text.toString().toDouble()
            val Stage4 = Answers1.text.toString().toDouble()
            val ActualBuyer = BuyersActual.text.toString().toDouble()
            val TargetBuyer = BuyersTarget.text.toString().toDouble()

            val visitorCovert = (Stage1 / Stage1) * 100
            ConversionLeads.text = visitorCovert.toString()
            val LoginsCovert = (Stage2 / Stage1 * 100)
            ConversionCalls.text =LoginsCovert.toString()
            val SHoppersCovert = (Stage3/ Stage1* 100)
            ConversionAnswers.text =SHoppersCovert.toString()
            val SHoppers1Covert = (Stage4 / Stage1 * 100)
            ConversionAnswers1.text =SHoppers1Covert.toString()

            val ActualBuyerConvert = (ActualBuyer / Stage1 * 100)
            ConversionBuyersActual.text =ActualBuyerConvert.toString()
            val TargetSt1 = (TargetBuyer / (ActualBuyerConvert / 100))
            TargetLeads.text =TargetSt1.toString()
            val TargetSt2 = (TargetSt1 * (LoginsCovert / 100))
            TargetCalls.text =TargetSt2.toString()
            val TargetSt3 = (TargetSt1 * (SHoppersCovert / 100))
            TargetAnswers.text =TargetSt3.toString()
            val TargetSt4 = (TargetSt1 * (SHoppers1Covert / 100))
            TargetAnswers1.text =TargetSt4.toString()
            val TargetBuyersCal = (TargetSt1 * (ActualBuyerConvert / 100))
            TargetBuyersTarget.text =TargetBuyersCal.toString()
        }

        Clearbtn.setOnClickListener {
            Leads.setText("")
            Calls.setText("")
            Answers.setText("")
            Answers1.setText("")
            BuyersActual.setText("")
            BuyersTarget.setText("")
        }
        val formulaBtn:Button = view.findViewById(R.id.formula)
        formulaBtn.setOnClickListener {
            val i = Intent(context , FormulasActivity::class.java)
            startActivity(i)
        }
        // Inflate the layout for this fragment
        return view
    }
}
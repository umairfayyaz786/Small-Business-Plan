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

class BusinessSales : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View=inflater.inflate(R.layout.fragment_business_sales, container, false)
        var sale1=view.findViewById<EditText>(R.id.sale1)
        var sale2=view.findViewById<EditText>(R.id.sale2)
        var sale3=view.findViewById<EditText>(R.id.sale3)
        var sale4=view.findViewById<EditText>(R.id.sale4)
        var BuyersActual=view.findViewById<EditText>(R.id.BuyersActual)
        var BuyersTarget=view.findViewById<EditText>(R.id.BuyersTarget)

        var ConversionStage1=view.findViewById<TextView>(R.id.stage1_result)
        var ConversionStage2=view.findViewById<TextView>(R.id.stage2_result)
        var ConversionStage3=view.findViewById<TextView>(R.id.stage3_result)
        var ConversionStage4=view.findViewById<TextView>(R.id.Stage4_result)
        var ConversionBuyersActual=view.findViewById<TextView>(R.id.BuyersActual_result)

        var TargetStage1=view.findViewById<TextView>(R.id.Stage21_result)
        var TargetStage2=view.findViewById<TextView>(R.id.Stage22_result)
        var TargetStage3=view.findViewById<TextView>(R.id.Stage23_result)
        var TargetStage4=view.findViewById<TextView>(R.id.Stage24_result)
        var TargetBuyersTarget=view.findViewById<TextView>(R.id.BuyersTarget_result)

        var Calculatebtn=view.findViewById<Button>(R.id.calculate)
        var Clearbtn=view.findViewById<Button>(R.id.clear)

        Calculatebtn.setOnClickListener {
            if (sale1.text.toString().isEmpty() || sale1.text.toString().isBlank()){
                Toast.makeText(context, "Sales Stage 1 must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (sale2.text.toString().isEmpty() || sale2.text.toString().isBlank()){
                Toast.makeText(context, "Sales Stage 2 must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (sale3.text.toString().isEmpty() || sale3.text.toString().isBlank()){
                Toast.makeText(context, "Sales Stage 3 must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (sale4.text.toString().isEmpty() || sale4.text.toString().isBlank()){
                Toast.makeText(context, "Sales Stage 4 must be required!", Toast.LENGTH_SHORT).show()
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
            val Stage1 = sale1.text.toString().toDouble()
            val Stage2 = sale2.text.toString().toDouble()
            val Stage3 = sale3.text.toString().toDouble()
            val Stage4 = sale4.text.toString().toDouble()
            val ActualBuyer = BuyersActual.text.toString().toDouble()
            val TargetBuyer = BuyersTarget.text.toString().toDouble()

            val stage1Covert = (Stage1 / Stage1) * 100
            ConversionStage1.text = stage1Covert.toString()
            val stage2Covert = (Stage2 / Stage1 * 100)
            ConversionStage2.text = stage2Covert.toString()
            val stage3Covert = (Stage3 / Stage1 * 100)
            ConversionStage3.text = stage3Covert.toString()
            val stage4Covert = (Stage4/ Stage1 * 100)
            ConversionStage4.text = stage4Covert.toString()

            val ActualBuyerConvert = (ActualBuyer / Stage1 * 100)
            ConversionBuyersActual.text = ActualBuyerConvert.toString()
            val TargetSt1 = (TargetBuyer / (ActualBuyerConvert / 100))
            TargetStage1.text = TargetSt1.toString()
            val TargetSt2 = (TargetSt1 * (stage2Covert / 100))
            TargetStage2.text = TargetSt2.toString()
            val TargetSt3 = (TargetSt1 * (stage3Covert / 100))
            TargetStage3.text = TargetSt3.toString()
            val TargetSt4 = (TargetSt1 * (stage4Covert / 100))
            TargetStage4.text = TargetSt4.toString()
            val TargetBuyersCal = (TargetSt1 * (ActualBuyerConvert / 100))
            TargetBuyersTarget.text = TargetBuyersCal.toString()
        }

        Clearbtn.setOnClickListener {
            sale1.setText("")
            sale2.setText("")
            sale3.setText("")
            sale4.setText("")
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
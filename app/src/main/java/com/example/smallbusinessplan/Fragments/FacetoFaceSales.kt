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


class FacetoFaceSales : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View=inflater.inflate(R.layout.fragment_faceto_face_sales, container, false)
        var visitors=view.findViewById<EditText>(R.id.Leads)
        var Logins=view.findViewById<EditText>(R.id.Pitches)
        var Shoppers=view.findViewById<EditText>(R.id.Quotations)
        var Shoppers1=view.findViewById<EditText>(R.id.Quotations1)
        var BuyersActual=view.findViewById<EditText>(R.id.BuyersActual)
        var BuyersTarget=view.findViewById<EditText>(R.id.BuyersTarget)

        var Conversionvisitors=view.findViewById<TextView>(R.id.Leads_result)
        var ConversionLogins=view.findViewById<TextView>(R.id.Pitches_result)
        var ConversionShoppers=view.findViewById<TextView>(R.id.Quotations_result)
        var ConversionShoppers1=view.findViewById<TextView>(R.id.Quotations1_result)
        var ConversionBuyersActual=view.findViewById<TextView>(R.id.BuyersActual_result)

        var TargetVisitor=view.findViewById<TextView>(R.id.tLeads_result)
        var TargetLogins=view.findViewById<TextView>(R.id.tpitches_result)
        var TargetShopper=view.findViewById<TextView>(R.id.tQuotations_result)
        var TargetShopper1=view.findViewById<TextView>(R.id.tQuotations1_result)
        var TargetBuyersTarget=view.findViewById<TextView>(R.id.BuyersTarget_result)

        var Calculatebtn=view.findViewById<Button>(R.id.calculate)
        var Clearbtn=view.findViewById<Button>(R.id.clear)

        Calculatebtn.setOnClickListener {
            if (visitors.text.toString().isEmpty() || visitors.text.toString().isBlank()){
                Toast.makeText(context, "Leads must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Logins.text.toString().isEmpty() || Logins.text.toString().isBlank()){
                Toast.makeText(context, "Pitches must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Shoppers.text.toString().isEmpty() || Shoppers.text.toString().isBlank()){
                Toast.makeText(context, "Quotations must be required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Shoppers1.text.toString().isEmpty() || Shoppers1.text.toString().isBlank()){
                Toast.makeText(context, "Empty Below Quotations must be required!", Toast.LENGTH_SHORT).show()
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
            val Stage1 = visitors.text.toString().toDouble()
            val Stage2 = Logins.text.toString().toDouble()
            val Stage3 = Shoppers.text.toString().toDouble()
            val Stage4 = Shoppers1.text.toString().toDouble()
            val ActualBuyer = BuyersActual.text.toString().toDouble()
            val TargetBuyer = BuyersTarget.text.toString().toDouble()

            val visitorCovert = (Stage1/ Stage1) * 100
            Conversionvisitors.text = visitorCovert.toString()
            val LoginsCovert = (Stage2/ Stage1 * 100)
            ConversionLogins.text =LoginsCovert.toString()
            val SHoppersCovert = (Stage3 / Stage1* 100)
            ConversionShoppers.text =SHoppersCovert.toString()
            val SHoppers1Covert = (Stage4 / Stage1* 100)
            ConversionShoppers1.text =SHoppers1Covert.toString()

            val ActualBuyerConvert = (ActualBuyer/ Stage1 * 100)
            ConversionBuyersActual.text =ActualBuyerConvert.toString()
            val TargetSt1 = (TargetBuyer/ (ActualBuyerConvert / 100))
            TargetVisitor.text =TargetSt1.toString()
            val TargetSt2 = (TargetSt1 * (LoginsCovert / 100))
            TargetLogins.text =TargetSt2.toString()
            val TargetSt3 = (TargetSt1 * (SHoppersCovert / 100))
            TargetShopper.text =TargetSt3.toString()
            val TargetSt4 = (TargetSt1 * (SHoppers1Covert / 100))
            TargetShopper1.text =TargetSt4.toString()
            val TargetBuyersCal = (TargetSt1 * (ActualBuyerConvert / 100))
            TargetBuyersTarget.text =TargetBuyersCal.toString()
        }

        Clearbtn.setOnClickListener {
            visitors.setText("")
            Logins.setText("")
            Shoppers.setText("")
            Shoppers1.setText("")
            BuyersActual.setText("")
            BuyersTarget.setText("")
        }
        val formulaBtn:Button = view.findViewById(R.id.formula)
        formulaBtn.setOnClickListener {
            val i = Intent(context , FormulasActivity::class.java)
            startActivity(i)
        }
        return view
    }
        // Inflate the layout for this fragmen
}
package com.example.solarcalc13


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_calculate.*

class CalculateFragment : Fragment(), View.OnClickListener{

    lateinit var navController: NavController
    lateinit var emailText: String
    lateinit var slider: SeekBar
    lateinit var value: TextView
    lateinit var slider2 : SeekBar
    lateinit var value2: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.view_offers_btn).setOnClickListener(this)

        //Defining the villanyszamla seekbar and changing the textView according to its progress

        slider = view.findViewById(R.id.seekBarVillany) as SeekBar
        value = view.findViewById(R.id.seekBarVillany_result) as TextView

        slider.max = 40000 // means that the slider goes up to 50
        slider.progress = 10000 // starting position of slider

        slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                value.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        slider2 = view.findViewById(R.id.seekBarSaving) as SeekBar
        value2 = view.findViewById(R.id.seekBarSaving_result) as TextView
        slider2.max = 100
        slider2.progress = 40
        slider2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                value2.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

    }

    fun getAmount(): String {
        emailText = value.text.toString()
        return emailText
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.view_offers_btn ->
            {
                if(!TextUtils.isEmpty(value.text.toString())){
                    val bundle = bundleOf(
                        "amount" to value.text.toString(),
                        "roof" to ferde_btn.isChecked,
                        "cserep" to cserep_btn.isChecked,
                        "pala" to pala_btn.isChecked,
                        "beton" to beton_btn.isChecked,
                        "zsin" to zsin_btn.isChecked,
                        "fem" to fem_btn.isChecked,
                        "angle" to angle.text.toString()
                    )
                    navController.navigate(
                        R.id.action_calculateAccurateFragment_to_viewOffersFragment,
                        bundle
                    )
                }
                else {
                    Toast.makeText(activity, "Adjon meg összeget", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
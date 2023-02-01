package com.example.aboutme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aboutme.databinding.ActivityMainBinding
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {

    lateinit  var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

    }

    private fun calculateTip(){

        //must convert the editable to string prior to using toDouble
        val costString = binding.billTotalEditText.text.toString()
        val cost = costString.toDoubleOrNull()
        if (cost == null)
        {
            binding.tipResultTextview.text = ""
            return
        }
        else
        {
            //Now we will get the id of the checked radioButton in the radio group
            val tipPercentage = when(binding.tipRadioGroup.checkedRadioButtonId)
            {
                R.id.twentyTip_radioButton -> .20
                R.id.eighteenTip_radioButton -> .18
                else -> .15
            }
            var totalCost = cost + (cost*tipPercentage)
            if(binding.roundUpSwitch.isChecked)
            {
                totalCost = ceil(totalCost)
            }
            binding.tipResultTextview.text = getString(R.string.total_amount, totalCost.toString())
        }

    }
}
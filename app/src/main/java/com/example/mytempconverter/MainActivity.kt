package com.example.mytempconverter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    private lateinit var etTemperature: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnConvert: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTemperature = findViewById(R.id.etTemperature)
        radioGroup = findViewById(R.id.radioGroup)
        btnConvert = findViewById(R.id.btnConvert)
        tvResult = findViewById(R.id.tvResult)

        btnConvert.setOnClickListener {
            convertTemperature()
        }
    }

    private fun convertTemperature() {
        val temperatureText = etTemperature.text.toString().trim()

        if (temperatureText.isNotEmpty()) {
            val temperature = temperatureText.toDouble()
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)

            val result = when (selectedRadioButton.id) {
                R.id.rbCelsiusToFahrenheit -> celsiusToFahrenheit(temperature)
                R.id.rbFahrenheitToCelsius -> fahrenheitToCelsius(temperature)
                else -> throw IllegalArgumentException("Invalid radio button selected")
            }

            tvResult.text = result.toString()
        } else {
            tvResult.text = ""
        }
    }

    private fun celsiusToFahrenheit(celsius: Double): Double {
        return round((celsius * 9 / 5) + 32)
    }

    private fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return round((fahrenheit - 32) * 5 / 9)
    }
}

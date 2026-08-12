package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.network.RetrofitClient
import kotlinx.coroutines.launch

private const val API_KEY = "944d6b6658a6b087ee5e306fa146afce"

class MainActivity : AppCompatActivity() {

    private lateinit var etCitySearch: EditText
    private lateinit var btnSearchWeather: Button
    private lateinit var tvCityName: TextView
    private lateinit var tvTemperature: TextView
    private lateinit var tvWeatherCondition: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWindSpeed: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        etCitySearch = findViewById(R.id.etCitySearch)
        btnSearchWeather = findViewById(R.id.btnSearchWeather)
        tvCityName = findViewById(R.id.tvCityName)
        tvTemperature = findViewById(R.id.tvTemperature)
        tvWeatherCondition = findViewById(R.id.tvWeatherCondition)
        tvHumidity = findViewById(R.id.tvHumidity)
        tvWindSpeed = findViewById(R.id.tvWindSpeed)

        // Call temporary API test (Member 3 Step 13)
        testApiConnection()

        // Handle Search Button Click
        btnSearchWeather.setOnClickListener {
            val queryCity = etCitySearch.text.toString().trim()
            if (queryCity.isNotEmpty()) {
                updateWeatherDisplay(queryCity)
            } else {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Temporary API connection test (Member 3 Step 12)
     */
    private fun testApiConnection() {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.getWeather(
                    "Colombo",
                    API_KEY
                )

                if (response.isSuccessful) {
                    println("API CONNECTION SUCCESS")
                    Log.d("WeatherApp", "API CONNECTION SUCCESS: ${response.body()}")
                } else {
                    println("API ERROR: ${response.code()}")
                    Log.e("WeatherApp", "API ERROR: ${response.code()}")
                }

            } catch (e: Exception) {
                println("NETWORK ERROR: ${e.message}")
                Log.e("WeatherApp", "NETWORK ERROR: ${e.message}")
            }
        }
    }

    /**
     * Placeholder method to simulate updating weather data upon user search.
     * Replace or extend with an API call (e.g., OpenWeatherMap using Retrofit).
     */
    private fun updateWeatherDisplay(cityName: String) {
        tvCityName.text = cityName.lowercase().replaceFirstChar { it.uppercase() }

        // Example mock values for demonstration
        tvTemperature.text = "30°C"
        tvWeatherCondition.text = "Sunny"
        tvHumidity.text = "65%"
        tvWindSpeed.text = "15 km/h"

        etCitySearch.text.clear()
        Toast.makeText(this, "Updated weather for $cityName", Toast.LENGTH_SHORT).show()
    }
}
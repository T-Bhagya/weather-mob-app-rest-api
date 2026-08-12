package com.example.weatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.network.RetrofitClient
import com.example.weatherapp.network.WeatherResponse
import kotlinx.coroutines.launch

private const val API_KEY = "944d6b6658a6b087ee5e306fa146afce"

class MainActivity : AppCompatActivity() {

    private lateinit var etCity: EditText
    private lateinit var btnSearch: Button

    private lateinit var tvCity: TextView
    private lateinit var tvTemperature: TextView
    private lateinit var tvCondition: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWind: TextView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCity = findViewById(R.id.etCity)
        btnSearch = findViewById(R.id.btnSearch)

        tvCity = findViewById(R.id.tvCity)
        tvTemperature = findViewById(R.id.tvTemperature)
        tvCondition = findViewById(R.id.tvCondition)
        tvHumidity = findViewById(R.id.tvHumidity)
        tvWind = findViewById(R.id.tvWind)
        tvError = findViewById(R.id.tvError)

        btnSearch.setOnClickListener {
            val city = etCity.text.toString().trim()

            if (city.isEmpty()) {
                tvError.text = "Please enter a city name."
            } else {
                tvError.text = ""
                getWeather(city)
            }
        }
    }

    private fun getWeather(city: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.getWeather(
                    city,
                    API_KEY
                )

                if (response.isSuccessful) {
                    val weather = response.body()

                    if (weather != null) {
                        displayWeather(weather)
                    } else {
                        tvError.text = "No weather data available."
                    }
                } else {
                    when (response.code()) {
                        404 -> tvError.text = "City not found."
                        else -> tvError.text = "API error."
                    }
                }
            } catch (e: Exception) {
                tvError.text = "Unable to connect. Please check your internet connection."
            }
        }
    }

    private fun displayWeather(weather: WeatherResponse) {
        tvCity.text = weather.name
        tvTemperature.text = "${weather.main.temp} °C"
        tvCondition.text = weather.weather.firstOrNull()?.description ?: "Unknown"
        tvHumidity.text = "Humidity: ${weather.main.humidity}%"
        tvWind.text = "Wind Speed: ${weather.wind.speed} m/s"
        tvError.text = ""
    }
}
package com.example.weatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
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
    private lateinit var btnHeaderSearch: ImageButton

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
        btnHeaderSearch = findViewById(R.id.btnHeaderSearch)

        tvCity = findViewById(R.id.tvCity)
        tvTemperature = findViewById(R.id.tvTemperature)
        tvCondition = findViewById(R.id.tvCondition)
        tvHumidity = findViewById(R.id.tvHumidity)
        tvWind = findViewById(R.id.tvWind)
        tvError = findViewById(R.id.tvError)

        btnSearch.setOnClickListener { performSearch() }
        btnHeaderSearch.setOnClickListener { performSearch() }
    }

    private fun performSearch() {
        val city = etCity.text.toString().trim()

        if (city.isEmpty()) {
            tvError.text = getString(R.string.error_empty_city)
        } else {
            tvError.text = ""
            getWeather(city)
        }
    }

    private fun getWeather(city: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.api.getWeather(
                    city,
                    API_KEY,
                )

                if (response.isSuccessful) {
                    val weather = response.body()

                    if (weather != null) {
                        displayWeather(weather)
                    } else {
                        tvError.text = getString(R.string.error_no_data)
                    }
                } else {
                    when (response.code()) {
                        404 -> tvError.text = getString(R.string.error_city_not_found)
                        else -> tvError.text = getString(R.string.error_api)
                    }
                }
            } catch (_: Exception) {
                tvError.text = getString(R.string.error_connection)
            }
        }
    }

    private fun displayWeather(weather: WeatherResponse) {
        tvCity.text = weather.name

        tvTemperature.text = getString(R.string.temp_unit, weather.main.temp.toString())
        tvCondition.text = weather.weather.firstOrNull()?.description ?: "Unknown"
        tvHumidity.text = getString(R.string.humidity_format, weather.main.humidity)
        tvWind.text = getString(R.string.wind_speed_format, weather.wind.speed)
        tvError.text = ""
    }
}
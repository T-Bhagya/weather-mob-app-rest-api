package com.example.weatherapp.network

import com.google.gson.annotations.SerializedName

/**
 * Data model for OpenWeatherMap API response.
 * Created as a starting model for Member 4 to expand if needed.
 */
data class WeatherResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("main") val main: MainInfo? = null,
    @SerializedName("weather") val weather: List<WeatherDescription>? = null,
    @SerializedName("wind") val wind: WindInfo? = null
)

data class MainInfo(
    @SerializedName("temp") val temp: Double? = null,
    @SerializedName("humidity") val humidity: Int? = null
)

data class WeatherDescription(
    @SerializedName("main") val main: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("icon") val icon: String? = null
)

data class WindInfo(
    @SerializedName("speed") val speed: Double? = null
)

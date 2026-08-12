# Weather Mobile App (REST API) 🌤️

An Android mobile application built with **Kotlin**, **Retrofit 2**, and **Coroutines** that fetches and displays real-time weather information for any city using the **OpenWeather REST API**.

---

## 📱 Features

- 🔍 **City Weather Search**: Look up current weather details by entering any city name.
- 🌡️ **Real-Time Weather Metrics**:
  - Current Temperature (°C)
  - Weather Condition & Description (e.g., clear sky, light rain, scattered clouds)
  - Humidity Percentage (%)
  - Wind Speed (m/s)
- ⚠️ **Input Validation & Error Handling**:
  - Prevents empty searches with prompt notifications.
  - Clear error messaging for non-existent cities (`404 City Not Found`).
  - Network connectivity error handling for offline or unstable connections.
- ⚡ **Asynchronous REST API Integration**: Clean network call execution using Kotlin Coroutines and Retrofit 2.

---

## 🛠️ Tech Stack & Libraries

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: Android XML Layouts & AppCompat
- **Networking**: [Retrofit 2](https://square.github.io/retrofit/) & OkHttp
- **JSON Converter**: [Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)
- **Concurrency**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & `lifecycleScope`
- **Architecture**: Modern Android architecture separating Network layer (`RetrofitClient`, `WeatherApi`, `WeatherResponse`) and UI (`MainActivity`).

---

## 📡 API Specification

This app integrates with the **OpenWeather Current Weather Data API**.

| Detail | Specification |
|---|---|
| **API Name** | OpenWeather Current Weather Data API |
| **Base URL** | `https://api.openweathermap.org/` |
| **Endpoint** | `data/2.5/weather` |
| **HTTP Method** | `GET` |
| **Unit System** | Metric (`°C`, `m/s`) |

For full endpoint documentation and query details, check out [`API_DOCUMENTATION.md`](file:///d:/Mobile%20Apps/weather-mob-app-rest-api/API_DOCUMENTATION.md).

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio** (Ladybug / Jellyfish or newer recommended)
- **JDK 17** or higher
- **Android SDK** (API Level 24+)
- **OpenWeather API Key** (Get one free at [OpenWeather](https://openweathermap.org/api))

### Installation & Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/T-Bhagya/weather-mob-app-rest-api.git
   cd weather-mob-app-rest-api
   ```

2. **Open in Android Studio**
   Open Android Studio, choose **File > Open**, and select the project directory.

3. **Configure API Key**
   In [`MainActivity.kt`](app/src/main/java/com/example/weatherapp/MainActivity.kt), ensure your OpenWeather API key is configured:
   ```kotlin
   private const val API_KEY = "YOUR_OPENWEATHER_API_KEY"
   ```

4. **Build and Run**
   Sync Gradle and run the application on an Android Emulator or a physical Android device.

---

## 📁 Project Structure

```
weather-mob-app-rest-api/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/weatherapp/
│           │   ├── network/
│           │   │   ├── RetrofitClient.kt   # Singleton Retrofit setup
│           │   │   ├── WeatherApi.kt       # Retrofit endpoint interface
│           │   │   └── WeatherResponse.kt # Data models for JSON deserialization
│           │   └── MainActivity.kt        # App UI logic and event handlers
│           ├── res/
│           │   └── layout/
│           │       └── activity_main.xml  # App layout interface
│           └── AndroidManifest.xml        # Manifest with INTERNET permissions
├── API_DOCUMENTATION.md                   # API details & request samples
└── README.md                              # Project documentation
```

---

## 📄 License

This project is open-source and available under the standard MIT License.

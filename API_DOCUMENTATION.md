# Weather API Documentation

## 1. API Name

OpenWeather Current Weather Data API

## 2. HTTP Method

GET

## 3. Base URL

https://api.openweathermap.org/

## 4. Endpoint

data/2.5/weather

## 5. Query Parameters

| Parameter | Description | Example |
|---|---|---|
| q | City name | Colombo |
| appid | OpenWeather API key | Hidden for security |
| units | Unit system | metric |

## 6. Example Request

```text
https://api.openweathermap.org/data/2.5/weather?q=Colombo&appid=YOUR_API_KEY&units=metric
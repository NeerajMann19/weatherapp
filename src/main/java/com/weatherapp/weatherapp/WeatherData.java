package com.weatherapp.weatherapp;

public class WeatherData {
    public String city, country, condition, windDir, aqiLevel, uvLevel;
    public double tempC, feelsLikeC, windKph, uv;
    public int humidity, aqi;

    public String getUVLevel() {
        if (uv <= 2) return "Low";
        else if (uv <= 5) return "Moderate";
        else if (uv <= 7) return "High";
        else if (uv <= 10) return "Very High";
        else return "Extreme";
    }

    public String getAQILevel() {
        switch (aqi) {
            case 1: return "Good";
            case 2: return "Moderate";
            case 3: return "Unhealthy for Sensitive Groups";
            case 4: return "Unhealthy";
            case 5: return "Very Unhealthy";
            case 6: return "Hazardous";
            default: return "Unknown";
        }
    }
}
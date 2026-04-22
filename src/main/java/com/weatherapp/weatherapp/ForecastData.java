package com.weatherapp.weatherapp;

public class ForecastData {
    public String date, condition, sunrise, sunset;
    public double maxTempC, minTempC;
    public int chanceOfRain;

    public String getDayLabel(int index) {
        switch (index) {
            case 0: return "Today";
            case 1: return "Tomorrow";
            default: return "Day After";
        }
    }
}
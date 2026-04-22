package com.weatherapp.weatherapp;

public class HourlyData {
    public String time;
    public double tempC;
    public String condition;
    public int chanceOfRain;

    public String getShortTime() {
        // "2024-04-22 15:00" -> "3 PM"
        if (time == null || time.equals("N/A")) return "";
        String[] parts = time.split(" ");
        if (parts.length < 2) return time;
        String[] hm = parts[1].split(":");
        int hour = Integer.parseInt(hm[0]);
        if (hour == 0) return "12 AM";
        if (hour == 12) return "12 PM";
        return hour < 12 ? hour + " AM" : (hour - 12) + " PM";
    }
}
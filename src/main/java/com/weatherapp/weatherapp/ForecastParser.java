package com.weatherapp.weatherapp;

import java.util.ArrayList;

public class ForecastParser {

    public static ArrayList<ForecastData> parse(String json) {
        ArrayList<ForecastData> list = new ArrayList<>();
        String[] days = json.split("\\{\"date\":");

        for (int i = 1; i <= 3 && i < days.length; i++) {
            String day = days[i];
            ForecastData fd = new ForecastData();

            fd.date = extract(day, "\"", "\"");
            fd.maxTempC = Double.parseDouble(extract(day, "\"maxtemp_c\":", ","));
            fd.minTempC = Double.parseDouble(extract(day, "\"mintemp_c\":", ","));
            fd.condition = extract(day, "\"text\":\"", "\"");
            fd.chanceOfRain = Integer.parseInt(extract(day, "\"daily_chance_of_rain\":", ","));
            fd.sunrise = extract(day, "\"sunrise\":\"", "\"");
            fd.sunset = extract(day, "\"sunset\":\"", "\"");

            list.add(fd);
        }
        return list;
    }

    public static ArrayList<HourlyData> parseHourly(String json) {
        ArrayList<HourlyData> list = new ArrayList<>();

        // Get today's segment (first day)
        String[] days = json.split("\\{\"date\":");
        if (days.length < 2) return list;
        String today = days[1];

        // Split on each hour entry
        String[] hours = today.split("\\{\"time_epoch\":");

        for (int i = 1; i < hours.length; i++) {
            String h = hours[i];
            HourlyData hd = new HourlyData();

            hd.time = extract(h, "\"time\":\"", "\"");
            hd.tempC = Double.parseDouble(extract(h, "\"temp_c\":", ","));
            hd.condition = extract(h, "\"text\":\"", "\"");

            String rain = extract(h, "\"chance_of_rain\":", ",");
            if (rain.equals("N/A")) rain = "0";
            hd.chanceOfRain = (int) Double.parseDouble(rain);

            list.add(hd);
        }
        return list;
    }

    private static String extract(String json, String start, String end) {
        int s = json.indexOf(start);
        if (s == -1) return "N/A";
        s += start.length();
        int e = json.indexOf(end, s);
        if (e == -1) return "N/A";
        return json.substring(s, e).trim();
    }
}
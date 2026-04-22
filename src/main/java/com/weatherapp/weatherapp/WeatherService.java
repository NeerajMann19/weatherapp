package com.weatherapp.weatherapp;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Service
public class WeatherService {

    private static final String API_KEY = "e1c303bbb133409fb1994421262503";
    private static final String CURRENT_URL = "http://api.weatherapi.com/v1/current.json";
    private static final String FORECAST_URL = "http://api.weatherapi.com/v1/forecast.json";

    public WeatherData getWeather(String city) throws Exception {
        String urlString = CURRENT_URL + "?key=" + API_KEY + "&q=" + city + "&aqi=yes";
        String json = callAPI(urlString);
        return parseWeather(json);
    }

    public ArrayList<ForecastData> getForecast(String city) throws Exception {
        String urlString = FORECAST_URL + "?key=" + API_KEY + "&q=" + city + "&days=3&aqi=no&alerts=no";
        String json = callAPI(urlString);
        return ForecastParser.parse(json);
    }

    public ArrayList<HourlyData> getHourly(String city) throws Exception {
        String urlString = FORECAST_URL + "?key=" + API_KEY + "&q=" + city + "&days=1&aqi=no&alerts=no";
        String json = callAPI(urlString);
        return ForecastParser.parseHourly(json);
    }
    public String getAITip(WeatherData weather) throws Exception {
        String prompt = "Given this weather: " +
            weather.tempC + "°C, feels like " + weather.feelsLikeC + "°C, " +
            "condition: " + weather.condition + ", humidity: " + weather.humidity + "%, " +
            "UV index: " + weather.uv + ", AQI: " + weather.aqi + ". " +
            "Give a single, friendly, practical tip for the day in one sentence. " +
            "Be specific to the conditions. No preamble, just the tip.";

        String body = "{\"model\":\"claude-sonnet-4-20250514\",\"max_tokens\":100," +
            "\"messages\":[{\"role\":\"user\",\"content\":\"" +
            prompt.replace("\"", "\\\"") + "\"}]}";

        URL url = new URL("https://api.anthropic.com/v1/messages");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("x-api-key", System.getenv("ANTHROPIC_API_KEY"));
        conn.setRequestProperty("anthropic-version", "2023-06-01");
        conn.setDoOutput(true);
        conn.getOutputStream().write(body.getBytes());

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) response.append(line);
        in.close();

        String json = response.toString();
        return extract(json, "\"text\":\"", "\"");
    }
    
    private String callAPI(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) response.append(line);
        in.close();
        return response.toString();
    }

    private WeatherData parseWeather(String json) {
        WeatherData data = new WeatherData();
        data.city = extract(json, "\"name\":\"", "\"");
        data.country = extract(json, "\"country\":\"", "\"");
        data.tempC = Double.parseDouble(extract(json, "\"temp_c\":", ","));
        data.feelsLikeC = Double.parseDouble(extract(json, "\"feelslike_c\":", ","));
        data.condition = extract(json, "\"text\":\"", "\"");
        data.humidity = Integer.parseInt(extract(json, "\"humidity\":", ","));
        data.windKph = Double.parseDouble(extract(json, "\"wind_kph\":", ","));
        data.windDir = extract(json, "\"wind_dir\":\"", "\"");
        data.uv = Double.parseDouble(extract(json, "\"uv\":", ","));
        data.aqi = (int) Double.parseDouble(extract(json, "\"us-epa-index\":", ","));
        return data;
    }

    private String extract(String json, String start, String end) {
        int s = json.indexOf(start);
        if (s == -1) return "N/A";
        s += start.length();
        int e = json.indexOf(end, s);
        if (e == -1) return "N/A";
        return json.substring(s, e).trim();
    }
}
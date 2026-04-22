package com.weatherapp.weatherapp;

public class WeatherTips {

    public static String getTip(WeatherData w) {
        String cond = w.condition.toLowerCase();

        // Storm / Thunder
        if (cond.contains("thunder") || cond.contains("storm"))
            return "⛈️ Thunderstorm alert! Stay indoors and avoid open areas.";

        // Heavy rain
        if (cond.contains("rain") || cond.contains("drizzle")) {
            if (w.humidity > 85)
                return "🌧️ Heavy rain expected. Carry an umbrella and avoid waterlogged roads.";
            return "🌂 Light rain likely. Keep an umbrella handy just in case.";
        }

        // Snow
        if (cond.contains("snow"))
            return "❄️ Snowfall today. Dress in warm layers and drive carefully.";

        // Fog / Mist
        if (cond.contains("fog") || cond.contains("mist"))
            return "🌫️ Low visibility due to fog. Drive slowly and use fog lights.";

        // Extreme heat
        if (w.tempC >= 42)
            return "🔥 Extreme heat warning! Avoid going out between 11am–4pm and stay hydrated.";

        if (w.tempC >= 38) {
            if (w.uv >= 8)
                return "☀️ Very hot and high UV. Apply SPF 50+ sunscreen and drink plenty of water.";
            return "🌡️ Heatwave conditions. Stay in shade, wear light clothing, and keep hydrated.";
        }

        // High UV
        if (w.uv >= 8)
            return "🕶️ UV index is very high. Wear sunscreen, sunglasses, and a hat outdoors.";

        if (w.uv >= 6)
            return "🧴 Moderate-high UV today. Apply sunscreen if you're spending time outside.";

        // Cold
        if (w.tempC <= 5)
            return "🧥 It's very cold outside. Wear heavy layers and cover your extremities.";

        if (w.tempC <= 15)
            return "🧣 Chilly today. A jacket or sweater is recommended.";

        // Windy
        if (w.windKph >= 50)
            return "💨 Strong winds today. Secure loose items and be careful outdoors.";

        // Poor AQI
        if (w.aqi >= 4)
            return "😷 Air quality is poor. Wear a mask if going outside, especially if sensitive.";

        if (w.aqi == 3)
            return "🌬️ Moderate air quality. Limit prolonged outdoor activity if you have asthma.";

        // Cloudy / Overcast
        if (cond.contains("cloud") || cond.contains("overcast"))
            return "☁️ Overcast skies today. A light jacket might be comfortable.";

        // Pleasant / Sunny
        if (w.tempC >= 22 && w.tempC <= 30 && w.uv < 6)
            return "😊 Beautiful weather today! Great time for a walk or outdoor activity.";

        // Default
        return "🌤️ Looks like a decent day. Dress comfortably and enjoy!";
    }
}
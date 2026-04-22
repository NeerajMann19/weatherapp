# 🌤 WeatherApp

A full-stack weather web application built with **Spring Boot** and vanilla **HTML/CSS/JS**, fetching real-time data from [WeatherAPI.com](https://www.weatherapi.com/).

---

## ✨ Features

- 🔍 **City Search** — Search any city worldwide for live weather
- 📍 **Location Auto-Detect** — One click to fetch weather for your current location
- 🌡️ **Current Weather** — Temperature, feels like, condition, humidity, wind, UV index, AQI
- ⏱️ **Hourly Forecast** — 24-hour scrollable forecast with temperature trend graph
- 📅 **3-Day Forecast** — Daily high/low, condition, and rain chance
- 💡 **Smart Weather Tips** — Context-aware advice (heatwave alerts, UV warnings, rain reminders)
- ⭐ **Favourites** — Save and quick-switch between favourite cities
- 🎨 **Dynamic Themes** — Background and colors change based on weather condition
- 🌙 **Auto Day/Night Mode** — Dark starry sky at night, vibrant gradients during the day
- 📊 **UV Progress Bar** — Visual UV index indicator with color gradient
- 🏷️ **AQI Badge** — Color-coded air quality badge

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Spring Boot 4.0.5 |
| Language | Java 21 |
| Build Tool | Maven 3.9.15 |
| Template Engine | Thymeleaf |
| Frontend | HTML + CSS + Vanilla JS |
| Fonts | Google Fonts (Poppins, DM Mono) |
| Weather Data | WeatherAPI.com (Free Tier) |
| Storage | Flat file (favourites.txt) |

---

## 📁 Project Structure
weatherapp/
├── src/main/
│   ├── java/com/weatherapp/weatherapp/
│   │   ├── WeatherappApplication.java
│   │   ├── WeatherController.java
│   │   ├── WeatherService.java
│   │   ├── WeatherData.java
│   │   ├── ForecastData.java
│   │   ├── ForecastParser.java
│   │   ├── HourlyData.java
│   │   ├── FavouritesService.java
│   │   └── WeatherTips.java
│   └── resources/
│       ├── templates/
│       │   └── index.html
│       └── static/
│           └── style.css
└── pom.xml

---

## 🚀 Getting Started

### Prerequisites
- JDK 21
- Maven 3.9+

### Run Locally

```bash
cd WeatherApp-Web/weatherapp
mvn spring-boot:run
```

Then open: [http://localhost:8080](http://localhost:8080)

### Environment Variables

If Maven or Java is not recognized in a new terminal, set them manually:

```cmd
set JAVA_HOME=C:\Program Files\Java\jdk-21.0.10
set PATH=%PATH%;C:\Users\Neeraj\Downloads\apache-maven-3.9.15-bin\apache-maven-3.9.15\bin
```

---

## 🌦️ Weather Themes

| Condition | Theme |
|---|---|
| ☀️ Sunny / Clear | Warm orange gradient + sun rays |
| 🌧️ Rain / Drizzle | Deep blue gradient + falling rain |
| ☁️ Cloudy / Overcast | Indigo gradient + drifting clouds |
| ⛈️ Thunder / Storm | Near-black + lightning flash effect |
| ❄️ Snow | Cool blue gradient + falling snowflakes |
| 🌫️ Fog / Mist | Grey gradient + fog pulse |
| 🌙 Night (7pm–6am) | Dark navy gradient + twinkling stars |

---

## 📸 Screenshots

> Add screenshots here after deployment

---

## 🔮 Planned Features

- [ ] Navbar with dark/light toggle
- [ ] Floating location button
- [ ] AQI circular gauge
- [ ] Cloud deployment

---

## 👨‍💻 Developer

**Neeraj** — Built as a learning project exploring Java, Spring Boot, and modern frontend design.
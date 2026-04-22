package com.weatherapp.weatherapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;

@Controller
public class WeatherController {

    private final WeatherService weatherService;
    private final FavouritesService favouritesService;

    public WeatherController(WeatherService weatherService, FavouritesService favouritesService) {
        this.weatherService = weatherService;
        this.favouritesService = favouritesService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("favourites", favouritesService.getAll());
        return "index";
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city, Model model) {
        try {
            WeatherData data = weatherService.getWeather(city);
            ArrayList<ForecastData> forecast = weatherService.getForecast(city);
            ArrayList<HourlyData> hourly = weatherService.getHourly(city);
            String tip = WeatherTips.getTip(data);
            model.addAttribute("weather", data);
            model.addAttribute("forecast", forecast);
            model.addAttribute("hourly", hourly);
            model.addAttribute("tip", tip);
        } catch (Exception e) {
            model.addAttribute("error", "City not found. Please try again.");
        }
        model.addAttribute("city", city);
        model.addAttribute("favourites", favouritesService.getAll());
        return "index";
    }

    @GetMapping("/favourite/add")
    public String addFavourite(@RequestParam String city) {
        favouritesService.add(city);
        return "redirect:/weather?city=" + city;
    }

    @GetMapping("/favourite/remove")
    public String removeFavourite(@RequestParam String city, @RequestParam(required = false) String redirect) {
        favouritesService.remove(city);
        if (redirect != null && !redirect.isEmpty())
            return "redirect:/weather?city=" + redirect;
        return "redirect:/";
    }
}
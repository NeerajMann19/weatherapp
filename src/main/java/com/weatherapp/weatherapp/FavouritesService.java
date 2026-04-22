package com.weatherapp.weatherapp;

import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavouritesService {

    private static final String FILE = "favourites.txt";

    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        File f = new File(FILE);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) list.add(line.trim());
            }
        } catch (Exception e) { /* ignore */ }
        return list;
    }

    public void add(String city) {
        List<String> list = getAll();
        if (list.stream().anyMatch(c -> c.equalsIgnoreCase(city))) return;
        list.add(city);
        save(list);
    }

    public void remove(String city) {
        List<String> list = getAll();
        list.removeIf(c -> c.equalsIgnoreCase(city));
        save(list);
    }

    private void save(List<String> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (String c : list) bw.write(c + "\n");
        } catch (Exception e) { /* ignore */ }
    }
}
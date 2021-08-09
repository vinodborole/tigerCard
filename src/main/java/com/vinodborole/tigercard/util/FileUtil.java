package com.vinodborole.tigercard.util;

import com.vinodborole.tigercard.entity.Journey;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileUtil {
    public static List<Journey> getJourney(InputStream inputStream) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<Journey> journeys = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] split = line.split(" ");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = formatter.parse(split[0] + " " + split[1]);
            Journey r = new Journey(date, Integer.parseInt(split[2]), Integer.parseInt(split[3]));
            journeys.add(r);
        }
        return journeys;
    }
}

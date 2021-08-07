package com.vinodborole.tigercard;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TigerCardUtil {
    public static List<Journey> getJourney(InputStream inputStream) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        List<Journey> journeys = new ArrayList<Journey>();
        while ((line = br.readLine()) != null) {
            String[] splited = line.split(" ");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = formatter.parse(splited[0] + " " + splited[1]);
            Journey r = new Journey(date, Integer.parseInt(splited[2]), Integer.parseInt(splited[3]));
            journeys.add(r);
            System.out.println(r.getDate() + " " + r.getFromZone() + " " + r.getToZone() + " " + r.getFare());
        }
        return journeys;
    }
}

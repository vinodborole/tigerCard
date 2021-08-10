package com.vinodborole.tigercard.util;

import com.vinodborole.tigercard.entity.Journey;
import com.vinodborole.tigercard.exception.JourneyException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileUtil {
    /**
     * Returns list of Journey fetched from an input stream.
     *
     * This method reads the journey data from the input stream, it expects a fix format.
     * Single journey entry at every line, with journey data separated with a single space.
     * Journey data consist of Journey date and time in the format dd/MM/yyyy HH:mm:ss followed by Journey
     * from zone and to Zone
     *
     * @param  inputStream  InputStream object
     * @return              List of Journey
     * @throws Throws a JourneyException in case format of data is incorrect.
     */
    public static List<Journey> getJourney(InputStream inputStream) throws JourneyException {
        try {
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
        } catch (Exception e) {
           throw new JourneyException(e.getMessage());
        }
    }
}

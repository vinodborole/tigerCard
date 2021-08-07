package com.vinodborole.tigercard;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TigerCardApp {

    public static void main(String []args){
        InputStream inputStream =null;
        try {
            inputStream = TigerCardApp.class.getResourceAsStream("/input.txt");
            List<Journey> journeys = TigerCardUtil.getJourney(inputStream);
            System.out.println(FareUtil.getDailyCapForJourneys(journeys));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.vinodborole.tigercard;

import com.vinodborole.tigercard.entity.Journey;
import com.vinodborole.tigercard.util.TigerCardUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TigerCardApp {

    public static void main(String []args){
        InputStream inputStream =null;
        try {
            inputStream = TigerCardApp.class.getResourceAsStream("/input.txt");
            List<Journey> journeys = TigerCardUtil.getJourney(inputStream);
            int TotalApplicableFare = 0;
            for (Journey j : journeys){
                //TODO: groupby day and groupby week to get fare capping
                TotalApplicableFare+=j.getFare();
            }
            System.out.println("Total Applicable Fare : "+ TotalApplicableFare);
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

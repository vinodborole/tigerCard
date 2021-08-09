package com.vinodborole.tigercard;

import com.vinodborole.tigercard.entity.Journey;
import com.vinodborole.tigercard.util.FareUtil;
import com.vinodborole.tigercard.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TigerCardApp {

    public static void main(String []args){
        InputStream inputStream =null;
        try {
            inputStream = TigerCardApp.class.getResourceAsStream("/input.txt");
            List<Journey> journeys = FileUtil.getJourney(inputStream);
            System.out.println("Total Applicable Fair :"+ FareUtil.calculateTotalApplicableFare(journeys));
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

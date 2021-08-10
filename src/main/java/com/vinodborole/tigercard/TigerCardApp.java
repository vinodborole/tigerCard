package com.vinodborole.tigercard;

import com.vinodborole.tigercard.entity.Journey;
import com.vinodborole.tigercard.util.FareUtil;
import com.vinodborole.tigercard.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
/**
 * The transport officials from the city of Nepu want to design
 * a payment system for public metro transport.
 *
 * They have come up with the idea of a prepaid card - TigerCard - which is
 * an NFC enabled card that is to be tapped at entry and exit points of the metro stations.
 * To make the fares easier to understand for the commuters,the city has been divided into zones.
 * Zone 1 is the central area and Zone 2 forms a concentric ring around Zone 1.
 * Each metro station has been assigned to a zone.
 *
 * In the future, more zones will be added as the metro expands.
 * The problem statement is to design the fare calculation engine for TigerCard..
 *
 */
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

package com.example.model.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.model.NomLieu;

public class utility {
    


    public static NomLieu fromStringToLieu(String lieu){

        List<NomLieu> lesLieux = Arrays.asList(NomLieu.values());
        
        // Print all enum values in the list
        for (NomLieu place : lesLieux) {
            if (place.toString() == lieu){
                return place;
            }
        }

        return null;

    }
}

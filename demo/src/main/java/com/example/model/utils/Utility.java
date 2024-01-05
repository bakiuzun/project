package com.example.model.utils;


import java.util.Arrays;
import java.util.List;

import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;

public class Utility {
    


    public static NomLieu fromStringToNomLieu(String lieu){

        List<NomLieu> lesLieux = Arrays.asList(NomLieu.values());
        // Print all enum values in the list
        for (NomLieu place : lesLieux) {
            if (place.toString().equals(lieu)){return place;}
        }
        return null;

    }

    public static TypeTamagotchi fromStringToTamgotchiType(String tamagotchi){

        List<TypeTamagotchi> lesTama = Arrays.asList(TypeTamagotchi.values());
        // Print all enum values in the list
        for (TypeTamagotchi one_tama : lesTama) {
            if (one_tama.toString().equals(tamagotchi)){return one_tama;}
        }
        return null;

    }
}

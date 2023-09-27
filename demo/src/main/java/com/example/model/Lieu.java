package com.example.model;

import java.util.ArrayList;

public class Lieu {
    

    private ArrayList<Lieu> voisins;
    private NomLieu nomLieu;

    public Lieu(NomLieu nomLieu){
        this.nomLieu = nomLieu;
        
        /*
        switch (this.nomLieu){
            case MAISON:
                voisins.add(new Lieu(NomLieu.DEHORS));
            case DEHORS:
                voisins.add(new Lieu(NomLieu.MAISON));
                System.out.println("Je suis dans la maisons");
            case SALLE_DE_BAIN:
                System.out.println("Je suis dans la salle de bain");
            default:
                break;
        }
        */
    }


    public NomLieu getNomLieu(){
        return this.nomLieu;
    }

}

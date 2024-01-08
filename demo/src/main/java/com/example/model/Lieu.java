package com.example.model;

import java.util.ArrayList;
import java.util.List;



public class Lieu {
    private ArrayList<NomLieu> voisins;
    private NomLieu nomLieu;
    private String img_path;

    public Lieu(NomLieu nomLieu) {
        this.nomLieu = nomLieu;
        this.voisins = new ArrayList<>();

        switch (nomLieu) {
            case HOME:
                this.img_path = "images/MAISON.png";
                break;
            case BATHROOM:
                this.img_path = "images/BATHROOM.png";
                break;
            case GARDEN:
                this.img_path = "images/GARDEN.png";
                break;
            case KITCHEN:
                this.img_path = "images/KITCHEN.png";
                break;
            case TOILET:
                this.img_path = "images/TOILET.png";
                break;
            case BEDROOM:
                this.img_path = "images/BEDROOM.png";
                break;
            case GARAGE:
                this.img_path = "images/GARAGE.png";
                break;
            case ROAD:
                this.img_path = "images/ROAD.png";
                break;
            case GAS_STATION:
                this.img_path = "images/GAS.png";
                break;
            case WASHING_STATION:
                this.img_path = "images/CARWASH.png";
                break;
            default:
                break;
        }
      
    }

    public NomLieu getNomLieu() {
        return this.nomLieu;
    }


    public String getImgpath(){return img_path;}
    
    public ArrayList<NomLieu> getVoisins() {
        return voisins;
    }

    public void addVoisin(NomLieu lieu){this.voisins.add(lieu);}

    // Method to set predefined neighbors based on the current place
    public void setPredefinedNeighbors(List<NomLieu> neighbors) {
        voisins.addAll(neighbors);
    }
}

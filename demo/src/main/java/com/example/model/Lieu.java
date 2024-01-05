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
                this.voisins.add(NomLieu.GARDEN);
                this.voisins.add(NomLieu.BATHROOM);
                this.voisins.add(NomLieu.KITCHEN);
                this.voisins.add(NomLieu.BEDROOM);
                break;
            case BATHROOM:
                this.img_path = "images/BATHROOM.png";
                this.voisins.add(NomLieu.HOME);
                this.voisins.add(NomLieu.TOILET);
                break;
            case GARDEN:
                this.img_path = "images/GARDEN.png";
                this.voisins.add(NomLieu.HOME);
                this.voisins.add(NomLieu.GARAGE);
                this.voisins.add(NomLieu.ROAD);
                break;
            case KITCHEN:
                this.img_path = "images/KITCHEN.png";
                this.voisins.add(NomLieu.HOME);
                this.voisins.add(NomLieu.BEDROOM);
                break;
            case TOILET:
                this.img_path = "images/CAT.png";
                this.voisins.add(NomLieu.BATHROOM);
                break;
            case BEDROOM:
                this.img_path = "images/CAT.png";
                this.voisins.add(NomLieu.HOME);
                this.voisins.add(NomLieu.KITCHEN);
                break;
            case GARAGE:
                this.img_path = "images/GARDEN.png";
                this.voisins.add(NomLieu.ROAD);
                this.voisins.add(NomLieu.GARDEN);
                break;
            case ROAD:
                this.img_path = "images/GARDEN.png";
                this.voisins.add(NomLieu.GARAGE);
                this.voisins.add(NomLieu.GAS_STATION);
                this.voisins.add(NomLieu.WASHING_STATION);
                break;
            case GAS_STATION:
                this.img_path = "images/GARDEN.png";
                this.voisins.add(NomLieu.ROAD);
                this.voisins.add(NomLieu.WASHING_STATION);
                break;
            case WASHING_STATION:
                this.img_path = "images/GARDEN.png";
                this.voisins.add(NomLieu.ROAD);
                this.voisins.add(NomLieu.GAS_STATION);
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

    // Method to set predefined neighbors based on the current place
    public void setPredefinedNeighbors(List<NomLieu> neighbors) {
        voisins.addAll(neighbors);
    }
}

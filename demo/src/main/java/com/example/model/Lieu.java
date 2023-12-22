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
                break;

            case GARDEN:
                this.img_path = "images/CAT.png";
                this.voisins.add(NomLieu.HOME);
            default:
                break;
        }
      
    }

    public NomLieu getNomLieu() {
        return this.nomLieu;
    }

    public void addVoisin(NomLieu voisin) {
        voisins.add(voisin);
    }


    public String getImgpath(){return img_path;}
    
    public ArrayList<NomLieu> getVoisins() {
        return voisins;
    }

    // Method to move to a neighboring place
    public void moveTo(NomLieu destination) {
        if (voisins.contains(destination)) {
            // Implement any logic related to moving here
        } else {
        }
    }

    // Method to set predefined neighbors based on the current place
    public void setPredefinedNeighbors(List<NomLieu> neighbors) {
        voisins.addAll(neighbors);
    }
}

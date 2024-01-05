package com.example.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.example.model.Lieu;
import com.example.model.NomLieu;


public class LieuTest {
    @Test
    public void creationTest(){

        Lieu home = new Lieu(NomLieu.HOME);
        ArrayList<NomLieu> homeVoisin = new ArrayList<NomLieu>();
        homeVoisin.add(NomLieu.GARDEN);
        homeVoisin.add(NomLieu.KITCHEN);
        homeVoisin.add(NomLieu.BATHROOM);
        homeVoisin.add(NomLieu.GARDEN);
        Assert.assertTrue(home.getVoisins().containsAll(homeVoisin));

        Lieu garden = new Lieu(NomLieu.GARDEN);
        ArrayList<NomLieu> gardenVoisin = new ArrayList<NomLieu>();
        gardenVoisin.add(NomLieu.HOME);
        Assert.assertTrue(garden.getVoisins().containsAll(gardenVoisin));

        Lieu kitchen = new Lieu(NomLieu.KITCHEN);
        ArrayList<NomLieu> kitchenVoisin = new ArrayList<NomLieu>();
        kitchenVoisin.add(NomLieu.HOME);
        kitchenVoisin.add(NomLieu.BEDROOM);
        Assert.assertTrue(kitchen.getVoisins().containsAll(kitchenVoisin));

        Lieu bathroom = new Lieu(NomLieu.BATHROOM);
        ArrayList<NomLieu> bathroomVoisin = new ArrayList<NomLieu>();
        bathroomVoisin.add(NomLieu.HOME);
        bathroomVoisin.add(NomLieu.TOILET);
        Assert.assertTrue(bathroom.getVoisins().containsAll(bathroomVoisin));

        Lieu bedroom = new Lieu(NomLieu.BEDROOM);
        ArrayList<NomLieu> bedroomVoisin = new ArrayList<NomLieu>();
        bedroomVoisin.add(NomLieu.HOME);
        bedroomVoisin.add(NomLieu.KITCHEN);
        Assert.assertTrue(bedroom.getVoisins().containsAll(bedroomVoisin));

        Lieu toilet = new Lieu(NomLieu.TOILET);
        ArrayList<NomLieu> toiletVoisin = new ArrayList<NomLieu>();
        toiletVoisin.add(NomLieu.BATHROOM);
        Assert.assertTrue(toilet.getVoisins().containsAll(toiletVoisin));
    }
}

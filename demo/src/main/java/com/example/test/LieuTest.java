package com.example.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.example.model.Lieu;
import com.example.model.NomLieu;


public class LieuTest {
    @Test
    public void homeTest(){
        Lieu home = new Lieu(NomLieu.HOME);
        ArrayList<NomLieu> homeVoisin = new ArrayList<NomLieu>();
        homeVoisin.add(NomLieu.GARDEN);
        homeVoisin.add(NomLieu.KITCHEN);
        homeVoisin.add(NomLieu.BATHROOM);
        homeVoisin.add(NomLieu.GARDEN);
        Assert.assertTrue(home.getVoisins().containsAll(homeVoisin));
    }

    @Test
    public void gardenTest(){
        Lieu garden = new Lieu(NomLieu.GARDEN);
        ArrayList<NomLieu> gardenVoisin = new ArrayList<NomLieu>();
        gardenVoisin.add(NomLieu.HOME);
        Assert.assertTrue(garden.getVoisins().containsAll(gardenVoisin));
    }

    @Test
    public void kitchenTest(){
        Lieu kitchen = new Lieu(NomLieu.KITCHEN);
        ArrayList<NomLieu> kitchenVoisin = new ArrayList<NomLieu>();
        kitchenVoisin.add(NomLieu.HOME);
        kitchenVoisin.add(NomLieu.BEDROOM);
        Assert.assertTrue(kitchen.getVoisins().containsAll(kitchenVoisin));
    }

    @Test
    public void bathroomTest(){
        Lieu bathroom = new Lieu(NomLieu.BATHROOM);
        ArrayList<NomLieu> bathroomVoisin = new ArrayList<NomLieu>();
        bathroomVoisin.add(NomLieu.HOME);
        bathroomVoisin.add(NomLieu.TOILET);
        Assert.assertTrue(bathroom.getVoisins().containsAll(bathroomVoisin));
    }
    
    @Test
    public void bedroomTest(){
        Lieu bedroom = new Lieu(NomLieu.BEDROOM);
        ArrayList<NomLieu> bedroomVoisin = new ArrayList<NomLieu>();
        bedroomVoisin.add(NomLieu.HOME);
        bedroomVoisin.add(NomLieu.KITCHEN);
        Assert.assertTrue(bedroom.getVoisins().containsAll(bedroomVoisin));
    }

    @Test
    public void toiletTest(){
        Lieu toilet = new Lieu(NomLieu.TOILET);
        ArrayList<NomLieu> toiletVoisin = new ArrayList<NomLieu>();
        toiletVoisin.add(NomLieu.BATHROOM);
        Assert.assertTrue(toilet.getVoisins().containsAll(toiletVoisin));
    }

    @Test
    public void garageTest(){
        Lieu garage = new Lieu(NomLieu.GARAGE);
        ArrayList<NomLieu> garageVoisin = new ArrayList<NomLieu>();
        garageVoisin.add(NomLieu.ROAD);
        garageVoisin.add(NomLieu.GARDEN);
        Assert.assertTrue(garage.getVoisins().containsAll(garageVoisin));
    }

    @Test
    public void roadTest(){
        Lieu road = new Lieu(NomLieu.ROAD);
        ArrayList<NomLieu> roadVoisin = new ArrayList<NomLieu>();
        roadVoisin.add(NomLieu.GARDEN);
        roadVoisin.add(NomLieu.GARAGE);
        roadVoisin.add(NomLieu.GAS_STATION);
        roadVoisin.add(NomLieu.WASHING_STATION);
        Assert.assertTrue(road.getVoisins().containsAll(roadVoisin));
    }

    @Test
    public void washingStationTest(){
        Lieu washingStation = new Lieu(NomLieu.WASHING_STATION);
        ArrayList<NomLieu> washingStationVoisin = new ArrayList<NomLieu>();
        washingStationVoisin.add(NomLieu.GARDEN);
        washingStationVoisin.add(NomLieu.GARAGE);
        washingStationVoisin.add(NomLieu.GAS_STATION);
        Assert.assertTrue(washingStation.getVoisins().containsAll(washingStationVoisin));
    }

    @Test
    public void gasStationTest(){
        Lieu gasStation = new Lieu(NomLieu.GAS_STATION);
        ArrayList<NomLieu> gasStationVoisin = new ArrayList<NomLieu>();
        gasStationVoisin.add(NomLieu.GARDEN);
        gasStationVoisin.add(NomLieu.GARAGE);
        gasStationVoisin.add(NomLieu.WASHING_STATION);
        Assert.assertTrue(gasStation.getVoisins().containsAll(gasStationVoisin));
    }

}

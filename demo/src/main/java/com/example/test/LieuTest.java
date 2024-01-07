package com.example.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.example.model.Lieu;
import com.example.model.NomLieu;
import com.example.model.tama.NonVivant;
import com.example.model.tama.Vivant;
import com.example.model.tama.tamaNonVivant.Robot;
import com.example.model.tama.tamaVivant.Cat;


public class LieuTest {
    @Test
    public void homeTest(){
        Vivant tamagotchi = new Cat();
        tamagotchi.initNewTamagotchi();
        tamagotchi.setCurrentPlace(new Lieu(NomLieu.HOME));
        tamagotchi.addNeighbor();
        Lieu home = tamagotchi.getCurrentPlace();
        ArrayList<NomLieu> homeVoisin = new ArrayList<NomLieu>();
        homeVoisin.add(NomLieu.GARDEN);
        homeVoisin.add(NomLieu.KITCHEN);
        homeVoisin.add(NomLieu.BATHROOM);
        Assert.assertTrue(home.getVoisins().containsAll(homeVoisin));
    }

    @Test
    public void gardenTest(){
        Vivant tamagotchi = new Cat();
        tamagotchi.initNewTamagotchi();
        tamagotchi.setCurrentPlace(new Lieu(NomLieu.GARDEN));
        tamagotchi.addNeighbor();
        Lieu garden = tamagotchi.getCurrentPlace();
        ArrayList<NomLieu> gardenVoisin = new ArrayList<NomLieu>();
        gardenVoisin.add(NomLieu.HOME);
        Assert.assertTrue(garden.getVoisins().containsAll(gardenVoisin));
    }

    @Test
    public void kitchenTest(){
        Vivant tamagotchi = new Cat();
        tamagotchi.initNewTamagotchi();
        tamagotchi.setCurrentPlace(new Lieu(NomLieu.KITCHEN));
        tamagotchi.addNeighbor();
        Lieu kitchen = tamagotchi.getCurrentPlace();
        ArrayList<NomLieu> kitchenVoisin = new ArrayList<NomLieu>();
        kitchenVoisin.add(NomLieu.HOME);
        kitchenVoisin.add(NomLieu.BEDROOM);
        Assert.assertTrue(kitchen.getVoisins().containsAll(kitchenVoisin));
    }

    @Test
    public void bathroomTest(){
        Vivant tamagotchi = new Cat();
        tamagotchi.initNewTamagotchi();
        tamagotchi.setCurrentPlace(new Lieu(NomLieu.BATHROOM));
        tamagotchi.addNeighbor();
        Lieu bathroom = tamagotchi.getCurrentPlace();
        ArrayList<NomLieu> bathroomVoisin = new ArrayList<NomLieu>();
        bathroomVoisin.add(NomLieu.HOME);
        bathroomVoisin.add(NomLieu.TOILET);
        Assert.assertTrue(bathroom.getVoisins().containsAll(bathroomVoisin));
    }
    
    @Test
    public void bedroomTest(){
        Vivant tamagotchi = new Cat();
        tamagotchi.initNewTamagotchi();
        tamagotchi.setCurrentPlace(new Lieu(NomLieu.BEDROOM));
        tamagotchi.addNeighbor();
        Lieu bedroom = tamagotchi.getCurrentPlace();
        ArrayList<NomLieu> bedroomVoisin = new ArrayList<NomLieu>();
        bedroomVoisin.add(NomLieu.HOME);
        bedroomVoisin.add(NomLieu.KITCHEN);
        Assert.assertTrue(bedroom.getVoisins().containsAll(bedroomVoisin));
    }

    @Test
    public void toiletTest(){
        Vivant tamagotchi = new Cat();
        tamagotchi.initNewTamagotchi();
        tamagotchi.setCurrentPlace(new Lieu(NomLieu.TOILET));
        tamagotchi.addNeighbor();
        Lieu toilet = tamagotchi.getCurrentPlace();
        ArrayList<NomLieu> toiletVoisin = new ArrayList<NomLieu>();
        toiletVoisin.add(NomLieu.BATHROOM);
        Assert.assertTrue(toilet.getVoisins().containsAll(toiletVoisin));
    }

    @Test
    public void garageTest(){
        NonVivant tamagotchi = new Robot();
        tamagotchi.initNewTamagotchi();
        tamagotchi.setCurrentPlace(new Lieu(NomLieu.GARAGE));
        tamagotchi.addNeighbor();
        Lieu garage = tamagotchi.getCurrentPlace();
        ArrayList<NomLieu> garageVoisin = new ArrayList<NomLieu>();
        garageVoisin.add(NomLieu.ROAD);
        Assert.assertTrue(garage.getVoisins().containsAll(garageVoisin));
    }

    @Test
    public void roadTest(){
        NonVivant tamagotchi = new Robot();
        tamagotchi.initNewTamagotchi();
        tamagotchi.setCurrentPlace(new Lieu(NomLieu.ROAD));
        tamagotchi.addNeighbor();
        Lieu road = tamagotchi.getCurrentPlace();
        ArrayList<NomLieu> roadVoisin = new ArrayList<NomLieu>();
        roadVoisin.add(NomLieu.GARAGE);
        roadVoisin.add(NomLieu.GAS_STATION);
        roadVoisin.add(NomLieu.WASHING_STATION);
        Assert.assertTrue(road.getVoisins().containsAll(roadVoisin));
    }

    @Test
    public void washingStationTest(){
        NonVivant tamagotchi = new Robot();
        tamagotchi.initNewTamagotchi();
        tamagotchi.setCurrentPlace(new Lieu(NomLieu.WASHING_STATION));
        tamagotchi.addNeighbor();
        Lieu washingStation = tamagotchi.getCurrentPlace();
        ArrayList<NomLieu> washingStationVoisin = new ArrayList<NomLieu>();
        washingStationVoisin.add(NomLieu.ROAD);
        washingStationVoisin.add(NomLieu.GAS_STATION);
        Assert.assertTrue(washingStation.getVoisins().containsAll(washingStationVoisin));
    }

    @Test
    public void gasStationTest(){
        NonVivant tamagotchi = new Robot();
        tamagotchi.initNewTamagotchi();
        tamagotchi.setCurrentPlace(new Lieu(NomLieu.GAS_STATION));
        tamagotchi.addNeighbor();
        Lieu gasStation = tamagotchi.getCurrentPlace();
        ArrayList<NomLieu> gasStationVoisin = new ArrayList<NomLieu>();
        gasStationVoisin.add(NomLieu.ROAD);
        gasStationVoisin.add(NomLieu.WASHING_STATION);
        Assert.assertTrue(gasStation.getVoisins().containsAll(gasStationVoisin));
    }

}

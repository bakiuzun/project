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
        tamagotchi.init_new_tamagothi();
        tamagotchi.setLieuActuel(new Lieu(NomLieu.HOME));
        tamagotchi.addNeighbord();
        Lieu home = tamagotchi.getLieuActuel();
        ArrayList<NomLieu> homeVoisin = new ArrayList<NomLieu>();
        homeVoisin.add(NomLieu.GARDEN);
        homeVoisin.add(NomLieu.KITCHEN);
        homeVoisin.add(NomLieu.BATHROOM);
        Assert.assertTrue(home.getVoisins().containsAll(homeVoisin));
    }

    @Test
    public void gardenTest(){
        Vivant tamagotchi = new Cat();
        tamagotchi.init_new_tamagothi();
        tamagotchi.setLieuActuel(new Lieu(NomLieu.GARDEN));
        tamagotchi.addNeighbord();
        Lieu garden = tamagotchi.getLieuActuel();
        ArrayList<NomLieu> gardenVoisin = new ArrayList<NomLieu>();
        gardenVoisin.add(NomLieu.HOME);
        Assert.assertTrue(garden.getVoisins().containsAll(gardenVoisin));
    }

    @Test
    public void kitchenTest(){
        Vivant tamagotchi = new Cat();
        tamagotchi.init_new_tamagothi();
        tamagotchi.setLieuActuel(new Lieu(NomLieu.KITCHEN));
        tamagotchi.addNeighbord();
        Lieu kitchen = tamagotchi.getLieuActuel();
        ArrayList<NomLieu> kitchenVoisin = new ArrayList<NomLieu>();
        kitchenVoisin.add(NomLieu.HOME);
        kitchenVoisin.add(NomLieu.BEDROOM);
        Assert.assertTrue(kitchen.getVoisins().containsAll(kitchenVoisin));
    }

    @Test
    public void bathroomTest(){
        Vivant tamagotchi = new Cat();
        tamagotchi.init_new_tamagothi();
        tamagotchi.setLieuActuel(new Lieu(NomLieu.BATHROOM));
        tamagotchi.addNeighbord();
        Lieu bathroom = tamagotchi.getLieuActuel();
        ArrayList<NomLieu> bathroomVoisin = new ArrayList<NomLieu>();
        bathroomVoisin.add(NomLieu.HOME);
        bathroomVoisin.add(NomLieu.TOILET);
        Assert.assertTrue(bathroom.getVoisins().containsAll(bathroomVoisin));
    }
    
    @Test
    public void bedroomTest(){
        Vivant tamagotchi = new Cat();
        tamagotchi.init_new_tamagothi();
        tamagotchi.setLieuActuel(new Lieu(NomLieu.BEDROOM));
        tamagotchi.addNeighbord();
        Lieu bedroom = tamagotchi.getLieuActuel();
        ArrayList<NomLieu> bedroomVoisin = new ArrayList<NomLieu>();
        bedroomVoisin.add(NomLieu.HOME);
        bedroomVoisin.add(NomLieu.KITCHEN);
        Assert.assertTrue(bedroom.getVoisins().containsAll(bedroomVoisin));
    }

    @Test
    public void toiletTest(){
        Vivant tamagotchi = new Cat();
        tamagotchi.init_new_tamagothi();
        tamagotchi.setLieuActuel(new Lieu(NomLieu.TOILET));
        tamagotchi.addNeighbord();
        Lieu toilet = tamagotchi.getLieuActuel();
        ArrayList<NomLieu> toiletVoisin = new ArrayList<NomLieu>();
        toiletVoisin.add(NomLieu.BATHROOM);
        Assert.assertTrue(toilet.getVoisins().containsAll(toiletVoisin));
    }

    @Test
    public void garageTest(){
        NonVivant tamagotchi = new Robot();
        tamagotchi.init_new_tamagothi();
        tamagotchi.setLieuActuel(new Lieu(NomLieu.GARAGE));
        tamagotchi.addNeighbord();
        Lieu garage = tamagotchi.getLieuActuel();
        ArrayList<NomLieu> garageVoisin = new ArrayList<NomLieu>();
        garageVoisin.add(NomLieu.ROAD);
        Assert.assertTrue(garage.getVoisins().containsAll(garageVoisin));
    }

    @Test
    public void roadTest(){
        NonVivant tamagotchi = new Robot();
        tamagotchi.init_new_tamagothi();
        tamagotchi.setLieuActuel(new Lieu(NomLieu.ROAD));
        tamagotchi.addNeighbord();
        Lieu road = tamagotchi.getLieuActuel();
        ArrayList<NomLieu> roadVoisin = new ArrayList<NomLieu>();
        roadVoisin.add(NomLieu.GARAGE);
        roadVoisin.add(NomLieu.GAS_STATION);
        roadVoisin.add(NomLieu.WASHING_STATION);
        Assert.assertTrue(road.getVoisins().containsAll(roadVoisin));
    }

    @Test
    public void washingStationTest(){
        NonVivant tamagotchi = new Robot();
        tamagotchi.init_new_tamagothi();
        tamagotchi.setLieuActuel(new Lieu(NomLieu.WASHING_STATION));
        tamagotchi.addNeighbord();
        Lieu washingStation = tamagotchi.getLieuActuel();
        ArrayList<NomLieu> washingStationVoisin = new ArrayList<NomLieu>();
        washingStationVoisin.add(NomLieu.ROAD);
        washingStationVoisin.add(NomLieu.GAS_STATION);
        Assert.assertTrue(washingStation.getVoisins().containsAll(washingStationVoisin));
    }

    @Test
    public void gasStationTest(){
        NonVivant tamagotchi = new Robot();
        tamagotchi.init_new_tamagothi();
        tamagotchi.setLieuActuel(new Lieu(NomLieu.GAS_STATION));
        tamagotchi.addNeighbord();
        Lieu gasStation = tamagotchi.getLieuActuel();
        ArrayList<NomLieu> gasStationVoisin = new ArrayList<NomLieu>();
        gasStationVoisin.add(NomLieu.ROAD);
        gasStationVoisin.add(NomLieu.WASHING_STATION);
        Assert.assertTrue(gasStation.getVoisins().containsAll(gasStationVoisin));
    }

}

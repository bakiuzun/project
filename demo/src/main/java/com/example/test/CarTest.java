package com.example.test;




import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;
import com.example.model.tama.tamaNonVivant.Voiture;
import com.example.model.utils.ActionConstant;


public class CarTest {

    @Before
    public void runBeforeEachTest(){
        System.out.println("yo");
    }

    @Test
    public void creationTest(){
        Voiture car = new Voiture();
        car.init_new_tamagothi();
        Assert.assertEquals(car.getTypeTamagotchi(), TypeTamagotchi.VOITURE);
        Assert.assertEquals(car.getLieuActuel().getNomLieu(), NomLieu.HOME);
        Assert.assertEquals(car.getLife(), ActionConstant.LIFE_MAX);
        Assert.assertEquals((int)car.getOil(), ActionConstant.OIL_MAX);
        Assert.assertEquals((int)car.getBattery(), ActionConstant.BATTERY_MAX);
        Assert.assertEquals((int)car.getTemperature(), ActionConstant.TEMPERATURE_MAX);
        Assert.assertEquals((int)car.getRust(), ActionConstant.RUST_MAX);
    }
}

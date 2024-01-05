package com.example.test;




import org.junit.Assert;
import org.junit.Test;

import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;
import com.example.model.tama.tamaNonVivant.Voiture;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;


public class CarTest {

    @Test
    public void creationTest(){
        Voiture car = new Voiture();
        car.init_new_tamagothi();
        Assert.assertEquals(car.getTypeTamagotchi(), TypeTamagotchi.VOITURE);
        Assert.assertEquals(car.getLieuActuel().getNomLieu(), NomLieu.GARAGE);
        Assert.assertEquals(car.getLife(), ActionConstant.LIFE_MAX);
        Assert.assertEquals((int)car.getOil(), ActionConstant.OIL_MAX);
        Assert.assertEquals((int)car.getBattery(), ActionConstant.BATTERY_MAX);
        Assert.assertEquals((int)car.getTemperature(), ActionConstant.TEMPERATURE_MAX);
        Assert.assertEquals((int)car.getRust(), ActionConstant.RUST_MAX);
    }

    @Test
    public void batteringTest(){
        Voiture car = new Voiture();
        car.init_new_tamagothi();
        car.getActions().put(AttributeConstant.ACTION_BATTERING_CAR, car::battering);

        car.doAction(AttributeConstant.ACTION_BATTERING_CAR);
        Assert.assertEquals((int)car.getBattery(), ActionConstant.BATTERY_MAX);
        Assert.assertEquals((int)car.getTemperature(), ActionConstant.TEMPERATURE_MAX+ActionConstant.BATTERING_TEMPERATURE);

        for(int i=0;i<21;i++){car.updateState();}
        car.doAction(AttributeConstant.ACTION_BATTERING_CAR);
        Assert.assertEquals((int)car.getBattery(), ActionConstant.BATTERY_MAX-ActionConstant.DELTA_BATTERY_CAR*21+ActionConstant.BATTERING);
        Assert.assertEquals((int)car.getTemperature(), ActionConstant.TEMPERATURE_MAX-ActionConstant.DELTA_TEMPERATURE_CAR*21+ActionConstant.BATTERING_TEMPERATURE*2);
    }

    @Test
    public void oilingTest(){
        Voiture car = new Voiture();
        car.init_new_tamagothi();
        car.getActions().put(AttributeConstant.ACTION_OILING_CAR, car::oiling);

        car.doAction(AttributeConstant.ACTION_OILING_CAR);
        Assert.assertEquals((int)car.getOil(), ActionConstant.OIL_MAX);
        Assert.assertEquals((int)car.getRust(), ActionConstant.RUST_MAX+ActionConstant.OILING_RUST);
        
        for(int i=0;i<13;i++){car.updateState();}
        car.doAction(AttributeConstant.ACTION_OILING_CAR);
        Assert.assertEquals((int)car.getOil(), ActionConstant.OIL_MAX-ActionConstant.DELTA_OIL_CAR*13+ActionConstant.OILING);
        Assert.assertEquals((int)car.getRust(), ActionConstant.RUST_MAX-ActionConstant.DELTA_RUST_CAR*13+ActionConstant.OILING_RUST*2);
    }

    @Test
    public void coolingTest(){
        Voiture car = new Voiture();
        car.init_new_tamagothi();
        car.getActions().put(AttributeConstant.ACTION_COOLING_CAR, car::cooling);

        car.doAction(AttributeConstant.ACTION_COOLING_CAR);
        Assert.assertEquals((int)car.getTemperature(), ActionConstant.TEMPERATURE_MAX);
    
        for(int i=0;i<16;i++){car.updateState();}
        car.doAction(AttributeConstant.ACTION_COOLING_CAR);
        Assert.assertEquals((int)car.getTemperature(), ActionConstant.TEMPERATURE_MAX-ActionConstant.DELTA_TEMPERATURE_CAR*16+ActionConstant.COOLING);
    }

    @Test
    public void cleaningTest(){
        Voiture car = new Voiture();
        car.init_new_tamagothi();
        car.getActions().put(AttributeConstant.ACTION_CLEANING_CAR, car::cleaning);

        car.doAction(AttributeConstant.ACTION_CLEANING_CAR);
        Assert.assertEquals((int)car.getRust(), ActionConstant.RUST_MAX);

        for(int i=0;i<8;i++){car.updateState();}
        car.doAction(AttributeConstant.ACTION_CLEANING_CAR);
        Assert.assertEquals((int)car.getRust(), ActionConstant.RUST_MAX-ActionConstant.DELTA_RUST_CAR*8+ActionConstant.CLEANING);
    }


}

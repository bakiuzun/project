package com.example.test;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;
import com.example.model.tama.tamaNonVivant.Robot;
import com.example.model.tama.tamaNonVivant.Robot;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;


public class RobotTest {

    @Test
    public void creationTest(){
        Robot robot = new Robot();
        robot.init_new_tamagothi();
        Assert.assertEquals(robot.getTypeTamagotchi(), TypeTamagotchi.ROBOT);
        Assert.assertEquals(robot.getLieuActuel().getNomLieu(), NomLieu.HOME);
        Assert.assertEquals(robot.getLife(), ActionConstant.LIFE_MAX);
        Assert.assertEquals((int)robot.getOil(), ActionConstant.OIL_MAX);
        Assert.assertEquals((int)robot.getBattery(), ActionConstant.BATTERY_MAX);
        Assert.assertEquals((int)robot.getTemperature(), ActionConstant.TEMPERATURE_MAX);
        Assert.assertEquals((int)robot.getRust(), ActionConstant.RUST_MAX);
    }

    @Test
    public void batteringTest(){
        Robot robot = new Robot();
        robot.init_new_tamagothi();
        robot.getActions().put(AttributeConstant.ACTION_BATTERING_ROBOT, robot::battering);

        robot.doAction(AttributeConstant.ACTION_BATTERING_ROBOT);
        Assert.assertEquals((int)robot.getBattery(), ActionConstant.BATTERY_MAX);
        Assert.assertEquals((int)robot.getTemperature(), ActionConstant.TEMPERATURE_MAX+ActionConstant.BATTERING_TEMPERATURE);

        for(int i=0;i<21;i++){robot.updateState();}
        robot.doAction(AttributeConstant.ACTION_BATTERING_ROBOT);
        Assert.assertEquals((int)robot.getBattery(), ActionConstant.BATTERY_MAX-ActionConstant.DELTA_BATTERY_ROBOT*21+ActionConstant.BATTERING);
        Assert.assertEquals((int)robot.getTemperature(), ActionConstant.TEMPERATURE_MAX-ActionConstant.DELTA_TEMPERATURE_ROBOT*21+ActionConstant.BATTERING_TEMPERATURE*2);
    }

    @Test
    public void oilingTest(){
        Robot robot = new Robot();
        robot.init_new_tamagothi();
        robot.getActions().put(AttributeConstant.ACTION_OILING_ROBOT, robot::oiling);

        robot.doAction(AttributeConstant.ACTION_OILING_ROBOT);
        Assert.assertEquals((int)robot.getOil(), ActionConstant.OIL_MAX);
        Assert.assertEquals((int)robot.getRust(), ActionConstant.RUST_MAX+ActionConstant.OILING_RUST);

        for(int i=0;i<13;i++){robot.updateState();}
        robot.doAction(AttributeConstant.ACTION_OILING_ROBOT);
        Assert.assertEquals((int)robot.getOil(), ActionConstant.OIL_MAX-ActionConstant.DELTA_OIL_ROBOT*13+ActionConstant.OILING);
        Assert.assertEquals((int)robot.getRust(), ActionConstant.RUST_MAX-ActionConstant.DELTA_RUST_ROBOT*13+ActionConstant.OILING_RUST*2);
    }

    @Test
    public void coolingTest(){
        Robot robot = new Robot();
        robot.init_new_tamagothi();
        robot.getActions().put(AttributeConstant.ACTION_COOLING_ROBOT, robot::cooling);

        robot.doAction(AttributeConstant.ACTION_COOLING_ROBOT);
        Assert.assertEquals((int)robot.getTemperature(), ActionConstant.TEMPERATURE_MAX);

        for(int i=0;i<16;i++){robot.updateState();}
        robot.doAction(AttributeConstant.ACTION_COOLING_ROBOT);
        Assert.assertEquals((int)robot.getTemperature(), ActionConstant.TEMPERATURE_MAX-ActionConstant.DELTA_TEMPERATURE_ROBOT*16+ActionConstant.COOLING);
    }

    @Test
    public void cleaningTest(){
        Robot robot = new Robot();
        robot.init_new_tamagothi();
        robot.getActions().put(AttributeConstant.ACTION_CLEANING_ROBOT, robot::cleaning);

        robot.doAction(AttributeConstant.ACTION_CLEANING_ROBOT);
        Assert.assertEquals((int)robot.getRust(), ActionConstant.RUST_MAX);

        for(int i=0;i<8;i++){robot.updateState();}
        robot.doAction(AttributeConstant.ACTION_CLEANING_ROBOT);
        Assert.assertEquals((int)robot.getRust(), ActionConstant.RUST_MAX-ActionConstant.DELTA_RUST_ROBOT*8+ActionConstant.CLEANING);
    }
}

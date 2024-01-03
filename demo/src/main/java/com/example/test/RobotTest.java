package com.example.test;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;
import com.example.model.tama.tamaNonVivant.Robot;
import com.example.model.utils.ActionConstant;


public class RobotTest {

    @Before
    public void runBeforeEachTest(){
        System.out.println("yo");
    }

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
}

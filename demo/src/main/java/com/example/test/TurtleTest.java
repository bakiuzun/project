package com.example.test;




import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;

import com.example.model.tama.tamaVivant.Turtle;
import com.example.model.utils.ActionConstant;


public class TurtleTest {

    @Before
    public void runBeforeEachTest(){
        System.out.println("yo");
    }

    @Test
    public void creationTest(){
        Turtle turtle = new Turtle();
        turtle.init_new_tamagothi();
        Assert.assertEquals(turtle.getTypeTamagotchi(), TypeTamagotchi.TURTLE);
        Assert.assertEquals(turtle.getLieuActuel().getNomLieu(), NomLieu.HOME);
        Assert.assertEquals(turtle.getLife(), ActionConstant.LIFE_MAX);
        Assert.assertEquals((int)turtle.getMood(), ActionConstant.MOOD_MAX);
        Assert.assertEquals((int)turtle.getHunger(), ActionConstant.HUNGER_MAX);
        Assert.assertEquals((int)turtle.getHygiene(), ActionConstant.HYGIENE_MAX);
        Assert.assertEquals((int)turtle.getWeight(), ActionConstant.TURTLE_WEIGHT);
    }
}

package com.example.test;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;

import com.example.model.tama.tamaVivant.Rabbit;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;


public class RabbitTest {

    @Before
    public void runBeforeEachTest(){
        System.out.println("yo");
    }

    @Test
    public void creationTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.init_new_tamagothi();
        Assert.assertEquals(rabbit.getTypeTamagotchi(), TypeTamagotchi.RABBIT);
        Assert.assertEquals(rabbit.getLieuActuel().getNomLieu(), NomLieu.HOME);
        Assert.assertEquals(rabbit.getLife(), ActionConstant.LIFE_MAX);
        Assert.assertEquals((int)rabbit.getMood(), ActionConstant.MOOD_MAX);
        Assert.assertEquals((int)rabbit.getHunger(), ActionConstant.HUNGER_MAX);
        Assert.assertEquals((int)rabbit.getHygiene(), ActionConstant.HYGIENE_MAX);
        Assert.assertEquals((int)rabbit.getWeight(), ActionConstant.RABBIT_WEIGHT);
    }

    @Test
    public void eatTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.init_new_tamagothi();
        rabbit.getActions().put(AttributeConstant.ACTION_EATING_RABBIT, rabbit::eating);
        rabbit.doAction(AttributeConstant.ACTION_EATING_RABBIT);
        Assert.assertEquals((int)rabbit.getHunger(), ActionConstant.HUNGER_MAX);
        Assert.assertEquals((int)rabbit.getWeight(), ActionConstant.RABBIT_WEIGHT + ActionConstant.KILOMAX);
    }
}

package com.example.test;




import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;

import com.example.model.tama.tamaVivant.Cat;

import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;


public class CatTest {

    @Before
    public void runBeforeEachTest(){
        System.out.println("yo");
    }

    @Test
    public void creationTest(){
        Cat cat = new Cat();
        cat.init_new_tamagothi();
        Assert.assertEquals(cat.getTypeTamagotchi(), TypeTamagotchi.CAT);
        Assert.assertEquals(cat.getLieuActuel().getNomLieu(), NomLieu.HOME);
        Assert.assertEquals(cat.getLife(), ActionConstant.LIFE_MAX);
        Assert.assertEquals((int)cat.getMood(), ActionConstant.MOOD_MAX);
        Assert.assertEquals((int)cat.getHunger(), ActionConstant.HUNGER_MAX);
        Assert.assertEquals((int)cat.getHygiene(), ActionConstant.HYGIENE_MAX);
        Assert.assertEquals((int)cat.getWeight(), ActionConstant.CAT_WEIGHT);
    }

    @Test
    public void eatTest(){
        Cat cat = new Cat();
        cat.init_new_tamagothi();
        cat.getActions().put(AttributeConstant.ACTION_EATING_CAT, cat::eating);
        cat.doAction(AttributeConstant.ACTION_EATING_CAT);
        Assert.assertEquals((int)cat.getHunger(), ActionConstant.HUNGER_MAX);
        Assert.assertEquals((int)cat.getWeight(), ActionConstant.CAT_WEIGHT + ActionConstant.KILOMAX);
    }
}

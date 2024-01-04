package com.example.test;




import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;

import com.example.model.tama.tamaVivant.Dog;

import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;


public class DogTest {

    @Before
    public void runBeforeEachTest(){
        System.out.println("yo");
    }

    @Test
    public void creationTest(){
        Dog dog = new Dog();
        dog.init_new_tamagothi();
        Assert.assertEquals(dog.getTypeTamagotchi(), TypeTamagotchi.DOG);
        Assert.assertEquals(dog.getLieuActuel().getNomLieu(), NomLieu.HOME);
        Assert.assertEquals(dog.getLife(), ActionConstant.LIFE_MAX);
        Assert.assertEquals((int)dog.getMood(), ActionConstant.MOOD_MAX);
        Assert.assertEquals((int)dog.getHunger(), ActionConstant.HUNGER_MAX);
        Assert.assertEquals((int)dog.getHygiene(), ActionConstant.HYGIENE_MAX);
        Assert.assertEquals((int)dog.getWeight(), ActionConstant.DOG_WEIGHT);
    }

    @Test
    public void eatTest(){
        Dog dog = new Dog();
        dog.init_new_tamagothi();
        dog.getActions().put(AttributeConstant.ACTION_EATING_DOG, dog::eating);
        dog.doAction(AttributeConstant.ACTION_EATING_DOG);
        Assert.assertEquals((int)dog.getHunger(), ActionConstant.HUNGER_MAX);
        Assert.assertEquals((int)dog.getWeight(), ActionConstant.DOG_WEIGHT + ActionConstant.KILOMAX);
    }
}

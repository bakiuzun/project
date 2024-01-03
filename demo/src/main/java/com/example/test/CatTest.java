package com.example.test;


import java.beans.BeanProperty;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.model.TypeTamagotchi;

import com.example.model.tama.tamaVivant.Cat;


public class CatTest {

    @Before
    public void runBeforeEachTest(){
        System.out.println("yo");
    }
    @Test
    public void creationTest(){
        Cat cat = new Cat();
        cat.init_new_tamagothi();
        Assert.assertEquals(cat.getTypeTamagotchi(), TypeTamagotchi.DOG);
    }
}

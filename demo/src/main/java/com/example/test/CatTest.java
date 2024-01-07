package com.example.test;




import org.junit.Assert;
import org.junit.Test;

import com.example.model.Lieu;
import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;

import com.example.model.tama.tamaVivant.Cat;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;


public class CatTest {

    @Test
    public void creationTest(){
        Cat cat = new Cat();
        cat.initNewTamagotchi();
        Assert.assertEquals(cat.getTypeTamagotchi(), TypeTamagotchi.CAT);
        Assert.assertEquals(cat.getCurrentPlace().getNomLieu(), NomLieu.HOME);
        Assert.assertEquals(cat.getLife(), ActionConstant.LIFE_MAX);
        Assert.assertEquals((int)cat.getMood(), ActionConstant.MOOD_MAX);
        Assert.assertEquals((int)cat.getHunger(), ActionConstant.HUNGER_MAX);
        Assert.assertEquals((int)cat.getHygiene(), ActionConstant.HYGIENE_MAX);
        Assert.assertEquals((int)cat.getWeight(), ActionConstant.CAT_WEIGHT);
    }

    @Test
    public void eatTest(){
        Cat cat = new Cat();
        cat.initNewTamagotchi();
        cat.setCurrentPlace(new Lieu(NomLieu.KITCHEN));
        cat.loadAction();

        cat.doAction(AttributeConstant.ACTION_EATING_CAT);
        Assert.assertEquals((int)cat.getHunger(), ActionConstant.HUNGER_MAX);
        Assert.assertEquals((int)cat.getWeight(), ActionConstant.CAT_WEIGHT + ActionConstant.KILOMAX);
    }

    @Test
    public void sleepingTest(){
        Cat cat = new Cat();
        cat.initNewTamagotchi();
        cat.setCurrentPlace(new Lieu(NomLieu.BEDROOM));
        cat.loadAction();
        
        cat.doAction(AttributeConstant.ACTION_SLEEPING_CAT);
        Assert.assertEquals((int)cat.getTiredness(), ActionConstant.TIREDNESS_MAX);
       
        for(int i=0;i<18;i++){cat.updateState();}
        cat.doAction(AttributeConstant.ACTION_SLEEPING_CAT);
        Assert.assertEquals((int)cat.getTiredness(), ActionConstant.TIREDNESS_MAX-ActionConstant.DELTA_TIREDNESS_CAT*18+ActionConstant.SLEEPING);
    }

    @Test
    public void usingToiletTest(){
        Cat cat = new Cat();
        cat.initNewTamagotchi();
        cat.setCurrentPlace(new Lieu(NomLieu.TOILET));
        cat.loadAction();
        
        cat.doAction(AttributeConstant.ACTION_USING_TOILET_CAT);
        Assert.assertEquals((int)cat.getHygiene(), ActionConstant.HYGIENE_MAX+ActionConstant.USING_TOILET_HYGIENE);
        Assert.assertEquals((int)cat.getMood(), ActionConstant.MOOD_MAX);
        
        for(int i=0;i<14;i++){cat.updateState();}
        cat.doAction(AttributeConstant.ACTION_USING_TOILET_CAT);
        Assert.assertEquals((int)cat.getHygiene(), ActionConstant.HYGIENE_MAX-ActionConstant.DELTA_HYGIENE_CAT*14+ActionConstant.USING_TOILET_HYGIENE*2);
        Assert.assertEquals((int)cat.getMood(), ActionConstant.MOOD_MAX-ActionConstant.DELTA_MOOD_CAT*14+ActionConstant.USING_TOILET_MOOD);
    }
    
    @Test
    public void doingSportTest(){
        Cat cat = new Cat();
        cat.initNewTamagotchi();
        cat.setCurrentPlace(new Lieu(NomLieu.GARDEN));
        cat.loadAction();
        
        cat.doAction(AttributeConstant.ACTION_DOING_SPORT_CAT);
        Assert.assertEquals((int)cat.getHunger(), ActionConstant.HUNGER_MAX+ActionConstant.DOING_SPORT_HUNGER);
        Assert.assertEquals((int)cat.getMood(), ActionConstant.MOOD_MAX);
        Assert.assertEquals((int)cat.getTiredness(), ActionConstant.TIREDNESS_MAX+ActionConstant.DOING_SPORT_TIREDNESS);

        for(int i=0;i<17;i++){cat.updateState();}
        cat.doAction(AttributeConstant.ACTION_DOING_SPORT_CAT);
        Assert.assertEquals((int)cat.getHunger(), ActionConstant.HUNGER_MAX-ActionConstant.DELTA_HUNGER_CAT*17+ActionConstant.DOING_SPORT_HUNGER*2);
        Assert.assertEquals((int)cat.getMood(), ActionConstant.MOOD_MAX-ActionConstant.DELTA_MOOD_CAT*17+ActionConstant.DOING_SPORT_MOOD);
        Assert.assertEquals((int)cat.getTiredness(), ActionConstant.TIREDNESS_MAX-ActionConstant.DELTA_TIREDNESS_CAT*17+ActionConstant.DOING_SPORT_TIREDNESS*2);
    }
    
    @Test
    public void washingTest(){
        Cat cat = new Cat();
        cat.initNewTamagotchi();
        cat.setCurrentPlace(new Lieu(NomLieu.BATHROOM));
        cat.loadAction();
        
        cat.doAction(AttributeConstant.ACTION_WASHING_CAT);
        Assert.assertEquals((int)cat.getHygiene(), ActionConstant.HYGIENE_MAX);
        
        for(int i=0;i<20;i++){cat.updateState();}
        cat.doAction(AttributeConstant.ACTION_WASHING_CAT);
        Assert.assertEquals((int)cat.getHygiene(), ActionConstant.HYGIENE_MAX-ActionConstant.DELTA_HYGIENE_CAT*20+ActionConstant.WASHING_HYGIENE);
    }
    
    @Test
    public void playingTest(){
        Cat cat = new Cat();
        cat.initNewTamagotchi();
        cat.setCurrentPlace(new Lieu(NomLieu.HOME));
        cat.loadAction();
        
        cat.doAction(AttributeConstant.ACTION_PLAYING_CAT);
        Assert.assertEquals((int)cat.getMood(), ActionConstant.MOOD_MAX);
        
        for(int i=0;i<10;i++){cat.updateState();}
        cat.doAction(AttributeConstant.ACTION_PLAYING_CAT);
        Assert.assertEquals((int)cat.getMood(), ActionConstant.MOOD_MAX-ActionConstant.DELTA_MOOD_CAT*10+ActionConstant.PLAYING);
    
    }
}

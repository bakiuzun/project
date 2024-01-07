package com.example.test;



import org.junit.Assert;
import org.junit.Test;

import com.example.model.Lieu;
import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;

import com.example.model.tama.tamaVivant.Rabbit;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;


public class RabbitTest {

    @Test
    public void creationTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.initNewTamagotchi();
        Assert.assertEquals(rabbit.getTypeTamagotchi(), TypeTamagotchi.RABBIT);
        Assert.assertEquals(rabbit.getCurrentPlace().getNomLieu(), NomLieu.HOME);
        Assert.assertEquals(rabbit.getLife(), ActionConstant.LIFE_MAX);
        Assert.assertEquals((int)rabbit.getMood(), ActionConstant.MOOD_MAX);
        Assert.assertEquals((int)rabbit.getHunger(), ActionConstant.HUNGER_MAX);
        Assert.assertEquals((int)rabbit.getHygiene(), ActionConstant.HYGIENE_MAX);
        Assert.assertEquals((int)rabbit.getWeight(), ActionConstant.RABBIT_WEIGHT);
    }

    @Test
    public void eatTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.initNewTamagotchi();
        rabbit.setCurrentPlace(new Lieu(NomLieu.KITCHEN));
        rabbit.loadAction();

        rabbit.doAction(AttributeConstant.ACTION_EATING_RABBIT);
        Assert.assertEquals((int)rabbit.getHunger(), ActionConstant.HUNGER_MAX);
        Assert.assertEquals((int)rabbit.getWeight(), ActionConstant.RABBIT_WEIGHT + ActionConstant.KILOMAX);
    }

    @Test
    public void sleepingTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.initNewTamagotchi();
        rabbit.setCurrentPlace(new Lieu(NomLieu.BEDROOM));
        rabbit.loadAction();
        
        rabbit.doAction(AttributeConstant.ACTION_SLEEPING_RABBIT);
        Assert.assertEquals((int)rabbit.getTiredness(), ActionConstant.TIREDNESS_MAX);
       
        for(int i=0;i<18;i++){rabbit.updateState();}
        rabbit.doAction(AttributeConstant.ACTION_SLEEPING_RABBIT);
        Assert.assertEquals((int)rabbit.getTiredness(), ActionConstant.TIREDNESS_MAX-ActionConstant.DELTA_TIREDNESS_RABBIT*18+ActionConstant.SLEEPING);
    }

    @Test
    public void usingToiletTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.initNewTamagotchi();
        rabbit.setCurrentPlace(new Lieu(NomLieu.TOILET));
        rabbit.loadAction();
        
        rabbit.doAction(AttributeConstant.ACTION_USING_TOILET_RABBIT);
        Assert.assertEquals((int)rabbit.getHygiene(), ActionConstant.HYGIENE_MAX+ActionConstant.USING_TOILET_HYGIENE);
        Assert.assertEquals((int)rabbit.getMood(), ActionConstant.MOOD_MAX);
        
        for(int i=0;i<14;i++){rabbit.updateState();}
        rabbit.doAction(AttributeConstant.ACTION_USING_TOILET_RABBIT);
        Assert.assertEquals((int)rabbit.getHygiene(), ActionConstant.HYGIENE_MAX-ActionConstant.DELTA_HYGIENE_RABBIT*14+ActionConstant.USING_TOILET_HYGIENE*2);
        Assert.assertEquals((int)rabbit.getMood(), ActionConstant.MOOD_MAX-ActionConstant.DELTA_MOOD_RABBIT*14+ActionConstant.USING_TOILET_MOOD);
    }
    
    @Test
    public void doingSportTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.initNewTamagotchi();
        rabbit.setCurrentPlace(new Lieu(NomLieu.GARDEN));
        rabbit.loadAction();
        
        rabbit.doAction(AttributeConstant.ACTION_DOING_SPORT_RABBIT);
        Assert.assertEquals((int)rabbit.getHunger(), ActionConstant.HUNGER_MAX+ActionConstant.DOING_SPORT_HUNGER);
        Assert.assertEquals((int)rabbit.getMood(), ActionConstant.MOOD_MAX);
        Assert.assertEquals((int)rabbit.getTiredness(), ActionConstant.TIREDNESS_MAX+ActionConstant.DOING_SPORT_TIREDNESS);

        for(int i=0;i<17;i++){rabbit.updateState();}
        rabbit.doAction(AttributeConstant.ACTION_DOING_SPORT_RABBIT);
        Assert.assertEquals((int)rabbit.getHunger(), ActionConstant.HUNGER_MAX-ActionConstant.DELTA_HUNGER_RABBIT*17+ActionConstant.DOING_SPORT_HUNGER*2);
        Assert.assertEquals((int)rabbit.getMood(), ActionConstant.MOOD_MAX-ActionConstant.DELTA_MOOD_RABBIT*17+ActionConstant.DOING_SPORT_MOOD);
        Assert.assertEquals((int)rabbit.getTiredness(), ActionConstant.TIREDNESS_MAX-ActionConstant.DELTA_TIREDNESS_RABBIT*17+ActionConstant.DOING_SPORT_TIREDNESS*2);
    }
    
    @Test
    public void washingTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.initNewTamagotchi();
        rabbit.setCurrentPlace(new Lieu(NomLieu.BATHROOM));
        rabbit.loadAction();
        
        rabbit.doAction(AttributeConstant.ACTION_WASHING_RABBIT);
        Assert.assertEquals((int)rabbit.getHygiene(), ActionConstant.HYGIENE_MAX);
        
        for(int i=0;i<20;i++){rabbit.updateState();}
        rabbit.doAction(AttributeConstant.ACTION_WASHING_RABBIT);
        Assert.assertEquals((int)rabbit.getHygiene(), ActionConstant.HYGIENE_MAX-ActionConstant.DELTA_HYGIENE_RABBIT*20+ActionConstant.WASHING_HYGIENE);
    }
    
    @Test
    public void playingTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.initNewTamagotchi();
        rabbit.setCurrentPlace(new Lieu(NomLieu.HOME));
        rabbit.loadAction();
        
        rabbit.doAction(AttributeConstant.ACTION_PLAYING_RABBIT);
        Assert.assertEquals((int)rabbit.getMood(), ActionConstant.MOOD_MAX);
        
        for(int i=0;i<10;i++){rabbit.updateState();}
        rabbit.doAction(AttributeConstant.ACTION_PLAYING_RABBIT);
        Assert.assertEquals((int)rabbit.getMood(), ActionConstant.MOOD_MAX-ActionConstant.DELTA_MOOD_RABBIT*10+ActionConstant.PLAYING);
    
    }
}

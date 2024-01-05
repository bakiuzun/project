package com.example.test;




import org.junit.Assert;
import org.junit.Test;

import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;

import com.example.model.tama.tamaVivant.Dog;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;


public class DogTest {

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

    @Test
    public void sleepingTest(){
        Dog dog = new Dog();
        dog.init_new_tamagothi();
        dog.getActions().put(AttributeConstant.ACTION_SLEEPING_DOG, dog::sleeping);
        
        dog.doAction(AttributeConstant.ACTION_SLEEPING_DOG);
        Assert.assertEquals((int)dog.getTiredness(), ActionConstant.TIREDNESS_MAX);
       
        for(int i=0;i<18;i++){dog.updateState();}
        dog.doAction(AttributeConstant.ACTION_SLEEPING_DOG);
        Assert.assertEquals((int)dog.getTiredness(), ActionConstant.TIREDNESS_MAX-ActionConstant.DELTA_TIREDNESS_DOG*18+ActionConstant.SLEEPING);
    }

    @Test
    public void usingToiletTest(){
        Dog dog = new Dog();
        dog.init_new_tamagothi();
        dog.getActions().put(AttributeConstant.ACTION_USING_TOILET_DOG, dog::usingToilet);
        
        dog.doAction(AttributeConstant.ACTION_USING_TOILET_DOG);
        Assert.assertEquals((int)dog.getHygiene(), ActionConstant.HYGIENE_MAX-5);
        Assert.assertEquals((int)dog.getMood(), ActionConstant.MOOD_MAX);
        
        for(int i=0;i<14;i++){dog.updateState();}
        dog.doAction(AttributeConstant.ACTION_USING_TOILET_DOG);
        Assert.assertEquals((int)dog.getHygiene(), ActionConstant.HYGIENE_MAX-ActionConstant.DELTA_HYGIENE_DOG*14+ActionConstant.USING_TOILET_HYGIENE*2);
        Assert.assertEquals((int)dog.getMood(), ActionConstant.MOOD_MAX-ActionConstant.DELTA_MOOD_DOG*14+ActionConstant.USING_TOILET_MOOD);
    }
    
    @Test
    public void doingSportTest(){
        Dog dog = new Dog();
        dog.init_new_tamagothi();
        dog.getActions().put(AttributeConstant.ACTION_DOING_SPORT_DOG, dog::doingSport);
        
        dog.doAction(AttributeConstant.ACTION_DOING_SPORT_DOG);
        Assert.assertEquals((int)dog.getHunger(), ActionConstant.HUNGER_MAX+ActionConstant.DOING_SPORT_HUNGER);
        Assert.assertEquals((int)dog.getMood(), ActionConstant.MOOD_MAX);
        Assert.assertEquals((int)dog.getTiredness(), ActionConstant.TIREDNESS_MAX+ActionConstant.DOING_SPORT_TIREDNESS);

        for(int i=0;i<17;i++){dog.updateState();}
        dog.doAction(AttributeConstant.ACTION_DOING_SPORT_DOG);
        Assert.assertEquals((int)dog.getHunger(), ActionConstant.HUNGER_MAX-ActionConstant.DELTA_HUNGER_DOG*17+ActionConstant.DOING_SPORT_HUNGER*2);
        Assert.assertEquals((int)dog.getMood(), ActionConstant.MOOD_MAX-ActionConstant.DELTA_MOOD_DOG*17+ActionConstant.DOING_SPORT_MOOD);
        Assert.assertEquals((int)dog.getTiredness(), ActionConstant.TIREDNESS_MAX-ActionConstant.DELTA_TIREDNESS_DOG*17+ActionConstant.DOING_SPORT_TIREDNESS*2);
    }
    
    @Test
    public void washingTest(){
        Dog dog = new Dog();
        dog.init_new_tamagothi();
        dog.getActions().put(AttributeConstant.ACTION_WASHING_DOG, dog::washing);
        
        dog.doAction(AttributeConstant.ACTION_WASHING_DOG);
        Assert.assertEquals((int)dog.getHygiene(), ActionConstant.HYGIENE_MAX);
        
        for(int i=0;i<20;i++){dog.updateState();}
        dog.doAction(AttributeConstant.ACTION_WASHING_DOG);
        Assert.assertEquals((int)dog.getHygiene(), ActionConstant.HYGIENE_MAX-ActionConstant.DELTA_HYGIENE_DOG*20+ActionConstant.WASHING_HYGIENE);
    }
    
    @Test
    public void playingTest(){
        Dog dog = new Dog();
        dog.init_new_tamagothi();
        dog.getActions().put(AttributeConstant.ACTION_PLAYING_DOG, dog::playing);
        
        dog.doAction(AttributeConstant.ACTION_PLAYING_DOG);
        Assert.assertEquals((int)dog.getMood(), ActionConstant.MOOD_MAX);
        
        for(int i=0;i<10;i++){dog.updateState();}
        dog.doAction(AttributeConstant.ACTION_PLAYING_DOG);
        Assert.assertEquals((int)dog.getMood(), ActionConstant.MOOD_MAX-ActionConstant.DELTA_MOOD_DOG*10+ActionConstant.PLAYING);
    
    }
}

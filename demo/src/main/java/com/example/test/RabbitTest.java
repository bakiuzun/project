package com.example.test;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;

import com.example.model.tama.tamaVivant.Rabbit;
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

    @Test
    public void sleepingTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.init_new_tamagothi();
        rabbit.getActions().put(AttributeConstant.ACTION_SLEEPING_RABBIT, rabbit::sleeping);
        
        rabbit.doAction(AttributeConstant.ACTION_SLEEPING_RABBIT);
        Assert.assertEquals((int)rabbit.getTiredness(), ActionConstant.TIREDNESS_MAX);
       
        for(int i=0;i<18;i++){rabbit.updateState();}
        rabbit.doAction(AttributeConstant.ACTION_SLEEPING_RABBIT);
        Assert.assertEquals((int)rabbit.getTiredness(), ActionConstant.TIREDNESS_MAX-ActionConstant.DELTA_TIREDNESS_RABBIT*18+ActionConstant.SLEEPING);
    }

    @Test
    public void usingToiletTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.init_new_tamagothi();
        rabbit.getActions().put(AttributeConstant.ACTION_USING_TOILET_RABBIT, rabbit::usingToilet);
        
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
        rabbit.init_new_tamagothi();
        rabbit.getActions().put(AttributeConstant.ACTION_DOING_SPORT_RABBIT, rabbit::doingSport);
        
        rabbit.doAction(AttributeConstant.ACTION_DOING_SPORT_RABBIT);
        Assert.assertEquals((int)rabbit.getHunger(), ActionConstant.HUNGER_MAX+ActionConstant.DOING_SPORT_HUNGER);
        Assert.assertEquals((int)rabbit.getWeight(), ActionConstant.RABBIT_WEIGHT-2);
        Assert.assertEquals((int)rabbit.getMood(), ActionConstant.MOOD_MAX);
        Assert.assertEquals((int)rabbit.getTiredness(), ActionConstant.TIREDNESS_MAX+ActionConstant.DOING_SPORT_TIREDNESS);

        for(int i=0;i<17;i++){rabbit.updateState();}
        rabbit.doAction(AttributeConstant.ACTION_DOING_SPORT_RABBIT);
        Assert.assertEquals((int)rabbit.getHunger(), ActionConstant.HUNGER_MAX-ActionConstant.DELTA_HUNGER_RABBIT*17+ActionConstant.DOING_SPORT_HUNGER*2);
        Assert.assertEquals((int)rabbit.getWeight(), ActionConstant.RABBIT_WEIGHT-2*2);
        Assert.assertEquals((int)rabbit.getMood(), ActionConstant.MOOD_MAX-ActionConstant.DELTA_MOOD_RABBIT*17+ActionConstant.DOING_SPORT_MOOD);
        Assert.assertEquals((int)rabbit.getTiredness(), ActionConstant.TIREDNESS_MAX-ActionConstant.DELTA_TIREDNESS_RABBIT*17+ActionConstant.DOING_SPORT_TIREDNESS*2);
    }
    
    @Test
    public void washingTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.init_new_tamagothi();
        rabbit.getActions().put(AttributeConstant.ACTION_WASHING_RABBIT, rabbit::washing);
        
        rabbit.doAction(AttributeConstant.ACTION_WASHING_RABBIT);
        Assert.assertEquals((int)rabbit.getHygiene(), ActionConstant.HYGIENE_MAX);
        
        for(int i=0;i<20;i++){rabbit.updateState();}
        rabbit.doAction(AttributeConstant.ACTION_WASHING_RABBIT);
        Assert.assertEquals((int)rabbit.getHygiene(), ActionConstant.HYGIENE_MAX-ActionConstant.DELTA_HYGIENE_RABBIT*20+ActionConstant.WASHING_HYGIENE);
    }
    
    @Test
    public void playingTest(){
        Rabbit rabbit = new Rabbit();
        rabbit.init_new_tamagothi();
        rabbit.getActions().put(AttributeConstant.ACTION_PLAYING_RABBIT, rabbit::playing);
        
        rabbit.doAction(AttributeConstant.ACTION_PLAYING_RABBIT);
        Assert.assertEquals((int)rabbit.getMood(), ActionConstant.MOOD_MAX);
        
        for(int i=0;i<10;i++){rabbit.updateState();}
        rabbit.doAction(AttributeConstant.ACTION_PLAYING_RABBIT);
        Assert.assertEquals((int)rabbit.getMood(), ActionConstant.MOOD_MAX-ActionConstant.DELTA_MOOD_RABBIT*10+ActionConstant.PLAYING);
    
    }
}

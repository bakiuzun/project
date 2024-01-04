package com.example.test;






import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.model.NomLieu;
import com.example.model.TypeTamagotchi;

import com.example.model.tama.tamaVivant.Turtle;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;


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

    @Test
    public void eatTest(){
        Turtle turtle = new Turtle();
        turtle.init_new_tamagothi();
        turtle.getActions().put(AttributeConstant.ACTION_EATING_TURTLE, turtle::eating);
        
        turtle.doAction(AttributeConstant.ACTION_EATING_TURTLE);
        Assert.assertEquals((int)turtle.getHunger(), ActionConstant.HUNGER_MAX);
        Assert.assertEquals((int)turtle.getWeight(), ActionConstant.TURTLE_WEIGHT + ActionConstant.KILOMAX);
    }

    @Test
    public void sleepingTest(){
        Turtle turtle = new Turtle();
        turtle.init_new_tamagothi();
        turtle.getActions().put(AttributeConstant.ACTION_SLEEPING_TURTLE, turtle::sleeping);
        
        turtle.doAction(AttributeConstant.ACTION_SLEEPING_TURTLE);
        Assert.assertEquals((int)turtle.getTiredness(), ActionConstant.TIREDNESS_MAX);
       
        for(int i=0;i<18;i++){turtle.updateState();}
        turtle.doAction(AttributeConstant.ACTION_SLEEPING_TURTLE);
        Assert.assertEquals((int)turtle.getTiredness(), ActionConstant.TIREDNESS_MAX-36+10);
    }

    @Test
    public void usingToiletTest(){
        Turtle turtle = new Turtle();
        turtle.init_new_tamagothi();
        turtle.getActions().put(AttributeConstant.ACTION_USING_TOILET_TURTLE, turtle::usingToilet);
        
        turtle.doAction(AttributeConstant.ACTION_USING_TOILET_TURTLE);
        Assert.assertEquals((int)turtle.getHygiene(), ActionConstant.HYGIENE_MAX-5);
        Assert.assertEquals((int)turtle.getMood(), ActionConstant.MOOD_MAX);
        
        for(int i=0;i<14;i++){turtle.updateState();}
        turtle.doAction(AttributeConstant.ACTION_USING_TOILET_TURTLE);
        Assert.assertEquals((int)turtle.getHygiene(), ActionConstant.HYGIENE_MAX-66);
    }
    
    @Test
    public void doingSportTest(){
        Turtle turtle = new Turtle();
        turtle.init_new_tamagothi();
        turtle.getActions().put(AttributeConstant.ACTION_DOING_SPORT_TURTLE, turtle::doingSport);
        
        turtle.doAction(AttributeConstant.ACTION_DOING_SPORT_TURTLE);
        Assert.assertEquals((int)turtle.getHunger(), ActionConstant.HUNGER_MAX-7);
        Assert.assertEquals((int)turtle.getWeight(), ActionConstant.TURTLE_WEIGHT-2);
        Assert.assertEquals((int)turtle.getMood(), ActionConstant.MOOD_MAX);
        Assert.assertEquals((int)turtle.getTiredness(), ActionConstant.TIREDNESS_MAX-7);

        for(int i=0;i<17;i++){turtle.updateState();}
        turtle.doAction(AttributeConstant.ACTION_DOING_SPORT_TURTLE);
        Assert.assertEquals((int)turtle.getHunger(), ActionConstant.HUNGER_MAX-31);
        Assert.assertEquals((int)turtle.getWeight(), ActionConstant.TURTLE_WEIGHT-4);
        Assert.assertEquals((int)turtle.getMood(), ActionConstant.MOOD_MAX-46);
        Assert.assertEquals((int)turtle.getTiredness(), ActionConstant.TIREDNESS_MAX-48);
    }
    //pas terminer
    @Test
    public void washingTest(){
        Turtle turtle = new Turtle();
        turtle.init_new_tamagothi();
        turtle.getActions().put(AttributeConstant.ACTION_WASHING_TURTLE, turtle::washing);
        
        turtle.doAction(AttributeConstant.ACTION_WASHING_TURTLE);
        Assert.assertEquals((int)turtle.getHygiene(), ActionConstant.HYGIENE_MAX);
    }

    @Test
    public void playingTest(){
        Turtle turtle = new Turtle();
        turtle.init_new_tamagothi();
        turtle.getActions().put(AttributeConstant.ACTION_PLAYING_TURTLE, turtle::playing);
        
        turtle.doAction(AttributeConstant.ACTION_PLAYING_TURTLE);
        Assert.assertEquals((int)turtle.getHygiene(), ActionConstant.MOOD_MAX);
    }

}

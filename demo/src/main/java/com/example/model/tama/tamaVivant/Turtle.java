package com.example.model.tama.tamaVivant;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.Vivant;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

public class Turtle extends Vivant {
    

    public Turtle(){       
        loadAction();
    }



    public void init_new_tamagothi(){
        this.typeTamagotchi = TypeTamagotchi.TURTLE;
        this.weight = ActionConstant.TURTLE_WEIGHT; // Attribut Vivant

        super.init_new_tamagothi();
        super.addAttributes();  
    }

    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
    }

    public void updateState(){
        delta_hunger = ActionConstant.DELTA_HUNGER_TURTLE; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_TURTLE;  
        delta_mood = ActionConstant.DELTA_MOOD_TURTLE; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_TURTLE;  
        delta_weight = ActionConstant.DELTA_WEIGHT_TURTLE; 
        super.updateState();
    }

    public void loadAction(){
        super.loadAction();
        actions.put(AttributeConstant.ACTION_EATING_TURTLE, this::eating);
        actions.put(AttributeConstant.ACTION_SLEEPING_TURTLE, this::sleeping);
        actions.put(AttributeConstant.ACTION_PLAYING_TURTLE, this::playing);
        actions.put(AttributeConstant.ACTION_WASHING_TURTLE, this::washing);
        actions.put(AttributeConstant.ACTION_DOING_SPORT_TURTLE, this::doingSport);
        actions.put(AttributeConstant.ACTION_USING_TOILET_TURTLE, this::usingToilet);
    }

    public ArrayList<String> printAttributes(){
        
        ArrayList<String> res =  super.printAttributes();
        
        return res;
    }


    public  void loadTamagotchiInfo(){
        
    }
}

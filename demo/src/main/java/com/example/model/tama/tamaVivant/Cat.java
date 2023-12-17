package com.example.model.tama.tamaVivant;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.example.controller.ConnectedController.AttributeEntry;
import com.example.model.TypeTamagotchi;
import com.example.model.tama.Vivant;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

public class Cat extends Vivant {

    public Cat(){
        loadAction();
    }

    public void init_new_tamagothi(){
        this.typeTamagotchi = TypeTamagotchi.CAT;
        this.weight = ActionConstant.CAT_WEIGHT; // Attribut Vivant
        
        super.init_new_tamagothi();
        super.addAttributes();
    }

    public void loadTamaFromDatabase(JSONObject tama){
        // attribute of the cat 
        super.loadTamaFromDatabase(tama);
        super.addAttributes();

    }

    public void updateState(){
        delta_hunger = ActionConstant.DELTA_HUNGER_CAT; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_CAT;  
        delta_mood = ActionConstant.DELTA_MOOD_CAT; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_CAT;  
        delta_weight = ActionConstant.DELTA_WEIGHT_CAT; 
        super.updateState();
    }

    public void loadAction(){
        super.loadAction();
        actions.put(AttributeConstant.ACTION_EATING_CAT, this::eating);
        actions.put(AttributeConstant.ACTION_SLEEPING_CAT, this::sleeping);
        actions.put(AttributeConstant.ACTION_PLAYING_CAT, this::playing);
        actions.put(AttributeConstant.ACTION_WASHING_CAT, this::washing);
        actions.put(AttributeConstant.ACTION_DOING_SPORT_CAT, this::doingSport);
        actions.put(AttributeConstant.ACTION_USING_TOILET_CAT, this::usingToilet);
    }

    public ArrayList<String> printAttributes(){
        
        ArrayList<String> res =  super.printAttributes();
        
        return res;
    }
    
}

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
    }

    public void init_new_tamagothi(){
        this.typeTamagotchi = TypeTamagotchi.CAT;
        this.weight = ActionConstant.CAT_WEIGHT; // Attribut Vivant
        
        super.init_new_tamagothi();
        super.addAttributes();
        loadAction();
    }

    public void loadTamaFromDatabase(JSONObject tama){
        // attribute of the cat 
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
        loadAction();

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
        switch(getLieuActuel().getNomLieu()){
            case HOME:
                actions.put(AttributeConstant.ACTION_PLAYING_CAT, this::playing);
            break;
            case BATHROOM:
                actions.put(AttributeConstant.ACTION_WASHING_CAT, this::washing);
            break;
            case GARDEN:
                actions.put(AttributeConstant.ACTION_DOING_SPORT_CAT, this::doingSport);
            break;
            case KITCHEN:
                actions.put(AttributeConstant.ACTION_EATING_CAT, this::eating);
            break;
            case TOILET:
                actions.put(AttributeConstant.ACTION_USING_TOILET_CAT, this::usingToilet);
            break;
            case BEDROOM:
                actions.put(AttributeConstant.ACTION_SLEEPING_CAT, this::sleeping);
            break;
        }
    }

    public ArrayList<String> printAttributes(){
        
        ArrayList<String> res =  super.printAttributes();
        
        return res;
    }
    
}

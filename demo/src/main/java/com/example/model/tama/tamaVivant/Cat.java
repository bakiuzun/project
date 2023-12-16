package com.example.model.tama.tamaVivant;

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
        System.out.println("I PROMISE YOU IM THE KING OF THE BARBARRIANS");
        super.loadTamaFromDatabase(tama);
        super.addAttributes();

    }

    public void updateState(){
        System.out.println("CAT UDPATE HOLD ON ");
        delta_hunger = ActionConstant.DELTA_HUNGER_CAT; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_CAT;  
        delta_mood = ActionConstant.DELTA_MOOD_CAT; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_CAT;  
        delta_weight = ActionConstant.DELTA_WEIGHT_CAT; 
        super.updateState();
    }

    public void loadAction(){
        super.loadAction();
        
    }
    
}

package com.example.model.tama.tamaVivant;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.Vivant;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

public class Dog extends Vivant {
    

    public Dog(){       
        loadAction();
    }



    public void init_new_tamagothi(){
        this.typeTamagotchi = TypeTamagotchi.DOG;
        this.weight = ActionConstant.DOG_WEIGHT; // Attribut Vivant

        super.init_new_tamagothi();
        super.addAttributes();
    }

    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
    }

    public void updateState(){
        delta_hunger = ActionConstant.DELTA_HUNGER_DOG; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_DOG;  
        delta_mood = ActionConstant.DELTA_MOOD_DOG; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_DOG;  
        delta_weight = ActionConstant.DELTA_WEIGHT_DOG; 
        super.updateState();
    }

    public void loadAction(){
        super.loadAction();
        actions.put(AttributeConstant.ACTION_EATING_DOG, this::eating);
        actions.put(AttributeConstant.ACTION_SLEEPING_DOG, this::sleeping);
        actions.put(AttributeConstant.ACTION_PLAYING_DOG, this::playing);
        actions.put(AttributeConstant.ACTION_WASHING_DOG, this::washing);
        actions.put(AttributeConstant.ACTION_DOING_SPORT_DOG, this::doingSport);
        actions.put(AttributeConstant.ACTION_USING_TOILET_DOG, this::usingToilet);
    }

    public ArrayList<String> printAttributes(){
        
        ArrayList<String> res =  super.printAttributes();
        
        return res;
    }

    public  void loadTamagotchiInfo(){
        
    }
}

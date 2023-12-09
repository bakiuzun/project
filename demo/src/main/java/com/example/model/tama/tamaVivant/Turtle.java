package com.example.model.tama.tamaVivant;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.Vivant;
import com.example.model.utils.ActionConstant;

public class Turtle extends Vivant {
    

    public Turtle(){       
        super.loadAction();
    }



    public void init_new_tamagothi(){
        
        this.typeTamagotchi = TypeTamagotchi.TURTLE;
        
        this.weight = ActionConstant.TURTLE_WEIGHT; // Attribut Vivant
        super.init_new_tamagothi();
        super.addAttributes();
        
    }

    public void updateState(){
        System.out.println("TURTLE UDPATE HOLD ON ");
        delta_hunger = ActionConstant.DELTA_HUNGER_TURTLE; 
        delta_hygiene = ActionConstant.DELTA_HYGIENE_TURTLE;  
        delta_mood = ActionConstant.DELTA_MOOD_TURTLE; 
        delta_tiredness = ActionConstant.DELTA_TIREDNESS_TURTLE;  
        delta_weight = ActionConstant.DELTA_WEIGHT_TURTLE; 
        super.updateState();
    }


    public  void loadTamagotchiInfo(){
        
    }
}

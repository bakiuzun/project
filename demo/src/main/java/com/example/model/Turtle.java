package com.example.model;

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
}

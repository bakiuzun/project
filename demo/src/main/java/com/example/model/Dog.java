package com.example.model;

public class Dog extends Vivant {
    

    public Dog(){       
        super.loadAction();
    }



    public void init_new_tamagothi(){
        
        this.typeTamagotchi = TypeTamagotchi.DOG;

        super.init_new_tamagothi();
        super.addAttributes();

        
        
    }
}

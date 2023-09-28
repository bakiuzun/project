package com.example.model;

public class Robot extends NonVivant {

    public Robot(){
        super.loadAction();
    }

    public void init_new_tamagothi(){

        this.typeTamagotchi = TypeTamagotchi.ROBOT;

        super.init_new_tamagothi();
        super.addAttributes();

    }

}
    

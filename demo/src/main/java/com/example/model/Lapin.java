package com.example.model;

public class Lapin extends Vivant {

    public Lapin(){
        super.loadAction();
    }

    public void init_new_tamagothi(){

        this.typeTamagotchi = TypeTamagotchi.LAPIN;

        super.init_new_tamagothi();
        
    }

}

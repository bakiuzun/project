package com.example.model;

public class Cat extends Vivant {

    public Cat(){

        super.loadAction();

    }

    public void init_new_tamagothi(){

        this.typeTamagotchi = TypeTamagotchi.CAT;

        super.init_new_tamagothi();
        super.addAttributes();
    }

    
}

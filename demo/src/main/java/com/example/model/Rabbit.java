package com.example.model;

public class Rabbit extends Vivant {

    public Rabbit(){
        super.loadAction();
    }

    public void init_new_tamagothi(){

        this.typeTamagotchi = TypeTamagotchi.RABBIT;
        this.weight = ActionConstant.RABBIT_WEIGHT; // Attribut Vivant
        super.init_new_tamagothi();
        super.addAttributes();

    }

}

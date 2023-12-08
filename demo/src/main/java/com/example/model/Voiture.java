package com.example.model;

public class Voiture extends NonVivant {

    public Voiture(){
        super.loadAction();
    }

    public void init_new_tamagothi(){

        this.typeTamagotchi = TypeTamagotchi.VOITURE;

        super.init_new_tamagothi();
        super.addAttributes();

    }

    public void updateState(){
        System.out.println("ROBOT UDPATE HOLD ON ");
        this.battery -= ActionConstant.DELTA_BATTERY_CAR;
        this.oil -= ActionConstant.DELTA_OIL_CAR;
        this.temperature -= ActionConstant.DELTA_TEMPERATURE_CAR;
        this.rust -= ActionConstant.DELTA_RUST_CAR;
        super.updateState();
    }
}
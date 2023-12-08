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

    public void updateState(){
        System.out.println("ROBOT UDPATE HOLD ON ");
        this.battery -= ActionConstant.DELTA_BATTERY_ROBOT;
        this.oil -= ActionConstant.DELTA_OIL_ROBOT;
        this.temperature -= ActionConstant.DELTA_TEMPERATURE_ROBOT;
        this.rust -= ActionConstant.DELTA_RUST_ROBOT;
        super.updateState();
    }


    public  void loadTamagotchiInfo(){
        
    }
}
    

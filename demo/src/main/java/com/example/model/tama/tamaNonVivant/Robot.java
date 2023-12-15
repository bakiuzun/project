package com.example.model.tama.tamaNonVivant;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.NonVivant;
import com.example.model.utils.ActionConstant;

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
        delta_battery = ActionConstant.DELTA_BATTERY_ROBOT;
        delta_oil = ActionConstant.DELTA_OIL_ROBOT;
        delta_temperature = ActionConstant.DELTA_TEMPERATURE_ROBOT;
        delta_rust = ActionConstant.DELTA_RUST_ROBOT;
        super.updateState();
    }


    public  void loadTamagotchiInfo(){
        
    }
}
    

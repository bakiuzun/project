package com.example.model.tama.tamaNonVivant;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.NonVivant;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

public class Robot extends NonVivant {

    public Robot(){
        loadAction();
    }

    public void init_new_tamagothi(){

        this.typeTamagotchi = TypeTamagotchi.ROBOT;

        super.init_new_tamagothi();
        super.addAttributes();

    }

    public void loadTamaFromDatabase(JSONObject tama){
        super.loadTamaFromDatabase(tama);
        super.addAttributes();
    }

    public void updateState(){
        delta_battery = ActionConstant.DELTA_BATTERY_ROBOT;
        delta_oil = ActionConstant.DELTA_OIL_ROBOT;
        delta_temperature = ActionConstant.DELTA_TEMPERATURE_ROBOT;
        delta_rust = ActionConstant.DELTA_RUST_ROBOT;
        super.updateState();
    }

    public void loadAction(){
        super.loadAction();
        actions.put(AttributeConstant.ACTION_BATTERING_ROBOT, this::battering);
        actions.put(AttributeConstant.ACTION_OILING_ROBOT, this::oiling);
        actions.put(AttributeConstant.ACTION_COOLING_ROBOT, this::cooling);
        actions.put(AttributeConstant.ACTION_CLEANING_ROBOT, this::cleaning);
    }

    public ArrayList<String> printAttributes(){
        
        ArrayList<String> res =  super.printAttributes();
        
        return res;
    }


    public  void loadTamagotchiInfo(){
        
    }
}
    

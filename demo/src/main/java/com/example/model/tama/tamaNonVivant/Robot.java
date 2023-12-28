package com.example.model.tama.tamaNonVivant;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.example.model.TypeTamagotchi;
import com.example.model.tama.NonVivant;
import com.example.model.utils.ActionConstant;
import com.example.model.utils.AttributeConstant;

public class Robot extends NonVivant {

    public Robot(){
        
    }

    public void init_new_tamagothi(){

        this.typeTamagotchi = TypeTamagotchi.ROBOT;

        super.init_new_tamagothi();
        super.addAttributes();
        loadAction();
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
        switch(getLieuActuel().getNomLieu()){
            case BEDROOM:
                actions.put(AttributeConstant.ACTION_BATTERING_ROBOT, this::battering);
            break;
            case GARAGE:
                actions.put(AttributeConstant.ACTION_OILING_ROBOT, this::oiling);
            break;
            case HOME:
                actions.put(AttributeConstant.ACTION_COOLING_ROBOT, this::cooling);
            break;
            case GARDEN:
                actions.put(AttributeConstant.ACTION_CLEANING_ROBOT, this::cleaning);
            break;
        }   
    }

    public ArrayList<String> printAttributes(){
        
        ArrayList<String> res =  super.printAttributes();
        
        return res;
    }


}
    

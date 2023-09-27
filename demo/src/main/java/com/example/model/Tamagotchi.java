package com.example.model;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class Tamagotchi {

    protected Map<String,String> attributes = new HashMap<>();
    protected Map<String,Runnable> actions = new HashMap<>();
    
    protected String actionEnCours;
    protected int vie;

    private Session maSessions;
    protected TypeTamagotchi typeTamagotchi;
    protected Lieu lieuActuel;

    public TypeTamagotchi getTypeTamagotchi(){
        return typeTamagotchi;
    }

    public void setTypeTamagotchi(TypeTamagotchi typeTamagotchi){
        this.typeTamagotchi = typeTamagotchi;
    }

    public void loadAction(){
    }

    public void addAttributes(){
        this.attributes.put("vie", String.valueOf(this.vie));
        this.attributes.put("actionEnCours", actionEnCours);
        this.attributes.put("typeTamagotchi",  typeTamagotchi.name());
        this.attributes.put("lieuActuel",  lieuActuel.getNomLieu().name());
        //this.attributes.putAll(this.maSessions.getAttributes());
    }

    public void setSession(Session session){
        this.maSessions = session;
    }


    public Map<String,String> getAttributes(){
        return this.attributes;
    }

    public Session getSession(){
        return maSessions;
    }

    public void init_new_tamagothi(){

        Date currentDate = new Date();
        Time currentTime = new Time(currentDate.getTime());

        this.vie = 100;
        this.actionEnCours = "Pas d'action en cours";
        this.lieuActuel = new Lieu(NomLieu.MAISON);
        this.maSessions = new Session(0, currentTime, currentTime, currentTime);
        //this.maSessions = Session.init_new_session();
    }

    public String toString(){
        StringBuilder ret = new StringBuilder(); // Use StringBuilder for efficient string concatenation

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            ret.append(key).append(": ").append(value).append(",").append("\n");
        }
        return ret.toString();
    }


}

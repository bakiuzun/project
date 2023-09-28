package com.example.model;

public abstract class Vivant extends Tamagotchi {

    protected int faim;
    protected int fatigue;
    protected int hygiene;
    protected int humeur;
    protected int poid;




    public void init_new_tamagothi(){
        super.init_new_tamagothi();

        this.faim = 100;
        this.fatigue = 100;
        this.hygiene = 100;
        this.humeur = 100;
        this.poid = 50;
    }


    public void loadAction(){
        super.loadAction();
    }


    public void addAttributes(){
        super.addAttributes();
        attributes.put("faim", String.valueOf(this.faim));
        attributes.put("fatigue", String.valueOf(this.fatigue));
        attributes.put("hygiene", String.valueOf(this.hygiene));
        attributes.put("humeur", String.valueOf(this.humeur));
        attributes.put("poid", String.valueOf(this.poid));
    }
    
}

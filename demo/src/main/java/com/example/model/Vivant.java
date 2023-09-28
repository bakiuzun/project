package com.example.model;

public abstract class Vivant extends Tamagotchi {

    protected Integer faim;
    protected Integer fatigue;
    protected Integer hygiene;
    protected Integer humeur;
    protected Integer poid;

    protected int delta_faim;
    protected int delta_fatigue;
    protected int delta_hygiene;
    protected int delta_humeur;
    protected int delta_poid;




    public void init_new_tamagothi(){
        super.init_new_tamagothi();

        this.faim = 100;
        this.fatigue = 100;
        this.hygiene = 100;
        this.humeur = 100;

        this.delta_faim = 2;
        this.delta_fatigue = 2;
        this.delta_hygiene = 2;
        this.delta_humeur = 2;
        this.delta_poid = 2;
        
        // le poid et init en fonction du Vivant dans les sous-classes
    }


    public void loadAction(){
        super.loadAction();
    }

    public void updateState(){

        this.faim -= delta_faim;
        this.fatigue -= delta_fatigue;
        this.humeur -= delta_humeur;
        this.hygiene -= delta_hygiene;

        replace_new_attributes_values();
        
        super.updateState();
    }


    public void replace_new_attributes_values(){
        attributes.replace("faim", String.valueOf(this.faim));
        attributes.replace("fatigue", String.valueOf(this.fatigue));
        attributes.replace("hygiene", String.valueOf(this.hygiene));
        attributes.replace("humeur", String.valueOf(this.humeur));
        attributes.replace("poid", String.valueOf(this.poid));

    }


    public void addAttributes(){
        super.addAttributes();
        attributes.put("faim", String.valueOf(this.faim));
        attributes.put("fatigue", String.valueOf(this.fatigue));
        attributes.put("hygiene", String.valueOf(this.hygiene));
        attributes.put("humeur", String.valueOf(this.humeur));
        attributes.put("poid", String.valueOf(this.poid));
    }
    

    public void manger(){

    }

    public void seReposer(){

    }

    public void seLaver(){

    }

    public void jouer(){
        
    }


}

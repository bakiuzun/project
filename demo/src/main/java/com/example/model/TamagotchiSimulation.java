package com.example.model;


import com.example.model.tama.Tamagotchi;
import com.example.model.tama.NonVivant;
import com.example.model.tama.Vivant;
import com.example.model.tama.tamaNonVivant.Robot;
import com.example.model.tama.tamaVivant.Cat;
import com.example.model.utils.AttributeConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TamagotchiSimulation {
    private ArrayList<Tamagotchi> tamagotchis;
    private Random random;

    public TamagotchiSimulation() {
        tamagotchis = new ArrayList<>();
        random = new Random();
    }

    public static void main(String[] args){
        TamagotchiSimulation tamagotchiSimulation = new TamagotchiSimulation();
        tamagotchiSimulation.generatePlots();
    }

    public void generateTamagotchis(int numTamagotchis) {
        for (int i = 0; i < numTamagotchis; i++) {
            Tamagotchi tamagotchi;
            if (random.nextBoolean()) {
                tamagotchi = new Robot();

            } else {
                tamagotchi = new Cat();
            };

            tamagotchis.add(tamagotchi);
        }
    }

    public HashMap<Tamagotchi, ArrayList<Integer>> runSimulation() {
        HashMap<Tamagotchi, ArrayList<Integer>> healthData = new HashMap<>();
        generateTamagotchis(5);

        for(Tamagotchi tamagotchi : tamagotchis){
            Session new_tama_session = Session.init_new_session(tamagotchi.toString(),0000);
            tamagotchi.init_new_tamagothi();
            tamagotchi.setSession(new_tama_session);
            healthData.put(tamagotchi, new ArrayList<Integer>());
        }

        // 1 update toutes les 5 heures

        int heures = 480;

      

        for(int i = 0; i < heures/5; i++) {
       
            for(Tamagotchi tamagotchi : tamagotchis){
                tamagotchi.updateState();
                tamagotchi.printAttributes();
                healthData.get(tamagotchi).add(Integer.parseInt(tamagotchi.getAttributes().get(AttributeConstant.LIFE)));
            }
        }

      
        return healthData;

    }

    public void generatePlots() {

        HashMap<Tamagotchi, ArrayList<Integer>> healthData = runSimulation();


        healthData.forEach((tamagotchi, health) -> {
            System.out.println(tamagotchi);
            for(Integer healthValue : health){
                System.out.println(healthValue);
            }
            
        });

    }
}
package com.example.model;


import com.example.model.tama.Tamagotchi;
import com.example.model.tama.NonVivant;
import com.example.model.tama.Vivant;
import com.example.model.tama.tamaNonVivant.Robot;
import com.example.model.tama.tamaNonVivant.Voiture;
import com.example.model.tama.tamaVivant.Cat;
import com.example.model.tama.tamaVivant.Dog;
import com.example.model.tama.tamaVivant.Rabbit;
import com.example.model.tama.tamaVivant.Turtle;
import com.example.model.utils.AttributeConstant;
import com.example.model.TypeTamagotchi;


import java.io.PrintWriter;
import java.io.IOException;


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
    Random random = new Random();
    TypeTamagotchi[] types = TypeTamagotchi.values();

    for (int i = 0; i < numTamagotchis; i++) {
        // Select a random type
        TypeTamagotchi type = types[random.nextInt(types.length)];

        Tamagotchi tamagotchi;

        switch (type) {
            case CAT:
                tamagotchi = new Cat();
                break;
            case ROBOT:
                tamagotchi = new Robot();
                break;
            case DOG:
                tamagotchi = new Dog();
                break;
            case RABBIT:
                tamagotchi = new Rabbit();
                break;
            case VOITURE:
                tamagotchi = new Voiture();
                break;
            case TURTLE:
                tamagotchi = new Turtle();
                break;
            default:
                throw new IllegalArgumentException("Invalid Tamagotchi type: " + type);
        }

        tamagotchis.add(tamagotchi);
    }
}

    public HashMap<Integer, ArrayList<Integer>> runSimulation() {
        HashMap<Integer, ArrayList<Integer>> healthData = new HashMap<>();
        generateTamagotchis(40);

        for(Tamagotchi tamagotchi : tamagotchis){
            Session new_tama_session = Session.init_new_session(tamagotchi.toString(),0000);
            tamagotchi.init_new_tamagothi();
            tamagotchi.setSession(new_tama_session);
            healthData.put(tamagotchi.hashCode(), new ArrayList<Integer>());
        }

        // 1 update toutes les 2h30

        int heures = 75;

      

        for(int i = 0; i < heures/2.5; i++) {
       
            for(Tamagotchi tamagotchi : tamagotchis){
                tamagotchi.updateState();
                tamagotchi.printAttributes();
                healthData.get(tamagotchi.hashCode()).add(Integer.parseInt(tamagotchi.getAttributes().get(AttributeConstant.LIFE)));
            }
        }

      
        return healthData;

    }

    public void generatePlots() {

        HashMap<Integer, ArrayList<Integer>> healthData = runSimulation();


        healthData.forEach((tamagotchi, health) -> {
            System.out.println(tamagotchi);
            for(Integer healthValue : health){
                System.out.println(healthValue);
            }
            
        });

        try {
            PrintWriter writer = new PrintWriter("healthData.csv", "UTF-8");
            healthData.forEach((tamagotchi, health) -> {
                writer.print(tamagotchi + ",");
                for(Integer healthValue : health){
                    writer.print(healthValue + ",");
                }
                writer.println();
            });
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        // generate a plot using the data
        try {
            ProcessBuilder pb = new ProcessBuilder("python3","plot.py");
            Process p = pb.start();
            int exitCode = p.waitFor();
            assert exitCode == 0;
        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred while executing the Python script.");
            e.printStackTrace();
        }



    }
}
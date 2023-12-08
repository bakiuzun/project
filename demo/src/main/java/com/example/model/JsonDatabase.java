package com.example.model;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class JsonDatabase {

    static public Tamagotchi currentTamagotchi = null;


    public static Tamagotchi getTama(){
        return currentTamagotchi;
    }
    public static void create_new_session(Tamagotchi tamagotchi){
        
        try (FileReader fileReader = new FileReader(AttributeConstant.FILE)) {

            currentTamagotchi = tamagotchi;
            System.out.println("TAMAGOTCHI = " + currentTamagotchi.getTypeTamagotchi());

            Map<String,String> attr_sess =  tamagotchi.getSession().getAttributes();
            Map<String, String> attr_tama =  tamagotchi.getAttributes();

            Map<String, Map<String,String>> new_sessions = new HashMap<>();
            new_sessions.put(AttributeConstant.TAMAGOTCHI_INFO, attr_tama);
            new_sessions.put(AttributeConstant.SESSION_INFO, attr_sess);

            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(fileReader);

            JSONObject sessions = (JSONObject) jsonData.get(AttributeConstant.SESSION);
            sessions.put(attr_sess.get(AttributeConstant.ID), new_sessions);
            jsonData.put(AttributeConstant.FREE_SESSION_ID, tamagotchi.getSession().getId()+1);
            jsonData.put(AttributeConstant.SESSION, sessions);


            FileWriter file_x = new FileWriter(AttributeConstant.FILE);
            file_x.write(jsonData.toJSONString());
			file_x.flush();
			file_x.close();


        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
            
        }

    }


    public static ArrayList<Tamagotchi> getAllSession(){

        ArrayList<Tamagotchi> ret = null;

        try (FileReader fileReader = new FileReader(AttributeConstant.FILE)){
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(fileReader);
            
            JSONObject sessions = (JSONObject) jsonData.get(AttributeConstant.SESSION);
            
            for (Object key : sessions.keySet()) {
                String sessionId = (String) key;
                JSONObject one_session = (JSONObject) sessions.get(sessionId);
                JSONObject this_session_info = (JSONObject) one_session.get("session_info");
                JSONObject this_session_tama = (JSONObject) one_session.get("tamagotchi_info");
                
                createTama(this_session_info, this_session_tama);
                break;
                // Get the id value for the current session
                //String idValue = (String) sessionInfo.get("dateCreation");
                
                // Print the id value for each session
                //System.out.println("ID value for session " + sessionId + ": " + idValue);
            }
            

        } catch (Exception e){
            System.out.println("HUGE ERROR GET ALL SESSION THO " + e.getLocalizedMessage());
        }

        return ret;
    }


    private static Tamagotchi createTama(JSONObject session,JSONObject tama){
        
        
        // session 
        String dateCreation =  (String) session.get("dateCreation");
        String dateDerniereConnexion =  (String) session.get("dateCreation");
        String nom_tamagotchi =  (String) session.get("nom_tamagotchi");
        String id =  (String) session.get("id");
        String time =  (String) session.get("time");
        String codePin =  (String) session.get("codePin");

        Session ma_session = new Session(Integer.parseInt(id), null, null, null, Integer.parseInt(codePin), nom_tamagotchi);
        
        // session 
        String typeTamagotchi =  (String) tama.get("typeTamagotchi");
        
        
        switch (typeTamagotchi ) {
            case "CAT":
                
                
                Cat xtama = new Cat();
                return xtama;
                //{"faim":"100","fatigue":"100","vie":"100","actionEnCours":"Pas d'action en cours","lieuActuel":"MAISON","hygiene":"100","humeur":"100","typeTamagotchi":"CAT","poid":"5"}
                
            
            case "DOG":
                break;
            default:
                break;
        }

        
        /* 
        String dateDerniereConnexion =  (String) tama.get("dateCreation");
        String nom_tamagotchi =  (String) tama.get("nom_tamagotchi");
        String id =  (String) tama.get("id");
        String time =  (String) tama.get("time");
        String codePin =  (String) tama.get("codePin");

        typeTamagotchi
        */
        
        
        return null;
    } 




    public static Integer getFreeSessionID(){
        String filePath = AttributeConstant.FILE;
        
        Integer freeSessionId = null;
        try (FileReader reader = new FileReader(filePath)) {
            // Parse the JSON file
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Get the value associated with the "free_session_id" key
            freeSessionId = (Integer) jsonObject.get(AttributeConstant.FREE_SESSION_ID);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return freeSessionId;

       
    }


}


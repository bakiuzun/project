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

import com.example.model.tama.Tamagotchi;
import com.example.model.tama.tamaVivant.Cat;
import com.example.model.utils.AttributeConstant;
import com.example.model.utils.utility;



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
            jsonData.put(AttributeConstant.FREE_SESSION_ID,String.valueOf(tamagotchi.getSession().getId()+1));
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


    public static ArrayList<Session> getAllSession(){

        ArrayList<Session> ret = new ArrayList<>();

        try (FileReader fileReader = new FileReader(AttributeConstant.FILE)){
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(fileReader);
            
            JSONObject sessions = (JSONObject) jsonData.get(AttributeConstant.SESSION);
            
            for (Object key : sessions.keySet()) {
                String sessionId = (String) key;
                JSONObject one_session = (JSONObject) sessions.get(sessionId);
                JSONObject this_session_info = (JSONObject) one_session.get(AttributeConstant.SESSION_INFO);
                JSONObject this_session_tama = (JSONObject) one_session.get(AttributeConstant.TAMAGOTCHI_INFO);

                ret.add(createTama(this_session_info));
            }
            

        } catch (Exception e){
            System.out.println("HUGE ERROR GET ALL SESSION THO " + e.getLocalizedMessage());
        }

        return ret;
    }


    private static Session createTama(JSONObject session){
        
        
        // session 
        String dateCreation =  (String) session.get(AttributeConstant.CREATION_DATE);
        String dateDerniereConnexion =  (String) session.get(AttributeConstant.LAST_CONNECTION);
        String nom_tamagotchi =  (String) session.get(AttributeConstant.TAMAGOTCHI_NAME);
        String id =  (String) session.get(AttributeConstant.ID);
        String time =  (String) session.get(AttributeConstant.TOTAL_GAME_TIME);
        String codePin =  (String) session.get(AttributeConstant.PIN);
        String img_path =  (String) session.get(AttributeConstant.TAMAGOTCHI_IMG_PATH);

        Session ma_session = new Session(Integer.parseInt(id), Long.parseLong(time),Long.parseLong(dateCreation), 
                                        Long.parseLong(dateDerniereConnexion), Integer.parseInt(codePin), nom_tamagotchi);
        ma_session.setTamagotchi_img_path(img_path);

        return ma_session;
    } 




    public static Integer getFreeSessionID(){
        String filePath = AttributeConstant.FILE;
        
        Integer freeSessionId = null;
        try (FileReader reader = new FileReader(filePath)) {
            // Parse the JSON file
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Get the value associated with the "free_session_id" key
            freeSessionId = Integer.parseInt((String) jsonObject.get(AttributeConstant.FREE_SESSION_ID));
            System.out.println("MY SESSION ID: "+ freeSessionId);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return freeSessionId;

       
    }


}


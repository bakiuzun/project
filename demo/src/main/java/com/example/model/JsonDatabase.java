package com.example.model;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.sql.Time;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.Main;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;



public class JsonDatabase {

    

    public static void create_new_session(Tamagotchi tamagotchi,TypeTamagotchi typeTamagotchi){
        String newBlock = "{ " + tamagotchi.toString() + "}"; 
        readJsonFile(newBlock);

        
        ClassLoader classLoader = Main.class.getClassLoader();
        File file = new File(classLoader.getResource("database/database.json").getFile());




        
        Map<String,String> attr_sess =  tamagotchi.getSession().getAttributes();
        Map<String, String> attr_tama =  tamagotchi.getAttributes();

        Map<String, Map<String,String>> new_sessions = new HashMap<>();
        new_sessions.put("tamagotchi_info", attr_tama);
        new_sessions.put("session_info", attr_sess);
        

        JSONObject sessions_object = new JSONObject();
        sessions_object.put("sessions", new_sessions);


       try {

			FileWriter file_x = new FileWriter("test.json");
			file_x.write(sessions_object.toJSONString());
			file_x.flush();
			file_x.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.print(sessions_object.toJSONString());
               

    }

    public static  void readJsonFile(String newBlock){
        
        
        
        
    }  
    
}

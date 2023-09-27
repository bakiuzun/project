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

        JSONObject employeeDetails = new JSONObject();

        

        Map<String,String> attr =  tamagotchi.getAttributes();

         for (String key   : attr.keySet()) {
            String value = attr.get(key);
            employeeDetails.put(key, value);
       }  
               

    }

    public static  void readJsonFile(String newBlock){
        
        
        
        
    }  
    
}

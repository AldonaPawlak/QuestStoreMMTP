package org.example.config;

import jdk.nashorn.internal.parser.JSONParser;
import java.io.FileReader;

public class JSONreader {

    public void read() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("/Users/User/Desktop/course.json"));
            JSONObject jsonObject = (JSONObject)obj;
            String name = (String)jsonObject.get("Name");
            String course = (String)jsonObject.get("Course");
            JSONArray subjects = (JSONArray)jsonObject.get("Subjects");
            System.out.println("Name: " + name);
            System.out.println("Course: " + course);
            System.out.println("Subjects:");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
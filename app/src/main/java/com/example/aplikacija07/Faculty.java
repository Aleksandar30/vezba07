package com.example.aplikacija07;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class Faculty {
    private String name, acronym, website;

    public Faculty() {
    }

    public Faculty(String name, String acronym, String website) {
        this.name = name;
        this.acronym = acronym;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", acronym='" + acronym + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    public static Faculty parseJSONObject(JSONObject object){
        Faculty fakultet = new Faculty();
        try {
            if (object.has("name")) {
                fakultet.setName(object.getString("name"));
            }
            if (object.has("acronym")){
                fakultet.setAcronym(object.getString("acronym"));
            }
            if (object.has("website")){
                fakultet.setWebsite(object.getString("website"));
            }
        }catch (Exception e){
            e.getMessage();
        }
        return fakultet;
    }

    public static LinkedList<Faculty> parseJSONArray (JSONArray array){
        LinkedList<Faculty> lista = new LinkedList<>();
        try{
            for (int i = 0;i < array.length();i++){
                Faculty faculty = parseJSONObject(array.getJSONObject(i));
                lista.add(faculty);
            }

        }catch (Exception e) {
            e.getMessage();
        }

        return  lista;
    }
}

package com.example.model;

import java.util.List;
import java.util.Map;

public class Staff {

    private String name;
    private int age;
    private String[] position;              //  Array
    private List<String> skills;            //  List
    private Map<String, Integer> salary; //  Map
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String[] getPosition() {
        return position;
    }
    public List<String> getSkills() {
        return skills;
    }
    public Map<String, Integer> getSalary() {
        return salary;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setPosition(String[] position) {
        this.position = position;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    public void setSalary(Map<String, Integer> salary) {
        this.salary = salary;
    }

	// getters , setters, some boring stuff
}
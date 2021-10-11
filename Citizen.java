package com.company;

public class Citizen {
    String name;
    int age;
    String id;
    int doses;
    int dose_day;
    String vaccine;
    public Citizen(String name, int age, String id){
        this.name = name;
        this.age = age;
        this.id = id;
        this.doses=0;
        this.dose_day=0;
    }
}

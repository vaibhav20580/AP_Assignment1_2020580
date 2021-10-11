package com.company;

public class Slot {
    String id;
    int day;
    int qty;
    String vaccine;
    int doses;
    int gap;
    public Slot(String vaccine, String id, int day, int qty,int doses, int gap){
        this.vaccine = vaccine;
        this.id = id;
        this.day = day;
        this.qty = qty;
        this.doses=doses;
        this.gap=gap;
    }

}

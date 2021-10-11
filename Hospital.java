package com.company;

public class Hospital {
    String name;
    int pincode;
    String id;
    Slot[] s = new Slot[100];
    int s_idx;
    public Hospital(String name, int pincode, String id){
        this.name = name;
        this.pincode = pincode;
        this.id = id;
        this.s_idx=0;
    }
}

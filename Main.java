package com.company;
import java.util.Scanner;
public class Main {
    public static void add_vaccine(Vaccine[] v, int v_idx){
        Scanner sc = new Scanner(System.in);
        System.out.println("Vaccine Name: ");
        String name = sc.nextLine();
        System.out.println("Number of Doses: ");
        int doses = sc.nextInt();
        int gap;
        if(doses > 1){
            System.out.println("Gap between Doses: ");
            gap = sc.nextInt();
        }
        else{
            gap = 0;
        }
        v[v_idx] = new Vaccine(name,doses,gap);
        System.out.println("Vaccine Name: " + name + ", Number of Doses:"+doses+ ", Gap between Doses: "+ gap);
    }
    public static void register_hospital(Hospital[] h, int h_idx){
        Scanner sc = new Scanner(System.in);
        System.out.println("Hospital Name: ");
        String name = sc.nextLine();
        System.out.println("Pincode: ");
        int pincode = sc.nextInt();
        String id =  "1" + h_idx;
        int l = id.length();
        for(int i=0; i<6-l; i++){
            id = id + "0";
        }
        h[h_idx] = new Hospital(name, pincode, id);
        System.out.println("Hospital Name:" + name + ", Pincode:" + pincode + ", Unique ID:" + id);
    }
    public static int register_citizen(Citizen[] c, int c_idx){
        Scanner sc = new Scanner(System.in);
        System.out.println("Citizen Name: ");
        String name = sc.nextLine();
        System.out.println("Age: ");
        int age = sc.nextInt();
        System.out.println("Enter unique ID:");
        sc.nextLine();
        String id = sc.nextLine();
        System.out.println("Citizen Name: "+ name+ ", Age: " + age+", Unique ID: "+ id);
        if(age<18){
            System.out.println("Only above 18 are allowed");
            return 1;
        }
        c[c_idx] = new Citizen(name,age,id);
        return 0;
    }
    public static void add_slot(Vaccine[] v, int v_idx, Hospital[] h, int h_idx){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Hospital ID:");
        String id = sc.nextLine();
        System.out.println("Enter number of Slots to be added:");
        int num = sc.nextInt();
        int idx=-1;
        for(int i=0; i<h_idx; i++){
            if(h[i].id.equals(id)){
                idx=i;
            }
        }
        for(int i=0; i<num; i++) {
            System.out.println("Enter Day number:");
            int day = sc.nextInt();
            System.out.println("Enter Quantity:");
            int qty = sc.nextInt();
            System.out.println("Select Vaccine:");
            for (int j = 0; j < v_idx; j++) {
                System.out.println(j + ". " + v[j].name);
            }
            int ix = sc.nextInt();
            h[idx].s[h[idx].s_idx] = new Slot(v[ix].name, id, day, qty,v[ix].doses,v[ix].gap);
            h[idx].s_idx++;
            System.out.println("Slot added by Hospital "+ h[idx].id + " for day: " + day + ", Available Quantity: " + qty + " of Vaccine "+ v[ix].name);
        }
    }
    public static void book_slot(Citizen c[], int c_idx, Hospital h[], int h_idx){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter patient Unique ID:");
        String citizen_id = sc.nextLine();
        int cit = -1;
        for(int i=0; i<c_idx; i++){
            if(citizen_id.equals(c[i].id)){
                cit = i;
            }
        }
        System.out.println("1. Search by Area");
        System.out.println("2. Search by Vaccine");
        System.out.println("3. Exit");
        System.out.println("Enter Option:");
        int op = sc.nextInt();
        if(op==1){
            System.out.println("Enter pincode:");
            int pincode = sc.nextInt();
            sc.nextLine();
            for(int i=0; i<h_idx; i++){
                if(pincode==h[i].pincode){
                    System.out.println(h[i].id + " " + h[i].name);
                }
            }
            System.out.println("Enter Hospital ID:");
            String id = sc.nextLine();
            int idx=-1;
            for(int i=0; i<h_idx; i++){
                if(id.equals(h[i].id)){
                    idx=i;
                }
            }
            int cnt=0;
            for(int i=0; i<h[idx].s_idx; i++){
                if(h[idx].s[i].day<c[cit].dose_day){
                    continue;
                }
                cnt++;
                System.out.println(i+"-> Day: "+h[idx].s[i].day+" Available Qty: "+h[idx].s[i].qty+" Vaccine: "+h[idx].s[i].vaccine);
            }
            if(cnt==0){
                System.out.println("No slots available");
                return;
            }
            System.out.println("Choose slot: ");
            int n = sc.nextInt();
            c[cit].vaccine=h[idx].s[n].vaccine;
            System.out.println(c[cit].name + " vaccinated with "+h[idx].s[n].vaccine);
            c[cit].doses++;
            c[cit].dose_day=h[idx].s[n].day+h[idx].s[n].gap;
            h[idx].s[n].qty--;
            if(h[idx].s[n].qty==0){
                h[idx].s_idx--;
                for(int i=n; i<h[idx].s_idx; i++){
                    h[idx].s[i]=h[idx].s[i+1];
                }
            }
        }
        if(op==2){
            System.out.println("Enter Vaccine:");
            sc.nextLine();
            String vaccine = sc.nextLine();
            for(int i=0; i<h_idx; i++){
                for(int j=0; j<h[i].s_idx; j++){
                    if(h[i].s[j].vaccine.equals(vaccine)){
                        System.out.println(h[i].id + " " + h[i].name);
                        break;
                    }
                }
            }
            c[cit].vaccine=vaccine;
            System.out.println("Enter Hospital ID:");
            String id = sc.nextLine();
            int idx=-1;
            for(int i=0; i<h_idx; i++){
                if(id.equals(h[i].id)){
                    idx=i;
                }
            }
            int cnt=0;
            for(int i=0; i<h[idx].s_idx; i++){
                if(h[idx].s[i].day<c[cit].dose_day || !(h[idx].s[i].vaccine.equals(vaccine))){
                    continue;
                }
                cnt++;
                System.out.println(i+"-> Day: "+h[idx].s[i].day+" Available Qty: "+h[idx].s[i].qty+" Vaccine: "+h[idx].s[i].vaccine);
            }
            if(cnt==0){
                System.out.println("No slots available");
                return;
            }
            System.out.println("Choose slot: ");
            int n = sc.nextInt();
            System.out.println(c[cit].name + " vaccinated with "+h[idx].s[n].vaccine);
            c[cit].doses++;
            c[cit].dose_day=h[idx].s[n].day+h[idx].s[n].gap;
            h[idx].s[n].qty--;
            if(h[idx].s[n].qty==0){
                h[idx].s_idx--;
                for(int i=n; i<h[idx].s_idx; i++){
                    h[idx].s[i]=h[idx].s[i+1];
                }
            }
        }
        if(op==3){
            return;
        }
    }
    public static void check_slot(Hospital[] h, int h_idx){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Hospital ID:");
        String id = sc.nextLine();
        int idx=-1;
        for(int i=0; i<h_idx; i++){
            if(id.equals(h[i].id)){
                idx=i;
            }
        }
        for(int i=0; i<h[idx].s_idx; i++){
            System.out.println("Day: "+h[idx].s[i].day+" Vaccine: "+h[idx].s[i].vaccine+" Available Qty: "+h[idx].s[i].qty);
        }
    }
    public static void check_status(Citizen[] c, int c_idx, Vaccine v[], int v_idx) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter patient ID:");
        String citizen_id = sc.nextLine();
        int cit = -1;
        for (int i = 0; i < c_idx; i++) {
            if (citizen_id.equals(c[i].id)) {
                cit = i;
            }
        }
        int ix = -1;
        int total = -1;
        for (int i = 0; i < v_idx; i++) {
            if (v[i].name.equals(c[cit].vaccine)) {
                total = v[i].doses;
            }
        }
        if (c[cit].doses == 0) {
            System.out.println("REGSITERED");
        } else if (c[cit].doses == total) {
            System.out.println("FULLY VACCINATED");
        } else {
            System.out.println("PARTIALLY VACCINATED");
        }
        if (c[cit].doses > 0) {
            System.out.println("Vaccine Given: " + c[cit].vaccine);
            System.out.println("Number of doses given: " + c[cit].doses);
            if(c[cit].doses!=total) {
                System.out.println("Next dose due date: " + c[cit].dose_day);
            }
        }
    }
    public static void menu_options(){
        System.out.println("1. Add Vaccine");
        System.out.println("2. Register Hospital");
        System.out.println("3. Register Citizen");
        System.out.println("4. Add Slot for Vaccination");
        System.out.println("5. Book Slot for Vaccination");
        System.out.println("6. List all slots for a hospital");
        System.out.println("7. Check Vaccination Status");
        System.out.println("8. Exit");
        System.out.println("---------------------------------");
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Hospital[] h;
        h = new Hospital[100];
        int h_idx = 0;
        Citizen[] c;
        c = new Citizen[100];
        int c_idx = 0;
        Vaccine[] v;
        v = new Vaccine[100];
        int v_idx = 0;
        while(true){
            menu_options();
            int input = sc.nextInt();
            if(input==1){
                add_vaccine(v,v_idx);
                v_idx++;
            }
            if(input==2){
                register_hospital(h,h_idx);
                h_idx++;
            }
            if(input==3){
                int x = register_citizen(c,c_idx);
                if(x==0){
                    c_idx++;
                }
            }
            if(input==4){
                add_slot(v,v_idx,h,h_idx);
            }
            if(input==5){
                book_slot(c,c_idx,h,h_idx);
            }
            if(input==6){
                check_slot(h,h_idx);
            }
            if(input==7){
                check_status(c,c_idx,v,v_idx);
            }
            if(input==8) {
                break;
            }
            System.out.println("---------------------------------");
        }
    }
}

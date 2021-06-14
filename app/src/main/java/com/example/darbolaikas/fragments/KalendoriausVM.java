package com.example.darbolaikas.fragments;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;


public class KalendoriausVM extends ViewModel {
    public ArrayList<String> arrayList = new ArrayList<>();
    public ArrayList<String> arrayList2 = new ArrayList<>();
    public String fName,menName;
    public int km,tsk;

    public void setGrrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }
    public void setGrrayList2(ArrayList<String> arrayList) {
        this.arrayList2 = arrayList;
    }
    public void setfName(String fName) {
        this.fName = fName;
    }
    public void setMenName(String menName) {
        this.menName = menName;
    }
    public void setKm(int km) {
        Log.i("KM: ", String.valueOf(km));
        this.km = km;
    }
    public void setTsk(int tsk) {
        this.tsk = tsk;
    }


    public String getMenName() { return menName; }
    public String getfName() {
        return fName;
    }
    public ArrayList<String> getGrrayList2(){
        return this.arrayList2;
    }
    public ArrayList<String> getGrrayList(){
        return this.arrayList;
    }
    public int getKm() {
        return this.km;
    }

    public int getTsk() {
        return this.tsk;
    }

    public void prideti() {
        arrayList.add("Važiavimas: ");
    }
}
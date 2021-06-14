package com.example.darbolaikas;

import android.os.Environment;
import android.util.Log;
import android.view.View;


import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Fread {
    static File menFldr;
    static File file;
    static String line;

    int laikas, prlaikas,dlaikas,valandos,minutes,darboPbH,darboPbM, sav;

    public interface Listener2 {

    }



    public static void setFName(String fileN){
        file = new File(menFldr, fileN); }
    public static void setMenFldr(String dir){
        menFldr = new File(Environment.getDataDirectory(), "/data/com.example.darbolaikas/logai/" + dir); }

     public static int getDayCount(){
            return Objects.requireNonNull(menFldr.list()).length;
     }



    public static ArrayList<String> getDayF(String diena, String men){

        setMenFldr(men);
        setFName(diena);

        ArrayList<String> duom = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int x = 0;

            while ((line = br.readLine()) != null) {
                duom.add(line);
                Log.i("Line: ", line);
                x++;
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }
        return duom;
    }

    public static String getFName(){
        return file.getName();
    }


}




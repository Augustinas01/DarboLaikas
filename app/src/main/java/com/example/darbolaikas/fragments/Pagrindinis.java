package com.example.darbolaikas.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.nio.file.SimpleFileVisitor;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.time.*;
import java.math.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.darbolaikas.R;
import com.example.darbolaikas.Logeris;

import org.w3c.dom.Text;

public class Pagrindinis extends Fragment  {

    View view;
    Button knopke, knopke2;
    static Date date;
    static TextView darboprd,darbopbg,darbobndr;
    static String darbopbgl;
    static int laikas, prlaikas,dlaikas,valandos,minutes,darboPbH,darboPbM;
    private static Logeris logeris;
    static ClipboardManager clipboard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_pgr, container, false);
        knopke = view.findViewById(R.id.knopke);
        knopke2 = view.findViewById(R.id.knopke2);
//        darbopbg = view.findViewById(R.id.darbopbg);
//        darboprd = view.findViewById(R.id.darboprd);
//        darbobndr = view.findViewById(R.id.bndrlaikas);
        date = new Date();
        logeris = new Logeris();
        prlaikas = 800;
//        darboprd.setText("8:00");
        knopke.setText("Baigiam");
        knopke2.setText("REPORT");
        clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);


        startuolis();
        return view;
    }

    public void startuolis(){
        knopke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoticeDialog();

            }
        });
        knopke2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRaport();

            }
        });

    }
    public static void laikas(LocalTime time){
        LocalDateTime laikrodis = LocalDateTime.now();
        darbopbgl = new SimpleDateFormat("HHmm").format(date);
        laikas = Integer.parseInt(darbopbgl);
        dlaikas = laikas-prlaikas;
        darboPbH = time.getHour();

//        darboPbM = (time.getMinute()>10)?time.getMinute():(Integer.parseInt("0" + time.getMinute()));
        darboPbM = darboMinutes(time.getMinute());
//        darbobndr.setText("Pradirbta:  " + fromatikas(dlaikas));
//        darbopbg.setText(darboPbH + ":" + darboPbM);

    }

    public static String fromatikas(int x){
        minutes = x%100;
        valandos = (x-minutes)/100;
        return (valandos + ":" + minutes);
    }

    public static double savaitela(LocalDate dates){
        return Math.round((double) dates.getDayOfYear()/7);
    }

    public static void medkirtys(LocalDate dates, int km, int tsk, String isv, int nprst,String prlk){
        String men = dates.getMonth().toString();

        int mend = dates.getDayOfMonth();
        logeris.appendLog(String.valueOf(prlaikas),men,mend);
        logeris.appendLog(String.valueOf(darboPbH),men,mend);
        logeris.appendLog(String.valueOf(darboPbM),men,mend);
        logeris.appendLog(String.valueOf(savaitela(dates)),men,mend);
        logeris.appendLog(String.valueOf(km), men, mend);
        logeris.appendLog(String.valueOf(tsk), men, mend);
        logeris.appendLog(String.valueOf(isv), men, mend);
        logeris.appendLog(prlk, men, mend);

        String blk = ( "Išvykau: " + isv.charAt(0) + ":" + isv.substring(1)   +
                "\nGrįžau: " + darboPbH + ":" + darboPbM +
                "\nNuvažiuota: " + km + "km" +
                "\nTaškų: " + tsk +
                "\nNepristatyta: " + nprst +
                "\nPerliukai: " + prlk );
        clipboard.setPrimaryClip(ClipData.newPlainText("report'as", blk));


    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new Dialog_Pgr();
        dialog.show(getChildFragmentManager(), "NoticeDialogFragment");
    }
    public void showRaport(){
        DialogFragment raport = new Dialog_Rprt();
        raport.show(getChildFragmentManager(),"Report Dialog");
    }


    private static int darboMinutes(int x){
        if(0<x && x<=10){ return 10; }
        if(10<x && x<=20){ return 20; }
        if(20<x && x<=30){ return 30; }
        if(30<x && x<=40){ return 40; }
        if(40<x && x<=50){ return 50; }
        if((50<x && x<=60) || x==0){
            darboPbH+=1;
            return 00; }
        return 00;
    }






}

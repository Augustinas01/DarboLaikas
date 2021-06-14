package com.example.darbolaikas;

import android.Manifest;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;


import com.example.darbolaikas.fragments.Dialog_Pgr;
import com.example.darbolaikas.fragments.Dialog_Rprt;
import com.example.darbolaikas.fragments.KalendoriausVM;
import com.example.darbolaikas.fragments.Kalendorius;
import com.example.darbolaikas.fragments.ViewPagerAdapter;
import com.example.darbolaikas.fragments.Pagrindinis;
import com.example.darbolaikas.vp.VP_Kalendorius;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Dialog_Pgr.NoticeDialogListener, Dialog_Rprt.ReportDialogListener {

    private static final Logeris logeris = new Logeris();

    public ViewPager2 viewPager;
    public FragmentStateAdapter adapter = new ViewPagerAdapter(this);
    public ViewPagerAdapter vadapter = new ViewPagerAdapter(this);
    static ClipboardManager clipboard;

    public KalendoriausVM kVM;
    public ArrayList<String> arrayList = new ArrayList<>();
    public ArrayList<String> arrayListmen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestPermfile();

        viewPager =(ViewPager2) findViewById(R.id.pager);
        vadapter.addFragment(new Pagrindinis(), "pgr");
        vadapter.addFragment(new VP_Kalendorius(), "kalen");
        viewPager.setAdapter(adapter);


        kVM = new ViewModelProvider(this).get(KalendoriausVM.class);
        surasymas();
        kVM.setGrrayList(arrayList);

    }



    public void surasymas(){
        File vazSkcVt = new File(Environment.getDataDirectory(),"/data/com.example.darbolaikas/logai");
        if(vazSkcVt.length() != 0) {
            File[] vazSkcFl = vazSkcVt.listFiles();
            for (File file : vazSkcFl) {
                arrayList.add(file.getName());
            }
            //Log.i("surArr: ", String.valueOf(arrayList.size()));
        }

    }

    public void requestPermfile() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, int km, int tsk, String isv, int nprst,String prlk) {
        Log.i("LOL: ", String.valueOf(kVM.getKm()));
        LocalDate dates = LocalDate.now();
        LocalTime time = LocalTime.now();
        //Pagrindinis.laikas(time);
        Pagrindinis.medkirtys(dates,km,tsk,isv, nprst, prlk);

    }
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogThisWeek(DialogFragment dialog) {
        weekReport(true);
    }

    @Override
    public void onDialogLastWeek(DialogFragment dialog) {
        weekReport(false);
    }
    public ArrayList<String> surasymas(String men){
        File vazSkcVt = new File(Environment.getDataDirectory(),"/data/com.example.darbolaikas/logai/" + men);
        if(vazSkcVt.length() != 0) {
            File[] vazSkcFl = vazSkcVt.listFiles();
            for (File file : vazSkcFl) {
                arrayListmen.add(file.getName());
            }
            //Log.i("surArr: ", String.valueOf(arrayList.size()));
        }
        return arrayListmen;

    }

    private void weekReport(boolean thisWeek){
        LocalDate dates = LocalDate.now();
        StringBuilder report = new StringBuilder();
        double svt = (thisWeek)? Pagrindinis.savaitela(dates):(Pagrindinis.savaitela(dates)-1);

        for (String day:surasymas(dates.getMonth().toString())){
            ArrayList<String> dayf = Fread.getDayF(day,dates.getMonth().toString());
            if(Double.parseDouble(dayf.get(3)) == (svt)){
                report.append(dates.getMonthValue())
                        .append("-")
                        .append(Fread.getFName())
                        .append(": ")
                        .append(dayf.get(0).charAt(0))
                        .append(":")
                        .append(dayf.get(0).substring(1))
                        .append("-")
                        .append(dayf.get(1))
                        .append(":")
                        .append(dayf.get(2))
                        .append(" h\n");
            }
        }
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText("report'as", report));

    }

    public static void medkirtys(LocalDate dates, int km, int tsk, String isv, int nprst,String prlk){
        String men = dates.getMonth().toString();
        LocalDateTime prlaikas = LocalDateTime.of(LocalDate.now(),LocalTime.of(prH,prM));

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
}

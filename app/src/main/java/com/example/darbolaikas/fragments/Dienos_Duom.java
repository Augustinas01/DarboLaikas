package com.example.darbolaikas.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.darbolaikas.Fread;
import com.example.darbolaikas.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Dienos_Duom extends Fragment {
    View view;
    String[] tkstvw = {"lks","tsk"};
    public static String[] tekstview = {"laikas","taskai"};
    TextView laikas,taskai,vidgreitis;
    Button rDuom;

    public Dienos_Duom() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KalendoriausDien kln = new KalendoriausDien();
        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                //transaction.addToBackStack(null);
                transaction.replace(R.id.vpkln, kln).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_duom, container, false);
        KalendoriausVM mViewModel = new ViewModelProvider(getActivity()).get(KalendoriausVM.class);
        Log.i("FName: ", mViewModel.getfName());
        surasymas(mViewModel.getfName(), mViewModel.getMenName());
        rDuom = view.findViewById(R.id.rdien);

        return view;

    }


    public void surasymas(String duom, String men){
        Fread fRead = new Fread();
        String h = "";
        int x= 0;
        for (String line:fRead.getDayF(duom, men)){
            Log.i("line: ", line);
            switch (x){
        case 0:
            laikas = view.findViewById(R.id.darbo_pradzia);
            laikas.setText("Darba pradejai: " + line.charAt(0) + ":" + line.substring(1));
            break;
        case 1:
            h = line;
            break;
        case 2:
            laikas = view.findViewById(R.id.darbo_h);
            laikas.setText("Darba pabaigei: " + h +":"+ line);
            break;
        case 3:
           laikas = view.findViewById(R.id.savaite);
            laikas.setText("Savaite: " + line);
            break;
        case 4:
            laikas = view.findViewById(R.id.kilometrai);
            laikas.setText("Kilometrai: " + line +"km");
            break;
        case 5:
            laikas = view.findViewById(R.id.taskai);
            laikas.setText("Taskai: " + line);
            break;
        case 6:
            laikas = view.findViewById(R.id.isvaziavau);
            laikas.setText("Isvaziavau: " + line.charAt(0)+":"+line.substring(1));
            break;
        case 7:
            laikas = view.findViewById(R.id.perliukai);
            laikas.setText("Perliukai: " + line);
            break;


        }
        x++;
    }

    }
}

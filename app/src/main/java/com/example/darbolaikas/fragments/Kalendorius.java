package com.example.darbolaikas.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.darbolaikas.R;

import java.io.File;
import java.util.ArrayList;

public class Kalendorius extends Fragment {

    View view;
    String pspstMen;
    KalendoriausDien dduom = new KalendoriausDien();
    public ArrayList<String> arrayList = new ArrayList<>();

    public Kalendorius(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frg_kln, container, false);
        final ListView list =(ListView) view.findViewById(R.id.list);
        KalendoriausVM mViewModel = new ViewModelProvider(getActivity()).get(KalendoriausVM.class);

        //surasymas();

        ArrayAdapter<String> arrayAdapter = new android.widget.ArrayAdapter<String>(getContext(),
                R.layout.men_lang, R.id.men, mViewModel.getGrrayList());




        list.setAdapter(arrayAdapter);
        list.setDivider(new ColorDrawable(Color.parseColor("#000000")));
        list.setDividerHeight(4);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pspstMen = (String) list.getItemAtPosition(position);
                mViewModel.setMenName(pspstMen);
                surasymas(pspstMen);
                Log.i("pspstVaz: ",pspstMen);
                mViewModel.setGrrayList2(arrayList);
                //Log.i("pspstVaz: ",pspstVaz);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.vpkln, dduom).commit();

            }
        });
        return view;
    }

    public void surasymas(String pspstMen){
        File vazSkcVt = new File(Environment.getDataDirectory(),"/data/com.example.darbolaikas/logai/" +pspstMen);
        if(vazSkcVt.length() != 0) {
            File[] vazSkcFl = vazSkcVt.listFiles();
            for (File file : vazSkcFl) {
                arrayList.add(file.getName());
            }
            //Log.i("surArr: ", String.valueOf(arrayList.size()));
        }

    }
}

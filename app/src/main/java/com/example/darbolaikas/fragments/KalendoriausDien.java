package com.example.darbolaikas.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.darbolaikas.R;

public class KalendoriausDien extends Fragment {

    View view;
    String pspstMen;

    //Reikalingas tuscias konstruktorius
    public KalendoriausDien(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Kalendorius kln = new Kalendorius();
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                //transaction.addToBackStack(null);
                transaction.replace(R.id.vpkln, kln).commit();
                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frg_kln_dien, container, false);
        final ListView list =(ListView) view.findViewById(R.id.list_men);
        KalendoriausVM mViewModel = new ViewModelProvider(getActivity()).get(KalendoriausVM.class);

        //surasymas();

        ArrayAdapter<String> arrayAdapter = new android.widget.ArrayAdapter<String>(getContext(),
                R.layout.men_lang, R.id.men, mViewModel.getGrrayList2());




        list.setAdapter(arrayAdapter);
        list.setDivider(new ColorDrawable(Color.parseColor("#000000")));
        list.setDividerHeight(4);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pspstMen = (String) list.getItemAtPosition(position);
                //Log.i("pspstVaz: ",pspstVaz);
                Dienos_Duom dduom = new Dienos_Duom();
                mViewModel.setfName(pspstMen);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.vpkln, dduom).commit();

            }
        });
        return view;
    }
}

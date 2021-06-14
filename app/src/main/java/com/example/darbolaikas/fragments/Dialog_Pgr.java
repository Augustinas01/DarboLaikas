package com.example.darbolaikas.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.darbolaikas.R;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

public class Dialog_Pgr extends DialogFragment {

    KalendoriausVM kVM = new KalendoriausVM();

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, int km, int tsk, String isv, int nprst, String prlk);
        public void onDialogNegativeClick(DialogFragment dialog);
    }



    // Use this instance of the interface to deliver action events
    NoticeDialogListener listener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement NoticeDialogListener");
        }
    }




    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        kVM = new ViewModelProvider(this).get(KalendoriausVM.class);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.dial_frg_pgr,null);
        builder.setView(v);
        EditText kilometrai = (EditText) v.findViewById(R.id.km);
        EditText taskai = (EditText) v.findViewById(R.id.tsk);
        EditText isvykau = (EditText) v.findViewById(R.id.isv);
        EditText nepristaciau = (EditText) v.findViewById(R.id.nprst);
        EditText perliukai = (EditText) v.findViewById(R.id.prl);
        nepristaciau.setText("0");
        perliukai.setText("Nebuvo ;(");

                // Add action buttons
           builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Log.i("KM: ", kilometrai.getText().toString());

                    listener.onDialogPositiveClick(Dialog_Pgr.this,
                            Integer.parseInt(kilometrai.getText().toString()),
                            Integer.parseInt(taskai.getText().toString()),
                            isvykau.getText().toString(),
                            Integer.parseInt(nepristaciau.getText().toString()),
                            perliukai.getText().toString());
                }
            });
//            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                    listener.onDialogNegativeClick(Dialog_Pgr.this);
//
//                }
//            });

        return builder.create();
    }




}

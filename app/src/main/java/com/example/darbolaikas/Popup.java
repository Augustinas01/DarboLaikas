package com.example.darbolaikas;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Popup {
    public static void pop(View view) {/**






        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                MainActivity.ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup_welcome, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.setElevation(20);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        /**popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });*----------------
        Button prdttrs;
        final EditText trspv = popupView.findViewById(R.id.trspav);

        prdttrs = popupView.findViewById(R.id.trsknpk);
        prdttrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pav = trspv.getText().toString();
                Logeris.issaugoti(pav);

                popupWindow.dismiss();

            }
        });


    **/}



}
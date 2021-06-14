package com.example.darbolaikas.fragments;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

   private static List<Fragment> fragmentList = new ArrayList<>();
   private static List<String> fragmentListTitles = new ArrayList<>();




    public ViewPagerAdapter(FragmentActivity fa) {
        super(fa);
        //addFragment();

    }

    @Override
    public Fragment createFragment(int position) {
        Log.i("Pos: ", String.valueOf(position));
        return fragmentList.get(position);

        //return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
        //Log.i("LOL: ", String.valueOf(fragmentListTitles.size()));
        //return fragmentListTitles.size();
    }

    public static String getTitle(int pos){
        return fragmentListTitles.get(pos);

    }



    public  void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        fragmentListTitles.add(title);



    }
    public  void removeFragment(int pos, Fragment fragment){
        fragmentList.remove(pos);
        fragmentList.add(2, fragment);



    }


}

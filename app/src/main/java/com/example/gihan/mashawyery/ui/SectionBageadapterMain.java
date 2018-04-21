package com.example.gihan.mashawyery.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.gihan.mashawyery.fragment.MainFragmentOne;
import com.example.gihan.mashawyery.fragment.MainFragmentTwo;
import com.example.gihan.mashawyery.fragment.MainfragmentThree;

/**
 * Created by Gihan on 4/20/2018.
 */

public class SectionBageadapterMain extends FragmentPagerAdapter {

    public SectionBageadapterMain(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MainFragmentOne tripsWillGoFragment = new MainFragmentOne();
                return tripsWillGoFragment;
            case 1:
                MainFragmentTwo tripsPreviousGo = new MainFragmentTwo();
                return tripsPreviousGo;
            case 2:
                MainfragmentThree cancelTrips = new MainfragmentThree();
                return cancelTrips;
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int postion) {

        switch (postion) {
            case 0:
                return "ملاكي";
            case 1:
                return "اجره";
            case 2:
                return "نقل";
            default:
                return null;
        }
    }
}

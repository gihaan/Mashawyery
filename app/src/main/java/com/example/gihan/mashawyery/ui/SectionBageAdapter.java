package com.example.gihan.mashawyery.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.gihan.mashawyery.fragment.CancelTrips;
import com.example.gihan.mashawyery.fragment.TripsPreviousGo;
import com.example.gihan.mashawyery.fragment.TripsWillGoFragment;

/**
 * Created by Gihan on 7/16/2017.
 */

class SectionBageAdapter extends FragmentPagerAdapter {


    public SectionBageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TripsWillGoFragment tripsWillGoFragment = new TripsWillGoFragment();
                return tripsWillGoFragment;
            case 1:
                TripsPreviousGo tripsPreviousGo = new TripsPreviousGo();
                return tripsPreviousGo;
            case 2:
                CancelTrips cancelTrips = new CancelTrips();
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
                return "المشاوير القادمه";
            case 1:
                return "المشاوير السايقه";
            case 2:
                return "المشاوير الملغيه";
            default:
                return null;
        }
    }
}

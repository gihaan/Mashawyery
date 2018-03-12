package com.example.gihan.mashawyery.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.gihan.mashawyery.R;

public class TripsActivity extends AppCompatActivity {



    private ViewPager mViewPager;

    SectionBageAdapter mSectionBageAdapter;
    private TabLayout mTablLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);



        mViewPager=(ViewPager)findViewById(R.id.main_tab_pager);
        mSectionBageAdapter=new SectionBageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionBageAdapter);

        mTablLayout=(TabLayout)findViewById(R.id.main_tabs);
        mTablLayout.setupWithViewPager(mViewPager);

    }
}

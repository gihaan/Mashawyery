package com.example.gihan.mashawyery.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.gihan.mashawyery.R;

public class CashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);


        //TO PUT FRAGMENT ON ACTIVITY
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.cash_activity, new com.example.gihan.mashawyery.fragment.CashFragment())
                    .commit();
        }


        Toolbar mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.cash_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("الدفع نقديه  ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


}

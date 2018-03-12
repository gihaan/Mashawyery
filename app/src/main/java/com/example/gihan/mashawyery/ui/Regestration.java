
package com.example.gihan.mashawyery.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import com.example.gihan.mashawyery.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Regestration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);

        //TO PUT FRAGMENT ON ACTIVITY
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.regesteration_activity, new com.example.gihan.mashawyery.fragment.RegistrationFragment())
                    .commit();
        }


        Toolbar mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.regester_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.tool_bar_regeser));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}

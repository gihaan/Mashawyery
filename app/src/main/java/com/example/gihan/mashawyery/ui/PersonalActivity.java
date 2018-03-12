package com.example.gihan.mashawyery.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.gihan.mashawyery.R;

public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);


        //TO PUT FRAGMENT ON ACTIVITY
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.personal_activity, new com.example.gihan.mashawyery.fragment.PersonalFragment())
                    .commit();
        }


        Toolbar mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.personal_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("الصفحه الشخصيه ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}

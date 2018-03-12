package com.example.gihan.mashawyery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gihan.mashawyery.R;
import com.example.gihan.mashawyery.ui.Login;
import com.example.gihan.mashawyery.ui.Regestration;

public class First extends Fragment {

    Button btnLogin;
    Button btnRegister;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        btnLogin = (Button) v.findViewById(R.id.btn_first_fragment_sign);
        btnRegister = (Button) v.findViewById(R.id.btn_first_fragment_regester);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Login.class);
                startActivity(i);
//                Toast.makeText(getActivity(),"Gggggggggg",Toast.LENGTH_SHORT).show();

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Regestration.class);
                startActivity(i);

            }
        });


        return v;
    }


}

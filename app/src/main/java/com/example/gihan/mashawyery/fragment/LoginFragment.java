package com.example.gihan.mashawyery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gihan.mashawyery.R;
import com.example.gihan.mashawyery.ui.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    EditText userName;
    EditText password;
    Button login;
    FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        userName = (EditText) v.findViewById(R.id.etlogin_username);
        password = (EditText) v.findViewById(R.id.et_login_password);
        login = v.findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });

        return v;
    }

    void login() {


        //Check Validation
        if (TextUtils.isEmpty(userName.getText().toString())) {
            Toast.makeText(getContext(), "user name not found ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(getContext(), "password not found ", Toast.LENGTH_SHORT).show();

            return;
        }


        //SIGN IN
        mAuth.signInWithEmailAndPassword(userName.getText().toString(), password.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getContext(), "sucessful ", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getContext(), MainActivity.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });


    }





}
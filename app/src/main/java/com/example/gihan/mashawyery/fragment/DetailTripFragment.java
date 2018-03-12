package com.example.gihan.mashawyery.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gihan.mashawyery.R;
import com.example.gihan.mashawyery.model.trip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailTripFragment extends Fragment {

    TextView tvDistnation, tvDay, tvTime, tvDistance, tvPrice, tvWaitTime, tvCost, tvKindCar,tvDriverName;

    private DatabaseReference mUserDatabase;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail_trip, container, false);

        trip object = (trip) getActivity().getIntent().getSerializableExtra("trip");

        tvDistnation = (TextView) v.findViewById(R.id.detail_trip_tv_distnation);
        tvDay = (TextView) v.findViewById(R.id.detail_trip_tv_day);
        tvTime = (TextView) v.findViewById(R.id.detail_trip_tv_time);
        tvDistance = (TextView) v.findViewById(R.id.detail_trip_tv_distance);
        tvPrice = (TextView) v.findViewById(R.id.detail_trip_tv_price);
        tvWaitTime = (TextView) v.findViewById(R.id.detail_trip_tv_wait_hour);
        tvCost = (TextView) v.findViewById(R.id.detail_trip_tv_cost);
        tvKindCar = (TextView) v.findViewById(R.id.detail_trip_tv_kind_car);
        tvDriverName = (TextView) v.findViewById(R.id.detail_trip_tv_driver_name);



        //    private String from, to, driver, user, day, date, time, distance, price, waitTime, cost, kindOFTrip;
        String from = object.getFrom();
        String to = object.getTo();
        final String driver = object.getDriver();
        String user = object.getUser();
        String day = object.getDay();
        String date = object.getDate();
        String time = object.getTime();
        String distanc = object.getDistance();
        String price = object.getPrice();
        String waitTime = object.getWaitTime();
        String cost = object.getCost();
        String kind = object.getKindCar();

        tvDistnation.setText("من " + from + "الي " + to);
        tvTime.setText(time);
        tvDay.setText(day + "   " + date);
        tvPrice.setText(price);
        tvDistance.setText(distanc);
        tvWaitTime.setText(waitTime);
        tvCost.setText(cost);
        tvKindCar.setText(kind);

        mAuth = FirebaseAuth.getInstance();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("drivers");
        mUserDatabase.keepSynced(true);


        mUserDatabase.child(driver).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String driverName = dataSnapshot.child("name").getValue().toString();
                tvDriverName.setText(driverName);

                // final String image = dataSnapshot.child("to").getValue().toString();




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;
    }

}

package com.example.gihan.mashawyery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gihan.mashawyery.R;
import com.example.gihan.mashawyery.model.trip;
import com.example.gihan.mashawyery.ui.DetailTripActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;



public class TripsWillGoFragment extends Fragment {

    private RecyclerView mTripList;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseReference mTripsDatabase;
    private DatabaseReference mUserDatabase;
    private FirebaseAuth mAuth;
    private String current_user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_trips_will_go, container, false);

        mTripList = (RecyclerView) v.findViewById(R.id.will_go_trip_list);
        mLayoutManager = new LinearLayoutManager(v.getContext());

        mTripList.setHasFixedSize(true);
        mTripList.setLayoutManager(mLayoutManager);

        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();
        mTripsDatabase = FirebaseDatabase.getInstance().getReference().child("trips").child(current_user_id);
        mTripsDatabase.keepSynced(true);
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        mUserDatabase.keepSynced(true);

        return v;

    }

    public void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<trip, TripsWillGoFragment.TripViewHolder> adapter = new FirebaseRecyclerAdapter<trip, TripsWillGoFragment.TripViewHolder>(
                trip.class,
                R.layout.row_trip,
                TripsWillGoFragment.TripViewHolder.class,
                mTripsDatabase
        ) {
            @Override
            protected void populateViewHolder(final TripsWillGoFragment.TripViewHolder tripViewHolder, final trip model, final int position) {



                tripViewHolder.setDistnation("من    "+model.getFrom() +"  الي   "+model.getTo());
                tripViewHolder.setDate(model.getDate());

                tripViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent detailIntent=new Intent(getActivity(),DetailTripActivity.class);
                        detailIntent.putExtra("trip", (Serializable) model);
                        startActivity(detailIntent);



                    }
                });




            }
        };


        mTripList.setAdapter(adapter);
    }

    public static class TripViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public TripViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDistnation(String place) {
            TextView destination = (TextView) mView.findViewById(R.id.row_trip_tv_distnation);
            destination.setText(place);
        }

        public void setDate(String date) {
            TextView tvDate = (TextView) mView.findViewById(R.id.row_trip_tv_date);
            tvDate.setText(date);
        }


    }


}

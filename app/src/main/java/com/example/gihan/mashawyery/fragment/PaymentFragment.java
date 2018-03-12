package com.example.gihan.mashawyery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gihan.mashawyery.R;
import com.example.gihan.mashawyery.ui.CashActivity;
import com.example.gihan.mashawyery.ui.CreditActivity;


public class PaymentFragment extends Fragment {

    TextView cash, credit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_payment, container, false);

        cash = (TextView) v.findViewById(R.id.payment_fragment_tv_cash);
        credit = (TextView) v.findViewById(R.id.payment_fragment_tv_credit);


        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cashIntent = new Intent(getActivity(), CashActivity.class);
                startActivity(cashIntent);

            }
        });

        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creditIntent = new Intent(getActivity(), CreditActivity.class);
                startActivity(creditIntent);
            }
        });

        return v;
    }

}

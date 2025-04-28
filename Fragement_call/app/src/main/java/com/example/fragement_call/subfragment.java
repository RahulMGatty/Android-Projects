package com.example.fragement_call;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class subfragment extends Fragment {

    public subfragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();

       // if bundle is null( experimental)
//        if (bundle != null) {
//            double first = bundle.getDouble("FNO", 0); // Default to 0 if missing
//            double second = bundle.getDouble("SNO", 0);
//            double sum = first - second;
//
//            View v = inflater.inflate(R.layout.fragment_subfragment, container, false);
//            TextView result = v.findViewById(R.id.txtaddresult);
//            result.setText(String.valueOf(sum)); // Safer conversion
//            return v;
//        }
        //end of experimental code
        double first = bundle.getDouble("FNO");
        double second = bundle.getDouble("SNO");
        double sum = first - second;

        View v = inflater.inflate(R.layout.fragment_subfragment,
                container,false);
        TextView result = v.findViewById(R.id.txtaddresult);
        result.setText(sum+"");
        return v;
    }
}
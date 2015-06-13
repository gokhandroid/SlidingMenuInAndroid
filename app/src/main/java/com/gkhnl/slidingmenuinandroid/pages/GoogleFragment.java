package com.gkhnl.slidingmenuinandroid.pages;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gkhnl.slidingmenuinandroid.MainActivity;
import com.gkhnl.slidingmenuinandroid.R;


public class GoogleFragment extends Fragment {

    Activity titleChange;

    public GoogleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        titleChange.setTitle("Google Plus");
        return inflater.inflate(R.layout.fragment_google, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        titleChange = (MainActivity) activity;
    }

}

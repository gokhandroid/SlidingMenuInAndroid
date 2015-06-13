package com.gkhnl.slidingmenuinandroid.pages;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gkhnl.slidingmenuinandroid.MainActivity;
import com.gkhnl.slidingmenuinandroid.R;


public class InstagramFragment extends Fragment {

    Activity titleChange;

    public InstagramFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        titleChange.setTitle("Instagram");
        return inflater.inflate(R.layout.fragment_instagram, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        titleChange = (MainActivity) activity;
    }
}

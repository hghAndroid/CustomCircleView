package com.itedu.mycustomview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itedu.mycustomview.R;

public class YouFragment extends Fragment {

    public YouFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static YouFragment newInstance(String param1, String param2) {
        YouFragment fragment = new YouFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_you, container, false);
    }

}

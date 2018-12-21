package com.itedu.mycustomview.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.itedu.mycustomview.R;
import com.itedu.mycustomview.weight.CustomView1;


public class MyFragment extends Fragment {

    public MyFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
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
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        CustomView1 view1 = view.findViewById(R.id.view1);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "nihao", Toast.LENGTH_LONG).show();
            }
        });

//        view1.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.translate));
        ObjectAnimator.ofFloat(view1,"translationX",0,300).setDuration(1000).start();
        ObjectAnimator.ofFloat(view1,"translationY",0,300).setDuration(1000).start();
    }
}

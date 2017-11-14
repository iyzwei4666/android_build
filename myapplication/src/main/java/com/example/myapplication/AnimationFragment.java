package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 设置页面
 */
public class AnimationFragment extends Fragment {

    private static final String TAG = AnimationFragment.class.getSimpleName();
    private View view;




    public AnimationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_setting, container, false);


        }
        return view ;
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


    }





}

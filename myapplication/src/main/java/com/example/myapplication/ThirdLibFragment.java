package com.example.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 会话列表界面
 */
public class ThirdLibFragment extends Fragment   {

    private final String TAG = "ThirdLibFragment";

    private View view;



    public ThirdLibFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_conversation, container, false);

        }

        return view;

    }

    @Override
    public void onResume(){
        super.onResume();


    }






}

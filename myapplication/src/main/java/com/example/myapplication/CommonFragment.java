package com.example.myapplication;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Observable;
import java.util.Observer;

/**
 * 联系人界面
 */
public class CommonFragment extends Fragment implements  View.OnClickListener, Observer {

    private View view;

    private String[] datas = new String[]{"greenDao", "OKHttp", "OKHttpUtils", "xUtils3", "NativeJson", "Gson", "FastJson", "Afinal", "Volley",
            "EvenBus", "ButterKnife", "ImageLoader", "Picasso", "RecycleView", "Glide", "Fresco", "UniversalVideoView",
            "JieCaoPlayer", "Banner", "CountdownView", "OpenDanmaku", "TableLayout", "greenDao", "RxJava", "jcvideoplayer", "pulltorefresh", "Expandablelistview", "....."};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null){
            view = inflater.inflate(R.layout.fragment_contact, container, false);

        }
        return view;
    }


    @Override
    public void onResume(){
        super.onResume();

    }



    @Override
    public void onClick(View view) {


    }


    @Override
    public void update(Observable o, Object arg) {

    }
}

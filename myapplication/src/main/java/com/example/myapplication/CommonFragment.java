package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frame.greendao.NoteActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 联系人界面
 */
public class CommonFragment extends Fragment implements  View.OnClickListener  {

    private View view;

    private List<ActivityBean> datas = new ArrayList<ActivityBean>(){{
        add(new ActivityBean("greendao","com.frame.greendao.NoteActivity"));
    }
    };
    private FrameAdapter notesAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null){
            view = inflater.inflate(R.layout.fragment_commonframe, container, false);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewNotes);
            //noinspection ConstantConditions
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            notesAdapter = new FrameAdapter(noteClickListener);
            notesAdapter.setNotes(datas);
            recyclerView.setAdapter(notesAdapter);
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
    FrameAdapter.ItemClickListener noteClickListener = new FrameAdapter.ItemClickListener() {
        @Override
        public void onItemClick(int position) {
        Intent intent = new Intent();
        intent.setClassName(getActivity() , datas.get(position).path);
        startActivity(intent);
        }
    };

}

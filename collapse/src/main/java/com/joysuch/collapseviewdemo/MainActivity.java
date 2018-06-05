package com.joysuch.collapseviewdemo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private RelativeLayout design_bottom_sheet_bar;
    private RelativeLayout design_bottom_sheet;
    private boolean setBottomSheetHeight = false ;
    private RecyclerView mRecyclerView;
    private TextView bottom_sheet_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 协调布局
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        // 伸展后显示类似 ActionBar
        design_bottom_sheet_bar = (RelativeLayout) findViewById(R.id.design_bottom_sheet_bar);
        // 折叠后显示的类似handle
        design_bottom_sheet = (RelativeLayout) findViewById(R.id.design_bottom_sheet);
        bottom_sheet_tv = (TextView) findViewById(R.id.bottom_sheet_tv);
        // RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.bottom_sheet_rv);
        initRecyclerView();

        final BottomSheetBehavior behavior = BottomSheetBehavior.from(design_bottom_sheet);
        // 默认让其展开状态，此时design_bottom_sheet的高度是总布局高度的一半，正好显示在中间。
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottom_sheet_tv.setVisibility(View.GONE);
        //点击上方的锚定条---折叠
        design_bottom_sheet_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            //BottomSheet状态改变时的回调
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // collapsed.
                if(newState!=BottomSheetBehavior.STATE_COLLAPSED && bottom_sheet_tv.getVisibility()==View.VISIBLE){
                    // 没有折叠且bottom_sheet_tv可见的状态下-------即滑动状态
                    bottom_sheet_tv.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                }else if(newState==BottomSheetBehavior.STATE_COLLAPSED&& bottom_sheet_tv.getVisibility()==View.GONE){
                    // 折叠状态下
                    bottom_sheet_tv.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }
            }

            //BottomSheet滑动时的回调
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                if(bottomSheet.getTop() > coordinatorLayout.getHeight() /2){
                    CoordinatorLayout.LayoutParams params =
                            (CoordinatorLayout.LayoutParams) design_bottom_sheet.getLayoutParams();
                    params.height = coordinatorLayout.getHeight() / 2;
                    design_bottom_sheet.setLayoutParams(params);
                }else {
                    CoordinatorLayout.LayoutParams params =
                            (CoordinatorLayout.LayoutParams) design_bottom_sheet.getLayoutParams();
                    params.height = coordinatorLayout.getHeight() - design_bottom_sheet_bar.getHeight();
                    design_bottom_sheet.setLayoutParams(params);
                }

                if(bottomSheet.getTop()<2*design_bottom_sheet_bar.getHeight()){
                    design_bottom_sheet_bar.setVisibility(View.VISIBLE);
                    design_bottom_sheet_bar.setAlpha(slideOffset);
                    design_bottom_sheet_bar.setTranslationY(bottomSheet.getTop()-2*design_bottom_sheet_bar.getHeight());
                }
                else{
                    design_bottom_sheet_bar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void initRecyclerView() {
        List<String> getDummyDatas = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            getDummyDatas.add("it is you !" + i) ;
        }
        //创建默认的线性LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置增加或删除条目的动画
        mRecyclerView.setItemAnimator( new DefaultItemAnimator());
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        MyAdapter mAdapter = new MyAdapter(getDummyDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        private final List<String> datas;

        public MyAdapter(List<String> data){
            this.datas = data ;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.test_list_item, null);

            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView tv;
            public ViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(android.R.id.text1);
            }

        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //修改design_bottom_sheet的高度 让其显示总布局一半的高度
        if(!setBottomSheetHeight){
            CoordinatorLayout.LayoutParams params =
                    (CoordinatorLayout.LayoutParams) design_bottom_sheet.getLayoutParams();
            params.height = coordinatorLayout.getHeight() / 2;
            design_bottom_sheet.setLayoutParams(params);
            setBottomSheetHeight = true ;
        }
    }
}

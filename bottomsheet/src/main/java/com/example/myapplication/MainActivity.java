package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.behavior.AnchorBottomSheetBehavior;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.topLayout)
    RelativeLayout topLayout;
    @BindView(R.id.bottom_sheet)
    NestedScrollView bottomSheet;
    @BindView(R.id.bottom_sheet_menu)
    LinearLayout bottomSheetMenu;
    @BindView(R.id.bottom_sheet_menu_detail_map)
    TextView bottomSheetMenuDetailMap;
    @BindView(R.id.bottom_sheet_menu_navi)
    TextView bottomSheetMenuNavi;
    @BindView(R.id.bottom_sheet_menu_internal)
    TextView bottomSheetMenuInternal;
    @BindView(R.id.bottom_sheet_menu_production_ledger)
    TextView bottomSheetMenuProductionLedger;
    @BindView(R.id.top_bar)
    RelativeLayout topBar;
    private AnchorBottomSheetBehavior mBehavior;
    private int mHeight = 150;
    private float mOffset = 0.0f;
    private int mSheetHeight = 120;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetMenu.getVisibility() == View.VISIBLE) {
                    bottomSheet.setVisibility(View.GONE);
                    bottomSheetMenu.setVisibility(View.GONE);
                } else {
                    bottomSheet.setVisibility(View.VISIBLE);
                    bottomSheetMenu.setVisibility(View.VISIBLE);

                }
                mBehavior.setState(AnchorBottomSheetBehavior.STATE_COLLAPSED);

            }
        });
        bottomSheetMenuDetailMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBehavior.getState() == AnchorBottomSheetBehavior.STATE_COLLAPSED) {
                    mBehavior.setState(AnchorBottomSheetBehavior.STATE_ANCHOR_POINT);
                } else {
                    mBehavior.setState(AnchorBottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
        mBehavior = AnchorBottomSheetBehavior.from(bottomSheet);
        int height = ScreenUtil.getScreenHeight(MainActivity.this) / 3;
        mBehavior.setAnchorPoint(height);
        mBehavior.addBottomSheetCallback(new AnchorBottomSheetBehavior.BottomSheetCallback() {
            private int oldState = 0;

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState) {
                    case AnchorBottomSheetBehavior.STATE_COLLAPSED:
                        Log.d("bottomsheet-", "STATE_COLLAPSED");
                        bottomSheetMenuDetailMap.setText("详情");
                        oldState = newState;
                        break;
                    case AnchorBottomSheetBehavior.STATE_DRAGGING:
                        Log.d("bottomsheet-", "STATE_DRAGGING");
                        if (oldState == AnchorBottomSheetBehavior.STATE_COLLAPSED)
                            bottomSheetMenuDetailMap.setText("地图");
                        break;
                    case AnchorBottomSheetBehavior.STATE_EXPANDED:
                        Log.d("bottomsheet-", "STATE_EXPANDED");
                        oldState = newState;
                        break;
                    case AnchorBottomSheetBehavior.STATE_ANCHOR_POINT:
                        Log.d("bottomsheet-", "STATE_ANCHOR_POINT");
                        bottomSheetMenuDetailMap.setText("地图");
                        oldState = newState;
                        break;
                    case AnchorBottomSheetBehavior.STATE_HIDDEN:
                        Log.d("bottomsheet-", "STATE_HIDDEN");
                        bottomSheetMenuDetailMap.setText("详情");
                        break;
                    default:
                        Log.d("bottomsheet-", "STATE_SETTLING");

                        break;
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d("bottomsheet-", "slideOffset:"+slideOffset);
                mOffset = slideOffset;
                topLayout.animate().translationY(-300 * (  slideOffset ));
            }
        });
        mBehavior.setState(AnchorBottomSheetBehavior.STATE_COLLAPSED);
    }
}

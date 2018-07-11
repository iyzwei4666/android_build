package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.behavior.AnchorBottomSheetBehavior;
import com.yinglan.scrolllayout.ScrollLayout;

import java.util.ArrayList;

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
    @BindView(R.id.layout_bottomsheet_shrank)
    LinearLayout layoutBottomsheetShrank;
    @BindView(R.id.layout_bottomsheet_opened)
    LinearLayout layoutBottomsheetOpened;
    @BindView(R.id.layout_bottomsheet_picture)
    RelativeLayout layoutBottomsheetPicture;
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

        initGirlUrl();


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


                if (newState != AnchorBottomSheetBehavior.STATE_COLLAPSED && layoutBottomsheetShrank.getVisibility() == View.VISIBLE) {
                    // 没有折叠且bottom_sheet_tv可见的状态下-------即滑动状态
                    layoutBottomsheetShrank.setVisibility(View.GONE);
                    layoutBottomsheetOpened.setVisibility(View.VISIBLE);
                } else if (newState == AnchorBottomSheetBehavior.STATE_COLLAPSED && layoutBottomsheetShrank.getVisibility() == View.GONE) {
                    // 折叠状态下
                    layoutBottomsheetShrank.setVisibility(View.VISIBLE);
                    layoutBottomsheetOpened.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d("bottomsheet-", "slideOffset:" + slideOffset);
                if (slideOffset > 0.6) {
                    topBar.setVisibility(View.VISIBLE);
                } else {
                    topBar.setVisibility(View.GONE);
                }
                mOffset = slideOffset;
                topLayout.animate().translationY(-300 * (slideOffset));

                if(bottomSheet.getTop() < 2 * layoutBottomsheetPicture.getHeight()){
                    layoutBottomsheetPicture.setVisibility(View.VISIBLE);
                    layoutBottomsheetPicture.setAlpha(slideOffset);
                    layoutBottomsheetPicture.setTranslationY(bottomSheet.getTop()-2*layoutBottomsheetPicture.getHeight());
                } else{
                    layoutBottomsheetPicture.setVisibility(View.INVISIBLE);
                }
            }
        });
        mBehavior.setState(AnchorBottomSheetBehavior.STATE_COLLAPSED);
    }



    private ScrollLayout mScrollLayout;
    private ArrayList<Address> mAllAddressList;
    private TextView mGirlDesText;
    private Toolbar toolbar;

    private MarkerAdapter.OnClickItemListenerImpl mOnClickItemListener = new MarkerAdapter.OnClickItemListenerImpl() {
        @Override
        public void onClickItem(View item, int position) {
            if (mScrollLayout.getCurrentStatus() == ScrollLayout.Status.OPENED) {
//                mScrollLayout.scrollToClose();
            }
        }
    };

    private ScrollLayout.OnScrollChangedListener mOnScrollChangedListener = new ScrollLayout.OnScrollChangedListener() {
        @Override
        public void onScrollProgressChanged(float currentProgress) {
            if(currentProgress >= 0) {
                float precent = 255 * currentProgress;
                if (precent > 255) {
                    precent = 255;
                } else if (precent < 0) {
                    precent = 0;
                }
                mScrollLayout.getBackground().setAlpha(255 - (int) precent);
                toolbar.getBackground().setAlpha(255 - (int) precent);
            }
        }

        @Override
        public void onScrollFinished(ScrollLayout.Status currentStatus) {
            if (currentStatus.equals(ScrollLayout.Status.EXIT)) {

            }
        }

        @Override
        public void onChildScroll(int top) {
        }
    };

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mGirlDesText.setText(mAllAddressList.get(position).getDesContent());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };





    private void initGirlUrl() {
        mAllAddressList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Address address = new Address();
            address.setImageUrl(Constant.ImageUrl[i]);
            address.setDesContent(Constant.DesContent[i]+Constant.DesContent[i]+Constant.DesContent[i]+Constant.DesContent[i]+Constant.DesContent[i]+Constant.DesContent[i]+Constant.DesContent[i]+Constant.DesContent[i]+Constant.DesContent[i]);
            mAllAddressList.add(address);
        }
    }
}

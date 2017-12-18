package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.events.MessageEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Tab页主界面
 */
public class HomeActivity extends BaseActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private LayoutInflater layoutInflater;
    private FragmentTabHost mTabHost;
    private final Class fragmentArray[] = {  CommonFragment.class, ThirdLibFragment.class ,CustomVFragment.class, AnimationFragment.class};
    private int mTitleArray[] = {R.string.home_frame , R.string.home_libs, R.string.home_view , R.string.home_anim};
    private int mImageViewArray[] = {R.drawable.tab_conversation, R.drawable.tab_contact, R.drawable.tab_setting ,R.drawable.tab_setting};
    private String mTextviewArray[] = {"frame", "libs", "view" ,"anim"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();


    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(MessageEvent event) {
        System.out.print("threadMode = ThreadMode.BACKGROUND)");
        /* Do something */

    };

    private void initView() {
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.contentPanel);
        int fragmentCount = fragmentArray.length;
        for (int i = 0; i < fragmentCount; ++i) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.getTabWidget().setDividerDrawable(null);

        }

    }

    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.home_tab, null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(mImageViewArray[index]);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(mTitleArray[index]);

        return view;
    }






    private boolean requestPermission(){
        if (afterM()){
            final List<String> permissionsList = new ArrayList<>();
            if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                    (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)) {
                return true;
            }
        }
        return false;
    }

    private boolean afterM(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


}

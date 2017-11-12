package com.example.chen.atguigucode.commom.tablelayout;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.tablelayout.adapter.ViewPagerAdapter;
import com.example.chen.atguigucode.commom.tablelayout.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 添加依赖:    compile 'com.android.support:design:26.0.0-alpha1'
 */


public class TablelayoutMainActivity extends AppCompatActivity {
    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;

    @BindView(R.id.vp_tablelayout)
    ViewPager vpTablelayout;


    @BindView(R.id.tl_tablelayout)
    TabLayout tlTablelayout;
    private Context context;


    private List<MyFragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablelayout_main);
        ButterKnife.bind(this);
        initTitle();

        initData();//初始化framgent

        //3. 设置viewpager的 adapter
        setAdapter();


        //4. 设置tablelayout
        setTableLayout();

    }

    private void setTableLayout() {

        tlTablelayout.setupWithViewPager(vpTablelayout);//设置tablelayout 的viewpager
      //tlTablelayout.setTabMode(TabLayout.MODE_SCROLLABLE); //2 .设置标题的滚动模式(可以在布局中设定)


    }



    /**
     * 3. 为viewpager 设置adapter
     */
    private void setAdapter() {


        FragmentManager fm = this.getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(fm, fragments);
        vpTablelayout.setAdapter(adapter);

    }


    /**
     * 2. 初始化数据:fragments
     */
    private void initData() {

        for (int i = 0; i < 10; i++) {
            fragments.add(new MyFragment("title" + i, "content:" + i));
        }

    }

    private void initTitle() {
        tvTopTile.setText("TableLayout");
    }
}

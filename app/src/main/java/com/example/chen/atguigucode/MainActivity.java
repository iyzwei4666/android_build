package com.example.chen.atguigucode;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chen.atguigucode.base.BaseFragment;
import com.example.chen.atguigucode.fragment.CommonFrameFragment;
import com.example.chen.atguigucode.fragment.CustomFragment;
import com.example.chen.atguigucode.fragment.OtherFragment;
import com.example.chen.atguigucode.fragment.ThirdPartFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_third)
    RadioButton rbThird;
    @BindView(R.id.rb_custom)
    RadioButton rbCustom;
    @BindView(R.id.rb_other)
    RadioButton rbOther;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;


    private List<BaseFragment> fragmentList;


    //当前显示的fragment的下标
    private int position = 0;


    private Fragment mContent;//当前显示的fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        //1. 初始化视图
        initView();


        //2. initFragment
        initFragment();//初始化帧布局


        //3. 设置radiogroup监听
        setListener();
    }


    /**
     * radiogroup 设置监听
     */
    private void setListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                switch (i) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_third:
                        position = 1;
                        break;
                    case R.id.rb_custom:
                        position = 2;
                        break;
                    case R.id.rb_other:
                        position = 3;
                        break;
                    default:
                        position = 0;
                        break;

                }

                BaseFragment to = fragmentList.get(position);
                switchFragment(mContent, to);//

            }
        });


    }


    /**
     * @param from :正在显示的fragment
     * @param to   :将要显示的fragment
     */
    private void switchFragment(Fragment from, Fragment to) {

        if (from != to) {//需要切换
            mContent = to;
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            //1. 判读to是否已经添加
            if (to.isAdded()) {//to已经添加--使用show /hide
                if (from != null) {
                    ft.hide(from);
                }

                ft.show(to);
                ft.commit();

            } else {//to没有添加--添加to


                if (from != null) {
                    ft.hide(from);

                }
                ft.add(R.id.fl_content, to);
                ft.commit();

            }
        }
    }

    /**
     * 根据position 更新要显示的fragment
     *
     * @param position private void switchFragment(int position) {
     *                 <p>
     *                 BaseFragment base = fragmentList.get(position);
     *                 <p>
     *                 //1. 得到manager
     *                 FragmentManager manager = getSupportFragmentManager();
     *                 //2. 开启事务
     *                 FragmentTransaction transaction = manager.beginTransaction();
     *                 //3. 替换
     *                 transaction.replace(R.id.fl_content,base);
     *                 //4. 提交事务
     *                 transaction.commit();
     *                 }
     */

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new CommonFrameFragment());
        fragmentList.add(new ThirdPartFragment());
        fragmentList.add(new CustomFragment());
        fragmentList.add(new OtherFragment());

         switchFragment(mContent,fragmentList.get(0));//默认显示第一个fragment
        rbHome.setChecked(true);//设置第一按钮的选中状态

    }


    private void initView() {
        //switchFragment(0);
    }


}

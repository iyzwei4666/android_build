package com.example.chen.atguigucode.commom.tablelayout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chen.atguigucode.commom.tablelayout.fragment.MyFragment;

import java.util.List;

/**
 * Created by chen on 2017/8/19.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<MyFragment> fragments;

    public ViewPagerAdapter(FragmentManager fm, List<MyFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments==null?0:fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return fragments.get(position).getTitle();
    }
}

package com.example.chen.atguigucode.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.adapter.OtherFragmentAdapter;
import com.example.chen.atguigucode.base.BaseFragment;
import com.example.chen.atguigucode.other.downloadservice.DownloadServiceMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chen on 2017/8/12.
 * 4. 其它
 */

public class OtherFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private String[] datas;
    @BindView(R.id.lv_other_item)
    ListView lvOtherItem;
    Unbinder unbinder;

    @Override
    protected void initData() {
        datas = new String[]{"DownloadService","......"};

        OtherFragmentAdapter adapter = new OtherFragmentAdapter(datas,mContext);
        lvOtherItem.setAdapter(adapter);
        lvOtherItem.setOnItemClickListener(this);

    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_other, null);
        ButterKnife.bind(this,view);
        return view;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * listview item 的点击监听
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String itemtext = ((TextView)view).getText().toString().trim();

        switch (itemtext.toLowerCase()){
            case "downloadservice":
                Intent intent = new Intent(mContext,DownloadServiceMainActivity.class);
                startActivity(intent);
                break;
        }




    }
}

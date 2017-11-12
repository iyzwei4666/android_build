package com.example.chen.atguigucode.commom.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.atguigucode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeActivity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.cb_butterKnife_item1)
    CheckBox cbButterKnifeItem1;
    @BindView(R.id.cb_butterKnife_item2)
    CheckBox cbButterKnifeItem2;
    @BindView(R.id.cb_butterKnife_item3)
    CheckBox cbButterKnifeItem3;
    @BindView(R.id.cb_butterKnife_item4)
    CheckBox cbButterKnifeItem4;
    @BindView(R.id.bt_butterknife_toas)
    Button btButterknifeToas;
    @BindView(R.id.tv_butterknife_info)
    TextView tvButterknifeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);

        //initTitle
        tvTopTile.setText("ButterKnife 使用");
    }

    @OnClick({R.id.cb_butterKnife_item1,R.id.cb_butterKnife_item2,R.id.cb_butterKnife_item3,R.id.cb_butterKnife_item4})
    public void checkBoxOnclick(View view){

        boolean isChecked =  ((CheckBox)view).isChecked();

        switch (view.getId()){
            case R.id.cb_butterKnife_item1:
                tvButterknifeInfo.setText("item1 is checked:"+isChecked);
                break;
            case R.id.cb_butterKnife_item2:
                tvButterknifeInfo.setText("item2 is checked"+isChecked);
                break;
            case R.id.cb_butterKnife_item3:
                tvButterknifeInfo.setText("item3 is checked"+isChecked);
                break;
            case R.id.cb_butterKnife_item4:
                tvButterknifeInfo.setText("item4 is checked"+isChecked);
                break;
        }

    }


    @OnClick({R.id.bt_butterknife_toas})
    void toasOnclick(View view){
        Toast.makeText(this, "Button被点击了", Toast.LENGTH_SHORT).show();
    }


}

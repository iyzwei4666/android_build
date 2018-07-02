package com.view.shapebg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShapeBgActivity extends AppCompatActivity {

    @BindView(R.id.summit)
    Button summit;
    @BindView(R.id.enable)
    Button enable;
    @BindView(R.id.disable)
    Button disable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_bg);
        ButterKnife.bind(this);

        enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                summit.setEnabled(true);
            }
        });
        disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                summit.setEnabled(false);
            }
        });
    }
}

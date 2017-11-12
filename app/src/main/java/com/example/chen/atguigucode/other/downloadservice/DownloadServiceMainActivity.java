package com.example.chen.atguigucode.other.downloadservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadServiceMainActivity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_other_download_start)
    Button btOtherDownloadStart;
    @BindView(R.id.bt_other_download_pause)
    Button btOtherDownloadPause;
    @BindView(R.id.bt_other_download_cancel)
    Button btOtherDownloadCancel;
    @BindView(R.id.tv_other_download_info)
    TextView tvOtherDownloadInfo;

    //1. 创建服务binder
    DownloadService.DownloadBinder binder ;

    //2. 创建服务connection
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = (DownloadService.DownloadBinder) iBinder; //绑定服务
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    //3. 初始化下载地址
    String url = "http://vfx.mtime.cn/Video/2017/08/08/mp4/170808142541264481.mp4";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_service_main);
        ButterKnife.bind(this);


        //启动服务
        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);//启动服务
        bindService(intent,connection,BIND_AUTO_CREATE);



    }




    @OnClick({R.id.bt_other_download_start,R.id.bt_other_download_pause,R.id.bt_other_download_cancel})
    void buttonOnclick(View view){

        if(binder==null) {
            return;
        }
        switch (view.getId()){
            case R.id.bt_other_download_start://开始下载
                tvOtherDownloadInfo.setText("开始下载");
                binder.startDownload(url);

                break;
            case R.id.bt_other_download_pause://暂停下载
                binder.pauseDownload();
                tvOtherDownloadInfo.setText("暂停下载");

                break;
            case R.id.bt_other_download_cancel://取消下载
                tvOtherDownloadInfo.setText("取消下载");

                binder.cancelDownload();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}

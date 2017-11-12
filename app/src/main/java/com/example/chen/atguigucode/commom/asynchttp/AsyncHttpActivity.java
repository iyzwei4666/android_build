package com.example.chen.atguigucode.commom.asynchttp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;


/**
 * 使用Async 执行联网操作
 * 官网:http://loopj.com/android-async-http/
 *
 * 1. 添加依赖: compile 'com.loopj.android:android-async-http:1.4.9'
 */

public class AsyncHttpActivity extends Activity {

    private String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    private static  AsyncHttpClient client = new AsyncHttpClient();

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_async_get)
    Button btAsyncGet;
    @BindView(R.id.bt_async_post)
    Button btAsyncPost;
    @BindView(R.id.tv_async_info)
    TextView tvAsyncInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_http);
        ButterKnife.bind(this);
    }





    @OnClick({R.id.bt_async_get,R.id.bt_async_post})
    public void buttonOnclick(View view){
        switch (view.getId()) {
            case R.id.bt_async_get :

                asyncGet();
                break;
            case R.id.bt_async_post:
                btAsyncPost();
                break;


        }
    }

    private void btAsyncPost() {

        RequestParams params = new RequestParams();

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                tvAsyncInfo.setText("POST"+new String(responseBody));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                tvAsyncInfo.setText("POST_ERROR:"+new String(error.getMessage()));


            }
        });

    }

    private void asyncGet() {

//        AsyncHttpClient client = new AsyncHttpClient();
//
//        RequestParams params = new RequestParams();
//        params.put("key","value");
//        client.get(url,params,new AsyncHttpResponseHandler(){
//            @Override
//            public void onSuccess(String content) {
//                tvAsyncInfo.setText("content:"+content);
//
//
//
//            }
//
//            @Override
//            public void onFailure(Throwable error, String content) {
//                tvAsyncInfo.setText("error:"+error.getMessage()+"\ncontent:"+content);
//
//            }
//        });


        client.get(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String result =  new String(responseBody);

                tvAsyncInfo.setText("StatusCode:"+statusCode);

                tvAsyncInfo.append("\nResponseBody:"+result);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {


                tvAsyncInfo.setText("联网失败:"+statusCode);
            }
        });



    }
}

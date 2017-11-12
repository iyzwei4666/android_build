package com.example.chen.atguigucode.commom.okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {


    private static final int GET = 1;
    private static final int POST = 2;
    private String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_okhttp_get)
    Button btOkhttpGet;
    @BindView(R.id.bt_okhttp_post)
    Button btOkhttpPost;
    @BindView(R.id.tv_respose_info)
    TextView tvResposeInfo;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GET:

                    String result = (String) msg.obj;
                    tvResposeInfo.setText(result);
                    break;
                case POST:
                    tvResposeInfo.setText((String) msg.obj);
                    break;
            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);

        initTitle();
    }

    private void initTitle() {
        tvTopTile.setText("OkHttp");
    }


    /**
     * okhttp get请求:
     *
     * @param view
     */
    @OnClick(R.id.bt_okhttp_get)
    public void getOnclick(View view) {
        tvResposeInfo.setText("");//清空数据

        new Thread(){
            @Override
            public void run() {
                try {

                    String content = okHttpGet(url);

                    if(content!=null) {
                        Message msg = Message.obtain();
                        msg.what = GET;
                        msg.obj = content;

                        handler.sendMessage(msg);//请求成功
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();



    }

    @OnClick(R.id.bt_okhttp_post)
    public void postOnclik() {

        tvResposeInfo.setText("");//清空数据

        new Thread(){
            @Override
            public void run() {

                try {
                    String result = okHttpPost(url,"");

                    if(result!=null) {
                        Message msg = Message.obtain();
                        msg.what = POST;
                        msg.obj = result;
                        handler.sendMessage(msg);

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();

    }


    /**
     * get
     */
    OkHttpClient client = new OkHttpClient();

    String okHttpGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }





    /**
     * POST
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
    String okHttpPost(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

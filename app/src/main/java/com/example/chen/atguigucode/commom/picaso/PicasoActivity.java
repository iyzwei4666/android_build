package com.example.chen.atguigucode.commom.picaso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PicasoActivity extends Activity {


    private static final int GET_JSON_DATA_SUCCESS = 1;//联网请求json数据成功
    @BindView(R.id.tv_picaso_inifo)
    TextView tvPicasoInifo;

    private Context mContex;

    private String jsonData;
    private List<Trailer.TrailersBean> list;

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_picaso_img_net)
    Button btPicasoImgNet;
    @BindView(R.id.bt_picaso_img_net_cut)
    Button btPicasoImgNetCut;
    @BindView(R.id.bt_picaso_img_net_rot)
    Button btPicasoImgNetRot;
    @BindView(R.id.bt_picaso_img_list)
    Button btPicasoImgList;
    @BindView(R.id.bt_picaso_img_change)
    Button btPicasoImgChange;
    @BindView(R.id.iv_picaso_net)
    ImageView ivPicasoNet;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_JSON_DATA_SUCCESS:
                    //1. 接受子线程联网返回的json数据
                    jsonData = (String) msg.obj;
                    tvPicasoInifo.setText(jsonData);
                    //2. 解析json数据:Gson
                    parseJsonDataByGson(jsonData);
                    break;
            }


        }
    };


    /**
     * 功能: 使用Gson框架,解析联网获取的json数据
     *
     * @param jsonData
     */
    private void parseJsonDataByGson(String jsonData) {
        if (TextUtils.isEmpty(jsonData)) {
            tvPicasoInifo.setText("联网请求数据为空");
            return;
        }

        Log.d("PicasoActivity", jsonData);//


    /*   List<Trailer.TrailersBean> trailers = new ArrayList<>();
        //使用fasjson 解析
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("trailers");

        if (jsonArray != null && jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject itemBean = jsonArray.getJSONObject(i);
                Trailer.TrailersBean trailersBean = itemBean.toJavaObject(Trailer.TrailersBean.class);
                trailers.add(trailersBean);
            }
        }*/

        list= new ArrayList<>();


        //JsonObject returnData = new JsonParser().parse(jsonstr).getAsJsonObject();

        JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

        JsonArray trailers = jsonObject.getAsJsonArray("trailers");


        for (int i = 0; i < trailers.size(); i++) {

            JsonObject jsonObj = trailers.get(i).getAsJsonObject();

            //int id = jsonObj.get("id").getAsInt();

            //Trailer.TrailersBean trailersBean = JSON.parseObject(jsonObj.toString(), Trailer.TrailersBean.class);//fasjsons

            Trailer.TrailersBean trailersBean = new Gson().fromJson(jsonObj, Trailer.TrailersBean.class);//gson解析
            list.add(trailersBean);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mContex = PicasoActivity.this;

        setContentView(R.layout.activity_picaso);
        ButterKnife.bind(this);


        initTitle();
        initData();
    }


    /**
     * 联网获取图片地址
     * 1. 使用okhttp 联网 --子线程 --> handler
     * 2. 使用原生Gson解析
     */
    private void initData() {
        //1. 联网获取json字符串
        final String url = " http://api.m.mtime.cn/PageSubArea/TrailerList.api";

        new Thread() {
            @Override
            public void run() {
                String jsonStr = jsonWithOkHttp(url);
                if (!TextUtils.isEmpty(jsonStr)) {
                    Message msg = Message.obtain();
                    msg.what = GET_JSON_DATA_SUCCESS;
                    msg.obj = jsonStr;
                    handler.sendMessage(msg);

                }

            }

        }.start();

    }

    /**
     * 功能: 使用OkHttp 联网请求json数据
     */
    private String jsonWithOkHttp(String url) {
        String json = "";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            json = response.body().string();


        } catch (IOException e) {
            e.printStackTrace();
        }


        return json;
    }

    private void initTitle() {
        tvTopTile.setText("Picasso图片");
    }


    @OnClick({R.id.bt_picaso_img_net, R.id.bt_picaso_img_net_cut, R.id.bt_picaso_img_net_rot, R.id.bt_picaso_img_list, R.id.bt_picaso_img_change})
    public void itemOnclick(View view) {
        switch (view.getId()) {
            case R.id.bt_picaso_img_net://普通加载
                tvPicasoInifo.setText("普通");
                imgNet();
                break;
            case R.id.bt_picaso_img_net_cut://剪裁
                tvPicasoInifo.setText("剪裁");
                imgNetCut();

                break;
            case R.id.bt_picaso_img_net_rot://旋转
                tvPicasoInifo.setText("旋转");
                imgNetRot();
                break;
            case R.id.bt_picaso_img_list:
                imgList();
                break;
            case R.id.bt_picaso_img_change:
                imgChange();
                break;
        }
    }



    /**
     * 一:加载网络图片(普通)
     */
    private void imgNet() {
        Log.d("PicasoActivity", "imgNet");



        String imgurl = list==null?"":list.get(1).getCoverImg();//随机选取一个图片地址

        Picasso.with(mContex)
                .load(imgurl)
                .into(ivPicasoNet);

    }
    /**
     * 一:加载网络图片(剪裁)
     */
    private void imgNetCut() {

        String imgurl = list==null?"":list.get(2).getCoverImg();//随机选取一个图片地址


        Picasso.with(mContex)
                .load(imgurl)
                .resize(200,200) //重设尺寸
                .into(ivPicasoNet);

    }


    /**
     * 一: 加载网络图片(旋转)
     */
    private void imgNetRot() {
        String imgurl = list==null?"":list.get(3).getCoverImg();//随机选取一个图片地址


        Picasso.with(mContex)
                .load(imgurl)
                .rotate(180)   //旋转
                .into(ivPicasoNet);

    }



    /**
     * 二: 加载网络列表图片
     */
    private void imgList() {
        Log.d("PicasoActivity", "imgList");
        Intent intent = new Intent(PicasoActivity.this, PicasonListviewActivity.class);

        //使用bundler 传递数据到下一个intent (当前 list 对应的类应该 需要implement Serializable)
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) list);
        intent.putExtras(bundle);

        startActivity(intent);


    }


    /**
     * 三:修改图片
     */
    private void imgChange() {
        Log.d("PicasoActivity", "imgChange");
        Intent intent = new Intent(PicasoActivity.this, PicasoTranformationActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) list);
        intent.putExtras(bundle);


        startActivity(intent);

    }

}

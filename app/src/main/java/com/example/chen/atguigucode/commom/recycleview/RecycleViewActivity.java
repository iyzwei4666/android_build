package com.example.chen.atguigucode.commom.recycleview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.example.chen.atguigucode.commom.recycleview.adapter.MyRecycleViewAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class RecycleViewActivity extends Activity {

    private static final int LOAD_JSON_SUCCESS = 1;//使用okhttputils 联网请求数据成功
    private static final int PARSE_JSON_FINISHED = 2; //解析json数据完成-->list


    private Context mContext ;

    private String jsonData = ""; //从服务器获取的json字符串
    private List<Trailer.TrailersBean> list; //json解析后的数据


    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_recycle_add)
    Button btRecycleAdd;
    @BindView(R.id.bt_recycle_del)
    Button btRecycleDel;
    @BindView(R.id.bt_recycle_list)
    Button btRecycleList;
    @BindView(R.id.bt_recycle_grid)
    Button btRecycleGrid;
    @BindView(R.id.bt_recycle_flow)
    Button btRecycleFlow;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case LOAD_JSON_SUCCESS: //加载json数据成功
                    //1. 得到json数据
                    jsonData = (String) msg.obj;
                    //2. 解析json数据
                    parseJson(jsonData);

                    break;
                case PARSE_JSON_FINISHED://解析json数据完成-> 设置recycleview的适配器

                    setRecycleViewAdapter();

                    break;
            }

        }
    };


    /**
     * 为recycleview 设置适配器
     */
    private MyRecycleViewAdapter adapter;
    private void setRecycleViewAdapter() {

        adapter = new MyRecycleViewAdapter(list, RecycleViewActivity.this);
        rvRecycler.setAdapter(adapter);

        //2. manager

        rvRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));//mContext;方向;是否倒序

        //rvRecycler.scrollToPosition(list.size()/2);
       // rvRecycler.smoothScrollToPosition(15);





        //设置图片或文字监听
        adapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {


            @Override
            public void onItemClick(View view, String data, int position) {

            }

            @Override
            public void onItemImageClick(View view, Trailer.TrailersBean data, int position) {

                Toast.makeText(mContext, "image:" + position + ":" + data.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemTextClick(View view, String data, int position) {
                Toast.makeText(mContext, "text:" + position + ":" + data, Toast.LENGTH_SHORT).show();

            }
        });

    }

    /**
     * 解析json数据:
     * 使用fastjson
     *
     * @param jsonData
     */
    private void parseJson(String jsonData) {


//        //使用fastJson解析
//        Trailer trailer = JSON.parseObject(jsonData, Trailer.class);
//        Log.d("RecycleViewActivity", "trailer:" + trailer);
//
//        List<Trailer.TrailersBean> trailers = trailer.getTrailers();
//        Log.d("RecycleViewActivity", trailers.toString());
//

        //使用Gson 解析
        JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
        Trailer trailer = new Gson().fromJson(jsonObject, Trailer.class);


        List<Trailer.TrailersBean> trailers = trailer.getTrailers();
        Log.d("RecycleViewActivity", "trailers:" + trailers.toString());

        list = trailers;


        //数据初始化完成--> handler 设置recycleveiw 的适配器


        handler.sendEmptyMessage(PARSE_JSON_FINISHED);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = RecycleViewActivity.this;
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);

        initTitle();

        initData();


        //3. 设置recycleview 适配器

    }

    /**
     * 初始化数据
     * 1. 设置url
     * 2. 执行联网请求,获取json数据
     * 3. 解析json 数据
     */
    private void initData() {
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        loadingJsonByOkHttpUtils(url);


    }

    /**
     * 联网: 使用okhttputils
     *
     * @param url
     */
    private void loadingJsonByOkHttpUtils(String url) {

        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Toast.makeText(RecycleViewActivity.this, "RecycleView:联网请求数据失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (TextUtils.isEmpty(response)) {
                            Toast.makeText(RecycleViewActivity.this, "RecycleView:请求数据为空", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Message msg = Message.obtain();
                        msg.what = LOAD_JSON_SUCCESS;
                        msg.obj = response;
                        handler.sendMessage(msg);

                    }
                });


    }

    private void initTitle() {
        tvTopTile.setText("RecycleView");

    }

    @OnClick({R.id.bt_recycle_add, R.id.bt_recycle_del, R.id.bt_recycle_list, R.id.bt_recycle_grid, R.id.bt_recycle_flow})
    public void itemOnclick(View view) {
        switch (view.getId()) {
            case R.id.bt_recycle_add:
                addItem();
                break;
            case R.id.bt_recycle_del:
                removeItem();
                break;
            case R.id.bt_recycle_list:
                rvRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

                break;
            case R.id.bt_recycle_grid:
                rvRecycler.setLayoutManager(new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false));
                break;
            case R.id.bt_recycle_flow:
                rvRecycler.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
        }


    }

    /**
     * 从recycleview 中移除item
     */
    private void removeItem() {

        adapter.removeItemData(0);
        Toast.makeText(mContext, "remoe:0", Toast.LENGTH_SHORT).show();


    }

    /**
     * 向recycleview 中添加 item
     */
    private void addItem() {
        adapter.addItemData(0,null);

        Toast.makeText(mContext, "add:0", Toast.LENGTH_SHORT).show();
    }

}

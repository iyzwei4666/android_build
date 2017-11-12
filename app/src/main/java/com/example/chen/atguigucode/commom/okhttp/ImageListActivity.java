package com.example.chen.atguigucode.commom.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.example.chen.atguigucode.utils.CacheUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class ImageListActivity extends Activity {


    private static final int GET_JSON_SUCCESS = 1;//联网请求json字符串成功
    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.lv_image_list)
    ListView lvImageList;
    @BindView(R.id.pb_image_list)
    ProgressBar pbImageList;
    @BindView(R.id.tv_imglist_info)
    TextView tvImglistInfo;


    //从网络获取的json数据
    List<Trailer.TrailersBean> datas ;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GET_JSON_SUCCESS:
                    //解析json
                    String json = (String) msg.obj;
                    datas = pareJson(json);

                    if(datas!=null&&datas.size()>0) { //有数据
                        //设置adapter
                        tvImglistInfo.setVisibility(View.GONE);
                        pbImageList.setVisibility(View.GONE);
                        lvImageList.setVisibility(View.VISIBLE);

                        ListAdapter adapter = new OkHttpImageListAdapter(datas,ImageListActivity.this);
                        lvImageList.setAdapter(adapter);




                    }else {
                        //没有数据
                        tvImglistInfo.setText("no data");
                        pbImageList.setVisibility(View.GONE);
                        lvImageList.setVisibility(View.GONE);
                    }

                    break;
            }

        }
    };

    /**
     * 解析联网请求的json字符串
     * @param json
     */
    private List<Trailer.TrailersBean> pareJson(String json) {
        Log.d("ImageListActivity", json);
        List<Trailer.TrailersBean> trailers = null;

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("trailers"); //解析array

            if(jsonArray!=null&& jsonArray.length()>0) {
               trailers = new ArrayList<>();

                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonBean = jsonArray.getJSONObject(i); //得到一个trailer 对象
                    
                    if(jsonBean!=null) {

                        int id = jsonBean.optInt("id");
                        String movieName = jsonBean.optString("movieName");
                        String coverImg = jsonBean.optString("coverImg");

                        int movieId = jsonBean.optInt("movieId");
                        String url = jsonBean.optString("url");
                        String hightUrl = jsonBean.optString("hightUrl");
                        String videoTitle = jsonBean.optString("videoTitle");
                        int videoLength = jsonBean.optInt("videoLength");

                        int rating = jsonBean.optInt("rating");
                        String summary = jsonBean.optString("summary");


                        //将type 分装成 typelist
                        JSONArray type  = jsonBean.optJSONArray("type");

                        List<String> typelist = new ArrayList<>();
                        if(type!=null&&type.length()>0) {

                            for (int j= 0;j< type.length();j++){

                                String itype = (String) type.get(j);
                                typelist.add(itype);
                            }
                        }


                        //将解析的jsonbean 封装到trailerBean

                        Trailer.TrailersBean trailersBean =
                                new Trailer.TrailersBean(id, movieName, coverImg, movieId, url, hightUrl, videoTitle, videoLength, rating, summary, typelist);

                        trailers.add(trailersBean);//将解析的trailersBean 方法trailerslist 中
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return trailers;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        ButterKnife.bind(this);

        initTitle();

        initData();


    }

    /**
     * 获取网络json数据
     */
    private void initData() {

        final String baseUrl = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

        //1. 获取缓存数据
        String cache = CacheUtils.getString(ImageListActivity.this, baseUrl);

        if(!TextUtils.isEmpty(cache)) {//本地缓存数据存在
            Message msg = Message.obtain();
            msg.what = GET_JSON_SUCCESS;
            msg.obj = cache;
            handler.sendMessage(msg);
            return;
        }

        // 本地数据不存在,联网请求数据,并保存到本地
        OkHttpUtils
                .get()
                .url(baseUrl)
                //.addParams("username", "hyman")
                //.addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        lvImageList.setVisibility(View.GONE); //隐藏listview
                        pbImageList.setVisibility(View.GONE); //隐藏progressbar
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        pbImageList.setVisibility(View.GONE);//隐藏progressbar
                        tvImglistInfo.setText(response);
                        if(response!=null) {
                            Message msg = Message.obtain();
                            msg.what = GET_JSON_SUCCESS;
                            msg.obj = response;
                            handler.sendMessage(msg);

                            //将数据写到本地缓存
                            CacheUtils.putString(ImageListActivity.this,baseUrl,response);
                        }

                    }
                });
        
        





    }

    /**
     * 使用okhttpuitls 请求列表图片
     */
    private void initTitle() {
        tvTopTile.setText("okhttputils-列表图片");
    }


}

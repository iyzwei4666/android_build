package com.example.chen.atguigucode;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import okhttp3.Call;

/**
 * Created by chen on 2017/8/14.
 */

public class AtguiguAppApplication extends Application {

    //
    private Trailer trailer ;

    private String jsonStr; //联网请求的json文本数据


    public static   List<Trailer.TrailersBean> list;
    public  static  AtguiguAppApplication App ;

    @Override
    public void onCreate() {
        super.onCreate();

        App = this;

        //初始化xutils3
        x.Ext.init(this);
        x.Ext.setDebug(false);// 开启debug会影响性能, 上线时关闭

        Fresco.initialize(this);




        initData();
    }

    /**
     * 获取常用api数据
     */
    private void initData() {

        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

        //1. 使用afinal 联网请求数据
       // jsonDataByAfinal(url);


        //2. 使用xutils 联网请求数据
       // josnDataByXutils3(url);


        //3. 使用okhttputils
        jsonDataByOkHttpUtils(url);


    }

    private void jsonDataByOkHttpUtils(String url) {

        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Toast.makeText(AtguiguAppApplication.this, "RecycleView:联网请求数据失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            jsonStr = response;
                            parseJson(response);
                            return;
                        }
                    }
                });


    }




    /**
     * 使用xutils3 联网请求数据
     */
    private void josnDataByXutils3(String url) {


        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                if(!result.isEmpty()) {
                    jsonStr = result;
                    parseJson(jsonStr);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(AtguiguAppApplication.this, "初始化app数据错误:" + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {


            }

            @Override
            public void onFinished() {
            }
        });
    }


    /**
     * 使用afinal获取json数据
     *
     */
    private void jsonDataByAfinal(String url) {

        if(TextUtils.isEmpty(url)) {
            return;
        }

        FinalHttp http = new FinalHttp();
        http.get(url, new AjaxCallBack<String>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(String s) {
                if(!s.isEmpty()) {
                    jsonStr = s;
                    parseJson(jsonStr);
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(AtguiguAppApplication.this, "初始化app数据错误:" + strMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 解析json数据
     * @param jsonStr
     */
    private void parseJson(String jsonStr) {
        if(TextUtils.isEmpty(jsonStr)) {
            return;
        }


//使用fastJson解析
//        Trailer trailer = JSON.parseObject(jsonData, Trailer.class);
//        Log.d("RecycleViewActivity", "trailer:" + trailer);
//
//        List<Trailer.TrailersBean> trailers = trailer.getTrailers();
//        Log.d("RecycleViewActivity", trailers.toString());
//

        //1. 使用Gson解析
        JsonObject jsonObj = new JsonParser().parse(jsonStr).getAsJsonObject();

        trailer = new Gson().fromJson(jsonObj, Trailer.class);

        Log.d("AtguiguAppApplication", "gosn:"+"trailer:" + trailer);


        this.list = trailer.getTrailers();

        Toast.makeText(this, "初始化app数据完成", Toast.LENGTH_SHORT).show();

    }


}

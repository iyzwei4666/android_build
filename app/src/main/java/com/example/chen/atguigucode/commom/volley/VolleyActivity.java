package com.example.chen.atguigucode.commom.volley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.example.chen.atguigucode.utils.BitmapCache;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolleyActivity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_volley_get)
    Button btVolleyGet;
    @BindView(R.id.bt_volley_post)
    Button btVolleyPost;
    @BindView(R.id.bt_volley_json)
    Button btVolleyJson;
    @BindView(R.id.bt_volley_img)
    Button btVolleyImg;
    @BindView(R.id.bt_volley_img_loader)
    Button btVolleyImgLoader;
    @BindView(R.id.bt_volley_img_view)
    Button btVolleyImgView;
    @BindView(R.id.tv_volley_info)
    TextView tvVolleyInfo;
    @BindView(R.id.iv_volley_singleImage)
    ImageView ivVolleySingleImage;
    @BindView(R.id.iv_volley_network_Image)
    NetworkImageView ivVolleyNetworkImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        ButterKnife.bind(this);

        //initTitle
        tvTopTile.setText("Volley");

    }


    @OnClick({R.id.bt_volley_get, R.id.bt_volley_post, R.id.bt_volley_json, R.id.bt_volley_img, R.id.bt_volley_img_view, R.id.bt_volley_img_loader})
    public void itemOnclick(View view) {
        switch (view.getId()) {
            case R.id.bt_volley_get:
                requestGet();
                break;
            case R.id.bt_volley_post:
                requestPost();
                break;
            case R.id.bt_volley_json:
                requestJson();
                break;
            case R.id.bt_volley_img:
                requestImage();
                break;
            case R.id.bt_volley_img_loader:
                requestImageLoader();
                break;
            case R.id.bt_volley_img_view:
                requestImageView();
                break;
        }

    }


    /**
     * 一: volley 发送get请求
     * 1. 准备请求地址
     * 2. 使用velley执行联网操作
     * 3. 数据展示
     */
    private void requestGet() {
        ivVolleySingleImage.setVisibility(View.INVISIBLE);
        tvVolleyInfo.setText("");
        Log.d("VolleyActivity", "requestGet");

        //1.
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

        //2.
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                tvVolleyInfo.setText("onReseponse:" + s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tvVolleyInfo.setText("onErrorResponse:" + volleyError.toString());
            }
        });

        requestQueue.add(stringRequest);


    }

    /**
     * 二: volley 发送post请求
     * 1. 准备请求地址
     * 2. 使用volley 发送post请求
     * > 创建reqestQueue
     * > 创建StringRequest(url,listen,errorListen){getParams()}
     * 3. 数据展示
     */
    private void requestPost() {
        ivVolleySingleImage.setVisibility(View.INVISIBLE);
        tvVolleyInfo.setText("");
        Log.d("VolleyActivity", "requestPost");
        //1.
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

        //2.
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                tvVolleyInfo.setText("onRespose:" + s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tvVolleyInfo.setText("Error:" + volleyError.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("pkey", "pvalue");
                return map;
            }
        };

        requestQueue.add(stringRequest);


    }

    /**
     * 三: volley 请求Json数据
     */
    private void requestJson() {
        ivVolleySingleImage.setVisibility(View.INVISIBLE);
        Log.d("VolleyActivity", "requestJson");

        //1.
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

        //2.
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                tvVolleyInfo.setText(jsonObject.toString()); //个人不推荐使用: 因为JsonObj和第三方库的JsonObj 不来自同一个包,解析困难

                try {
                    parseJson(jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tvVolleyInfo.setText(volleyError.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    private void parseJson(String result) throws JSONException {
        //使用fasjson 解析
        //使用fasjson 解析
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("trailers");

        List<Trailer.TrailersBean> trailers = new ArrayList<>();

        if (jsonArray != null && jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                com.alibaba.fastjson.JSONObject itemBean = jsonArray.getJSONObject(i);
                Trailer.TrailersBean trailersBean = itemBean.toJavaObject(Trailer.TrailersBean.class);

                trailers.add(trailersBean);
            }
        }

        tvVolleyInfo.setText(trailers.toString());
    }


    /**
     * 四: volley ImageRequest请求图片
     */
    private void requestImage() {
        ivVolleySingleImage.setVisibility(View.INVISIBLE);

        Log.d("VolleyActivity", "requestImage");

        String url = "http://img5.mtime.cn/mg/2017/07/23/173925.91198625.jpg";

        RequestQueue requestQueue = Volley.newRequestQueue(VolleyActivity.this);


        int maxWidth = 0;
        int maxHeight = 0;
        ImageRequest imageRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ivVolleySingleImage.setVisibility(View.VISIBLE);
                        ivVolleySingleImage.setImageBitmap(bitmap);


                    }
                }, maxWidth, maxHeight, Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        ivVolleySingleImage.setVisibility(View.GONE);
                        tvVolleyInfo.setText("加载图片失败:" + volleyError.toString());
                    }
                });

        requestQueue.add(imageRequest);


    }

    /**
     * 五: volley ImageLoader请求图片
     * 1. 准备图片地址
     * 2. 使用ImageLoader加载图片
     * > 创建RequestQueue
     * > 创建imageloader对象
     * > 获取imageListern =imagerLoader.getImageListener()
     * > 使用iamgeloader.get(url,listener) 加载图片
     */
    private void requestImageLoader() {
        ivVolleySingleImage.setVisibility(View.VISIBLE);
        Log.d("VolleyActivity", "requestImageLoader");
        String url = "http://img5.mtime.cn/mg/2017/03/29/170431.30153600.jpg";


        RequestQueue requestQueue = Volley.newRequestQueue(VolleyActivity.this);

        /**
         * 不带缓存
         *
         ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
        @Override public Bitmap getBitmap(String s) {
        return null;
        }

        @Override public void putBitmap(String s, Bitmap bitmap) {

        }
        });
         */

        //2. 带缓存
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());//使用缓存工具类


        ImageLoader.ImageListener imageListener = imageLoader.getImageListener(ivVolleySingleImage,
                R.drawable.atguigu_logo, R.drawable.center_collect_play);
        imageLoader.get(url, imageListener);
    }

    /**
     * 六: volley NetWorkImageView请求图片
     * : ivVolleyNetworkImage
     */
    private void requestImageView() {
        ivVolleySingleImage.setVisibility(View.GONE);
        ivVolleyNetworkImage.setVisibility(View.VISIBLE);
        Log.d("VolleyActivity", "requestImageView");

        String url = "http://img5.mtime.cn/mg/2017/06/15/175025.74431884.jpg";

        RequestQueue requestQueue = Volley.newRequestQueue(VolleyActivity.this);
        ImageLoader imageLoader = new ImageLoader(requestQueue,new BitmapCache());

        //2. 设置默认图片和加载失败的图片
        ivVolleyNetworkImage.setDefaultImageResId(R.drawable.atguigu_logo);
        ivVolleyNetworkImage.setErrorImageResId(R.drawable.center_collect_play);

        ivVolleyNetworkImage.setImageUrl(url,imageLoader);
    }


}

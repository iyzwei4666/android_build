package com.example.chen.atguigucode.commom.xtuils3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;
import com.example.chen.atguigucode.commom.xtuils3.net.XUtils3NetActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


@ContentView(R.layout.activity_xutils)
public class XUtilsActivity extends Activity {


    //使用xutils3 注解
    @ViewInject(R.id.tv_top_tile)
    public TextView tvTopTile;

    @ViewInject(R.id.bt_xtuils_inject)
    public Button btXtuilsInject;

    @ViewInject(R.id.bt_xutils_net)
    public Button btXutilsNet;

    @ViewInject(R.id.bt_xutils_sing_image)
    public Button btXutilsSingImage;

    @ViewInject(R.id.bt_xutils_list_image)
    public Button btXutilsListImage;

    @ViewInject(R.id.tv_xutils_info)
    public TextView tvXutilsInfo;

    @ViewInject(R.id.iv_xutils_single)
    public ImageView ivXutilsSingle;

    @ViewInject(R.id.lv_xutis3_img_list)
    public ListView lvXutis3ImgList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_xutils);

        //使用xtuils3 注解
        x.view().inject(this);


        tvXutilsInfo.setText("infofifndlkfnl"); //
        initTile();


    }

    /**
     * 初始化title
     */
    private void initTile() {
        tvTopTile.setText("xutils3 模块");

    }


    @Event(value = {R.id.tv_xutils_info, R.id.bt_xtuils_inject, R.id.bt_xutils_net, R.id.bt_xutils_sing_image, R.id.bt_xutils_list_image})
    private void textEvent(View view) {//使用public 不能行,不知道为什么

        switch (view.getId()) {
            case R.id.tv_xutils_info:
                Toast.makeText(this, "text被点击了", Toast.LENGTH_SHORT).show();
                break;

            case R.id.bt_xtuils_inject:
                Toast.makeText(this, "注解模块", Toast.LENGTH_SHORT).show();

                break;
            case R.id.bt_xutils_net:
                Toast.makeText(this, "联网模块", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(XUtilsActivity.this, XUtils3NetActivity.class);
                startActivity(intent);


                break;
            case R.id.bt_xutils_sing_image:
                Toast.makeText(this, "单张图片", Toast.LENGTH_SHORT).show();
                singleImage();
                break;

            case R.id.bt_xutils_list_image:
                Toast.makeText(this, "列表图片", Toast.LENGTH_SHORT).show();
                imageList();

                break;
        }
    }


    /**
     * 四: 显示列表图片
     * 1. 获取图片地址 list<url>
     * 2. 为imageviw 设置adapter
     * 3. 显示adapter
     */
    private void imageList() {
        ivXutilsSingle.setVisibility(View.GONE);
        tvXutilsInfo.setVisibility(View.GONE);


        //1. 获取图片数据

        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    parseJsonData(result);

                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
     * 解析json数据
     *
     * @param result
     */
    private void parseJsonData(String result) {

        List<Trailer.TrailersBean> trailers = new ArrayList<>();

        //使用fasjson 解析
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("trailers");

        if (jsonArray != null && jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject itemBean = jsonArray.getJSONObject(i);
                Trailer.TrailersBean trailersBean = itemBean.toJavaObject(Trailer.TrailersBean.class);
                trailers.add(trailersBean);
            }
        }

        //2. 提取所有图片地址 -> list

        List<String> imgUrls = new ArrayList<>();

        for (Trailer.TrailersBean bean : trailers) {
            String url = bean.getCoverImg();
            imgUrls.add(url);
        }

        //3. 为img listview 设置adapter


        lvXutis3ImgList.setAdapter(new ImageListAdapter(imgUrls,trailers));
        lvXutis3ImgList.setVisibility(View.VISIBLE);


    }

    /**
     * 三: 请求单张图片
     * 1. 图片地址url
     * 2. 使用xtuils3 请求图片
     * 3. 图片展示
     */
    private void singleImage() {
        String url = "http://img5.mtime.cn/mg/2017/07/23/173127.61663169.jpg";
        x.image().bind(ivXutilsSingle, url);
        lvXutis3ImgList.setVisibility(View.GONE); //隐藏listvew
        ivXutilsSingle.setVisibility(View.VISIBLE);
        tvXutilsInfo.setVisibility(View.VISIBLE);
    }


    /**
     * iamge list adapter
     */


    class ImageListAdapter extends BaseAdapter {
        List<String> imgUrls;
        List<Trailer.TrailersBean> trailers;
        public ImageListAdapter(List<String> imgUrls, List<Trailer.TrailersBean> trailers) {
            this.imgUrls = imgUrls;
            this.trailers = trailers;
        }

        @Override
        public int getCount() {
            return imgUrls.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = View.inflate(XUtilsActivity.this, R.layout.item_xutils3_list_image, null);
                viewHolder = new ViewHolder(view);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }


            x.image().bind(viewHolder.ivXutils3ImgList, imgUrls.get(i));//绑定图片


            //title
            String title = trailers.get(i).getMovieName();
            viewHolder.tvXutilsImageList.setText(title);


            return view;
        }


        class ViewHolder {
            @BindView(R.id.tv_xutils_image_list)
            TextView tvXutilsImageList;
            @BindView(R.id.iv_xutils3_img_list)
            ImageView ivXutils3ImgList;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}

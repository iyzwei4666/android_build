package com.example.chen.atguigucode.commom.glide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.example.chen.atguigucode.R;
import com.example.chen.atguigucode.commom.okhttp.bean.Trailer;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 使用
 */


public class GlideActivity extends Activity{


    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_glide_common)
    Button btGlideCommon;
    @BindView(R.id.bt_glide_recycle)
    Button btGlideRecycle;
    @BindView(R.id.bt_glide_transform)
    Button btGlideTransform;
    @BindView(R.id.iv_glide_img_show)
    ImageView ivGlideImgShow;
    @BindView(R.id.tv_glide_info)
    TextView tvGlideInfo;




    private List<Trailer.TrailersBean> list ;

    private Context mContext ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        this.mContext = GlideActivity.this;
        ButterKnife.bind(this);


        //初始化标题
        initTitle();

        //2. 初始化数据

        initData();

    }

    /**
     * 使用xutils3 联网请求数据
     */
    private void initData() {


        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                tvGlideInfo.setText(result);
                parseJson(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                tvGlideInfo.setText("onErrer:"+ex.getMessage());
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
     * 解析json字符串
     * @param result
     * 1. 使用FastJson
     * 2. 使用Gson
     */
    private void parseJson(String result) {

        if(result.isEmpty()) {
            tvGlideInfo.setText("数据为空");
            return;
        }

        //1. 使用fastjson
        Trailer trailer = JSON.parseObject(result, Trailer.class);
        tvGlideInfo.setText(trailer.toString());



        /**2.使用gson
        JsonObject jsonObj = new JsonParser().parse(result).getAsJsonObject();
        Trailer trailer = new Gson().fromJson(jsonObj, Trailer.class);

         */

        this.list = trailer.getTrailers();
        Toast.makeText(this, "初始化列表数据完成", Toast.LENGTH_SHORT).show();

    }

    private void initTitle() {
        tvTopTile.setText("Glide 图片");

    }

    @OnClick({R.id.bt_glide_common,R.id.bt_glide_recycle,R.id.bt_glide_transform})
    public void itemOnclick(View view){
        switch (view.getId()){
            case R.id.bt_glide_common :
                imgCommon();
                break;
            case R.id.bt_glide_recycle :
                imgRecycle();
                break;
            case R.id.bt_glide_transform :
                imgTransform();
                break;
        }
    }


    /**
     * 3. 图片变换
     */
    private void imgTransform() {
        tvGlideInfo.setText("transfor");

        Intent intent = new Intent(mContext,GlideTransformActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) list);

        intent.putExtras(bundle);
        startActivity(intent);

    }

    /**
     * 2. recycleview 中使用glide
     */
    private void imgRecycle() {
        tvGlideInfo.setText("recycle");

        Intent intent = new Intent(mContext,GlideRecycleViewActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) list);

        intent.putExtras(bundle);

        startActivity(intent);



    }


    /**
     * 1. 普通加载图片
     */
    private  int model = 0;
    private void imgCommon() {
        tvGlideInfo.setText("common");
        ivGlideImgShow.setVisibility(View.VISIBLE);

        switch (model){
            case 0:// 普通加载 网络图片
                Glide.with(mContext)
                        .load(list==null?"":list.get(1).getCoverImg())
                        .into(ivGlideImgShow);


                tvGlideInfo.setText(model+":网络图片");
                model++;
                break;
            case 1: //加载资源图片
                Glide.with(mContext)
                        .load(R.drawable.atguigu_logo)
                        .into(ivGlideImgShow);

                tvGlideInfo.setText(model+":资源图片");
                model++;
                break;
            case 2://加载本地磁盘图片
                File file = new File(Environment.getExternalStorageDirectory(),"/Download/test.png");
                Uri uri = Uri.fromFile(file);
                Glide.with(mContext).load(uri).into(ivGlideImgShow);

                tvGlideInfo.setText(model+":磁盘图片");
                model ++;
                break;
            case 3://加载网络gif图片

                String gifurl ="http://img4.imgtn.bdimg.com/it/u=2195944302,1341622003&fm=214&gp=0.jpg";
                Glide.with(mContext).load(gifurl).into(ivGlideImgShow);


                tvGlideInfo.setText(model+":网络gif图片");
                model ++;
                break;
            case 4://加载资源gif图片

                Glide.with(mContext).load(R.drawable.clion).into(ivGlideImgShow);


                tvGlideInfo.setText(model+":资源gif图片");
                model++;
                break;
            case 5://加载本地磁盘gif图片
                File gifFile = new File(Environment.getExternalStorageDirectory(),"Download/car.gif");//
                Uri gifuri = Uri.fromFile(gifFile);

                Glide.with(mContext).load(gifuri).into(ivGlideImgShow);

                tvGlideInfo.setText(model+":磁盘gif图片"+gifFile.getAbsolutePath());
                model++;
                break;

            case 6: //加载本地磁盘视频-快照
                File moveFile = new File(Environment.getExternalStorageDirectory(),"/Download/move.mp4");

                Uri moveUri = Uri.fromFile(moveFile);
                Glide.with(mContext).load(moveUri).into(ivGlideImgShow);

                tvGlideInfo.setText(model+":磁盘move");
                model++;


                break;

            case 7: //设置缩略图:先加载缩略图,后显示原图

                String netUrl = "http://pic1.win4000.com/wallpaper/c/57e35057d7888.jpg";

                Glide.with(mContext).load(netUrl).thumbnail(0.1f).centerCrop().placeholder(R.drawable.loading).into(ivGlideImgShow);
                tvGlideInfo.setText(model+":缩略图:");

                model++;
                break;
            case 8: //设置缩略图:先加载缩略图,后显示原图

                String height = "http://pic1.win4000.com/wallpaper/c/57e35057d7888.jpg";
                String bike = "http://img.zcool.cn/community/015ae6568f4d2732f87574be749350.gif";


                DrawableRequestBuilder builder = Glide.with(this).load(bike);
                Glide.with(mContext)
                        .load(height)   //1. 要显示的网络高清图片
                        .thumbnail(builder)  //2. 缩略图对象
                        .centerCrop()
                        .placeholder(R.drawable.loading)//3.加载等待时显示的图像
                        .into(ivGlideImgShow);
                tvGlideInfo.setText(model+":缩略图2:");
                model=0;
                break;
//            DrawableRequestBuilder thumbnailRequest=  Glide.with(this).load(new File(urlPath));
//            Glide.with(this).load(Uri.fromFile(videoFile)).thumbnail(thumbnailRequest).centerCrop().placeholder(R.mipmap.ic_launcher).into(ivGlide9);
//
//
//

        }

    }
}

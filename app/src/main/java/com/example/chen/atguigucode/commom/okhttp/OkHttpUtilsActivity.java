package com.example.chen.atguigucode.commom.okhttp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.atguigucode.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

public class OkHttpUtilsActivity extends Activity {


    private static final String TAG = "okhttp-utils:";
    @BindView(R.id.bt_file_download)
    Button btFileDownload;
    @BindView(R.id.bt_file_upload)
    Button btFileUpload;
    @BindView(R.id.pb_file_download)
    ProgressBar pbFileDownload;
    @BindView(R.id.bt_imge_download)
    Button btImgeDownload;
    @BindView(R.id.iv_okhttp)
    ImageView ivOkhttp;
    @BindView(R.id.bt_imge_list)
    Button btImgeList;
    private String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_okhttp_get)
    Button btOkhttpGet;
    @BindView(R.id.bt_okhttp_post)
    Button btOkhttpPost;
    @BindView(R.id.tv_respose_info)
    TextView tvResposeInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_utils);
        ButterKnife.bind(this);
        initTile();

        checkRuntimePermission();
    }

    private void initTile() {
        tvTopTile.setText("OkHttp-Utils");
    }


    /**
     * 发送GET请求
     *
     * @param view
     */
    @OnClick(R.id.bt_okhttp_get)
    public void getOnclick(View view) {
        tvResposeInfo.setText("get");//清空数据


        // String url2 = "http://www.csdn.net/"
        OkHttpUtils
                .get()
                .url(url)
                //.addParams("username", "hyman")
                //.addParams("password", "123")
                .build()
                .execute(new MyStringCallback());


    }

    /**
     * OkHttpUtils 发送POST请求
     */
    @OnClick(R.id.bt_okhttp_post)
    public void postOnclik() {
        tvResposeInfo.setText("");

        OkHttpUtils
                .post()
                .url(url)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .build()
                .execute(new MyStringCallback());

    }


    /**
     * 下载大文件
     *
     * @param v
     */
    @OnClick(R.id.bt_file_download)
    public void downloadOnclick(View v) {
        tvResposeInfo.setText("download");

        String fileUrl = "http://vfx.mtime.cn/Video/2017/08/02/mp4/170802074323236656.mp4";

        String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "okhttp_utils_dowload.mp4";

        OkHttpUtils
                .get() //
                .url(fileUrl)//
                .build()//
                .execute(new FileCallBack(fileDir, fileName)//
                {

                    @Override
                    public void onBefore(Request request, int id) {
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        pbFileDownload.setProgress((int) (100 * progress));
                        int percent = (int) (progress * 100);

                        tvResposeInfo.setText("进度:" + percent + "%");
                        Log.e(TAG, "inProgress :" + (int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int id) {
                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                    }
                });


    }


    /**
     * 文件上传
     *
     * @param v
     */
    @OnClick(R.id.bt_file_upload)
    public void uploadOnclick(View v) {

        String baseUrl = "http://192.168.1.104:8080/FileUpload/FileUploadServlet";

        File file = new File(Environment.getExternalStorageDirectory() + "/Download", "test.png");
        File textFile = new File(Environment.getExternalStorageDirectory() + "/Download", "test.txt");

        Log.d(TAG, file.getAbsolutePath().toString());
        multiFileUpload(baseUrl, file, textFile);


    }


    @OnClick(R.id.bt_imge_download)
    public void getImgOnClick(View view) {
        String imgUrl = "http://img5.mtime.cn/mg/2017/08/04/165113.24946335.jpg";
        getImage(imgUrl);
    }



    @OnClick(R.id.bt_imge_list)
    public  void imglistOnclick(View view){
        //启动Imagelist显示页面的activity
        Intent intent = new Intent(OkHttpUtilsActivity.this,ImageListActivity.class);
        startActivity(intent);
    }

    /**
     * 下载图片:方法
     *
     * @param
     */
    public void getImage(String imgUrl) {
        tvResposeInfo.setText("");
        OkHttpUtils
                .get()//
                .url(imgUrl)//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tvResposeInfo.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        Log.e("TAG", "onResponse：complete");
                        ivOkhttp.setImageBitmap(bitmap);
                    }
                });
    }

    /**
     * 多文件上传方法
     *
     * @param basurl
     * @param file1
     * @param file2
     */
    public void multiFileUpload(String basurl, File file1, File file2) {

        if (!file1.exists() || !file2.exists()) {
            Toast.makeText(OkHttpUtilsActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("username", "张鸿洋");
        params.put("password", "123");

        OkHttpUtils.post()//
                .addFile("mFile", "server.png", file1)//
                .addFile("mFile", "server.txt", file2)//
                .url(basurl)
                .params(params)//
                .build()//
                .execute(new MyStringCallback());
    }


    /**
     * 检查运行时权限
     */
    private void checkRuntimePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //如果未授予权限,申请
            ActivityCompat.requestPermissions(OkHttpUtilsActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            //权限已经授予
        }
    }


    /**
     * 运行时权限 回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //权限授予:成功

                } else {//权限授予失败
                    Toast.makeText(this, "申请权限被拒绝，关闭当前页面", Toast.LENGTH_SHORT).show();
                    finish();//关闭
                }
                break;

        }
    }


    public class MyStringCallback extends StringCallback {


        @Override
        public void onBefore(Request request, int id) {
            setTitle("loading...");
        }

        @Override
        public void onAfter(int id) {
            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            tvResposeInfo.setText("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            tvResposeInfo.setText("onResponse:\n" + response);

            switch (id) {
                case 100:
                    Toast.makeText(OkHttpUtilsActivity.this, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(OkHttpUtilsActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
//            Log.e(TAG, "inProgress:" + progress);
//            mProgressBar.setProgress((int) (100 * progress));
        }
    }
}

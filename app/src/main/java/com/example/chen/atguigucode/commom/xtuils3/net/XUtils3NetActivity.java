package com.example.chen.atguigucode.commom.xtuils3.net;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.atguigucode.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

@ContentView(R.layout.activity_xutils3_net)
public class XUtils3NetActivity extends Activity {


    @ViewInject(R.id.bt_xutils_net_get)
    Button btXutilsNetGet;

    @ViewInject(R.id.bt_xutils_net_post)
    Button btXutilsNetPost;

    @ViewInject(R.id.bt_xutils_net_download)
    Button btXutilsNetDownload;

    @ViewInject(R.id.bt_xutils_net_upload)
    Button btXutilsNetUpload;


    @ViewInject(R.id.tv_top_tile)
    TextView tvTopTile;

    @ViewInject(R.id.pb_xutil3_net)
    ProgressBar pbXutil3Net;

    @ViewInject(R.id.tv_xutils3_net_info)
    TextView tvXutils3NetInfo;


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_xutils3_net);

        mContext = XUtils3NetActivity.this;

        x.view().inject(this);

        initTitle();
        //checkRuntimePermission();

    }

    /**
     * 初始化Tile
     */
    private void initTitle() {
        tvTopTile.setText("xutils3联网模块");
    }

    @Event(value = {R.id.bt_xutils_net_get, R.id.bt_xutils_net_download, R.id.bt_xutils_net_post, R.id.bt_xutils_net_upload})
    private void itemOnclick(View view) {
        switch (view.getId()) {
            case R.id.bt_xutils_net_get:
                Toast.makeText(mContext, "GET请求", Toast.LENGTH_SHORT).show();
                xutilGet();
                break;

            case R.id.bt_xutils_net_post:
                Toast.makeText(mContext, "POST请求", Toast.LENGTH_SHORT).show();
                xutilPost();

                break;
            case R.id.bt_xutils_net_download:
                Toast.makeText(mContext, "下载文件", Toast.LENGTH_SHORT).show();
                xutils3FileDownload();
                break;

            case R.id.bt_xutils_net_upload:
                Toast.makeText(mContext, "上传文件", Toast.LENGTH_SHORT).show();
                xutils3FileUpload();
                break;


        }
    }

    /**
     * xutils3 下载文件
     */
    private void xutils3FileDownload() {
        String url = "http://vfx.mtime.cn/Video/2017/08/02/mp4/170802074323236656.mp4";
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" + "xutils3_download.mp4";


        RequestParams params = new RequestParams(url);

        params.setAutoRename(false);//自动命名
        params.setSaveFilePath(path);//设置保存路径
        params.setCancelFast(true);

//        x.http().get(params, new Callback.ProgressCallback<File>() {
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

                Log.d("XUtils3NetActivity", "onWaiting:");
            }

            @Override
            public void onStarted() {
                Log.d("XUtils3NetActivity", "onStarted:");

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                Log.d("XUtils3NetActivity", "onLoading:" + current + "/" + total);
                tvXutils3NetInfo.setText("进度:"+current+"/"+total);

                pbXutil3Net.setMax((int) total);
                pbXutil3Net.setProgress((int) current);

            }

            @Override
            public void onSuccess(File result) {
                String filepath = result.getAbsolutePath();

                tvXutils3NetInfo.setText("保存位置:" + filepath);

                Log.d("XUtils3NetActivity", "onSuccess:" + filepath);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("XUtils3NetActivity", "onError:" + ex.getMessage());

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("XUtils3NetActivity", "onCancelled:" + cex.getMessage());

            }

            @Override
            public void onFinished() {
                Log.d("XUtils3NetActivity", "onFinished:");

            }
        });


    }

    /**
     * xutils3 上传文件
     */
    private void xutils3FileUpload() {

        //上传地址
        String url = "http://192.168.1.104:8080/FileUpload/FileUploadServlet";

        //待上传文件
        File file = new File(Environment.getExternalStorageDirectory(),"Download/xutils3_download.mp4");

        RequestParams params = new RequestParams(url);
        params.setMultipart(true);//表单方式提交

        params.addBodyParameter("File",file,null,"xutls3_upload.mp4");

        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

                Log.d("XUtils3NetActivity", "onWaiting:");
            }

            @Override
            public void onStarted() {
                Log.d("XUtils3NetActivity", "onStarted:");

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                Log.d("XUtils3NetActivity", "onLoading:" + current + "/" + total);
                tvXutils3NetInfo.setText("进度:"+current+"/"+total);

                pbXutil3Net.setMax((int) total);
                pbXutil3Net.setProgress((int) current);

            }

            @Override
            public void onSuccess(File result) {
                String filepath = result.getAbsolutePath();

                tvXutils3NetInfo.setText("保存位置:" + filepath);

                Log.d("XUtils3NetActivity", "onSuccess:" + filepath);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("XUtils3NetActivity", "onError:" + ex.getMessage());

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("XUtils3NetActivity", "onCancelled:" + cex.getMessage());

            }

            @Override
            public void onFinished() {
                Log.d("XUtils3NetActivity", "onFinished:");

            }
        });



    }

    /**
     * 使用xutils3 执行post请求
     */
    private void xutilPost() {
        tvXutils3NetInfo.setText("");
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

        RequestParams params = new RequestParams(url);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.d("XUtils3NetActivity", "POST:onSuccess" + result);
                tvXutils3NetInfo.setText(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("XUtils3NetActivity", "POST:onError" + ex.getMessage());

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("XUtils3NetActivity", "POST:onCancelled" + cex.getMessage());

            }

            @Override
            public void onFinished() {
                Log.d("XUtils3NetActivity", "POST:onFinished");

            }
        });

    }

    /**
     * 使用xutils 执行get 请求
     */
    private void xutilGet() {
        tvXutils3NetInfo.setText("");
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";


        RequestParams params = new RequestParams(url);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("XUtils3NetActivity", "onSuccess:--");

                tvXutils3NetInfo.setText("GET" + result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("XUtils3NetActivity", "onError:--");
                tvXutils3NetInfo.setText(ex.getMessage());

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("XUtils3NetActivity", "onCancelled:--");

            }

            @Override
            public void onFinished() {
                Log.d("XUtils3NetActivity", "onFinished:--");

            }
        });


    }


    /**
     * 动态权限检查
     */

    public void checkRuntimePermission() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //如果未授予权限,申请
            ActivityCompat.requestPermissions(XUtils3NetActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        } else if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {

            //检查联网权限
            ActivityCompat.requestPermissions(XUtils3NetActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        } else {
            //权限已经授予
            Toast.makeText(mContext, "权限授予", Toast.LENGTH_SHORT).show();
        }
    }


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
            case 2: //检查联网权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //权限授予:成功

                } else {//权限授予失败
                    Toast.makeText(this, "申请权限被拒绝，关闭当前页面", Toast.LENGTH_SHORT).show();
                    finish();//关闭
                }
                break;
        }
    }
}

package com.example.chen.atguigucode.commom.afinal;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chen.atguigucode.R;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AfinalActivity extends AppCompatActivity {

    @BindView(R.id.bt_afinal_text)
    Button btAfinalText;
    @BindView(R.id.bt_afinal_img)
    Button btAfinalImg;
    @BindView(R.id.bt_afinal_file_upload)
    Button btAfinalFileUpload;
    @BindView(R.id.bt_afinal_text_file_download)
    Button btAfinalTextFileDownload;
    @BindView(R.id.tv_afinal_info)
    TextView tvAfinalInfo;
    @BindView(R.id.iv_afinal_single)
    ImageView ivAfinalSingle;
    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afinal2);
        ButterKnife.bind(this);


        tvTopTile.setText("Afinal");
    }

    @OnClick({R.id.bt_afinal_text, R.id.bt_afinal_img, R.id.bt_afinal_file_upload, R.id.bt_afinal_text_file_download})
    public void item(View view) {

        switch (view.getId()) {
            case R.id.bt_afinal_text:
                Log.d("AfinalActivity", "afinal:文本数据");
                textData();
                break;
            case R.id.bt_afinal_img:
                Log.d("AfinalActivity", "afinal:img数据");
                imgData();
                break;
            case R.id.bt_afinal_file_upload:
                Log.d("AfinalActivity", "afinal:文件上传");
                try {
                    uploadFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_afinal_text_file_download:
                Log.d("AfinalActivity", "afinal:文件下载数据");
                downloadFile();
                break;
        }
    }


    /**
     * 三: 文件上传
     * 1. 准备上传服务器地址 和待上传文件路径
     * 2. 创建finalhttp对象
     * 3. 执行
     */
    private void uploadFile() throws FileNotFoundException {
        tvAfinalInfo.setVisibility(View.VISIBLE);

        //1.
        //上传地址
        String url = "http://192.168.1.104:8080/FileUpload/FileUploadServlet";

        //待上传文件
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/afinal_download.mp4";
        File file = new File(Environment.getExternalStorageDirectory(),"Download/afinal_download.mp4");

        //2.
        FinalHttp finalHttp = new FinalHttp();

        //3.
        AjaxParams params = new AjaxParams();
        params.put("File",file);

        finalHttp.post(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onStart() {
                tvAfinalInfo.setText("onStart");
            }

            @Override
            public void onLoading(long count, long current) {
                tvAfinalInfo.setText("onLoading:"+current+"/"+count);
                Log.d("AfinalActivity", current + "/" + count);
            }

            @Override
            public void onSuccess(Object o) {
                tvAfinalInfo.setText("上传成功");
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                tvAfinalInfo.setText("onFailure:"+strMsg);
            }
        });


    }

    /**
     * 四: 文件下载
     * 1. 准备下载地址和存储路径
     * 2. 创建finalhttp
     * 3. 执行finalhttp.dowload(url,filepath,callback)
     *
     */
    private void downloadFile() {
        //1.
        String url = "http://vfx.mtime.cn/Video/2017/07/13/mp4/170713143008019651.mp4";

        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/afinal_download.mp4";

        //2.
        FinalHttp finalHttp = new FinalHttp();


        finalHttp.download(url, filePath, new AjaxCallBack<File>() {


            @Override
            public void onStart() {
                tvAfinalInfo.setText("onStart:");
            }

            @Override
            public void onLoading(long count, long current) {
                tvAfinalInfo.setText("onLoading:"+current+"/"+count);
            }

            @Override
            public void onSuccess(File file) {
                tvAfinalInfo.setText("onSuccess:"+file.getAbsolutePath());
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                tvAfinalInfo.setText("onFailur:"+strMsg);
            }
        });


    }


    /**
     * 一: 使用Afinal 加载文本数据
     * 1. 获取url
     * 2. 创建finalhttp
     * 3. 执行finalhttp.get(url,callback)
     */
    private void textData() {
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

        FinalHttp  http = new FinalHttp();
        http.get(url, new AjaxCallBack<String>() {
            @Override
            public void onStart() {
                tvAfinalInfo.setText("onStart");
            }

            @Override
            public void onSuccess(String s) {
                tvAfinalInfo.setText("onSuccess:"+s);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                tvAfinalInfo.setText("onFailure:"+ strMsg);
            }
        });

        tvAfinalInfo.setVisibility(View.VISIBLE);

        ivAfinalSingle.setVisibility(View.GONE);
    }

    /**
     * 二: 使用final加载网络图片
     * 1. 创建afinalbitmap 对象
     * 2. 设置默认图像
     * 3. 设置网络图像
     */
    private void imgData() {
        tvAfinalInfo.setVisibility(View.GONE);

        ivAfinalSingle.setVisibility(View.VISIBLE);
        FinalBitmap bitmap = FinalBitmap.create(this);


        //2.
        bitmap.configLoadingImage(R.drawable.atguigu_logo);//加载时默认显示的图片

        //3.
        String url = "http://img5.mtime.cn/mg/2017/03/13/162056.98478188.jpg";
        bitmap.display(ivAfinalSingle, url);

    }

}

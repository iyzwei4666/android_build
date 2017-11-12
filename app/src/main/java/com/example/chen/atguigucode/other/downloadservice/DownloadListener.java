package com.example.chen.atguigucode.other.downloadservice;

/**
 * Created by chen on 2017/8/20.
 * 定义下载监听借口
 * 1. 下载进度
 * 2. 下载成功
 * 3. 下载失败
 * 4. 下载取消
 */

public interface DownloadListener {

    void onProgress(int progress);
    void onSuccess(); //下载成功
    void onFailed();  //下载失败
    void onPaused();  //暂停
    void onCanceled();//取消下载



}

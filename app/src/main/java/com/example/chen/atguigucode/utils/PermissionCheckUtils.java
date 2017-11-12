package com.example.chen.atguigucode.utils;

/**
 * Created by chen on 2017/8/14.
 * 6.0以上动态权限
 */

public class PermissionCheckUtils {

/**

    public  void checkRuntimePermission() {
        if(ContextCompat.checkSelfPermission(contentxt, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            //如果未授予权限,申请
            ActivityCompat.requestPermissions(OkHttpUtilsActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            //权限已经授予
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

        }
    }

    */
}

package com.example.chen.atguigucode.commom.jsonnative;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GsonParseActivity extends AppCompatActivity {


    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_gson_to_java)
    Button btGsonToJava;
    @BindView(R.id.bt_gson_to_java_list)
    Button btGsonToJavaList;
    @BindView(R.id.bt_gson_json)
    Button btGsonJson;
    @BindView(R.id.bt_gson_to_json_array)
    Button btGsonToJsonArray;
    @BindView(R.id.tv_gson_org_data)
    TextView tvGsonOrgData;
    @BindView(R.id.tv_gson_later_data)
    TextView tvGsonLaterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_parse);
        ButterKnife.bind(this);

        initTitle();
    }


    private void initTitle() {
        tvTopTile.setText("Gson解析");
    }

    @OnClick({R.id.bt_gson_json, R.id.bt_gson_to_java, R.id.bt_gson_to_java_list, R.id.bt_gson_to_json_array})
    public void itemOnclick(View view) {
        switch (view.getId()){
            case R.id.bt_gson_to_java:
                Log.d("GsonParseActivity", "json->java");
                jsonToJava();

                break;
            case R.id.bt_gson_to_java_list:
                Log.d("GsonParseActivity", "json->java list");
                jsonArrayToJavaList();
                break;
            case R.id.bt_gson_json:
                Log.d("GsonParseActivity", "java->json");
                javaToJson();
                break;
            case R.id.bt_gson_to_json_array:
                Log.d("GsonParseActivity", "java->json array");
                javaListToJsonArray();
                break;
        }



    }

    /**
     * 四: java集合转换为json数组[]
     * 1. 获取java 集合
     * 2. 使用gson转换
     * 3. 数据展示
     */
    private void javaListToJsonArray() {

        //1.
        List<JsonBean> list = new ArrayList<>();
        JsonBean jsonBean =  new JsonBean(12,"鲍鱼",12.32,"http://img5.mtime.cn/mg/2017/08/04/165113.24946335.jpg");
        JsonBean jsonBean2 =  new JsonBean(12,"熊掌",123.32,"http://img5.mtime.cn/mg/2017/08/04/165113.24946335.jpg");

        list.add(jsonBean);
        list.add(jsonBean2);


        //2.
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list);

        //3.
        tvGsonOrgData.setText(list.toString());
        tvGsonLaterData.setText(jsonStr);


    }

    /**
     * 三: java对象转换为json字符串
     * 1. 获取java对象
     * 2. 使用gson转换
     *      > 创建gson对象
     *      > 使用gson.toJson() 方法
     * 3. 数据展示
     *
     */
    private void javaToJson() {

        //1 .
        JsonBean jsonBean = new JsonBean(12,"鲍鱼",12.32,"http://img5.mtime.cn/mg/2017/08/04/165113.24946335.jpg");

        //2.
        Gson gson = new Gson();
        String jsonStr = gson.toJson(jsonBean);

        //3.
        tvGsonOrgData.setText(jsonBean.toString());
        tvGsonLaterData.setText(jsonStr);


    }


    /**
     * 二: json数组 转换为java集合
     * 1. 准备json数据
     * 2. 使用Gson解析
     *      > 创建gson对象
     *      > 使用gson.from(jsonStr,type)
     * 3. 展示数据
     */
    private void jsonArrayToJavaList() {
        String json = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f1.jpg\",\n" +
                "        \"name\": \"大虾1\",\n" +
                "        \"price\": 12.3\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"imagePath\": \"http://192.168.10.165:8080/f2.jpg\",\n" +
                "        \"name\": \"大虾2\",\n" +
                "        \"price\": 12.5\n" +
                "    }\n" +
                "]";


        //2.
        Gson gson = new Gson();


        Type type = new TypeToken<List<JsonBean>>() {
        }.getType();
        List<JsonBean> list = gson.fromJson(json,type);

        //3.
        tvGsonOrgData.setText(json);
        tvGsonLaterData.setText(list.toString());


    }

    /**
     * 一: json 数据转换为java 对象
     * 1. 获取json数据
     * 2. 使用gson解析 --添加jar包
     *      > 创建gson对象
     *      > class.obj =  gson.from(str,class)
     * 3. 展示数据
     */
    private void jsonToJava() {
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}";

        //2. 解析
        Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson(json, JsonBean.class);

        //3. 展示数据
        tvGsonOrgData.setText(json);
        tvGsonLaterData.setText(jsonBean.toString());


    }
}
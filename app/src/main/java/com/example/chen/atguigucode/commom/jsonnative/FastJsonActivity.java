package com.example.chen.atguigucode.commom.jsonnative;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.chen.atguigucode.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FastJsonActivity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_fastjson_to_java)
    Button btFastjsonToJava;
    @BindView(R.id.bt_fastjson_to_java_list)
    Button btFastjsonToJavaList;
    @BindView(R.id.bt_fastjson_json)
    Button btFastjsonJson;
    @BindView(R.id.bt_fastjson_to_json_array)
    Button btFastjsonToJsonArray;
    @BindView(R.id.tv_fastjson_org_data)
    TextView tvFastjsonOrgData;
    @BindView(R.id.tv_fastjson_later_data)
    TextView tvFastjsonLaterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_json);
        ButterKnife.bind(this);

        //初始化titl
        tvTopTile.setText("FastJson解析");
    }




    @OnClick({R.id.bt_fastjson_to_java,R.id.bt_fastjson_to_java_list,R.id.bt_fastjson_json,R.id.bt_fastjson_to_json_array})
    public  void itemOnclick(View view){
        switch (view.getId()) {
            case R.id.bt_fastjson_to_java :
                Log.d("FastJsonActivity", "json->java");
                jsonToJava();
                break;
            case R.id.bt_fastjson_to_java_list :
                Log.d("FastJsonActivity", "json->java list");
                jsonToJavaList();
                break;
            case R.id.bt_fastjson_json :
                Log.d("FastJsonActivity", "java->json");
                javaToJson();
                break;
            case R.id.bt_fastjson_to_json_array :
                Log.d("FastJsonActivity", "java->json array");
                javaToJsonArray();
                break;
        }
    }


    /**
     * 一: json对象转换为java对象 {} ->
     * 1. 获取json数据
     * 2. 使用fastjson 解析 JSON.paraseObject(jsonStr,Bean.class)
     * 3. 数据展示
     */
    private void jsonToJava() {
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}";
        //2
        JsonBean jsonBean = JSON.parseObject(json, JsonBean.class);

        //3.
        tvFastjsonOrgData.setText(json);
        tvFastjsonLaterData.setText(jsonBean.toString());

    }


    /**
     * 二: json数组转换为java 集合 [] ->
     * 1. 获取json数据
     * 2. 使用fastjson 解析
     * 3. 数据展示
     */
    private void jsonToJavaList() {

        //1.
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
        List<JsonBean> beanList = JSON.parseArray(json, JsonBean.class);

        //3.
        tvFastjsonOrgData.setText(json);
        tvFastjsonLaterData.setText(beanList.toString());


    }


    /**
     * 三: java对象转换为json对象  -> {}
     * 1. 获取java数据
     * 2. 使用fastjson 转换
     * 3. 数据展示
     */
    private void javaToJson() {
        //1 .
        JsonBean jsonBean = new JsonBean(12,"鲍鱼",12.32,"http://img5.mtime.cn/mg/2017/08/04/165113.24946335.jpg");

        //2.
        String jsonString= JSON.toJSONString(jsonBean);


        //3.
        tvFastjsonOrgData.setText(jsonBean.toString());
        tvFastjsonLaterData.setText(jsonString);
    }


    /**
     * 四: java集合转换为json 数组  -> []
     * 1. 获取json数据
     * 2. 使用fastjson 解析
     * 3. 数据展示
     */

    private void javaToJsonArray() {

        //1.
        List<JsonBean> list = new ArrayList<>();
        JsonBean jsonBean =  new JsonBean(12,"鲍鱼",12.32,"http://img5.mtime.cn/mg/2017/08/04/165113.24946335.jpg");
        JsonBean jsonBean2 =  new JsonBean(12,"熊掌",123.32,"http://img5.mtime.cn/mg/2017/08/04/165113.24946335.jpg");

        list.add(jsonBean);
        list.add(jsonBean2);


        //2.
        String jsonString = JSON.toJSONString(list);

        //3
        tvFastjsonOrgData.setText(list.toString());
        tvFastjsonLaterData.setText(jsonString);


    }







}

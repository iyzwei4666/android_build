package com.example.chen.atguigucode.commom.jsonnative;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.atguigucode.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NativitJsonActivity extends Activity {

    @BindView(R.id.tv_top_tile)
    TextView tvTopTile;
    @BindView(R.id.bt_to_java)
    Button btToJava;
    @BindView(R.id.bt_to_java_list)
    Button btToJavaList;
    @BindView(R.id.bt_com_parse)
    Button btComParse;
    @BindView(R.id.bt_native_special)
    Button btNativeSpecial;
    @BindView(R.id.tv_org_data)
    TextView tvOrgData;
    @BindView(R.id.tv_later_data)
    TextView tvLaterData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nativit_json);
        ButterKnife.bind(this);


        initTitle();
    }

    private void initTitle() {
        tvTopTile.setText("原生json解析");
    }


    @OnClick({R.id.bt_to_java, R.id.bt_to_java_list, R.id.bt_com_parse, R.id.bt_native_special})
    public void itemOnclick(View view) {
        switch (view.getId()) {
            case R.id.bt_to_java:
                Log.d("NativitJsonActivity", "json->java");
                parseJsonByNativit();
                break;
            case R.id.bt_to_java_list:
                Log.d("NativitJsonActivity", "json->java list");
                parseJsonAarrayByNative();
                break;
            case R.id.bt_com_parse:
                Log.d("NativitJsonActivity", "com json->java");
                parseComJsonByNativit();
                break;
            case R.id.bt_native_special:
                Log.d("NativitJsonActivity", " special json->java");
                parseSpecialJsonByNativte();
                break;
        }
    }

    /**
     * 四: 特殊json 数据的解析
     * 其中的数据 key 为 0, 1 等整数
     */
    private void parseSpecialJsonByNativte() {
        String json = "{\n" +
                "    \"code\": 0,\n" +
                "    \"list\": {\n" +
                "        \"0\": {\n" +
                "            \"aid\": \"6008965\",\n" +
                "            \"author\": \"哔哩哔哩番剧\",\n" +
                "            \"coins\": 170,\n" +
                "            \"copyright\": \"Copy\",\n" +
                "            \"create\": \"2016-08-25 21:34\"\n" +
                "        },\n" +
                "        \"1\": {\n" +
                "            \"aid\": \"6008938\",\n" +
                "            \"author\": \"哔哩哔哩番剧\",\n" +
                "            \"coins\": 404,\n" +
                "            \"copyright\": \"Copy\",\n" +
                "            \"create\": \"2016-08-25 21:33\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        SpecialJsonBean specialJsonBean = new SpecialJsonBean();


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int code = jsonObject.optInt("code");
        List<SpecialJsonBean.ListBean> list = new ArrayList<>();


        JSONObject listObj = jsonObject.optJSONObject("list");

        if(listObj!=null && listObj.length()>0) {



            for (int i=0;i<listObj.length(); i++){

                JSONObject jsonObject1 = listObj.optJSONObject(i+"");
                String aid = jsonObject1.optString("aid");
                String author = jsonObject1.optString("author");

                int coins = jsonObject1.optInt("coins");

                String copyright = jsonObject1.optString("copyright");
                String create = jsonObject1.optString("create");


                SpecialJsonBean.ListBean listBean = new SpecialJsonBean.ListBean(aid,author,coins,copyright,create);
                list.add(listBean);

            }



        }


        specialJsonBean.setCode(code);
        specialJsonBean.setList(list);

        tvOrgData.setText(json);
        tvLaterData.setText(specialJsonBean.toString());



    }

    /**
     * 三: 复杂的json数据的解析
     */


    private void parseComJsonByNativit() {
        tvOrgData.setText("");

        //本地数据
      String json = "{\n" +
              "    \"rs_code\": \"1000\",\n" +
              "    \"data\": {\n" +
              "        \"count\": 5,\n" +
              "        \"items\": [\n" +
              "            {\n" +
              "                \"id\": 45,\n" +
              "                \"title\": \"坚果\"\n" +
              "            },\n" +
              "            {\n" +
              "                \"id\": 132,\n" +
              "                \"title\": \"炒货\"\n" +
              "            },\n" +
              "            {\n" +
              "                \"id\": 166,\n" +
              "                \"title\": \"蜜饯\"\n" +
              "            },\n" +
              "            {\n" +
              "                \"id\": 195,\n" +
              "                \"title\": \"果脯\"\n" +
              "            },\n" +
              "            {\n" +
              "                \"id\": 196,\n" +
              "                \"title\": \"礼盒\"\n" +
              "            }\n" +
              "        ]\n" +
              "    },\n" +
              "    \"rs_msg\": \"success\"\n" +
              "}";


        ComJsonBean jsonbean = new ComJsonBean();

        //2. 解析json 数据
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(json);


            String rs_code = jsonObject.optString("rs_code");
            String rs_msg = jsonObject.optString("rs_msg");

            //添加第一层
            jsonbean.setRs_code(rs_code);
            jsonbean.setRs_msg(rs_msg);



            //第二层javabean
            ComJsonBean.DataBean dataBean = new ComJsonBean.DataBean();

            JSONObject jsonObjData = jsonObject.optJSONObject("data");

            int count = jsonObjData.optInt("count");
            dataBean.setCount(count);


            List<ComJsonBean.DataBean.ItemsBean> items  = new ArrayList<>();
            dataBean.setItems(items);



            JSONArray itemsArray = jsonObjData.optJSONArray("items");

            if (itemsArray != null && itemsArray.length() > 0) {
                for (int i = 0; i < itemsArray.length(); i++) {

                    JSONObject item = itemsArray.getJSONObject(i);
                    int itemId = item.optInt("id");
                    String itemTitle = item.optString("title");


                    //第三层item
                    ComJsonBean.DataBean.ItemsBean itemsBean = new ComJsonBean.DataBean.ItemsBean();
                    itemsBean.setId(itemId);
                    itemsBean.setTitle(itemTitle);

                    items.add(itemsBean);
                }
            }

            jsonbean.setData(dataBean);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        tvOrgData.setText(json);
        tvLaterData.setText(jsonbean.toString());

    }

    /**
     * 1. 获取json数组
     * 2. 创建json对象
     * 3. 解析到java数组
     */
    private void parseJsonAarrayByNative() {

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

        List<JsonBean> beanList = new ArrayList<>();

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);

            if (jsonArray != null && jsonArray.length() > 0) {

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jobj = jsonArray.getJSONObject(i);

                    int id = jobj.optInt("id");
                    String name = jobj.optString("name");
                    Double price = jobj.optDouble("price");
                    String imagePath = jobj.optString("imagePath");
                    JsonBean jsonbean = new JsonBean(id, name, price, imagePath);

                    beanList.add(jsonbean);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        tvOrgData.setText(json);
        tvLaterData.setText(beanList.toString());

    }

    /**
     * 1. 原生方法,解析json->java
     */
    private void parseJsonByNativit() {
        String json = "{\n" +
                "\t\"id\":2, \"name\":\"大虾\", \n" +
                "\t\"price\":12.3, \n" +
                "\t\"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}";

        JSONObject jobj = null;
        try {
            jobj = new JSONObject(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        int id = jobj.optInt("id");
        String name = jobj.optString("name");
        Double price = jobj.optDouble("price");
        String imagePath = jobj.optString("imagePath");
        JsonBean jsonbean = new JsonBean(id, name, price, imagePath);


        tvOrgData.setText(json);
        tvLaterData.setText(jsonbean.toString());


    }
}

package com.cyw.a2018011201;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click1(View v)
    {
        //1.建立一個Queue
        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        //2.建立一個Request(1.網址2.資料抓成功了怎麼半3.資料抓失敗了怎麼半)
        StringRequest request=new StringRequest("http://data.ntpc.gov.tw/od/data/api/BF90FA7E-C358-4CDA-B579-B6C84ADC96A1?$format=json", new Response.Listener<String>() {
            @Override
            //response就是抓到的資料(此method不用寫執行緒)
            public void onResponse(String response) {
                Log.d("Net",response);
                //解析資料倆方法1. JSONArray+JSONObject, 方法2.建立一個類別,放入Gson即可使用
                // 以下用JSONArray & JSONObject解析資料 (JSON的格式 , {}代表物件, []代表陣列)
                try {
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++) {
                        JSONObject obj = array.getJSONObject(i);
                        String str1 = obj.getString("district");
                        Log.d("JsonArray", str1);
                        String str2 = obj.getString("address");
                        Log.d("JsonArray", str2);
                        String str3 = obj.getString("tel");
                        Log.d("JsonArray", str3);
                        String str4 = obj.getString("opening_hours");
                        Log.d("JsonArray", str4);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //以下用Gson法,要先把類別建好, 如animal
                // 先去google -> Gson+ grandle 找到import grandle的指令 ( https://mvnrepository.com/artifact/com.google.code.gson/gson)
                Gson gson=new Gson();
                animal[] houses=gson.fromJson(response,animal[].class);
                for (animal a : houses)
                {
                    Log.d("Gson", a.district + "," + a.address);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //3. 把2抓到的資料放到1
        queue.add(request);
        //4.執行
        queue.start();
    }
}

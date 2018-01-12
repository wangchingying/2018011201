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
        StringRequest request=new StringRequest("https://www.mobile01.com/rss/news.xml", new Response.Listener<String>() {
            @Override
            //response就是抓到的資料(此method不用寫執行緒)
            public void onResponse(String response) {
                Log.d("Net",response);
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

package com.example.dddddddd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    private final String path = "http://wthrcdn.etouch.cn/weather_mini?city=";
    private URL murl;
    private HttpURLConnection connection;
    private String result;
    private JSONObject resultJSON;
    private JSONObject dataJSON;
    private JSONObject todayJSON;
    private String city;
    private JSONArray forecastJSONArray;
    private List<Map<String, Object>> list;
    private ListView mlistView;
    private TextView content_fore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i=getIntent();
        city=i.getStringExtra("data");
        content_fore=findViewById(R.id.t1);
        new Thread(){
            public void run(){
                GetWeather(city);
            }
        }.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            String str = todayJSON.getString("date");
            content_fore.setText(str +"\n");
            str = todayJSON.getString("type");

            content_fore.append(str+"\n");
            str = todayJSON.getString("high");

            content_fore.append(str+"\n");
            str = todayJSON.getString("low");

            content_fore.append(str+"\n");
            str = todayJSON.getString("fengli");

            content_fore.append(str+"\n");
            str = todayJSON.getString("fengxiang");

            content_fore.append(str+"\n");
            str = dataJSON.getString("ganmao");
            content_fore.append(str+"\n");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void GetWeather(String city) {
        try {
            murl = new URL(path + city);
            connection = (HttpURLConnection) murl.openConnection();
            connection.setConnectTimeout(5 * 1000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            InputStream in = connection.getInputStream();
            BufferedReader reader = null;
            StringBuilder response = null;

            if (in != null) {
                reader = new BufferedReader(new InputStreamReader(in));
                response = new StringBuilder();
                while ((result = reader.readLine()) != null) {
                    response.append(result);
                }
            }
            result = response.toString();
            connection.disconnect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result != null)
        {
            try {
                resultJSON = new JSONObject(result);
                dataJSON = resultJSON.getJSONObject("data");
                forecastJSONArray = dataJSON.getJSONArray("forecast");
                todayJSON = (JSONObject) forecastJSONArray.get(2);
                String str = dataJSON.getString("ganmao");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}

package com.example.experiment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

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

public class MainActivity extends AppCompatActivity {

    private EditText e1;
    private Button btn;
    private TextView t1;
    private final String path = "http://wthrcdn.etouch.cn/weather_mini?city=";
    private String city;
    private String str = null;
    private URL murl;
    private HttpURLConnection connection;
    private String result;

    private JSONObject resultJSON;
    private JSONObject dataJSON;
    private JSONObject todayJSON;
    private JSONArray forecastJSONArray;
    //private List<Map<String, Object>> list;
    //private ListView mlistView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.e1);
        btn = findViewById(R.id.b1);
        t1 = findViewById(R.id.t1);

    }


    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                //Toast.makeText(this, "gggg", Toast.LENGTH_SHORT).show();
                new Thread() {
                    @Override
                    public void run() {
                        city = e1.getText().toString();
                        GetWeather(city);
                    }
                }.start();
                break;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {

            str = todayJSON.getString("date");
            t1.setText(str + "\n");
            str = todayJSON.getString("type");
            t1.append(str + "\n");
            str = todayJSON.getString("high");
            t1.append(str + "\n");
            str = todayJSON.getString("low");
            t1.append(str + "\n");
            str = todayJSON.getString("fengli");
            t1.append(str + "\n");
            str = todayJSON.getString("fengxiang");
            t1.append(str + "\n");
            str = dataJSON.getString("ganmao");
            t1.append(str + "\n");

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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        t1.setText(result);


        //进行数据解析
        if (result != null) {
            try {
                resultJSON = new JSONObject(result);
                dataJSON = resultJSON.getJSONObject("data");
                forecastJSONArray = dataJSON.getJSONArray("forecast");
                todayJSON = (JSONObject) forecastJSONArray.get(1);
                String str = dataJSON.getString("ganmao");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

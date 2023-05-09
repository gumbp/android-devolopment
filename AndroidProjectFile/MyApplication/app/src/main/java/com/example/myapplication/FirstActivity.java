package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        TextView text = (TextView)findViewById(R.id.text);
        String name = "B18060617";
        String password = "123456789";
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String y = bundle.getString("y","");
        String p = bundle.getString("p","");
        String t = new String();
        if(name.equals(y)) {
            if(password.equals(p)) {
                t="登陆成功";
            }else {
                t="密码错误";
            }
        } else {
            t="用户名错误";
        }
        text.setText(t);
        //BlankFragment fragment = new BlankFragment();
        //fragment.setTextView(t);
        //getFragmentManager().beginTransaction().replace(R.id.act,fragment).commit();
    }
}
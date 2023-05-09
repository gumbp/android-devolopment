package com.example.list;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    String st0=new String("2学分，王亚石，教4-309，周二34节");
    String st1=new String("2学分，吴尘，教4-308，周三12节");
    String st2=new String("2学分，张载龙，教4-309，周五12节");
    String st3=new String("2学分，魏飞，教4-312，周三34节");
    String st4=new String("2学分，郭婧，教4-309，周一34节");
    String st5=new String("3.5学分，教4-202，周二67节&周四89节");
    private String [] data={"移动应用开发技术","数据挖掘","无线定位技术","嵌入式系统原理与应用","物联网感知技术应用","毛泽东思想和中国特色社会主义理论概述"};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String> (
                MainActivity.this, android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                switch (position) {
                    case 0:
                        builder.setTitle("移动应用开发技术");
                        builder.setMessage(st0);
                        break;
                    case 1:
                        builder.setTitle("数据挖掘");
                        builder.setMessage(st1);
                        break;
                    case 2:
                        builder.setTitle("无线定位技术");
                        builder.setMessage(st2);
                        break;
                    case 3:
                        builder.setTitle("嵌入式系统原理与应用");
                        builder.setMessage(st3);
                        break;
                    case 4:
                        builder.setTitle("物联网感知技术应用");
                        builder.setMessage(st4);
                        break;
                    case 5:
                        builder.setTitle("毛泽东思想和中国特色社会主义理论概述");
                        builder.setMessage(st5);
                        break;
                }
                AlertDialog dialog = builder.create();
                dialog.show();
                }
        });

    }
}

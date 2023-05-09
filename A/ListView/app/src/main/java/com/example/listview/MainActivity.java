package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView list_one;
    private MyAdapter myAdapter=null;
    private List<Data> mData=null;
    private Context mContext=null;

    private TextView txt_empty;

    private ImageView btn_add;
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    /*private Button btn_clear;
    private Button btn_delete2;
    private int flag=1;
    private Data mData0=null;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        bindViews();
        mData = new LinkedList<Data>();

        mData.add(new Data(R.mipmap.ic_datam,"数据挖掘"));
        mData.add(new Data(R.mipmap.ic_wl,"无线定位技术"));
        mData.add(new Data(R.mipmap.ic_ad,"安卓开发"));

        myAdapter = new MyAdapter((LinkedList<Data>) mData, mContext);
        list_one.setAdapter(myAdapter);
        list_one.setOnItemClickListener(this::onItemClick);
    }

    public void onItemClick(AdapterView<?>parent,View view,int position,long id){

       switch(position){

           case 0:
               Dialog("课程编号：02","授课老师：吴尘","教室：教四-308","时间：周四8：00~9：35","学分：2");
               break;
           case 1:
               Dialog("课程编号：03","授课老师：张载龙","教室：教四-309","时间：周四8：00~9：35","学分：2");
               break;
           case 2:
               Dialog("课程编号：01","授课老师：王亚石","教室：教四-309","时间：周二9：50~11：25","学分：2");
               break;

       }

    }

  public void Dialog(String s1,String s2,String s3,String s4,String s5){
        final String[] mesg=new String[]{s1,s2,s3,s4,s5};
        alert=null;
        builder = new AlertDialog.Builder(mContext);
        alert=builder
                .setTitle("课程信息")
                .setItems(mesg, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();
        alert.show();
    }


    private void bindViews(){
        list_one=(ListView)findViewById(R.id.list_one);
        txt_empty=(TextView)findViewById(R.id.txt_empty);
        btn_add=(ImageView) findViewById(R.id.btn_add);
        /*btn_clear=(Button)findViewById(R.id.btn_delete);
        btn_delete2=(Button)findViewById(R.id.btn_delete2);*/

        txt_empty.setText("暂无数据!");
        list_one.setAdapter(myAdapter);
        list_one.setEmptyView(txt_empty);

        btn_add.setOnClickListener(this::onClick);
        /*btn_clear.setOnClickListener(this::onClick);
        btn_delete2.setOnClickListener(this::onClick);*/

    }

   /* private void updateListItem(int postion,Data mData){

        int visiblePosition=list_one.getFirstVisiblePosition();

        View v=list_one.getChildAt(postion-visiblePosition);

        ImageView img=(ImageView)v.findViewById(R.id.img_icon);

        TextView tv=(TextView)v.findViewById(R.id.txt_content);

        img.setImageResource(mData.getId());

        tv.setText(mData.getContent());
    }*/

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_add:
                    myAdapter.add(new Data(R.mipmap.ic_launcher, "新课程" ));
                break;
           /* case R.id.btn_delete:
                myAdapter.clear();
                break;
            case R.id.btn_delete2:
                myAdapter.remove(0);
                break;*/
        }
        }
    }
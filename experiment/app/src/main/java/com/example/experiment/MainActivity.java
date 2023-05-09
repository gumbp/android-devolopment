package com.example.experiment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ApplicationExitInfo;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;
    private DatabaseHelper dbHelper;
    EditText e1, e2, e3;
    Button b1, b2, b3, b4, b5, b6;
    public static final int VERSION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.e1);//学号
        e2 = findViewById(R.id.e2);//姓名
        e3 = findViewById(R.id.e3);//年龄
        b1 = findViewById(R.id.b1);//pre存入
        b2 = findViewById(R.id.b2);//数据库存入
        b3 = findViewById(R.id.b3);//SD卡存入
        b4 = findViewById(R.id.b4);//pre读取
        b5 = findViewById(R.id.b5);//数据库读取
        b6 = findViewById(R.id.b6);//SD卡读取
    }

    public void Click(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);    //创建结果呈现对话框

        //设置相应按钮点击事件
        switch (v.getId()) {
                //写入按钮
            case R.id.b1://写入pre
                sp = getSharedPreferences("Infor", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("id", e1.getText().toString());
                edit.putString("name", e2.getText().toString());
                edit.putString("age", e3.getText().toString());
                edit.commit();
                Toast.makeText(this, "存入成功", Toast.LENGTH_SHORT).show();
                break;


            case R.id.b2://写入数据库
                CreateDatabase();
                InsertValueToDatabase();
                break;

            case R.id.b3://写入文件
                saveFile();
                break;


                //读取按钮
            case R.id.b4://从pre读出
                String ID = sp.getString("id", "Null");
                String Name = sp.getString("name", "Null");
                String Age = sp.getString("age", "Null");
                builder.setTitle("查询结果");
                builder.setMessage("学号：" + ID + "\n" + "姓名：" + Name + "\n" + "年龄：" + Age);
                break;

            case R.id.b5://从数据库读出
                //DelectData();
                builder.setTitle("查询结果");
                builder.setMessage(QueryDataBase());
                break;

            case R.id.b6://从SD卡读出
                builder.setTitle("查询结果");
                builder.setMessage(readFile());
                break;

            default:
                break;
        }


        //显示查询结果
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    //保存文件到SD卡
    public void saveFile() {
        //判断获取SD卡状态
        String state = Environment.getExternalStorageState();
        FileOutputStream fos = null;
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "检查SD卡", Toast.LENGTH_SHORT).show();
            return;
        }
        //取得SD卡根目录  android10及以上版本获取路径时建议如下代码方能获取写入权限
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File sdCardDir = cw.getDir("保存路径", Context.MODE_PRIVATE);
        try {
            Log.d("======SD卡根目录：", "" + sdCardDir.getCanonicalPath().toString());
            fos = new FileOutputStream(sdCardDir + "/sd.txt", false);
            String str1 = "学号:" + e1.getText().toString() + "  ";
            String str2 = "姓名:" + e2.getText().toString() + "  ";
            String str3 = "年龄:" + e3.getText().toString();
            fos.write(str1.getBytes());
            fos.write(str2.getBytes());
            fos.write(str3.getBytes());
            Toast.makeText(this, "存入成功", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(this, "存入失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    //从SD卡读取文件
    public String readFile() {
        BufferedReader reader = null;
        FileInputStream fis = null;
        StringBuilder sbd = new StringBuilder();
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "SD卡未就绪", Toast.LENGTH_SHORT).show();
            return "";
        }
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File sdCardDir = cw.getDir("保存路径", Context.MODE_PRIVATE);
        try {
            fis = new FileInputStream(sdCardDir + "/sd.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
            String row = "";
            while ((row = reader.readLine()) != null) {
                sbd.append(row);
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sbd.toString();
    }





    //数据库创建按钮点击事件
    public void CreateDatabase() {
        //创建数据库stu_db
        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "stu_db", null, VERSION);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.close();
    }

//往数据库中插入数据
    public void InsertValueToDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "stu_db", null, VERSION);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();//临时变量
        String id = e1.getText().toString();
        String name = e2.getText().toString();
        String age = e3.getText().toString();
        cv.put("sid", id);
        cv.put("sname", name);
        cv.put("sage", age);
        db.insert("stu_table", null, cv);
    }





    //遍历数据库查询记录
    public String QueryDataBase() {
        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "stu_db", null, VERSION);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        StringBuilder content = new StringBuilder();
        //content.append("id"+"\t\t" +"Name"+"\t\t"+ "Age"+"\t\t" +"\n");
        //参数1：表名
        //参数2：要想显示的列
        //参数3：where子句
        //参数4：where子句对应的条件值
        //参数5：分组方式
        //参数6：having条件
        //参数7：排序方式
        Cursor cursor = db.query("stu_table", null, null,null,null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("sname"));
            String age = cursor.getString(cursor.getColumnIndex("sage"));
            String id = cursor.getString(cursor.getColumnIndex("sid"));
            content.append("学号："+"\t" +id+"姓名："+"\t"+name+ "年龄："+"\t" +age+"\n");
        }
        cursor.close();
        return content.toString();

    }


    //清除数据库记录
    public void DelectData(){
        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this, "stu_db", null, VERSION);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("stu_table",null,null);
    }
}















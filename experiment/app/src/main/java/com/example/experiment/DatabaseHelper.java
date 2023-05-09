package com.example.experiment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public  class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "TestSQLite";
    public static final int VERSION = 1;
    private  Context mContext;
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }




    // 当第一次创建数据库的时候，调用该方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建学生表
        String sql = "create table stu_table(sid text,sname text,sage text)";
//输出创建数据库的日志信息
        Log.e(TAG, "create Database------------->");
//execSQL函数用于执行SQL语句
        db.execSQL(sql);
        Toast.makeText(mContext, "创建成功", Toast.LENGTH_SHORT).show();
    }


    //当更新数据库的时候执行该方法
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        if(oldVersion>newVersion){
            return;
        }
        Log.e(TAG, "update Database------------->");
    }





    }

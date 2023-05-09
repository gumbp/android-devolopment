package com.example.ipc_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.picservice.AInterface;

public class MainActivity extends AppCompatActivity {

    int num1;
    int num2;
    String inputText1;
    String inputText2;
    int s1,s2,s3,s4;
    AInterface myInterface;
    ServiceConnection conn =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("------连接成功------");
            myInterface=AInterface.Stub.asInterface(service);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText1=(EditText)findViewById(R.id.edit1);
        EditText  editText2=(EditText)findViewById(R.id.edit2);
        Button btn1 = (Button) this.findViewById(R.id.button1);
        Button btn2=(Button) this.findViewById(R.id.button2);
        Button btn3=(Button) this.findViewById(R.id.button3);
        Button btn4=(Button) this.findViewById(R.id.button4);

        Intent intent=new Intent("com.example.picservice.AInterface.AIDL");
        intent.setPackage("com.example.picservice");
        bindService(intent,conn,BIND_AUTO_CREATE);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button bt=(Button)findViewById(R.id.button5);
                bt.setText("+");
                inputText1=editText1.getText().toString();
                inputText2=editText2.getText().toString();
                num1=Integer.parseInt(inputText1);
                num2=Integer.parseInt(inputText2);
                try {
                    s1=myInterface.plus(num1,num2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                inputText1=String.valueOf(s1);
                EditText jg=(EditText)findViewById(R.id.jg);
                jg.setText(inputText1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent,conn,BIND_AUTO_CREATE);
                Button bt=(Button)findViewById(R.id.button5);
                bt.setText("—");
                inputText1=editText1.getText().toString();
                inputText2=editText2.getText().toString();
                num1=Integer.parseInt(inputText1);
                num2=Integer.parseInt(inputText2);
                try {
                    s2=myInterface.sub(num1,num2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                inputText1=String.valueOf(s2);
                EditText jg=(EditText)findViewById(R.id.jg);
                jg.setText(inputText1);
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button bt=(Button)findViewById(R.id.button5);
                bt.setText("*");
                inputText1=editText1.getText().toString();
                inputText2=editText2.getText().toString();
                num1=Integer.parseInt(inputText1);
                num2=Integer.parseInt(inputText2);
                try {
                    s3=myInterface.mul(num1,num2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                inputText1=String.valueOf(s3);
                EditText jg=(EditText)findViewById(R.id.jg);
                jg.setText(inputText1);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button bt=(Button)findViewById(R.id.button5);
                bt.setText("/");
                inputText1=editText1.getText().toString();
                inputText2=editText2.getText().toString();
                num1=Integer.parseInt(inputText1);
                num2=Integer.parseInt(inputText2);
                try {
                    s4=myInterface.div(num1,num2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                inputText1=String.valueOf(s4);
                EditText jg=(EditText)findViewById(R.id.jg);
                jg.setText(inputText1);
            }
        });

    }
    }

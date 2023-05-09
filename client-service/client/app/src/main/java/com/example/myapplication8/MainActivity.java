package com.example.myapplication8;

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
import android.widget.TextView;

import com.example.myapplication.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    IMyAidlInterface iMyAidlInterface;
    int num1;
    int num2;
    String inputText1;
    String inputText2;
    int s1,s2;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText e1 = (EditText) findViewById(R.id.Edittext1);
        EditText e2 = (EditText) findViewById(R.id.Edittext2);
        TextView t1 = (TextView) findViewById(R.id.Textview1);
        Button b1 = (Button) this.findViewById(R.id.Btn_01);
        Button b2 = (Button) this.findViewById(R.id.Btn_02);
        Button b3 = (Button) this.findViewById(R.id.Btn_03);
        Button b4 = (Button) this.findViewById(R.id.Btn_04);
        Intent intent = new Intent();
        intent.setAction("com.example.myapplication.IMyAidlInterface.AIDL");
        intent.setPackage("com.example.Myapplication");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText1=e1.getText().toString();
                inputText2=e2.getText().toString();
                num1=Integer.parseInt(inputText1);
                num2=Integer.parseInt(inputText2);
                try {
                    s1=iMyAidlInterface.add(num1,num2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                inputText1=String.valueOf(s1);
                t1.setText(inputText1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText1=e1.getText().toString();
                inputText2=e2.getText().toString();
                num1=Integer.parseInt(inputText1);
                num2=Integer.parseInt(inputText2);
                try {
                    s1=iMyAidlInterface.minus(num1,num2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                inputText1=String.valueOf(s1);
                t1.setText(inputText1);
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText1=e1.getText().toString();
                inputText2=e2.getText().toString();
                num1=Integer.parseInt(inputText1);
                num2=Integer.parseInt(inputText2);
                try {
                    s1=iMyAidlInterface.multiply(num1,num2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                inputText1=String.valueOf(s1);
                t1.setText(inputText1);
            }
        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText1=e1.getText().toString();
                inputText2=e2.getText().toString();
                num1=Integer.parseInt(inputText1);
                num2=Integer.parseInt(inputText2);
                try {
                    s1=iMyAidlInterface.divide(num1,num2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                inputText1=String.valueOf(s1);
                t1.setText(inputText1);
            }
        });
    }
}
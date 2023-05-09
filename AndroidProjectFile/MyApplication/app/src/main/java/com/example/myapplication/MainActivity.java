package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText y_text = (EditText)findViewById(R.id.y_text);
        EditText p_text = (EditText)findViewById(R.id.p_text);
        Button d_btn = (Button)findViewById(R.id.d_btn);
        d_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String y = y_text.getText().toString();
                String p = p_text.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("y",y);
                bundle.putString("p",p);
                Intent intent = new Intent(MainActivity.this,FirstActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
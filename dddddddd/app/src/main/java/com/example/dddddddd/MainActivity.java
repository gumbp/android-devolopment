package com.example.dddddddd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private  String city;
    Button wstestBtn=null;
    EditText e1=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.e1);
        wstestBtn = (Button)findViewById(R.id.b1);
        wstestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                city = e1.getText().toString();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("data",city);
                startActivity(intent);
            }
        });
    }
}
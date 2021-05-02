package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView textView;
    private Button keelungbtn;
    private Button taipeibtn;
    private Button newtaipeibtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keelungbtn = (Button)findViewById(R.id.keelungbtn);
        taipeibtn = (Button)findViewById(R.id.taipeibtn);
        newtaipeibtn = (Button)findViewById(R.id.newtaipeibtn);

        keelungbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, KeelungCity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        taipeibtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TaipeiCity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        newtaipeibtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NewTaipeiCity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }


}
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
    private Button taoyuanbtn;
    private Button hsinchucitybtn;
    private Button hsinchucountybtn;
    private Button miaolicountybtn;
    private Button taichungbtn;
    private Button nantoubtn;
    private Button changhuabtn;
    private Button Yunlinbtn;
    private Button chiayicitybtn;
    private Button chiayicountybtn;
    private Button tainanbtn;
    private Button kaohsiungbtn;
    private Button pingtungbtn;
    private Button yilanbtn;
    private Button hualienbtn;
    private Button taitungbtn;
    private Button penghubtn;
    private Button kinmenbtn;
    private Button matsubtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keelungbtn = (Button)findViewById(R.id.keelungbtn);
        taipeibtn = (Button)findViewById(R.id.taipeibtn);
        newtaipeibtn = (Button)findViewById(R.id.newtaipeibtn);
        taoyuanbtn = (Button)findViewById(R.id.taoyuanbtn);
        hsinchucitybtn = (Button)findViewById(R.id.hsinchucitybtn);
        hsinchucountybtn = (Button)findViewById(R.id.hsinchucountybtn);
        miaolicountybtn = (Button)findViewById(R.id.miaolicountybtn);
        taichungbtn = (Button)findViewById(R.id.taichungbtn);
        nantoubtn = (Button)findViewById(R.id.nantoubtn);
        changhuabtn = (Button)findViewById(R.id.changhuabtn);
        Yunlinbtn = (Button)findViewById(R.id.Yunlinbtn);
        chiayicitybtn = (Button)findViewById(R.id.chiayicitybtn);
        chiayicountybtn = (Button)findViewById(R.id.chiayicountybtn);
        tainanbtn = (Button)findViewById(R.id.tainanbtn);
        kaohsiungbtn = (Button)findViewById(R.id.kaohsiungbtn);
        pingtungbtn = (Button)findViewById(R.id.pingtungbtn);
        yilanbtn = (Button)findViewById(R.id.yilanbtn);
        hualienbtn = (Button)findViewById(R.id.hualienbtn);
        taitungbtn = (Button)findViewById(R.id.taitungbtn);
        penghubtn = (Button)findViewById(R.id.penghubtn);
        kinmenbtn = (Button)findViewById(R.id.kinmenbtn);
        matsubtn = (Button)findViewById(R.id.matsubtn);

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
        taoyuanbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TaoyuanCity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        hsinchucitybtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HsinchuCity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        hsinchucountybtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HsinchuCounty.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        miaolicountybtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MiaoliCounty.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        taichungbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TaichungCity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        nantoubtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NantouCounty.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        changhuabtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ChanghuaCounty.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        Yunlinbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, YunlinCounty.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        chiayicitybtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ChiayiCity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        chiayicountybtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ChiayiCounty.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        tainanbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TainanCity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        kaohsiungbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, KaohsiungCity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        pingtungbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PingtungCounty.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        yilanbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, YilanCounty.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        hualienbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HualienCounty.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        taitungbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TaitungCounty.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        penghubtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,PenghuCounty.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        kinmenbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, KinmenCounty.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        matsubtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Matsu.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }


}
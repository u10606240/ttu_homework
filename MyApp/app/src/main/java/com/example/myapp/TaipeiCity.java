package com.example.myapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class TaipeiCity extends AppCompatActivity{
    /*public byte[] messages = new byte[1];

    private int ENABLE_BLUETOOTH=1;
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    BluetoothDevice bluetoothDevice;
    BluetoothSocket bluetoothSocket = null;
    OutputStream outputStream = null;
    private static final UUID MY_UUID_SECURE=UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private String blueAddress="FC:A8:9A:00:28:81";//蓝牙模块的MAC地址

    private static final String TAG = "tzbc";
    private static final int MSG_UPDATE_UI = 1136;
    private String post;
    private TextView tvSiteName, tvCounty, tvAQI, tvPM2_5, tvStatus, tvPublishTime;
    private String btAddress = "20:16:05:25:48:45";
    ArrayList<String> list = new ArrayList<String>();
    int foo,tmp;


    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            ArrayList<String> lis = (ArrayList<String>) msg.obj;
            tvSiteName.setText(lis.get(0));
            tvCounty.setText(lis.get(1));
            tvAQI.setText(lis.get(2));
            tvPM2_5.setText(lis.get(3));
            tvStatus.setText(lis.get(4));
            tvPublishTime.setText(lis.get(5));

            //System.out.println(foo);
        }
    };

    public byte getmsg(){
        System.out.println(messages[0]);
        return messages[0];
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taipei);

        airThread.start();

        tvSiteName = findViewById(R.id.tvSiteName);
        tvCounty = findViewById(R.id.tvCounty);
        tvAQI = findViewById(R.id.tvAQI);
        tvPM2_5 = findViewById(R.id.tvPM2_5);
        tvStatus = findViewById(R.id.tvStatus);
        tvPublishTime = findViewById(R.id.tvPublishTime);
        if(bluetoothAdapter==null){
            Toast.makeText(this,"不支持蓝牙",Toast.LENGTH_LONG).show();
            finish();
        }
        else if(!bluetoothAdapter.isEnabled()){
            Log.d("true","开始连接");
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,ENABLE_BLUETOOTH);
        }
        //System.out.println(message[0]);
        Button start = (Button)findViewById(R.id.button00);
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //System.out.println(foo);
                messages[0]= (byte) foo;//设置要发送的数值

                //bluesend(message);发送数值
                if(messages!=null) {
                    try {
                        outputStream = bluetoothSocket.getOutputStream();
                    } catch (IOException e) {
                        Log.e("Fatal Error", "in sendData() input and output stream creation failed:" + e.getMessage() + ".");
                    }
                    try {
                        outputStream.write(messages);
                    } catch (IOException e) { }
                }
                Log.d("value",""+messages[0]);
            }});
        Button button = (Button) findViewById(R.id.button02);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(TaipeiCity.this, MainActivity.class);
                startActivity(intent);
                //sendBT("1");
                TaipeiCity.this.finish();
            }
        });

    }

    //蓝牙发送数据
    @Override
    protected void onDestroy(){
        super.onDestroy();
        try{
            bluetoothSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        bluetoothDevice = bluetoothAdapter.getRemoteDevice(blueAddress);
        //System.out.println(blueAddress);
        try{
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(MY_UUID_SECURE);
        }catch (Exception e){ Log.e("","Error creating socket"); }
        try{
            bluetoothSocket.connect();
            Log.e("","完成连接");
        } catch (IOException e) {
            Log.e("",e.getMessage());
            try{
                Log.e("","trying fallback...");
                bluetoothSocket = (BluetoothSocket) devices.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(devices,1);
                bluetoothSocket.connect();
                Log.e("","完成连接");
            } catch(Exception e2){Log.e("","Couldn't establish bluetooth connection!"); }
        }

    }

    Thread airThread = new Thread(new Runnable() {
        @Override
        public void run() {
            getAir();
        }
    });

    private void getAir() {
        String path = "https://data.epa.gov.tw/api/v1/aqx_p_432?api_key=ea65dc8a-8320-4319-8510-c36498b1a5e4&format=json";
        try {
            String html = HtmlService.getHtml(path);
            Log.e(TAG, "html : " + html);
            analyzeAir(html);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void analyzeAir(String airStr) {
        try {
            JSONObject jsonObject = new JSONObject(airStr);
            String include = jsonObject.getString("include_total");
            String resourse = jsonObject.getString("resource_id");
            String fields = jsonObject.getString("fields");
            String extra = jsonObject.getString("__extras");
            String format = jsonObject.getString("records_format");
            String limit = jsonObject.getString("limit");
            String offset = jsonObject.getString("offset");
            String links = jsonObject.getString("_links");
            String total = jsonObject.getString("total");
            JSONArray array = jsonObject.getJSONArray("records");

            String go = "12";
            int tmp=1;
            for(int i=0;i<array.length();i++) {
                //JSONObject jsonObject = array.getJSONObject(i);
                //System.out.println(array.getJSONObject(i).getString("SiteId"));
                if(array.getJSONObject(i).getString("SiteId").equals(go)){
                    tmp=i;
                }
                //System.out.println(tmp);
            }
            //System.out.println(tmp);
            //JSONObject jsonObject = array.getJSONObject(83);
            list.add("縣市 : " + array.getJSONObject(tmp).getString("County"));
            list.add("地區 : " + array.getJSONObject(tmp).getString("SiteName"));
            list.add("空氣品質指標 : " + array.getJSONObject(tmp).getString("AQI"));
            list.add("PM2.5指數 : " + array.getJSONObject(tmp).getString("PM2.5"));
            list.add("空氣狀態 : " + array.getJSONObject(tmp).getString("Status"));
            list.add("發布時間 : " + array.getJSONObject(tmp).getString("PublishTime"));

            System.out.println(list);

            Message message = Message.obtain();
            message.what = MSG_UPDATE_UI;
            message.obj = list;
            post = list.get(2).substring(9,12);
            foo = Integer.parseInt(post);
            Bundle bundle = new Bundle();
            bundle.putInt("",foo);
            message.setData(bundle);
            handler.sendMessage(message);
            //System.out.println(foo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
}

package com.example.myapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class HualienCounty extends AppCompatActivity{
    public byte[] messages = new byte[1];

    private int ENABLE_BLUETOOTH=2;
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    BluetoothDevice bluetoothDevice;
    BluetoothSocket bluetoothSocket = null;
    OutputStream outputStream = null;
    private static final UUID MY_UUID_SECURE=UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private String btAddress="FC:A8:9A:00:28:81";//蓝牙模块的MAC地址

    int foo;
    int test;
    String post;
    String test2;
    TextView test3;
    private ArrayList<Air> lists = new ArrayList<>();
    private JsonAdapter jsonAdapter;
    String host="https://data.epa.gov.tw/api/v1/aqx_p_432?api_key=ea65dc8a-8320-4319-8510-c36498b1a5e4";
    //String host="https://data.epa.gov.tw/api/v1/aqx_p_432?limit=1000&api_key=9be7b239-557b-4c10-9775-78cadfc555e9&format=json";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hualien);

        initView(); Log.d("check","initView()");

        if(bluetoothAdapter==null){
            Toast.makeText(this,"不支持蓝牙",Toast.LENGTH_LONG).show();
            finish();
        }
        else if(!bluetoothAdapter.isEnabled()){
            Log.d("true","开始连接");
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,ENABLE_BLUETOOTH);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    parseJsonData(readParse(host));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //这里休眠是为了让子线程结束 lists才有值
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ListView listview = (ListView) findViewById(R.id.listview);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        jsonAdapter = new JsonAdapter(this,lists);
        jsonAdapter.notifyDataSetChanged();
        listview.setAdapter(jsonAdapter);

        test3 = (TextView)findViewById(R.id.test3);

        if(test>=0 && test < 50) test2 = "小提醒:能正常戶外活動";
        else if(test>=51 && test < 100) test2 = "小提醒:能正常戶外活動";
        else if(test>=101 && test < 150) test2 = "小提醒:一般民眾如果有不適，如眼痛，咳嗽或喉嚨痛等，應該考慮減少戶外活動";
        else if(test>=151 && test < 200) test2 = "小提醒:一般民眾如果有不適，如眼痛，咳嗽或喉嚨痛等，應減少體力消耗，特別是減少戶外活動";
        else if(test>=201 && test < 300) test2 = "小提醒:一般民眾應減少戶外活動";
        else if(test>=301 && test < 500) test2 = "小提醒:一般民眾應避免戶外活動，室內應緊閉門窗，必要外出應配戴口罩等防護用具";

        System.out.println(test2);

        test3.setText(test2);
    }

    public static String readParse(String urlPath) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream inStream = conn.getInputStream();
        while ((len = inStream.read(data)) != -1) {
            outStream.write(data, 0, len);
        }
        inStream.close();
        return new String(outStream.toByteArray());//通过out.Stream.toByteArray获取到写的数据
    }

    private void initView() {
        Button start = (Button)findViewById(R.id.button00);
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                messages[0]= (byte) foo;//设置要发送的数值
                bluesend(messages);//发送数值
                Log.d("value",""+messages[0]);
            }});
        Button button = (Button) findViewById(R.id.button02);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(HualienCounty.this, MainActivity.class);
                startActivity(intent);
                HualienCounty.this.finish();
            }
        });

    }

    //蓝牙发送数据
    public void bluesend(byte[] messages){
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
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        try{
            bluetoothSocket.close();
        }catch (IOException e){
            System.out.println("onDestroy錯誤!!!");
            e.printStackTrace();
        }
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        if(Build.VERSION.SDK_INT >= 10){
            try {
                final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[] { UUID.class });
                return (BluetoothSocket) m.invoke(device, MY_UUID_SECURE);
            } catch (Exception e) {
                Log.e("true", "Could not create Insecure RFComm Connection",e);
            }
        }
        return  device.createRfcommSocketToServiceRecord(MY_UUID_SECURE);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("true", "...onResume - try connect...");

        bluetoothDevice = bluetoothAdapter.getRemoteDevice(btAddress);

        try {
            bluetoothSocket = createBluetoothSocket(bluetoothDevice);
        } catch (IOException e) {
            Log.d("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
        }
        Log.d("true", "...Connecting...");
        try {
            bluetoothSocket.connect();
            Log.d("true", "....Connection ok...");
        } catch (IOException e) {
            try {
                bluetoothSocket.close();
                Log.d("true", "....Close...");
            } catch (IOException e2) {
                Log.d("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }
    }

    private void parseJsonData(String string){
        try {
            //JSONArray array = new JSONArray(airStr);
            JSONObject jsonObject = new JSONObject(string);
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
            String go = "63";
            String pro = "監測站未提供資料";
            Air air = new Air();
            int mode=0;
            int tmp=1;
            for(int i=0;i<array.length();i++) {
                if(array.getJSONObject(i).getString("SiteId").equals(go)){
                    tmp=i;
                }
            }
            for(int j=0;j<1;j++) {

                if(array.getJSONObject(tmp).getString("AQI").equals("")){
                    air.setAQI("空氣品質指標 : " + pro);
                    mode=1;
                }
                if(array.getJSONObject(tmp).getString("PM2.5").equals("")){
                    air.setPM2_5("PM2.5指數 : " + pro);
                    mode=1;
                }
                if(array.getJSONObject(tmp).getString("Status").equals("")){
                    air.setStatus("空氣狀態 : " + pro);
                    mode=1;
                }
                if(array.getJSONObject(tmp).getString("PublishTime").equals("")){
                    air.setPublishTime("發布時間 : " + pro);
                    mode=1;
                }
            }

            if(mode == 0) {
                air.setCounty("縣市 : " + array.getJSONObject(tmp).getString("County"));
                air.setSiteName("地區 : " + array.getJSONObject(tmp).getString("SiteName"));
                air.setAQI("空氣品質指標 : " + array.getJSONObject(tmp).getString("AQI"));
                air.setPM2_5("PM2.5指數 : " + array.getJSONObject(tmp).getString("PM2.5"));
                air.setStatus("空氣狀態 : " + array.getJSONObject(tmp).getString("Status"));
                air.setPublishTime("發布時間 : " + array.getJSONObject(tmp).getString("PublishTime"));
            }
            else if(mode == 1){
                air.setCounty("縣市 : " + array.getJSONObject(tmp).getString("County"));
                air.setSiteName("地區 : " + array.getJSONObject(tmp).getString("SiteName"));
                if(!array.getJSONObject(tmp).getString("AQI").equals("")) air.setAQI("空氣品質指標 : " + array.getJSONObject(tmp).getString("AQI"));
                if(!array.getJSONObject(tmp).getString("PM2.5").equals("")) air.setPM2_5("PM2.5指數 : " + array.getJSONObject(tmp).getString("PM2.5"));
                if(!array.getJSONObject(tmp).getString("Status").equals("")) air.setStatus("空氣狀態 : " + array.getJSONObject(tmp).getString("Status"));
                if(!array.getJSONObject(tmp).getString("PublishTime").equals("")) air.setPublishTime("發布時間 : " + array.getJSONObject(tmp).getString("PublishTime"));
            }

            lists.add(air);

            post = array.getJSONObject(tmp).getString("AQI");
            foo = Integer.parseInt(post);
            test = foo;

            Log.i("Test", "OK,数据存储完成");
            Log.i("Test", "List长度为："+lists.size());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

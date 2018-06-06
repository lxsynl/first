package com.bwie.rikao1;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main2Activity extends AppCompatActivity {
public String  myParemeter;
    private EditText nuserEt;
    private EditText nuserPwd;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s = (String) msg.obj;

        }
    };
    private Button bt_lijizhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bt_lijizhuce = findViewById(R.id.bt_lijizhuce);
        nuserEt = findViewById(R.id.et_nuser);
        nuserPwd = findViewById(R.id.et_npwd);
           new Thread(){
            @Override
            public void run() {
                super.run();
                bt_lijizhuce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getinfo();
                    }
                });

            }
        }.start();

    }

    private void getinfo() {
        try {
            URL url = new URL("http://120.27.23.105/user/reg");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(3000);
            urlConnection.setReadTimeout(5000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            myParemeter = "mobile="+nuserEt+"&password="+nuserPwd;
            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(myParemeter.getBytes());
            int responseCode = urlConnection.getResponseCode();
            Log.i("sss","响应码"+responseCode);
            if(urlConnection.getResponseCode()==200){
                InputStream inputStream = urlConnection.getInputStream();
                String s = streamToString(inputStream);
                Log.d("asas",s);
                Gson gson = new Gson();
                Bean bean = gson.fromJson(s, Bean.class);
                Message message = new Message();
                message.obj = bean;
                handler.sendMessage(message);
            }
        } catch ( Exception e) {


        }

    }

    private String streamToString(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] by = new byte[1024>>2];
        int len;
        try {
            while((len = inputStream.read(by))!=-1){
                byteArrayOutputStream.write(by,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();

    }


}

package com.bwie.rikao1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
        EditText userEt = findViewById(R.id.et_user);
        EditText userPwd = findViewById(R.id.et_pwd);
        Button zhuceBut = findViewById(R.id.but_zhuce);
        Button loginBut = findViewById(R.id.but_login);
        zhuceBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }

}

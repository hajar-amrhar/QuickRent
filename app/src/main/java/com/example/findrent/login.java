package com.example.findrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class login extends AppCompatActivity implements View.OnClickListener {

    Button  callSignUp,connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callSignUp=findViewById(R.id.creat);
        connect=findViewById(R.id.connect);


        callSignUp.setOnClickListener(this);
        connect.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.creat:
                {

                Intent intentCreat=new Intent(login.this,signup.class);
                startActivity(intentCreat);
                break;

            }
            case R.id.connect:
            {
                Intent intentConnect=new Intent(login.this,CreatSuccess.class)
            }
        }
    }
}
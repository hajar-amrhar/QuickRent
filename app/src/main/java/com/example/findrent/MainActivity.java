package com.example.findrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static  int splash =3000;
    Animation anim1;
    ImageView logo;
    TextView nomApp,slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        anim1= AnimationUtils.loadAnimation(this,R.anim.anim1);

        logo=findViewById(R.id.imageView);
        nomApp=findViewById(R.id.textView);
        slogan=findViewById(R.id.textView2);

        logo.setAnimation(anim1);
        slogan.setAnimation(anim1);
        nomApp.setAnimation(anim1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,login.class);
                startActivity(intent);
                finish();
            }
        },splash);
    }
}
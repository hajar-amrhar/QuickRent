package com.example.findrent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.DatabaseMetaData;

public class signup extends AppCompatActivity {

    Button login;
    DatabaseMetaData reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        login=findViewById(R.id.loginAl);
      //  login.setOnClickListener(new View.OnClickListener() {
        //    @Override
          //  public void onClick(View v) {

            //    Intent inten= new Intent(signup.this,login.getClass());
              //  startActivity(inten);
            //}
        //});
    }
}
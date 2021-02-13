package com.example.findrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    BottomNavigationView botNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        botNav= findViewById(R.id.butt_navigation);
        botNav.setOnNavigationItemSelectedListener(navListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_home:

                            break;

                        case R.id.nav_filter:
                           // startActivity(new Intent(this,filter.class));
                            break;

                        case R.id.nav_add:

                        // startActivity(new Intent(this,add.class));
                        break;

                        case R.id.nav_favorite:
                            // startActivity(new Intent(this,favorite.class));
                            break;

                        case R.id.nav_profile:
                            // startActivity(new Intent(this,profile.class));

                            break;
                }

                    return true;
                }
            };

}
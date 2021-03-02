package com.example.findrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.findrent.Fragment.VosFragment;
import com.example.findrent.Fragment.homeFragment;
import com.example.findrent.Fragment.profileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    Fragment selectedFragment;
    BottomNavigationView botNav;
    ImageView botTri;
   // Chip app, chambre, garc,duplexe,maison,loccom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        botNav = findViewById(R.id.butt_navigation);
        botNav.setOnNavigationItemSelectedListener(navListener);

        if (selectedFragment == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_espace, new homeFragment()).commit();
        }


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_home:

                            selectedFragment= new homeFragment();
                            break;

                        case R.id.nav_filter:

                            Intent intent1 = new Intent(Home.this,Test.class);
                            //  intent.putExtra("annonceObject2",annonce);

                            startActivity(intent1);
                            break;

                        case R.id.nav_add:
                            //getSupportFragmentManager().beginTransaction().replace(R.id.home_espace,new addFragment()).commit();
                            Intent intent = new Intent(Home.this, CamGaler.class);
                            startActivity(intent);
                        break;

                        case R.id.nav_favorite:

                            selectedFragment= new VosFragment();



                            break;

                        case R.id.nav_profile:

                           /* SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                            editor.putString("profileid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                            editor.apply();

                            break;*/

                           selectedFragment=new profileFragment();
                }

                if (selectedFragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_espace,selectedFragment).commit();
                }
                    return true;
                }
            };



}
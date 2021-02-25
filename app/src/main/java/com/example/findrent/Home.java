package com.example.findrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.findrent.Fragment.addFragment;
import com.example.findrent.Fragment.favFragment;
import com.example.findrent.Fragment.filterFragment;
import com.example.findrent.Fragment.homeFragment;
import com.example.findrent.Fragment.profileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    Fragment selectedFragment;
    BottomNavigationView botNav;
    ImageView botTri;
   // Chip app, chambre, garc,duplexe,maison,loccom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        botNav= findViewById(R.id.butt_navigation);
        botNav.setOnNavigationItemSelectedListener(navListener);

        if (selectedFragment == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.home_espace,new homeFragment()).commit();
        }




        /*

        app = findViewById(R.id.chipApp);
        garc= findViewById(R.id.chipGarc);
        chambre = findViewById(R.id.chipCham);
        duplexe = findViewById(R.id.chipDuplexe);
        maison = findViewById(R.id.chipMaison);
        loccom = findViewById(R.id.chipLocCom);



         */
        /*
        app.setOnClickListener(filterListener);
        garc.setOnClickListener(filterListener);
        chambre.setOnClickListener(filterListener);
        maison.setOnClickListener(filterListener);
        duplexe.setOnClickListener(filterListener);
        loccom.setOnClickListener(filterListener);


         */





    }

    /*private View.OnClickListener filterListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.chipApp:
                   break;
                case R.id.chipCham:

                    break;
                case R.id.chipGarc:
                    break;
                case R.id.chipDuplexe:
                    break;
                case R.id.chipMaison:
                    break;
                case R.id.chipLocCom:
                    break;
            }
        }
    };
*/



    /*

    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }


     */

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_home:

                            selectedFragment= new homeFragment();
                            break;

                        case R.id.nav_filter:

                            // selectedFragment= new filterFragment();
                            break;

                        case R.id.nav_add:
                            //getSupportFragmentManager().beginTransaction().replace(R.id.home_espace,new addFragment()).commit();


                            Intent intent = new Intent(Home.this, CamGaler.class);
                            startActivity(intent);

                            //selectedFragment= new addFragment();
                            //startActivity(intent);

                            // start activity add
                        break;

                        case R.id.nav_favorite:

                            //selectedFragment= new favFragment();



                            break;

                        case R.id.nav_profile:

                           /* SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                            editor.putString("profileid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                            editor.apply();
                            selectedFragment=new profileFragment();
                            break;

                            */
                }

                if (selectedFragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_espace,selectedFragment).commit();
                }
                    return true;
                }
            };



    /*@Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.option_1:
                //tri par date
                Toast.makeText(this,"tri par date", Toast.LENGTH_LONG).show();
                return true;
            case R.id.option_2:
                //tri par date
                Toast.makeText(this,"tri par date", Toast.LENGTH_LONG).show();
                return true;
            case R.id.option_3:
                //tri par date
                Toast.makeText(this,"tri par date", Toast.LENGTH_LONG).show();
                return true;
             default:
                 return false;

        }
    }*/
}
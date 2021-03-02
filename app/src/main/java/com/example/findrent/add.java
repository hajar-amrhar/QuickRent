package com.example.findrent;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.findrent.model.annonce;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class add extends AppCompatActivity {

    FusedLocationProviderClient client;
    TextInputEditText titre, description,prix,superficie,adresse;
    Chip app, chambre, garc, duplexe, maison, loccom, meublé,nonmeublé;
    String titreS, descriptionS,dateS,prixS,superficieS,logS,altS,adressS,categoryS;
    String ameublement="0";
    Button suivant ;
    ImageView annonceImage, annonceImage2, annonceImage3, annonceImage4;
    TextView date;
    ImageView  getlocation;
    String log,at;


    String uri1, uri2, uri3, uri4;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    //mDatabase = FirebaseDatabase.getInstance();
    // mDb = mDatabase.getReference();
    FirebaseUser user = mAuth.getCurrentUser();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //initialiser localisation clien
        client = LocationServices.getFusedLocationProviderClient(this);

        titre= findViewById(R.id.titreAnnonce2);
        description= findViewById(R.id.desciptionAnnonce2);

        //date=v.findViewById(R.id.dateAnnonce2);

        prix= findViewById(R.id.prixAnnonce2);
        superficie= findViewById(R.id.superficieAnnonce2);
        //log= v.findViewById(R.id.log);
        // alt= v.findViewById(R.id.at);
        adresse= findViewById(R.id.adresseAnnonce2);


        getlocation=findViewById(R.id.mapsAnnonce2);


        app = findViewById(R.id.chipApp2);
        garc = findViewById(R.id.chipGarc2);
        chambre = findViewById(R.id.chipCham2);
        duplexe = findViewById(R.id.chipDuplexe2);
        maison = findViewById(R.id.chipMaison2);
        loccom = findViewById(R.id.chipLocCom2);

        annonceImage= findViewById(R.id.imann1);
        annonceImage2= findViewById(R.id.imann2);
        annonceImage3 = findViewById(R.id.imann3);
        annonceImage4 = findViewById(R.id.imann4);



        meublé= findViewById(R.id.chipMeublé2);
        nonmeublé = findViewById(R.id.chipNonMeublé2);


        suivant=findViewById(R.id.AjouterAnn);

        getlocation.setOnClickListener(mapListener);

        suivant.setOnClickListener(sauvgard);

        app.setOnClickListener(filterListener);
        garc.setOnClickListener(filterListener);
        chambre.setOnClickListener(filterListener);
        maison.setOnClickListener(filterListener);
        duplexe.setOnClickListener(filterListener);
        loccom.setOnClickListener(filterListener);

        meublé.setOnClickListener(filterListener2);
        nonmeublé.setOnClickListener(filterListener2);

        //placer les image prises par la cam via realtimeDataBase dans addAnnonce

        Glide.with( add.this).load(FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("imageInst").child("uri1")).into(annonceImage);

        Glide.with(add.this).load(FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("imageInst").child("uri2")).into(annonceImage2);

        Glide.with(add.this).load(FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("imageInst").child("uri3")).into(annonceImage3);

        Glide.with(add.this).load(FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("imageInst").child("uri4")).into(annonceImage4);


       readUri();

    }


    private void readUri() {



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).child("imageInst");


        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        uri1=dataSnapshot.child("uri1").getValue(String.class);
                        uri2=dataSnapshot.child("uri2").getValue(String.class);
                        uri3=dataSnapshot.child("uri3").getValue(String.class);
                        uri4=dataSnapshot.child("uri4").getValue(String.class);


                    //}
                }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
            });
    }
    public void writeNewUser(String description, String adresse, String superficie, String prix, String ameublement, String titre, String date, String log, String alt, String uri1, String uri2, String uri3, String uri4,String userid,String category) {
        annonce aAnnonce = new annonce(description,adresse, superficie, prix, ameublement, titre, date, log, alt, uri1,uri2,uri3,uri4,userid,category);


        FirebaseDatabase.getInstance().getReference("annonce").child(titreS).setValue(aAnnonce);

        FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("vos annonces").child(titreS).setValue(aAnnonce);
    }

    private View.OnClickListener filterListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.chipApp:
                    categoryS="appartement";
                    break;
                case R.id.chipCham:
                    categoryS="chambre";
                    break;
                case R.id.chipGarc:
                    categoryS="garconniere";

                    break;
                case R.id.chipDuplexe:
                    categoryS="duplexe";

                    break;
                case R.id.chipMaison:
                    categoryS="maison";

                    break;
                case R.id.chipLocCom:
                    categoryS="local com";

                    break;
            }
        }
    };

    private View.OnClickListener filterListener2 = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.chipMeublé2:
                    ameublement="meublé(e)";

                    break;
                case R.id.chipNonMeublé2:
                    ameublement="non meublé(e)";
                    break;


            }
        }
    };



    private View.OnClickListener sauvgard = new View.OnClickListener() {
        public void onClick(View v) {

            titreS=titre.getText().toString();
            descriptionS=description.getText().toString();
            prixS=prix.getText().toString();
            superficieS=superficie.getText().toString();
            //logS=log.toString();
            //altS=alt.toString();
            adressS=adresse.getText().toString();



            if(titreS.isEmpty()){
                Toast.makeText(add.this, "ajouter un titre", Toast.LENGTH_SHORT).show();
            }
            else if(ameublement.isEmpty()){
                Toast.makeText(add.this, "cochez l'ameublement", Toast.LENGTH_SHORT).show();
            }
            else if(descriptionS.isEmpty()){
                Toast.makeText(add.this, "ajouter la description", Toast.LENGTH_SHORT).show();
            }
            else if(prixS.isEmpty()){
                Toast.makeText(add.this, "ajouter le prix ", Toast.LENGTH_SHORT).show();
            }
            else if(categoryS==""){
                Toast.makeText(add.this, "cochez la catégorie", Toast.LENGTH_SHORT).show();
            }
            else if(at==""||log==""){
                Toast.makeText(add.this, "ajouter la localisation", Toast.LENGTH_SHORT).show();
            }




            else if(superficieS.isEmpty()){
                Toast.makeText(add.this, "ajouter le superficie", Toast.LENGTH_SHORT).show();
            }
            else {
                Calendar calendar = Calendar.getInstance();
                String currentDate= DateFormat.getInstance().format(calendar.getTime());

                writeNewUser(descriptionS, adressS, superficieS, prixS, ameublement, titreS, currentDate, log, at,uri1,uri2,uri3,uri4,user.getUid(),categoryS);


                Toast.makeText(add.this, "ajout vc succ", Toast.LENGTH_SHORT).show();
                // supprimer les uri de users-->currentUserId-->photoUri par passer null à setValue
                FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("imageInst").setValue(null);

                startActivity(new Intent(add.this, Home.class));

            }
        }
    };


    private View.OnClickListener mapListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void onClick(View v) {


            if (ContextCompat.checkSelfPermission(add.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
                    &&ContextCompat.checkSelfPermission(add.this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){


                getCurrentLocation();
            }else{
                //when permission is not granted
                //request permission
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
            }

        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100 && (grantResults.length>0)&&
                (grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED)){



            // when permission are granted
            // call methode
            getCurrentLocation();
        }else {
            //when permission are denied
            //desplay toast
            Toast.makeText(add.this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        //initialize location manager
        LocationManager locationmanager =(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        //check condition
        if(locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                ||locationmanager.isProviderEnabled(locationmanager.NETWORK_PROVIDER)){
            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {

                    Location location =task.getResult();

                    if (location != null){
                        at=String.valueOf(location.getLatitude());
                        log=String.valueOf(location.getLongitude());


                        Toast.makeText(add.this, "log lat success1", Toast.LENGTH_SHORT).show();

                    }else {

                        //when location result is null
                        //initialize location result
                        LocationRequest locationRequest = new LocationRequest().
                                setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).
                                setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);
                        //initialise location call back
                        LocationCallback locationcallback = new LocationCallback(){

                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                //initialize location
                                Location location1 = (Location) locationResult.getLocations();
                                //set longitude

                                log=String.valueOf(location1.getLongitude());
                                //set latitude
                                at=String.valueOf(location1.getLatitude());
                                Toast.makeText(add.this, "log lat success2", Toast.LENGTH_SHORT).show();



                            }
                        };
                        //request location updates
                        client.requestLocationUpdates(locationRequest,locationcallback, Looper.myLooper());
                    }
                }
            });
        }else {
            //when location service is not enabled
            //open location settings
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }


}
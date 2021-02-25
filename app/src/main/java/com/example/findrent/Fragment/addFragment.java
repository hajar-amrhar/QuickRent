package com.example.findrent.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.findrent.Home;
import com.example.findrent.R;
import com.example.findrent.model.User;
import com.example.findrent.model.annonce;
import com.example.findrent.signup;
import com.example.findrent.uri;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class addFragment extends Fragment {

 //   private DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference("Annonce");

    Fragment selectedFragment;
    public Context mContext;


    FusedLocationProviderClient client;
// ...
    TextInputEditText titre, description,prix,superficie,adresse;
    Chip app, chambre, garc, duplexe, maison, loccom, meublé,nonmeublé;
    String titreS, descriptionS,dateS,prixS,superficieS,logS,altS,adressS,categoryS="";
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_add, container, false);

      /*



        //mDatabase = FirebaseDatabase.getInstance().getReference();

        //initialiser localisation clien
        client = LocationServices.getFusedLocationProviderClient(getActivity( ));

        titre= v.findViewById(R.id.titreAnnonce2);
        description= v.findViewById(R.id.desciptionAnnonce2);

        //date=v.findViewById(R.id.dateAnnonce2);

        prix= v.findViewById(R.id.titreAnnonce2);
        superficie= v.findViewById(R.id.superficieAnnonce2);
        //log= v.findViewById(R.id.log);
       // alt= v.findViewById(R.id.at);
        adresse= v.findViewById(R.id.adresseAnnonce2);


        getlocation=v.findViewById(R.id.mapsAnnonce2);


        app = v.findViewById(R.id.chipApp2);
        garc = v.findViewById(R.id.chipGarc2);
        chambre = v.findViewById(R.id.chipCham2);
        duplexe = v.findViewById(R.id.chipDuplexe2);
        maison = v.findViewById(R.id.chipMaison2);
        loccom = v.findViewById(R.id.chipLocCom2);

        annonceImage= v.findViewById(R.id.imann1);
        annonceImage2= v.findViewById(R.id.imann2);
        annonceImage3 = v.findViewById(R.id.imann3);
        annonceImage4 = v.findViewById(R.id.imann4);



        meublé= v.findViewById(R.id.chipMeublé2);
        nonmeublé = v.findViewById(R.id.chipNonMeublé2);


        suivant=v.findViewById(R.id.AjouterAnn);

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


        titreS=titre.toString();
        descriptionS=description.toString();
        prixS=prix.toString();
        superficieS=superficie.toString();
        //logS=log.toString();
        //altS=alt.toString();
        adressS=adresse.toString();


        //placer les image prises par la cam via realtimeDataBase dans addAnnonce




        Glide.with(getActivity()).load(FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("imageInst").child("uri1")).into(annonceImage);

        Glide.with(getActivity()).load(FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("imageInst").child("uri2")).into(annonceImage2);

        Glide.with(getActivity()).load(FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("imageInst").child("uri3")).into(annonceImage3);

        Glide.with(getActivity()).load(FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("imageInst").child("uri4")).into(annonceImage4);
        // Glide.with(mContext).load(annonce.getUri1()).into(holder.annonceImage);

        // supprimer les uri de users-->currentUserId-->photoUri




       */
        return v;
    }

    /*
    private void readUri(){
        DatabaseReference reference=  FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("imageInst");

        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //annoncelist.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    uri Uri=snapshot.getValue(uri.class);
                    //annoncelist.add(Annonce );
                    uri1=Uri.getUri1();
                    uri2=Uri.getUri2();

                    uri3=Uri.getUri3();

                    uri4=Uri.getUri4();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }







    public void writeNewUser(String description, String adresse, String superficie, String prix, String ameublement, String titre, String date, String log, String alt, String uri1, String uri2, String uri3, String uri4,String userid,String category) {
        annonce aAnnonce = new annonce(description,adresse, superficie, prix, ameublement, titre, date, log, alt, uri1,uri2,uri3,uri4,userid,category);

        //mDatabase.child("Annonce").child(mDatabase.push().getKey()).setValue(annonce);

        FirebaseDatabase.getInstance().getReference().child("annonce").setValue(aAnnonce);

        FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("vos annonces").setValue(aAnnonce);


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
                    ameublement="true";

                    break;
                case R.id.chipNonMeublé2:
                    ameublement="false";
                    break;


            }
        }
    };



    private View.OnClickListener sauvgard = new View.OnClickListener() {
        public void onClick(View v) {


            if(titreS.isEmpty()){
                Toast.makeText(getActivity(), "champs obligatoir1", Toast.LENGTH_SHORT).show();
            }
            else if(ameublement.isEmpty()){
                Toast.makeText(getActivity(), "champs obligatoir2", Toast.LENGTH_SHORT).show();
            }
            else if(descriptionS.isEmpty()){
                Toast.makeText(getActivity(), "champs obligatoir3", Toast.LENGTH_SHORT).show();
            }
            else if(prixS.isEmpty()){
                Toast.makeText(getActivity(), "champs obligatoir4", Toast.LENGTH_SHORT).show();
            }
           /* else if(categoryS.isEmpty()){
                Toast.makeText(getActivity(), "champs obligatoir5", Toast.LENGTH_SHORT).show();
            }


            else if(superficieS.isEmpty()){
                Toast.makeText(getActivity(), "champs obligatoir6", Toast.LENGTH_SHORT).show();
            }
            else {
                Calendar calendar = Calendar.getInstance();
                String currentDate= DateFormat.getInstance().format(calendar.getTime());

               // date.setText(currentDate);


             // annonce Annonce =new annonce(descriptionS, adressS, superficieS, prixS, ameublement, titreS, currentDate, logS, altS, annonceImage, annoncImage2, annonceImage3, annonceImage4);



                readUri();

              writeNewUser(descriptionS, adressS, superficieS, prixS, ameublement, titreS, currentDate, logS, altS,uri1,uri2,uri3,uri4,user.getUid(),categoryS);


              // demarrer frag vos annonce

              //tasks ???????
                Toast.makeText(getActivity(), "ajout vc succ", Toast.LENGTH_SHORT).show();

                // supprimer les uri de users-->currentUserId-->photoUri par passer null à setValue

                FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("imageInst").setValue(null);




                selectedFragment= new vosAnnoncesFragment();


            }




        }


    };


    private View.OnClickListener mapListener = new View.OnClickListener() {
        public void onClick(View v) {


            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
            &&ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){


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
            Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
            //initialize location manager
            LocationManager locationmanager =(LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

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


                            Toast.makeText(getActivity(), "log lat success1", Toast.LENGTH_SHORT).show();

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
                                    Toast.makeText(getActivity(), "log lat success2", Toast.LENGTH_SHORT).show();



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

     */
    }





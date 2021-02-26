package com.example.findrent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class mapDet extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_det);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        map = googleMap;

        //LatLng Maison = new LatLng(32.346083, -6.349694);
        // LatLng Maison = new LatLng(32.346083, -6.349694);
        //LatLng Duplexe = new LatLng(32.343292, -6.349457);
        //  LatLng Villa = new LatLng(32.342104, -6.373613 );
        //LatLng Appartement = new LatLng(32.339669, -6.349210);
        //LatLng Localcommercial1 = new LatLng(32.339266, -6.349144);
        // LatLng Chambre1 = new LatLng(32.340027, -6.349829);
        // LatLng Localcommercial2 = new LatLng(32.342535, -6.348500);
        // LatLng Chambre2 = new LatLng(32.339009, -6.356042 );
       // databaseReference = firebaseDatabase.getInstance().getReference().child("Annonce");
       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()){
                    String lat =s.child("latitude").getValue().toString();
                    String lng =s.child("longitude").getValue().toString();
                    double latitude=Double.parseDouble(lat);
                    double longitude=Double.parseDouble(lng);
                    LatLng lokasi = new LatLng(latitude,longitude);
                    map.addMarker(new MarkerOptions().position(lokasi).title(s.child("beni mellal").getValue().toString()));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });*/


        LatLng Maison = new LatLng(32.346083, -6.349694);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Maison,5));
        MarkerOptions options = new MarkerOptions().position(Maison).title("Maison");
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        // map.addMarker(new MarkerOptions().position(Maison).title("Maison"));
        //   map.moveCamera(CameraUpdateFactory.newLatLng(Maison));
        map.addMarker(options);



        LatLng Duplexe = new LatLng(32.343292, -6.349457);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Duplexe,5));
        MarkerOptions options1 = new MarkerOptions().position(Duplexe).title("Duplexe");
        options1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        map.addMarker(options1);


        LatLng Villa = new LatLng(32.342104, -6.373613 );
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Villa,5));
        MarkerOptions options2 = new MarkerOptions().position(Villa).title("Villa");
        options2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        map.addMarker(options2);


        LatLng Appartement = new LatLng(32.339669, -6.349210);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Appartement,5));
        MarkerOptions options3 = new MarkerOptions().position(Appartement).title("Appartement");
        options3.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        map.addMarker(options3);


        LatLng Localcommercial1 = new LatLng(32.339266, -6.349144);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Localcommercial1,5));
        MarkerOptions options4 = new MarkerOptions().position(Localcommercial1).title("Localcommercial1");
        options4.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        map.addMarker(options4);


        LatLng Localcommercial2 = new LatLng(32.342535, -6.348500);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Localcommercial2,5));
        MarkerOptions options5 = new MarkerOptions().position(Localcommercial2).title("Localcommercial2");
        options5.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        map.addMarker(options5);


        LatLng Chambre1 = new LatLng(32.340027, -6.349829);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Chambre1,5));
        MarkerOptions options6 = new MarkerOptions().position(Chambre1).title("Chambre1");
        options6.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        map.addMarker(options6);


        LatLng Chambre2 = new LatLng(32.339009, -6.356042 );
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Chambre2,5));
        MarkerOptions options7 = new MarkerOptions().position(Chambre2).title("Chambre2");
        options7.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        map.addMarker(options7);

    }
}
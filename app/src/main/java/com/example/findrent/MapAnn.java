package com.example.findrent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.findrent.model.annonce;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapAnn extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_ann);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map );
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;

        annonce item = (annonce) getIntent().getExtras().getSerializable("annonceObject");

        LatLng locAnnonce =new LatLng( Double.parseDouble(item.getAlt()), Double.parseDouble(item.getLog()));

        map.addMarker(new MarkerOptions().position(locAnnonce).title(item.getTitre()));

        map.moveCamera(CameraUpdateFactory.newLatLng(locAnnonce));

    }
}
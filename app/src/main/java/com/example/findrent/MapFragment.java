package com.example.findrent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findrent.model.annonce;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);;


        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);



       // SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            //    .findFragmentById(R.id.google_map );
        supportMapFragment.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map=googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);


        LatLng locAnnonce =new LatLng( Double.parseDouble(getArguments().getString("keyAt")), Double.parseDouble(getArguments().getString("keyLog")));

       map.addMarker(new MarkerOptions().position(locAnnonce).title(getArguments().getString("keyTitle")));

       // LatLng locAnnonce =new LatLng( 22.876587587587,22.67790);
      //  map.addMarker(new MarkerOptions().position(locAnnonce).title("kugtkutgku"));

        map.moveCamera(CameraUpdateFactory.newLatLng(locAnnonce));
    }
}
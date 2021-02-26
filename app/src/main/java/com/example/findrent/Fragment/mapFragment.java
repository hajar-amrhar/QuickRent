package com.example.findrent.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findrent.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapFragment extends Fragment {


    GoogleMap map;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {


                map=googleMap;

                // annonce item = (annonce) getIntent().getExtras().getSerializable("annonceObject");

                // LatLng locAnnonce =new LatLng( Double.parseDouble(item.getAlt()), Double.parseDouble(item.getLog()));

                //map.addMarker(new MarkerOptions().position(locAnnonce).title(item.getTitre()));
                LatLng locAnnonce =new LatLng( 22.876587587587,22.67790);
                map.addMarker(new MarkerOptions().position(locAnnonce).title("kugtkutgku"));


                map.moveCamera(CameraUpdateFactory.newLatLng(locAnnonce));
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            }
        });



        return view;
    }
}
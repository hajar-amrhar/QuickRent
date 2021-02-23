package com.example.findrent.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.findrent.MapAnn;
import com.example.findrent.R;
import com.example.findrent.model.annonce;


public class DetailsFragment extends Fragment {

    ImageView imann2,imann3,imann4,map;
    TextView  superficie,category,ameublement;
    private annonce annonce;
    Button contacter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);


         annonce = (annonce) getArguments().getSerializable("annonceObject");


        map=v.findViewById(R.id.mapsAnnonce3);
        imann2=v.findViewById(R.id.imann2);
        imann3=v.findViewById(R.id.imann3);
        imann4=v.findViewById(R.id.imann4);
        contacter=v.findViewById(R.id.contacter);


        superficie=v.findViewById(R.id.superficieD);
        category=v.findViewById(R.id.CatégorieD);
        ameublement=v.findViewById(R.id.ameublementD);

        map.setOnClickListener(showMap);
        contacter.setOnClickListener(Contacter);

        loadAnnonceData(annonce);


        return v;
    }

    private void loadAnnonceData(annonce annonce) {
        Glide.with(getActivity()).load(annonce.getUuri2()).into(imann2);
        Glide.with(getActivity()).load(annonce.getUri3()).into(imann3);
        Glide.with(getActivity()).load(annonce.getUri4()).into(imann4);

        superficie.setText(annonce.getSuperficie());
        category.setText(annonce.getCategoeie());
        ameublement.setText(annonce.getAmeublement());

    }


    private View.OnClickListener showMap = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getActivity(), MapAnn.class);
            intent.putExtra("annonceObject2",annonce);

            startActivity(intent);
        }
    };

    private View.OnClickListener Contacter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Bundle bundle = new Bundle();
            bundle.putString("keyUid",annonce.getAnnonceid());
            contacterFragment fragobj = new contacterFragment();
            fragobj.setArguments(bundle);

        }
    };
}



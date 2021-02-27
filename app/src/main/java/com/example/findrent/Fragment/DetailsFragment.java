package com.example.findrent.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.findrent.R;
import com.example.findrent.mapDet;
import com.example.findrent.model.annonce;


public class DetailsFragment extends Fragment {

    ImageView imann2,imann3,imann4,imann1,map;
    TextView  superficie,category,ameublement,titre,description,adresse,prix,date;
    private annonce annonce;
    Button contacter;
   // ProgressBar PB;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        Toast.makeText(getContext(), "Details", Toast.LENGTH_SHORT).show();



        annonce = (annonce) getArguments().getSerializable("annonceObject");


        map=v.findViewById(R.id.mapsAnnonce3);
        imann1=v.findViewById(R.id.imann11);

        imann2=v.findViewById(R.id.imann22);
        imann3=v.findViewById(R.id.imann33);
        imann4=v.findViewById(R.id.imann44);
        contacter=v.findViewById(R.id.contacter);

        titre=v.findViewById(R.id.titre4);
        description=v.findViewById(R.id.description5);
        date=v.findViewById(R.id.dateAnnonce4);
        prix=v.findViewById(R.id.prixD);


        superficie=v.findViewById(R.id.superficieD);
        category=v.findViewById(R.id.CatégorieD);
        ameublement=v.findViewById(R.id.ameublementD);

        map.setOnClickListener(showMap);
        contacter.setOnClickListener(Contacter);

        loadAnnonceData(annonce);


        return v;
    }

    private void loadAnnonceData(annonce annonce) {
        Glide.with(getActivity()).load(annonce.getUri1()).into(imann1);

        Glide.with(getActivity()).load(annonce.getUuri2()).into(imann2);
        Glide.with(getActivity()).load(annonce.getUri3()).into(imann3);
        Glide.with(getActivity()).load(annonce.getUri4()).into(imann4);

        superficie.setText(annonce.getSuperficie());
        category.setText(annonce.getCategoeie());
        ameublement.setText(annonce.getAmeublement());


        date.setText(annonce.getDate());

        description.setText(annonce.getDescription());
        titre.setText(annonce.getTitre());
        prix.setText(annonce.getPrix());






    }

    private View.OnClickListener showMap = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            PB.setVisibility(View.INVISIBLE);


            Bundle bundle = new Bundle();
            bundle.putString("keyLog",annonce.getLog());
            bundle.putString("keyAt",annonce.getAlt());
            bundle.putString("keyTitre",annonce.getTitre());


            mapFragment fragobj = new mapFragment();
            fragobj.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_espace, fragobj)
                    .addToBackStack(null)
                    .commit();

        }
    };




    private View.OnClickListener Contacter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            PB.setVisibility(View.INVISIBLE);


            Bundle bundle = new Bundle();
            bundle.putString("keyUid",annonce.getAnnonceid());
            contacterFragment fragobj = new contacterFragment();
            fragobj.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_espace, fragobj)
                    .addToBackStack(null)
                    .commit();

        }
    };
}



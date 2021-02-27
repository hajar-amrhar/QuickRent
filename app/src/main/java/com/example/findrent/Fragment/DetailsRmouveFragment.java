package com.example.findrent.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.findrent.R;
import com.example.findrent.model.annonce;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsRmouveFragment extends Fragment {

    ImageView imann2,imann3,imann4,imann1,map;
    TextView superficie,category,ameublement,titre,description,adresse,prix,date;
    private com.example.findrent.model.annonce annonce;
    Button contacter;
    // ProgressBar PB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_details_rmouve, container, false);




        annonce = (annonce) getArguments().getSerializable("annonceObject");


        map=v.findViewById(R.id.mapsAnnonce3);
        imann1=v.findViewById(R.id.imann11R);

        imann2=v.findViewById(R.id.imann22R);
        imann3=v.findViewById(R.id.imann33R);
        imann4=v.findViewById(R.id.imann44R);
        contacter=v.findViewById(R.id.contacterR);

        titre=v.findViewById(R.id.titreR);
        description=v.findViewById(R.id.descriptionR);
        date=v.findViewById(R.id.dateAnnonceR);
        prix=v.findViewById(R.id.prixR);


        superficie=v.findViewById(R.id.superficieR);
        category=v.findViewById(R.id.CatégorieR);
        ameublement=v.findViewById(R.id.ameublementR);

        // map.setOnClickListener(showMap);
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


    private View.OnClickListener Contacter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            PB.setVisibility(View.INVISIBLE);




            FirebaseDatabase.getInstance().getReference("annonce").child(annonce.getAnnonceid()).setValue(null);
            FirebaseDatabase.getInstance().getReference("users").child("vos annonces").child(annonce.getAnnonceid()).setValue(null);



            VosFragment fragobj = new VosFragment();
            //fragobj.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_espace, fragobj)
                    .addToBackStack(null)
                    .commit();

        }
    };
    }
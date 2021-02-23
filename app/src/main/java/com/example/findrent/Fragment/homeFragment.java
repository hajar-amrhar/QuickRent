package com.example.findrent.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findrent.Home;
import com.example.findrent.R;
import com.example.findrent.model.annonce;
import com.example.findrent.recycleAnn.AnnAdapt;
import com.example.findrent.recycleAnn.annonceCallback;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import androidx.core.util.Pair;

public class homeFragment extends Fragment implements PopupMenu.OnMenuItemClickListener, annonceCallback {


    private RecyclerView recyclerViewHome;
    private AnnAdapt annadpter;
    private List<annonce> annoncelist;
    //private List<String> followingList;




    ImageView botTri;
    Chip app, chambre, garc, duplexe, maison, loccom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment


      // recyclerViewHome=getActivity().findViewById(R.id.recycler_view_home);

        recyclerViewHome=v.findViewById(R.id.recycler_view_home);
        recyclerViewHome.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewHome.setLayoutManager(linearLayoutManager);
        annoncelist=new ArrayList<>();
        annadpter=new AnnAdapt(annoncelist, getContext(),this);
        recyclerViewHome.setAdapter(annadpter);








        app = v.findViewById(R.id.chipApp);
        garc = v.findViewById(R.id.chipGarc);
        chambre = v.findViewById(R.id.chipCham);
        duplexe = v.findViewById(R.id.chipDuplexe);
        maison = v.findViewById(R.id.chipMaison);
        loccom = v.findViewById(R.id.chipLocCom);

        botTri = v.findViewById(R.id.menu_button);
        botTri.setOnClickListener(more);


        app.setOnClickListener(filterListener);
        garc.setOnClickListener(filterListener);
        chambre.setOnClickListener(filterListener);
        maison.setOnClickListener(filterListener);
        duplexe.setOnClickListener(filterListener);
        loccom.setOnClickListener(filterListener);

        readAnnonce();

        return v;


    }

    private void readAnnonce(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Annonce");
        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                annoncelist.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    annonce Annonce=snapshot.getValue(annonce.class);
                    annoncelist.add(Annonce );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private View.OnClickListener filterListener = new View.OnClickListener() {
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


    private View.OnClickListener more = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            PopupMenu popup = new PopupMenu(getActivity(),v);
            popup.inflate(R.menu.popup_menu);
            popup.setOnMenuItemClickListener(homeFragment.this::onMenuItemClick);
            popup.show();
        }
    };



    /*
    public void ShowPopup(View v){
        PopupMenu popup = new PopupMenu (getActivity(),v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }


     */


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_1:
                //tri par date
               // Toast.makeText(this, "tri par date", Toast.LENGTH_LONG).show();
                return true;
            case R.id.option_2:
                //tri par date
               // Toast.makeText(this, "tri par date", Toast.LENGTH_LONG).show();
                return true;
            case R.id.option_3:
                //tri par date
                //Toast.makeText(this, "tri par date", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onAnnonceItemClick(int pos, ImageView imaannonce,
                                   TextView titleann, TextView descriptionann,
                                   TextView adresseann, TextView dateann,
                                    TextView prixann) {


        //creer bundle et envoyer l'objet annonce verd annonceDetals






        /*
        Intent intent = new Intent (getActivity(),DetailsFragment.class);
        intent.putExtra("annonceObject",annoncelist.get(pos));

         */

        //shared animation setup


/*

        Pair<View, String>p1 = Pair.create((View)imaannonce,"annTN1");
        Pair<View, String>p2 = Pair.create((View)titleann,"titreTN");
        Pair<View, String>p3 = Pair.create((View)descriptionann,"descriptionTN");
        Pair<View, String>p4 = Pair.create((View)adresseann,"adresseTN");
        Pair<View, String>p5 = Pair.create((View)dateann,"dateTN");
        Pair<View, String>p7 = Pair.create((View)prixann,"prixTN");


        ;

        ActivityOptionsCompat optionsCompat
                =ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),p1,p2,p3,p4,p5,p7);



 */
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_espace,new homeFragment())
                .addSharedElement(imaannonce,"annTN1")
                .addSharedElement(titleann,"titreTN")
                .addSharedElement(descriptionann,"descriptionTN")
                .addSharedElement(adresseann,"adresseTN")
                .addSharedElement(dateann,"dateTN")
                .addSharedElement(prixann,"prixTN")
                .commit()
        ;

        DetailsFragment fragmentD = new DetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("annonceObject",annoncelist.get(pos));

        fragmentD.setArguments(bundle);

        //startActivity(intent,optionsCompat.toBundle());

    }
}
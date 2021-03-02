package com.example.findrent.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findrent.R;
import com.example.findrent.model.annonce;
import com.example.findrent.recycleAnn.AnnonceAdapter;
import com.example.findrent.recycleAnn.annonceCallback;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class homeFragment extends Fragment implements PopupMenu.OnMenuItemClickListener, annonceCallback {


    private RecyclerView recyclerViewHome;
    private AnnonceAdapter annadpter;
    private List<annonce> annoncelist;
    Spinner spinner;
    TextView textView;




    ImageView botTri;
    Chip app, chambre, garc, duplexe, maison, loccom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment


      // recyclerViewHome=getActivity().findViewById(R.id.recycler_view_home);

        spinner=v.findViewById(R.id.spinner);
        //textView=v.findViewById(R.id.textR);


        listSpiner();



        recyclerViewHome=v.findViewById(R.id.recycler_view_home);
        recyclerViewHome.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewHome.setLayoutManager(linearLayoutManager);
        annoncelist=new ArrayList<>();
        annadpter=new AnnonceAdapter(annoncelist,getContext(),this);
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

    private void listSpiner() {

        ArrayList<String> titres =new ArrayList<>();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("annonce");
        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    annonce Annonce= snapshot.getValue(annonce.class);
                    titres.add(Annonce.getTitre());
                }
                //annadpter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        spinner.setAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,titres));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              if(position == 0){

                  //Toa
              }else {
                  DatabaseReference reference= FirebaseDatabase.getInstance().getReference("annonce");
                  reference.addValueEventListener(new ValueEventListener(){

                      @Override
                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                          annoncelist.clear();
                          for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                              annonce Annonce= snapshot.getValue(annonce.class);

                              if(Annonce.getTitre().equals(parent.getItemAtPosition(position).toString()))

                              {annoncelist.add(Annonce);
                              }
                          }
                          annadpter.notifyDataSetChanged();
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {
                      }
                  });
              }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void readAnnonce(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("annonce");
        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                annoncelist.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                annonce Annonce= snapshot.getValue(annonce.class);


                    annoncelist.add(Annonce);


                }
                annadpter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                //Toast.makeText(getActivity(), "homfFragmentCancelled", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private View.OnClickListener filterListener = new View.OnClickListener() {
        public void onClick(View v) {
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("annonce");


            switch (v.getId()){
                case R.id.chipApp:
                    reference.addValueEventListener(new ValueEventListener(){

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            annoncelist.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                annonce Annonce= snapshot.getValue(annonce.class);

                                if(Annonce.getPrix().equals("appartement")) {

                                    annoncelist.add(Annonce);
                       /*annoncelist.add(new annonce(Annonce.getDescription(),Annonce.getAdresse(),
                                Annonce.getSuperficie(),Annonce.getPrix(),Annonce.getAmeublement(),Annonce.getTitre(),
                                Annonce.getDate(),Annonce.getLog(),Annonce.getAlt(),Annonce.getUri1(),
                                Annonce.getUuri2(),Annonce.getUri3(),Annonce.getUri4(),Annonce.getAnnonceid(),Annonce.getCategoeie()));



                        */

                                }
                            }
                            annadpter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                            //Toast.makeText(getActivity(), "homfFragmentCancelled", Toast.LENGTH_SHORT).show();

                        }
                    });
                    break;
                case R.id.chipCham:
                    reference.addValueEventListener(new ValueEventListener(){

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            annoncelist.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                annonce Annonce= snapshot.getValue(annonce.class);

                                if(Annonce.getCategoeie().equals("chambre")) {


                                    annoncelist.add(Annonce);
                       /*annoncelist.add(new annonce(Annonce.getDescription(),Annonce.getAdresse(),
                                Annonce.getSuperficie(),Annonce.getPrix(),Annonce.getAmeublement(),Annonce.getTitre(),
                                Annonce.getDate(),Annonce.getLog(),Annonce.getAlt(),Annonce.getUri1(),
                                Annonce.getUuri2(),Annonce.getUri3(),Annonce.getUri4(),Annonce.getAnnonceid(),Annonce.getCategoeie()));



                        */

                                }
                            }
                            annadpter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {


                        }
                    });

                    break;
                case R.id.chipGarc:
                    reference.addValueEventListener(new ValueEventListener(){

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            annoncelist.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                annonce Annonce= snapshot.getValue(annonce.class);

                                if(Annonce.getCategoeie().equals("garconiere")) {

                                    annoncelist.add(Annonce);


                                }
                            }
                            annadpter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    break;
                case R.id.chipDuplexe:

                    reference.addValueEventListener(new ValueEventListener(){

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            annoncelist.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                annonce Annonce= snapshot.getValue(annonce.class);

                                if(Annonce.getCategoeie().equals("duplexe")) {

                                }
                            }
                            annadpter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {


                        }
                    });

                    break;
                case R.id.chipMaison:
                    reference.addValueEventListener(new ValueEventListener(){

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            annoncelist.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                annonce Annonce= snapshot.getValue(annonce.class);

                                if(Annonce.getCategoeie().equals("maison")) {

                                    annoncelist.add(Annonce);

                                }
                            }
                            annadpter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                            //Toast.makeText(getActivity(), "homfFragmentCancelled", Toast.LENGTH_SHORT).show();

                        }
                    });
                    break;
                case R.id.chipLocCom:
                    reference.addValueEventListener(new ValueEventListener(){

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            annoncelist.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                annonce Annonce= snapshot.getValue(annonce.class);

                                if(Annonce.getCategoeie().equals("local com")) {

                                    annoncelist.add(Annonce);
                                }
                            }
                            annadpter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
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





    @Override
    public boolean onMenuItemClick(MenuItem item) {

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("annonce");

        switch (item.getItemId()) {

            case R.id.option_1:

                readAnnonce();
                //tri par date
                return true;
            case R.id.option_2:
                reference.addValueEventListener(new ValueEventListener(){
                    List<Double> liste = new ArrayList<>();
                    int i=1;
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       annoncelist.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            annonce Annonce= snapshot.getValue(annonce.class);

                            liste.add(Double.parseDouble(Annonce.getPrix()));
                            Collections.sort(liste);

                        }

                        for(Double k: liste){

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                annonce Annonce= snapshot.getValue(annonce.class);


                            if (Double. parseDouble(Annonce.getPrix())==k)
                            { annoncelist.add(Annonce);
                                Toast.makeText(getActivity(), "for if" , Toast.LENGTH_LONG).show();
                                i++;
                            }}
                        }

                        annadpter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                //tri par date
               // Toast.makeText(this, "tri par date", Toast.LENGTH_LONG).show();
                return true;
            case R.id.option_3:
                reference.addValueEventListener(new ValueEventListener(){
                    List<Double> liste1 = new ArrayList<>();
                    int i=1;
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        annoncelist.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            annonce Annonce= snapshot.getValue(annonce.class);

                            liste1.add(Double.parseDouble(Annonce.getPrix()));
                            Collections.sort(liste1);
                            Collections.reverse(liste1);
                        }

                        for(Double k: liste1){

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                annonce Annonce= snapshot.getValue(annonce.class);


                                if (Double. parseDouble(Annonce.getPrix())==k)
                                { annoncelist.add(Annonce);
                                    Toast.makeText(getActivity(), "for if" , Toast.LENGTH_LONG).show();
                                    i++;
                                }}
                        }

                        annadpter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
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
       /*getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_espace,new homeFragment())
                .addSharedElement(imaannonce,"annTN1")
                .addSharedElement(titleann,"titreTN")
                .addSharedElement(descriptionann,"descriptionTN")
                .addSharedElement(adresseann,"adresseTN")
                .addSharedElement(dateann,"dateTN")
                .addSharedElement(prixann,"prixTN")
                .commit()
        ;

        */


        DetailsFragment fragmentD = new DetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("annonceObject",annoncelist.get(pos));

        fragmentD.setArguments(bundle);


        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_espace, fragmentD)
                .addToBackStack(null)
                .commit();

        //startActivity(intent,optionsCompat.toBundle());

    }




}
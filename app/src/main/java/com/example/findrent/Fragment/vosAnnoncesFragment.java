package com.example.findrent.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findrent.R;
import com.example.findrent.model.annonce;
import com.example.findrent.recycleAnn.AnnAdapt;
import com.example.findrent.recycleAnn.AnnAdapt2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class vosAnnoncesFragment extends Fragment {

    private RecyclerView recyclerViewHome;
    private AnnAdapt2 annadpter;
    private List<annonce> annoncelist;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    //mDatabase = FirebaseDatabase.getInstance();
    // mDb = mDatabase.getReference();
    FirebaseUser user = mAuth.getCurrentUser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v= inflater.inflate(R.layout.fragment_vos_annonces, container, false);

        // Inflate the layout for this fragment


        // recyclerViewHome=getActivity().findViewById(R.id.recycler_view_home);

        recyclerViewHome=v.findViewById(R.id.recycler_view_vosannonce);
        recyclerViewHome.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewHome.setLayoutManager(linearLayoutManager);
        annoncelist=new ArrayList<>();
        annadpter=new AnnAdapt2(annoncelist, getContext());
        recyclerViewHome.setAdapter(annadpter);
        // Inflate the layout for this fragment

        readAnnonce();

        return v;
    }


    private void readAnnonce(){
        FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("vos annonces").addValueEventListener(new ValueEventListener(){

        //DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Annonce");


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
}
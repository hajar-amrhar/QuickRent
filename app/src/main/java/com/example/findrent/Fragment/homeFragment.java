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
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.findrent.Home;
import com.example.findrent.R;
import com.example.findrent.model.annonce;
import com.example.findrent.recycleAnn.AnnAdapt;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homeFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {


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
        annadpter=new AnnAdapt(getContext(),annoncelist);
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
}
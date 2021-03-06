package com.example.findrent.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.findrent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class contacterFragment extends Fragment {

String Uid;
    TextView phn,eml;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_contacter, container, false);

        // Inflate the layout for this fragment
         Uid = getArguments().getString("keyUid");




        phn=v.findViewById(R.id.phnC);
        eml=v.findViewById(R.id.emailC);


        getCont();

        return  v;
    }

    public void getCont(){

        FirebaseDatabase.getInstance().getReference().child("users").child(Uid).child("phone").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    phn.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });

        FirebaseDatabase.getInstance().getReference().child("users").child(Uid).child("email").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    eml.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });

    }
}
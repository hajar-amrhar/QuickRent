package com.example.findrent.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.findrent.Availability;
import com.example.findrent.FirebaseHandler;
import com.example.findrent.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class profileFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_profile, container, false);

        // inflater.inflate(R.layout.fragment_profile, container, false);
        // return inflater.inflate(R.layout.fragment_profile, container, false);

        EditText tvProfileUserName;
        EditText tvProfileEmail;
        EditText tvProfileFullName;
        EditText tvProfilePhone;
        EditText tvProfileAddress;

        Button btnUpdate;

        String USER_TABLE = "users";
        DatabaseReference userDatabase;


        userDatabase = new FirebaseHandler().getFirebaseConnection(USER_TABLE);

        tvProfileUserName = v.findViewById(R.id.tvProfileUserName);
        tvProfileEmail = v.findViewById(R.id.tvProfileEmail);
        tvProfileFullName = v.findViewById(R.id.tvProfileFullName);
        tvProfilePhone = v.findViewById(R.id.tvProfilePhone);
        tvProfileAddress = v.findViewById(R.id.tvProfileAddress);
        btnUpdate = v.findViewById(R.id.btnUpdate);

/*        tvProfileUserName.setText(Availablity.currentdata.getUsername());
        tvProfileEmail.setText(Availablity.currentdata.getEmail());
        tvProfileFullName.setText(tvProfileFullName.getText());
        tvProfilePhone.setText(Availablity.currentdata.getPhone());
        tvProfileAddress.setText(tvProfileAddress.getText());*/

        btnUpdate.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        java.util.Map<String, Object> userChildUpdates = new HashMap<>();
                        userChildUpdates.put("password1", tvProfileAddress.getText().toString().trim());
                        userChildUpdates.put("username", tvProfileFullName.getText().toString().trim());
                        userChildUpdates.put("email", tvProfileEmail.getText().toString().trim());
                        userChildUpdates.put("phone", tvProfilePhone.getText().toString().trim());

                        userDatabase.child(Availability.currentdata.getUsername()).updateChildren(userChildUpdates);
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Success")

                                .setMessage("Updated successfully!")
                                .show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



        return v;

    }
}
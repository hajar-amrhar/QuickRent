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
import android.widget.TextView;
import android.widget.Toast;

import com.example.findrent.Availability;
import com.example.findrent.FirebaseHandler;
import com.example.findrent.R;
import com.example.findrent.data;
import com.example.findrent.model.annonce;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class profileFragment extends Fragment {


    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = mAuth.getCurrentUser();
    EditText ProfileEmail,ProfileuserName,ProfilePhone,ProfilePswrd;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());

    Button btnUpdate;
    TextView logout;

    private data Data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_profile, container, false);

        ProfileEmail = v.findViewById(R.id.ProfileEmail);
        ProfileuserName = v.findViewById(R.id.ProfileuserName);
        ProfilePhone = v.findViewById(R.id.ProfilePhone);
        ProfilePswrd = v.findViewById(R.id.ProfilePassword);

        btnUpdate = v.findViewById(R.id.btnUpdate);
        logout = v.findViewById(R.id.deconnection);





        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     Data= dataSnapshot.getValue(data.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

       // showData();

        btnUpdate.setOnClickListener(update);
        logout.setOnClickListener(deconnection);



     /*   btnUpdate.setOnClickListener(new android.view.View.OnClickListener() {
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



      */

        return v;

    }


    public void showData(){
        ProfileuserName.setText(Data.getUsername());
        ProfileEmail.setText(Data.getEmail());
        ProfilePhone.setText(Data.getPhone());
        ProfilePswrd.setText(Data.getPassword1());
    }

    private View.OnClickListener update = new View.OnClickListener() {
        public void onClick(View v) {
        if(nameChanged() || pswrdChanged() || phnChange() || emlChanged())
        {Toast.makeText(getActivity(),"mise à jour avec succès",Toast.LENGTH_LONG).show();

        showData();}
        showData();}

    };

    private boolean nameChanged() {
        if (!Data.getUsername().equals(ProfileuserName.getText().toString().trim())) {
            reference.child("username").setValue(ProfileuserName.getText().toString().trim());

            return true;
        } else

            return false;
    }

    private boolean phnChange() {
        if (!Data.getPhone().equals(ProfilePhone.getText().toString().trim())) {
            reference.child("phone").setValue(ProfilePhone.getText().toString().trim());

            return true;
        } else

            return false;    }

    private boolean pswrdChanged() {
        if (!Data.getPassword1().equals(ProfilePswrd.getText().toString().trim())) {
            reference.child("password1").setValue(ProfilePswrd.getText().toString().trim());

            return true;
        } else

            return false;    }

    private boolean emlChanged() {

        if (!Data.getEmail().equals(ProfileEmail.getText().toString().trim())) {
            reference.child("email").setValue(ProfileEmail.getText().toString().trim());

            return true;
        } else

            return false;    }


    private View.OnClickListener deconnection = new View.OnClickListener() {
        public void onClick(View v) {
            mAuth.signOut();
        }
    };

}
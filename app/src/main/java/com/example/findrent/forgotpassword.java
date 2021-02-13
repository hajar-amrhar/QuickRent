package com.example.findrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class forgotpassword extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    TextInputLayout usernm;
    Button Reintialise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        mAuth = FirebaseAuth.getInstance();

        usernm=findViewById(R.id.username_forgot);

        Reintialise=findViewById(R.id.new_password);
        Reintialise.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String eml = usernm.getEditText().getText().toString();
        if (eml.isEmpty()) {
            usernm.setError("email incorrecte");
            usernm.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(eml).matches()) {

            usernm.setError("email incomplet");
            usernm.requestFocus();
            return;}





        mAuth.sendPasswordResetEmail(eml).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(forgotpassword.this, "consultez votre boite email pour réinitialiser le mot d passe", Toast.LENGTH_SHORT).show();}


                else {Toast.makeText(forgotpassword.this, "le compte n'existe pas ", Toast.LENGTH_SHORT).show(); }

            }
            });}



}
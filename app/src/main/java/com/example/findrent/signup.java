package com.example.findrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DatabaseMetaData;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    Button create1, signin ;

    DatabaseReference myRef=FirebaseDatabase.getInstance().getReference("users");

    String nm,phn,eml,pswrd;
    TextInputLayout regNm,regPhn,regPswrd,regEml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mAuth = FirebaseAuth.getInstance();


        regNm=findViewById(R.id.username);
        regPhn=findViewById(R.id.phone);
        regEml=findViewById(R.id.email);
        regPswrd=findViewById(R.id.password1);

        create1=findViewById(R.id.createAcc);
        create1.setOnClickListener(this);

        signin=findViewById(R.id.loginAl);
        signin.setOnClickListener(this);



    }
    @Override
    public void onClick(View v){
       // myRef = FirebaseDatabase.getInstance().getReference();//myRef= node.getReference("users");


        switch (v.getId()) {
            case R.id.loginAl:
                startActivity(new Intent(this,login.class));
                break;


            case R.id.createAcc:
                creatingAcc();
                break;

        }

    }

    private void creatingAcc() {
        //recupperer les vall à passer vers database
        nm=regNm.getEditText().getText().toString().trim();
        phn=regPhn.getEditText().getText().toString();
        eml=regEml.getEditText().getText().toString();
        pswrd=regPswrd.getEditText().getText().toString();

        if(nm.isEmpty()){
            regNm.setError("nom d'utilisateur incorrect");
            regNm.requestFocus();
            return;
        }
        if(eml.isEmpty()){
            regEml.setError("email incorrect");
            regEml.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(eml).matches()){

            regEml.setError("email incorrect");
            regEml.requestFocus();
            return;
        }
        if(phn.isEmpty()){
            regPhn.setError("numéro de téléphone incorrect");
            regPhn.requestFocus();
            return;
        }

        if(pswrd.isEmpty()){
            regPswrd.setError("");
            regPswrd.requestFocus();
            return;
        }
        if(pswrd.length()<6){
            regPswrd.setError("entrer 6 cars ou plus ");
            regPswrd.requestFocus();
            return;
        }

// -------------??
        //tester si l'utilisateur a dejà un compte avant s'euthentifier
        mAuth.createUserWithEmailAndPassword(eml,pswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){ // l'utilisateur enregistré avec succes ?
                    data Data =new data(nm,phn,eml,pswrd);
                    //si oui envoyer les don"es vers database

                    DatabaseReference condit=myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                    condit.setValue(Data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(signup.this, "votre compte a etait créer avec succes ", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(signup.this,Home.class)); //passer vers home
                            }else{
                                Toast.makeText(signup.this, "Création échouée", Toast.LENGTH_LONG).show();

                            }
                            }
                    });

                }else{
                    Toast.makeText(signup.this, "authentification échouée", Toast.LENGTH_LONG).show();

                }
            }
        });

}}
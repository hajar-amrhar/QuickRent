package com.example.findrent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class login extends AppCompatActivity implements View.OnClickListener {

    //ProgressBar PB;
    private static final int RC_SIGN_IN = 111;
    private static final String TAG = "tag";
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    private GoogleSignInClient mGoogleSignInClient;
    private String eml, pswrd;
    private TextInputLayout regPswrd, regEml;
    ImageView fb, ggl, gml;
    Button callSignUp, connect;
    TextView forgPswrd;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        if(currentUser != null){
             startActivity(new Intent(login.this,Home.class));


        }}


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.create);
        connect = findViewById(R.id.connect);


        regEml = findViewById(R.id.username);
        regPswrd = findViewById(R.id.password);
        forgPswrd = findViewById(R.id.forgotPswrd);
        //fb = findViewById(R.id.facebook);
        ggl = findViewById(R.id.google);
       // gml = findViewById(R.id.email);

        callSignUp.setOnClickListener(this);
        connect.setOnClickListener(this);
        forgPswrd.setOnClickListener(this);
        //fb.setOnClickListener(this);
        ggl.setOnClickListener(this);
        //gml.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create: {

                startActivity(new Intent(this, signup.class));
                break;

            }
            case R.id.connect: {
                loginAuth();
                break;
                // Intent intentConnect=new Intent(login.this,CreatSuccess.class);
            }
            case R.id.forgotPswrd:

                startActivity(new Intent(this, forgotpassword.class));
                break;


            case R.id.google:
                signIn();
                break;

            case R.id.email:
                signin_gmail();
                break;
        }
    }

    private void signin_gmail() {
    }


    private void signIn() {
        //PB.setVisibility(View.INVISIBLE);

        // afficher le compte google existant
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show();
            }
        }
    }




    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(login.this, "signInWithCredential:success", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(login.this,Home.class));

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                           // Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                           // updateUI(null);
                            Toast.makeText(login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    private void signin_fb() {

    }

    private void loginAuth() {


        eml=regEml.getEditText().getText().toString();
        pswrd=regPswrd.getEditText().getText().toString();

        if(eml.isEmpty()){
            regEml.setError("email incorrecte");
            regEml.requestFocus();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(eml).matches()){

            regEml.setError("email incomplet");
            regEml.requestFocus();
            return;
        }
        if(pswrd.isEmpty()){
            regPswrd.setError("èèèèè");
            regPswrd.requestFocus();
            return;
        }
        if(pswrd.length()<6){
            regPswrd.setError("entrer 6 cars ou plus ");
            regPswrd.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(eml,pswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                PB.setVisibility(View.INVISIBLE);


                if (task.isSuccessful()){
                    FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        startActivity(new Intent(login.this,Home.class));

                    }
                    else{
                        Toast.makeText(login.this,"verifiez votre mot de passe  ",Toast.LENGTH_SHORT).show();
                }


                }
            else {
                Toast.makeText(login.this,"mot de passe ou nom d'utilisateur est incorrect",Toast.LENGTH_SHORT).show();
            }
            }
        });{

        }

    }
}
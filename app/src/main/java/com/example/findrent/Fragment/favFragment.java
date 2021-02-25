package com.example.findrent.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findrent.R;
import com.example.findrent.model.annonce;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import static android.content.ContentValues.TAG;

public class favFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fav, container, false);
        View like = v.findViewById (R.id.likeAnnonce);
        like.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                System.out.println("added to favorites...");//check logcat
            }

        });
        return v;
    }
    /*

    private void onStarClicked(DatabaseReference favoriteRef){
        favoriteRef .runTransaction(new Transaction.Handler () {
           @NonNull
           @Override
           public Transaction.Result doTransaction(@NonNull MutableData currentData) {
               annonce a=currentData.getValue (annonce.class);
                       if(a==null){
                        return Transaction.success (currentData);
                       }
                       if(a.like){
                           //favorite does notv exist create it
                         currentData.setValue (a);
                       }
                       else {
                           //favorite exists, remove it
                           currentData.setValue (null);
                       }

               return Transaction.success (currentData);

           }

           @Override

            public void onComplete(DatabaseError databaseError, boolean committed,
                                   DataSnapshot currentData) {
                // Transaction completed
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }
       });

        }
       private void onStartClicked(DatabaseReference annonceRef) {
        annonceRef.runTransaction (new Transaction.Handler () {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                annonce a = currentData.getValue (annonce.class);
                if (a == null) {
                    return Transaction.success (currentData);
                }
                if (a.stars.containsKey (getId ())) {
                    //Unstar the post and remove self from stars
                    a.starCount = a.starCount - 1;
                    a.stars.remove (getId ());
                } else {
                    //star the post and add self to stars
                    a.starCount = a.starCount + 1;
                    a.stars.put (getId (), true);
                }
//set value and report transaction success
                currentData.setValue (a);
                return Transaction.success (currentData);
            }
            @Override
            public void onComplete(DatabaseError databaseError, boolean committed,
                                   DataSnapshot currentData) {
                // Transaction completed
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }
        });
       }


     */
    }




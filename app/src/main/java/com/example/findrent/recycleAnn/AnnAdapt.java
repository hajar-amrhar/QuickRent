package com.example.findrent.recycleAnn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.findrent.Fragment.homeFragment;
import com.example.findrent.R;
import com.example.findrent.model.User;
import com.example.findrent.model.annonce;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AnnAdapt extends RecyclerView.Adapter<AnnAdapt.AnnonceViewHolder>{


    public List<annonce> mdata;
    public Context mContext;
    annonceCallback callback;

    private FirebaseUser firebaseUser;


    public AnnAdapt(List<annonce> mdata, Context mContext, annonceCallback callback) {
        this.mdata = mdata;
        this.mContext = mContext;
        this.callback = callback;
    }

    @NonNull
    @Override
    public AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_annonce,parent,false);


        return new AnnonceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnonceViewHolder holder, int position) {

        //recupere les dennées de l'annonce du database et les mettre dans l'item_annonce


        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        annonce annonce=mdata.get(1);

        Glide.with(mContext).load(annonce.getUri1()).into(holder.imAnn);


        if (annonce.getDescription().equals("")){
            holder.desc.setVisibility(View.GONE);
        }else{
          holder.desc.setVisibility(View.VISIBLE);
            holder.desc.setText(annonce.getDescription());
        }
        holder.titre.setVisibility(View.VISIBLE);
        holder.titre.setText(annonce.getTitre());

        holder.date.setVisibility(View.VISIBLE);
        holder.date.setText(annonce.getDate());

        holder.prix.setVisibility(View.VISIBLE);
        holder.prix.setText(annonce.getPrix());

        holder.adresse.setVisibility(View.VISIBLE);
        holder.adresse.setText(annonce.getAdresse());

        holder.category.setVisibility(View.VISIBLE);
        holder.category.setText(annonce.getCategoeie());

        holder.superficie.setVisibility(View.VISIBLE);
        holder.superficie.setText(annonce.getSuperficie());

        holder.ameublement.setVisibility(View.VISIBLE);
        holder.ameublement.setText(annonce.getAmeublement());




       // publisher(holder.pdp,holder.username,annonce.getAnnonceid); //???
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }






    public class AnnonceViewHolder extends RecyclerView.ViewHolder {



        //annonce sur home
        ImageView imfav,imAnn,pdp,imann2,imann3,imann4;
        TextView titre, desc,adresse, prix,date, superficie,category,ameublement;
        RatingBar ratingBar;
        
        public AnnonceViewHolder(@NonNull View itemView) {
            super(itemView);


            // recupere les id des elements de l'item_annonce

            //pdp=itemView.findViewById(R.id.pdp);// a ajouter à l'annonce
            imAnn=itemView.findViewById(R.id.imageAnnonce);






            titre=itemView.findViewById(R.id.titreAnnonce);
            desc=itemView.findViewById(R.id.desciption);

            adresse=itemView.findViewById(R.id.adresseAnnonce);

            ratingBar=itemView.findViewById(R.id.ratingBar);
            imfav=itemView.findViewById(R.id.imageView2);
            prix=itemView.findViewById(R.id.prixAnnonce);
            date=itemView.findViewById(R.id.dateAnnonce);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    callback.onAnnonceItemClick(getAdapterPosition(),imAnn,titre,
                            desc,adresse,date,prix);



                }
            });

        }
    }
    //info sur annonceur
   /* private void publisher(final ImageView image_profile,final TextView username,final String userid){

        DatabaseReference reference = FirebaseDatabase.getInstance().getInstance().getReference("Users").child(userid);

        reference.addValueEventListener(new ValueEventListener(){


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user= snapshot.getValue(User.class);
               // Glide.with(mContext).load(User.getImageurl()).into(image_profile);
                username.setText(User.getUsername());
                //publisher.setText(User.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/


}

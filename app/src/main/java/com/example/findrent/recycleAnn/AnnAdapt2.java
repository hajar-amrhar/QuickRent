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
import com.example.findrent.R;
import com.example.findrent.model.annonce;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class AnnAdapt2 extends RecyclerView.Adapter<AnnAdapt2.AnnonceViewHolder>{


    public List<annonce> mdata;
    public Context mContext;

    private FirebaseUser firebaseUser;

    public AnnAdapt2(List<annonce> mdata, Context mContext) {
        this.mdata = mdata;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public AnnAdapt2.AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_annonce,parent,false);


        return new AnnAdapt2.AnnonceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnAdapt2.AnnonceViewHolder holder, int position) {
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



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AnnonceViewHolder extends RecyclerView.ViewHolder {
        //annonce sur home
        ImageView imfav,imAnn,pdp;
        TextView titre, desc,adresse, prix,date;
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

        }
    }
}

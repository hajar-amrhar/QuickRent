package com.example.findrent.model;

import android.widget.ImageView;

//ajouter annonce
public class annonce {

    private String description, adresse, superficie,prix, ameublement, titre,date,pdp,user_name;
    private ImageView AnnonceImage ;


    public annonce() {

    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPdp() {
        return pdp;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPdp(String pdp) {
        this.pdp = pdp;
    }

    public ImageView getAnnonceImage() {
        return AnnonceImage;
    }

    public void setAnnonceImage(ImageView annonceImage) {
        AnnonceImage = annonceImage;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAmeublement() {
        return ameublement;
    }

    public void setAmeublement(String ameublement) {
        this.ameublement = ameublement;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superfice) {
        this.superficie = superfice;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

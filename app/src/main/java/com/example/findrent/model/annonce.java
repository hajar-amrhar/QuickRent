package com.example.findrent.model;

import android.widget.ImageView;

import java.io.Serializable;

//ajouter annonce
public class annonce implements Serializable {


    private String description, adresse, superficie, prix, ameublement,categoeie, titre, date, log, alt, uri1, uuri2, uri3, uri4,annonceid;

    public annonce() {
    }

    public annonce(String description, String adresse, String superficie, String prix, String ameublement, String titre, String date, String log, String alt, String uri1, String uuri2, String uri3, String uri4, String annonceid, String categorie) {
        this.description = description;
        this.adresse = adresse;
        this.superficie = superficie;
        this.prix = prix;
        this.ameublement = ameublement;
        this.titre = titre;
        this.date = date;
        this.log = log;
        this.alt = alt;
        this.uri1 = uri1;
        this.uuri2 = uuri2;
        this.uri3 = uri3;
        this.uri4 = uri4;
        this.annonceid = annonceid;
        this.categoeie=categorie;
    }

    public String getCategoeie() {
        return categoeie;
    }

    public void setCategoeie(String categoeie) {
        this.categoeie = categoeie;
    }

    public String getAnnonceid() {
        return annonceid;
    }

    public void setAnnonceid(String annonceid) {
        this.annonceid = annonceid;
    }

    public String getPublisher() {
        return annonceid;
    }

    public void setPublisher(String publisher) {
        this.annonceid = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getAmeublement() {
        return ameublement;
    }

    public void setAmeublement(String ameublement) {
        this.ameublement = ameublement;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getUri1() {
        return uri1;
    }

    public void setUri1(String uri1) {
        this.uri1 = uri1;
    }

    public String getUuri2() {
        return uuri2;
    }

    public void setUuri2(String uuri2) {
        this.uuri2 = uuri2;
    }

    public String getUri3() {
        return uri3;
    }

    public void setUri3(String uri3) {
        this.uri3 = uri3;
    }

    public String getUri4() {
        return uri4;
    }

    public void setUri4(String uri4) {
        this.uri4 = uri4;
    }
}
package com.example.firebasecrud;

import com.google.firebase.database.Exclude;

public class Voiture {

    private String key;
    private String name;
    private String couleur;
    private String description;
    private String marque ;
    private int url;
    private int prix;

    public Voiture() {
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Voiture(String name, String couleur, String description, String marque, int url, int prix) {
        this.name = name;
        this.couleur = couleur;
        this.description = description;
        this.marque = marque;
        this.url = url;
        this.prix = prix;
    }

    public String getMarque() {
        return marque;
    }

    public int getPrix() {
        return prix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

}

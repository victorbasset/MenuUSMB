package com.example.srava.menuusmb.Class;

/**
 * Created by perezgoa on 01/03/2016.
 */
// class categorie permettant de recuperer les categories des plats
public class Categorie {
    public String id_categorie;
    public String libelle_categorie;

    public Categorie() {
    }

    public Categorie(String id,String restaurant) {
        this.id_categorie = id;
        this.libelle_categorie=restaurant;
    }

    public String getIdRestaurant() {
        return id_categorie;
    }
    public String getLibelleRestaurant() {
        return libelle_categorie;
    }

    @Override
    public String toString() {
        return id_categorie+" :"+ libelle_categorie;
    }
}

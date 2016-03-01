package com.example.srava.menuusmb;

/**
 * Created by modenaq on 01/03/2016.
 */
public class Plat {
    public String id_plat;
    public String libelle_plat;
    public Float prix_plat;
    public String id_categorie;
    public String id_restaurant;

    public Restaurant() {
    }

    public Restaurant(String id,String libelle,Float prix,String idcategorie,String idrestaurant) {
        this.id_plat = id;
        this.libelle_plat=libelle;
    }

    public String getIdRestaurant() {
        return id_restaurant;
    }
    public String getLibelleRestaurant() {
        return libelle_restaurant;
    }

    @Override
    public String toString() {
        return id_restaurant+" :"+ libelle_restaurant;
    }
}

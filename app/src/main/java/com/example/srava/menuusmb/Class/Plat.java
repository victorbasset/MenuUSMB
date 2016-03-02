package com.example.srava.menuusmb.Class;

/**
 * Created by modenaq on 01/03/2016.
 */
public class Plat {
    public String id_plat;
    public String libelle_plat;
    public String prix_plat;
    public String id_categorie;
    public String id_restaurant;
    public String jour;

    public Plat() {
    }

    public Plat(String id,String libelle,String prix,String idcategorie,String idrestaurant,String jour) {
        this.id_plat = id;
        this.jour=jour;
        this.libelle_plat=libelle;
        this.prix_plat=prix;
        this.id_categorie=idcategorie;
        this.id_restaurant=idrestaurant;
    }

    @Override
    public String toString() {
        return id_plat+" : "+libelle_plat+" : "+prix_plat+" : "+id_categorie+" : "+id_restaurant+" : "+jour;
    }
}

package com.example.srava.menuusmb.Class;

/**
 * Created by modenaq on 01/03/2016.
 */
// class plat permettant de recuperer les plats
public class Plat {
    public String id_plat;
    public String libelle_plat;
    public String prix_plat;
    public String id_categorie;
    public String id_restaurant;
    public String jour;
    public Categorie categorie;
    public Restaurant restaurant;

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

    public void LinkTables(){
        for (Restaurant r : Restaurants.listeRestaurants){
            if(this.id_restaurant==r.id_restaurant)
                this.restaurant=r;
        }

        for(Categorie c:Categories.listeCategories){
            if(this.id_categorie==c.id_categorie)
                this.categorie=c;
        }
    }

    @Override
    public String toString() {
        return id_plat+" : "+libelle_plat+" : "+prix_plat+" : "+id_categorie+" : "+id_restaurant+" : "+jour;
    }
}

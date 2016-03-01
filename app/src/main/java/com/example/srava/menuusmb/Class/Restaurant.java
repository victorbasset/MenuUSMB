package com.example.srava.menuusmb.Class;

/**
 * Created by modenaq on 01/03/2016.
 */
public class Restaurant {
    public String id_restaurant;
    public String libelle_restaurant;

    public Restaurant() {
    }

    public Restaurant(String id,String restaurant) {
        this.id_restaurant = id;
        this.libelle_restaurant=restaurant;
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

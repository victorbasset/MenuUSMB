package com.example.srava.menuusmb.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by modenaq on 01/03/2016.
 */
// class restaurants permettant de recuperer la liste des restaurants
public class Restaurants {
    public static List<Restaurant> listeRestaurants;

    public Restaurants() {
        listeRestaurants=new ArrayList<Restaurant>();
    }

    public Restaurants(Restaurant restaurant) {
        listeRestaurants.add(restaurant);
    }

    @Override
    public String toString() {
        return "A completer";
    }



}

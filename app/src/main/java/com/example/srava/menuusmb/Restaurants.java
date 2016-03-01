package com.example.srava.menuusmb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by modenaq on 01/03/2016.
 */
public class Restaurants {
    public List<Restaurant> listeRestaurants;

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

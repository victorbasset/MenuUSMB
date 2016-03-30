package com.example.srava.menuusmb.Class;

import java.util.Date;

/**
 * Created by perezgoa on 01/03/2016.
 */
// class Note Restaurant permettant de recuperer les notes  des restaurants
public class NoteRestaurant {
    public String id_note_restaurant;
    public int note;
    public String commentaire;
    public String date;
    public String id_restaurant;
    public Restaurant restaurant;

    public NoteRestaurant() {
    }

    public NoteRestaurant(String id_note,int note, String commentaire,String date,String id_restaurant) {
        this.id_note_restaurant = id_note;
        this.note=note;
        this.commentaire=commentaire;
        this.date=date;
        this.id_restaurant=id_restaurant;

    }

    public void LinkTables(){
        for (Restaurant r : Restaurants.listeRestaurants){
            if(this.id_restaurant==r.id_restaurant)
                this.restaurant=r;
        }
    }

    @Override
    public String toString() {
        return id_note_restaurant+" : "+note+" : "+commentaire+" : "+date;
    }
}

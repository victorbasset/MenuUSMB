package com.example.srava.menuusmb.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perezgoa on 01/03/2016.
 */
// class Note Restaurant permettant de recuperer les notes  des restaurants
public class NoteRestaurants {
    public static List<NoteRestaurant> listeNoteRestaurants;

    public NoteRestaurants() {
        listeNoteRestaurants=new ArrayList<NoteRestaurant>();
    }

    public NoteRestaurants(NoteRestaurant noteRestaurant) {
        listeNoteRestaurants.add(noteRestaurant);
    }

    @Override
    public String toString() {
        return "A completer";
    }
}

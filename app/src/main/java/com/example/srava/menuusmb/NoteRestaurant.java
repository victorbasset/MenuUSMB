package com.example.srava.menuusmb;

import java.util.Date;

/**
 * Created by perezgoa on 01/03/2016.
 */
public class NoteRestaurant {
    public String _id_note;
    public int _note;
    public String _commentaire;
    public Date _date;

    public NoteRestaurant() {
    }

    public NoteRestaurant(String id_note,int note, String commentaire,Date date) {
        this._id_note = id_note;
        this._note=note;
        this._commentaire=commentaire;
        this._date=date;

    }

    @Override
    public String toString() {
        return "A completer";
    }
}

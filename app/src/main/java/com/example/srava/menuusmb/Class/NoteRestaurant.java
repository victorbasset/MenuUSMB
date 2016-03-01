package com.example.srava.menuusmb.Class;

import java.util.Date;

/**
 * Created by perezgoa on 01/03/2016.
 */
public class NoteRestaurant {
    public String _id_note;
    public int _note;
    public String _commentaire;
    public String _date;

    public NoteRestaurant() {
    }

    public NoteRestaurant(String id_note,int note, String commentaire,String date) {
        this._id_note = id_note;
        this._note=note;
        this._commentaire=commentaire;
        this._date=date;

    }

    @Override
    public String toString() {
        return _id_note+" : "+_note+" : "+_commentaire+" : "+_date;
    }
}

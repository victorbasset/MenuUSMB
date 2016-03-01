package com.example.srava.menuusmb;

import java.util.Date;

/**
 * Created by modenaq on 01/03/2016.
 */
public class NotesPlat {
    public String id_note;
    public Integer note;
    public String commentaire;
    public Date date;

    public NotesPlat() {
    }

    public NotesPlat(String id,Integer note,String commentaire,Date date) {
        this.id_note = id;
        this.note=note;
        this.commentaire=commentaire;
        this.date=date;
    }

    @Override
    public String toString() {
        return "A completer";
    }
}

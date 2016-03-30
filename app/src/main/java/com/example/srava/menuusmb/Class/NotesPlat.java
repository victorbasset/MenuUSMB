package com.example.srava.menuusmb.Class;

import java.util.Date;

/**
 * Created by modenaq on 01/03/2016.
 */
// class Note Plat permettant de recuperer les notes  des plats
public class NotesPlat {
    public String id_note_plat;
    public Integer note;
    public String commentaire;
    public String date;
    public String id_plat;
    public Plat plat;

    public NotesPlat() {
    }

    public NotesPlat(String id,Integer note,String commentaire,String date,String id_plat) {
        this.id_note_plat = id;
        this.note=note;
        this.commentaire=commentaire;
        this.date=date;
        this.id_plat=id_plat;
    }

    public void LinkTables(){
        for (Plat p : Plats.listePlats){
            if(this.id_plat==p.id_plat)
                this.plat=p;
        }
    }

    @Override
    public String toString() {
        return id_note_plat+" : "+note+" : "+commentaire+" : "+date;
    }
}

package com.example.srava.menuusmb.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by modenaq on 01/03/2016.
 */
public class NotesPlats {
    public static List<NotesPlat> listeNotesPlats;

    public NotesPlats() {
        listeNotesPlats=new ArrayList<NotesPlat>();
    }

    public NotesPlats(NotesPlat noteplat) {
        listeNotesPlats.add(noteplat);
    }

    @Override
    public String toString() {
        return "A completer";
    }

}

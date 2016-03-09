package com.example.srava.menuusmb.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by modenaq on 01/03/2016.
 */
public class Plats {
    public static List<Plat> listePlats;

    public Plats() {
        listePlats=new ArrayList<Plat>();
    }

    public Plats(Plat plat) {
        listePlats.add(plat);
    }

    @Override
    public String toString() {
        return "A completer";
    }
}

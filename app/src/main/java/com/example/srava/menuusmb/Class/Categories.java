package com.example.srava.menuusmb.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perezgoa on 01/03/2016.
 */
// class categories permettant de recuperer la liste des categories
public class Categories {
    public static List<Categorie> listeCategories;

    public Categories() {
        listeCategories=new ArrayList<Categorie>();
    }

    public Categories(Categorie categorie) {
        listeCategories.add(categorie);
    }

    @Override
    public String toString() {
        return "A completer";
    }
}

package com.example.srava.menuusmb.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by perezgoa on 02/03/2016.
 */
public class GestionDBhelper extends SQLiteOpenHelper {

    // String permettant la creation de la table
    private static final String DATABASE_CREATE = "CREATE TABLE RESTAURANT ( " +
            " id_restaurant integer , " +
            " libelle_restaurant  character varying (50) " +
            " " +
            "); " +
            "CREATE TABLE PLAT( " +
            " id_plat integer , " +
            " libelle_plat  character varying (50), " +
            " prix numeric(5,2), " +
            "id_categorie integer not null, " +
            "id_restaurant integer not null, " +
            "jour date " +
            "); " +
            "CREATE TABLE CATEGORIE_PLAT( " +
            " id_categorie integer , " +
            " libelle_categorie character varying (50) " +
            " " +
            "); " +
            "CREATE TABLE NOTE_PLAT( " +
            " id_note integer , " +
            " note integer not null, " +
            "commentaire character varying (200), " +
            "date date " +
            "); " +
            "CREATE TABLE NOTE_RESTAURANT( " +
            " id_note integer , " +
            " note integer not null, " +
            "commentaire character varying (200), " +
            "date date " +
            "); ";


    public GestionDBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                         int version) {
        super(context, name, factory, version);
        Log.i("ShotsDBhelper", "Helper construit");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// creation de la table
        db.execSQL(DATABASE_CREATE);
        Log.i("ShotsDBhelper", "Database created with instruction : " +
                DATABASE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// destruction de la base et recreation
        db.execSQL("DROP TABLE IF EXISTS RESTAURANT");
        db.execSQL("DROP TABLE IF EXISTS PLAT");
        db.execSQL("DROP TABLE IF EXISTS CATEGORIE_PLAT");
        db.execSQL("DROP TABLE IF EXISTS NOTE_PLAT");
        db.execSQL("DROP TABLE IF EXISTS NOTE_RESTAURANT");

        Log.i("ShotsDBhelper", "Base mise a jour !!!");
        onCreate(db);
    }
}
package com.example.srava.menuusmb.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by perezgoa on 02/03/2016.
 */
public class GestionDBhelper extends SQLiteOpenHelper {

    public static final String RESTAURANT_TABLE="RESTAURANT";
    public static final String RESTAURANT_ID="_id";
    public static final String RESTAURANT_ID_RESTAURANT="id_restaurant";
    public static final String RESTAURANT_LIBELLE="libelle_restaurant";

    public static final String PLAT_TABLE="PLAT";
    public static final String PLAT_ID="_id";
    public static final String PLAT_ID_PLAT="id_plat";
    public static final String PLAT_LIBELLE="libelle_plat";
    public static final String PLAT_PRIX="prix";
    public static final String PLAT_ID_CAT="id_categorie";
    public static final String PLAT_ID_REST="id_restaurant";
    public static final String PLAT_JOUR="jour";

    public static final String CATEGORIE_TABLE="CATEGORIE_PLAT";
    public static final String CATEGORIE_ID="_id";
    public static final String CATEGORIE_ID_CATEGORIE="id_categorie";
    public static final String CATEGORIE_LIBELLE="libelle_categorie";

    public static final String NOTE_PLAT_TABLE="NOTE_PLAT";
    public static final String NOTE_PLAT_ID="_id";
    public static final String NOTE_PLAT_ID_PLAT="id_plat_plat";
    public static final String NOTE_PLAT_ID_NOTE_PLAT="id_note";
    public static final String NOTE_PLAT_NOTE="note";
    public static final String NOTE_PLAT_COMMENTAIRE="commentaire";
    public static final String NOTE_PLAT_DATE="date";

    public static final String NOTE_RESTAURANT_TABLE="NOTE_RESTAURANT";
    public static final String NOTE_RESTAURANT_ID="_id";
    public static final String NOTE_RESTAURANT_ID_NOTE_RESTAURANT="id_note_restaurant";
    public static final String NOTE_RESTAURANT_NOTE="note";
    public static final String NOTE_RESTAURANT_COMMENTAIRE="commentaire";
    public static final String NOTE_RESTAURANT_DATE="date";
    public static final String NOTE_RESTAURANT_ID_RESTAURANT="id_restaurant";

    // String permettant la creation de la table
    private static final String DATABASE_CREATE_RESTAURANT = "CREATE TABLE "+RESTAURANT_TABLE+" ( " +
            RESTAURANT_ID +" integer primary key autoincrement , " +
            RESTAURANT_ID_RESTAURANT+ " integer,"+
            RESTAURANT_LIBELLE+" text " +
            "); ";
    private static final String DATABASE_CREATE_PLAT = "CREATE TABLE "+PLAT_TABLE+"( " +
            PLAT_ID+" integer primary key autoincrement , " +
            PLAT_ID_PLAT+ " integer,"+
            PLAT_LIBELLE+"  text , " +
            PLAT_PRIX+" numeric, " +
            PLAT_ID_CAT+" integer not null, " +
            PLAT_ID_REST+" integer not null, " +
            PLAT_JOUR+" text " +
            "); ";

    private static final String DATABASE_CREATE_CATEGORIE_PLAT = "CREATE TABLE "+CATEGORIE_TABLE+"( " +
            CATEGORIE_ID+" integer primary key autoincrement , " +
            CATEGORIE_ID_CATEGORIE+ " integer,"+
            CATEGORIE_LIBELLE+" text " +
            "); ";

    private static final String DATABASE_CREATE_NOTE_PLAT = "CREATE TABLE "+NOTE_PLAT_TABLE+"( " +
            NOTE_PLAT_ID+" integer primary key autoincrement , " +
            NOTE_PLAT_ID_NOTE_PLAT+ " integer,"+
            NOTE_PLAT_ID_PLAT+" integer,"+
            NOTE_PLAT_NOTE+" integer not null, " +
            NOTE_PLAT_COMMENTAIRE+" text , " +
            NOTE_PLAT_DATE+" text " +
            "); ";

    private static final String DATABASE_CREATE_NOTE_RESTAURANT = "CREATE TABLE "+NOTE_RESTAURANT_TABLE+"( " +
            NOTE_RESTAURANT_ID+" integer primary key autoincrement , " +
            NOTE_RESTAURANT_ID_NOTE_RESTAURANT+ " integer,"+
            NOTE_RESTAURANT_ID_RESTAURANT+" integer,"+
            NOTE_RESTAURANT_NOTE+" integer not null, " +
            NOTE_RESTAURANT_COMMENTAIRE+" text , " +
            NOTE_RESTAURANT_DATE+" text " +
            "); ";


    public GestionDBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                         int version) {
        super(context, name, factory, version);
        Log.i("ShotsDBhelper", "Helper construit");
       // onUpgrade()
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    // creation de la table
        db.execSQL(DATABASE_CREATE_RESTAURANT);
        db.execSQL(DATABASE_CREATE_PLAT);
        db.execSQL(DATABASE_CREATE_CATEGORIE_PLAT);
        db.execSQL(DATABASE_CREATE_NOTE_RESTAURANT);
        db.execSQL(DATABASE_CREATE_NOTE_PLAT);
        Log.i("ShotsDBhelper", "Database created with instruction : " +
                DATABASE_CREATE_RESTAURANT + DATABASE_CREATE_PLAT + DATABASE_CREATE_CATEGORIE_PLAT+DATABASE_CREATE_NOTE_RESTAURANT+DATABASE_CREATE_NOTE_PLAT);
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

    public void onReset(SQLiteDatabase db) {
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
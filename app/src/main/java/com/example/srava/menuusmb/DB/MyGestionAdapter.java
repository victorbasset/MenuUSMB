package com.example.srava.menuusmb.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by perezgoa on 02/03/2016.
 */
public class MyGestionAdapter {

        // variables de d
        // �finition de la base g�r�e
        private static final String DATABASE_NAME = "maBase31.db";
        private static final int DATABASE_VERSION = 2;
        private SQLiteDatabase gestionDB; // reference vers une base de donn�es
        public GestionDBhelper dbHelper; // r�f�rence vers le Helper de gestion de la base


        public MyGestionAdapter(Context context) { // constructeur
            Log.wtf("","Constructeur");
            dbHelper = new GestionDBhelper(context, DATABASE_NAME, null, DATABASE_VERSION); }

        public void open() throws SQLiteException { // ouverture de la base
            try{
                gestionDB=dbHelper.getWritableDatabase();
                Log.i("MyShotsAdapter", "Base ouverte en ecriture " + gestionDB);
            }catch (SQLiteException e){
                gestionDB=dbHelper.getReadableDatabase();
                Log.i("MyShotsAdapter", "Base ouverte en lecture " + gestionDB);
            }
        }

        public void close(){
            Log.i("MyShotsAdapter", "close: demande de fermeture de la base");
            dbHelper.close();
        } // fermeture de la base



        public long insertPlat(int id_plat, String libelle_plat, float prix,int id_categorie,int id_restaurant,String jour){
            ContentValues newValue;
            newValue= new ContentValues();
            newValue.put(GestionDBhelper.PLAT_ID_PLAT,id_plat);
            newValue.put(GestionDBhelper.PLAT_LIBELLE,libelle_plat);
            newValue.put(GestionDBhelper.PLAT_PRIX,prix);
            newValue.put(GestionDBhelper.PLAT_ID_CAT,id_categorie);
            newValue.put(GestionDBhelper.PLAT_ID_REST, id_restaurant);
            newValue.put(GestionDBhelper.PLAT_JOUR, jour);
            return gestionDB.insert(GestionDBhelper.PLAT_TABLE, null, newValue);
        }
        public long insertRestaurant(int id_restaurant, String libelle_restaurant){
            ContentValues newValue;
            newValue= new ContentValues();
            newValue.put(GestionDBhelper.RESTAURANT_ID_RESTAURANT,id_restaurant);
            newValue.put(GestionDBhelper.RESTAURANT_LIBELLE,libelle_restaurant);
            return gestionDB.insert(GestionDBhelper.RESTAURANT_TABLE, null, newValue);
        }
        public long insertCategoriePlat(int id_categorie, String libelle_categorie){
            ContentValues newValue;
            newValue= new ContentValues();
            newValue.put(GestionDBhelper.CATEGORIE_ID_CATEGORIE,id_categorie);
            newValue.put(GestionDBhelper.CATEGORIE_LIBELLE,libelle_categorie);
            return gestionDB.insert(GestionDBhelper.CATEGORIE_TABLE, null, newValue);
        }
        public long insertNotePlat(int id_note_plat, int note,String commentaire,String date,int id_plat){
            ContentValues newValue;
            newValue= new ContentValues();
            newValue.put(GestionDBhelper.NOTE_PLAT_ID_NOTE_PLAT,id_note_plat);
            newValue.put(GestionDBhelper.NOTE_PLAT_NOTE,note);
            newValue.put(GestionDBhelper.NOTE_PLAT_COMMENTAIRE,commentaire);
            newValue.put(GestionDBhelper.NOTE_PLAT_DATE,date);
            newValue.put(GestionDBhelper.NOTE_PLAT_ID_PLAT,id_plat);
            return gestionDB.insert(GestionDBhelper.NOTE_PLAT_TABLE, null, newValue);
        }
        public long insertNoteRestaurant(int id_note_restaurant, int note,String commentaire,String date,int id_restaurant){
            ContentValues newValue;
            newValue= new ContentValues();
            newValue.put(GestionDBhelper.NOTE_RESTAURANT_ID_NOTE_RESTAURANT,id_note_restaurant);
            newValue.put(GestionDBhelper.NOTE_RESTAURANT_NOTE,note);
            newValue.put(GestionDBhelper.NOTE_RESTAURANT_COMMENTAIRE,commentaire);
            newValue.put(GestionDBhelper.NOTE_RESTAURANT_DATE,date);
            newValue.put(GestionDBhelper.NOTE_RESTAURANT_ID_RESTAURANT,id_restaurant);
            return gestionDB.insert(GestionDBhelper.NOTE_RESTAURANT_TABLE, null, newValue);
        }

        /*public boolean updateShot(int ligneID, String chemin, String typeShot, String commentaire){
            ContentValues newValue;
            newValue= new ContentValues();
            newValue.put(GestionDBhelper.KEY_PATH, chemin);
            newValue.put(GestionDBhelper.KEY_TYPE, typeShot);
            newValue.put(GestionDBhelper.KEY_COMMENT, commentaire);
            return shotsDB.update(GestionDBhelper.NOM_TABLE, newValue,
                    GestionDBhelper.KEY_ID + " = " + ligneID, null) > 0;
        }
        public boolean removeShot(long ligneID){
            return shotsDB.delete(GestionDBhelper.NOM_TABLE, GestionDBhelper.KEY_ID + " = " + ligneID,
                    null)>0;
        }*/

        public Cursor getAllData(){
            Cursor c= gestionDB.query(GestionDBhelper.CATEGORIE_TABLE, new String[]{
                            GestionDBhelper.CATEGORIE_ID,GestionDBhelper.CATEGORIE_LIBELLE},
                    null, null, null, null, null);
            Log.wtf("azerty",  Integer.toString(c.getCount()));
            return  c;
        }
       /* public Cursor getSingleShot(long ligneID){
            return shotsDB.query(dbHelper.NOM_TABLE, new String[]{
                            GestionDBhelper.KEY_ID+" = " +ligneID,GestionDBhelper.KEY_COMMENT, GestionDBhelper.KEY_PATH, GestionDBhelper.KEY_TYPE},
                    null, null, null, null, null);
        }
        public Cursor getAllShotsOfAtype(String type_media){
            return shotsDB.query(dbHelper.NOM_TABLE, new String[]{
                            GestionDBhelper.KEY_ID,GestionDBhelper.KEY_COMMENT, GestionDBhelper.KEY_PATH, GestionDBhelper.KEY_TYPE+" = " +type_media},
                    null, null, null, null, null);
        }*/

    }

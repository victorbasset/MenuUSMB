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

        // variables de définition de la base gérée
        private static final String DATABASE_NAME = "maBase.db";
        private static final int DATABASE_VERSION = 1;
        private SQLiteDatabase shotsDB; // reference vers une base de données
        private GestionDBhelper dbHelper; // référence vers le Helper de gestion de la base


        public MyGestionAdapter(Context context) { // constructeur
            dbHelper = new GestionDBhelper(context, DATABASE_NAME, null, DATABASE_VERSION); }

        public void open() throws SQLiteException { // ouverture de la base
            try{
                shotsDB=dbHelper.getWritableDatabase();
                Log.i("MyShotsAdapter", "Base ouverte en ecriture " + shotsDB);
            }catch (SQLiteException e){
                shotsDB=dbHelper.getReadableDatabase();
                Log.i("MyShotsAdapter", "Base ouverte en lecture " + shotsDB);
            }
        }

        public void close(){
            Log.i("MyShotsAdapter", "close: demande de fermeture de la base");
            dbHelper.close();
        } // fermeture de la base

    /*    public long insertShot(String chemin, String typeShot, String commentaire){
            ContentValues newValue;
            newValue= new ContentValues();
            newValue.put(GestionDBhelper.KEY_PATH, chemin);
            newValue.put(GestionDBhelper.KEY_TYPE, typeShot);
            return shotsDB.insert(GestionDBhelper.NOM_TABLE, null, newValue);
        }
        public boolean updateShot(int ligneID, String chemin, String typeShot, String commentaire){
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
        }

        public Cursor getAllData(){
            return shotsDB.query(dbHelper.NOM_TABLE, new String[]{
                            GestionDBhelper.KEY_ID,GestionDBhelper.KEY_COMMENT, GestionDBhelper.KEY_PATH, GestionDBhelper.KEY_TYPE},
                    null, null, null, null, null);
        }
        public Cursor getSingleShot(long ligneID){
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

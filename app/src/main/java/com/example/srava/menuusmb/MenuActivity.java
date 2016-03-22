package com.example.srava.menuusmb;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.srava.menuusmb.Class.Categorie;
import com.example.srava.menuusmb.Class.Categories;
import com.example.srava.menuusmb.Class.NoteRestaurant;
import com.example.srava.menuusmb.Class.NoteRestaurants;
import com.example.srava.menuusmb.Class.NotesPlat;
import com.example.srava.menuusmb.Class.NotesPlats;
import com.example.srava.menuusmb.Class.Plat;
import com.example.srava.menuusmb.Class.Plats;
import com.example.srava.menuusmb.Class.Restaurant;
import com.example.srava.menuusmb.Class.Restaurants;
import com.example.srava.menuusmb.DB.GestionDBhelper;
import com.example.srava.menuusmb.DB.MyGestionAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import com.emoiluj.doubleviewpager.DoubleViewPager;
import com.emoiluj.doubleviewpager.DoubleViewPagerAdapter;


public class MenuActivity extends Activity implements View.OnClickListener {

    ProgressBar progressBar;
    public static MyGestionAdapter sauvegardeShotsDB;
    public static Cursor shotsDBcursor;
    private DoubleViewPager viewpager;
    private int horizontalChilds;
    private int verticalChilds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // Récuperation de la progressBar par l'id
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Récupération du boutton par l'id
        final ImageButton connect = (ImageButton) findViewById(R.id.refresh);

        //Set le listener sur le boutton
        connect.setOnClickListener(this);
        Log.wtf("", "Creation de base");
        sauvegardeShotsDB= new MyGestionAdapter(getBaseContext());
        Plats plats= new Plats();
        Restaurants r = new Restaurants();
        Categories c=new Categories();
        NoteRestaurants nr = new NoteRestaurants();
        NotesPlats np=new NotesPlats();

        populate("categorie");
        populate("plat");
        populate("restaurant");
        populate("notePlat");
        populate("noteRestaurant");


        if (Plats.listePlats.isEmpty())
            Initialisation(true);
        else
            Initialisation(false);


    }

    private void initialisationUI() {
        horizontalChilds = 4;
        verticalChilds = 5;
        loadUI();
        TextView tvTitre = (TextView) findViewById(R.id.tvTitre);
        Typeface type = Typeface.createFromAsset(getAssets(), "DJB.ttf");
        tvTitre.setTypeface(type);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.refresh) {
            Initialisation(true);
        }
    }

    private void Initialisation(Boolean etat) {

               //     sauvegardeShotsDB.dbHelper.onReset(sauvegardeShotsDB.dbHelper.getWritableDatabase());
        if(etat) {
            sauvegardeShotsDB.dbHelper.onReset(sauvegardeShotsDB.dbHelper.getWritableDatabase());

            recupData("categorie");
            recupData("plat");
            recupData("restaurant");
            recupData("notePlat");
            recupData("noteRestaurant");
        }
        threadPopulate();


    }

    private void recupData(String type) {
        Log.wtf("RecupData","Je recupe les data de "+type);
        final TextView connectionStatus = (TextView) findViewById(R.id.status );

        // Instanciation de la tâche asynchrone
        HttpRequestTaskManager result = new HttpRequestTaskManager();

        result.setConnectionStatus(connectionStatus);
        // On lui passe la progressBar et le texte de connectionStatus
        result.setProgressBar(progressBar);
        result.execute(new Post(type));
    }
    private void threadPopulate(){
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.wtf("Populate","All");
                                populate("categorie");
                                populate("plat");
                                populate("restaurant");
                                populate("notePlat");
                                populate("noteRestaurant");
                                if(progressBar.getProgress() == 100) {
                                    initialisationUI();
                                    interrupt();
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }
    private void populate(String data){

    sauvegardeShotsDB.open();
    Log.wtf("Populate","Populate");
        Cursor c;
        String[] table;
        int[] tableInt;
        switch(data){
            case "plat":
                c =sauvegardeShotsDB.getAllDataPlats();
                c.moveToFirst();
                for(int i = 1; i <= c.getCount(); i++)
                {

                    Plat p = new Plat(c.getString(c.getColumnIndex(GestionDBhelper.PLAT_ID_PLAT)),c.getString(c.getColumnIndex(GestionDBhelper.PLAT_LIBELLE)),c.getString(c.getColumnIndex(GestionDBhelper.PLAT_PRIX)),c.getString(c.getColumnIndex(GestionDBhelper.PLAT_ID_CAT)),c.getString(c.getColumnIndex(GestionDBhelper.PLAT_ID_REST)),c.getString(c.getColumnIndex(GestionDBhelper.PLAT_JOUR)));
                    Plats.listePlats.add(p);
                    c.moveToLast();

                }
                break;
            case "restaurant":
                c =sauvegardeShotsDB.getAllDataRestaurants();
                for(int i = 1; i <= c.getCount(); i++)
                {
                    c.moveToFirst();
                    Restaurants.listeRestaurants.add(new Restaurant(c.getString(c.getColumnIndex(GestionDBhelper.RESTAURANT_ID_RESTAURANT)), c.getString(c.getColumnIndex(GestionDBhelper.RESTAURANT_LIBELLE))));
                }
                break;
            case "categorie":
                c =sauvegardeShotsDB.getAllDataCategories();
                for(int i = 1; i <= c.getCount(); i++)
                {
                    c.moveToFirst();
                    Categories.listeCategories.add(new Categorie(c.getString(c.getColumnIndex(GestionDBhelper.CATEGORIE_ID_CATEGORIE)),c.getString(c.getColumnIndex(GestionDBhelper.CATEGORIE_LIBELLE))));
                }
                break;
            case "notePlat":
                c =sauvegardeShotsDB.getAllDataNotesPlats();
                for(int i = 1; i <= c.getCount(); i++)
                {
                    c.moveToFirst();
                    NotesPlats.listeNotesPlats.add(new NotesPlat(c.getString(c.getColumnIndex(GestionDBhelper.NOTE_PLAT_ID_NOTE_PLAT)),c.getInt(c.getColumnIndex(GestionDBhelper.NOTE_PLAT_NOTE)),c.getString(c.getColumnIndex(GestionDBhelper.NOTE_PLAT_ID_PLAT)),c.getString(c.getColumnIndex(GestionDBhelper.NOTE_PLAT_COMMENTAIRE)),c.getString(c.getColumnIndex(GestionDBhelper.NOTE_PLAT_DATE))));
                }
                break;
            case "noteRestaurant":
                c = sauvegardeShotsDB.getAllDataNotesRestaurants();
                for (int i = 1; i <= c.getCount(); i++) {
                    c.moveToFirst();
                    NoteRestaurants.listeNoteRestaurants.add(new NoteRestaurant(c.getString(c.getColumnIndex(GestionDBhelper.NOTE_RESTAURANT_ID_RESTAURANT)), c.getInt(c.getColumnIndex(GestionDBhelper.NOTE_RESTAURANT_NOTE)),c.getString(c.getColumnIndex(GestionDBhelper.NOTE_RESTAURANT_ID_RESTAURANT)),c.getString(c.getColumnIndex(GestionDBhelper.NOTE_RESTAURANT_COMMENTAIRE)),c.getString(c.getColumnIndex(GestionDBhelper.NOTE_RESTAURANT_DATE))));
                    progressBar.setProgress(100);
                }
                break;
        }


        sauvegardeShotsDB.close();
    }
    private void loadUI() {

        ArrayList<PagerAdapter> verticalAdapters = new ArrayList<PagerAdapter>();
        generateVerticalAdapters(verticalAdapters);

        viewpager = (DoubleViewPager) findViewById(R.id.pager);
        viewpager.setAdapter(new DoubleViewPagerAdapter(getApplicationContext(), verticalAdapters));
    }

    private void generateVerticalAdapters(ArrayList<PagerAdapter> verticalAdapters) {
        for (int i=0; i<horizontalChilds; i++){
            verticalAdapters.add(new VerticalPagerAdapter(this, i, verticalChilds));
        }
    }


}

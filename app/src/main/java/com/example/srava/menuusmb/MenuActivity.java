package com.example.srava.menuusmb;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.srava.menuusmb.Class.Plat;
import com.example.srava.menuusmb.Class.Plats;
import com.example.srava.menuusmb.DB.GestionDBhelper;
import com.example.srava.menuusmb.DB.MyGestionAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MenuActivity extends Activity implements View.OnClickListener {

    ProgressBar progressBar;
    public static MyGestionAdapter sauvegardeShotsDB;
    public static Cursor shotsDBcursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        // Récuperation de la progressBar par l'id
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Récupération du boutton par l'id
        final Button connect = (Button) findViewById(R.id.button);

        //Set le listener sur le boutton
        connect.setOnClickListener(this);
        Log.wtf("", "Creation de base");
        sauvegardeShotsDB= new MyGestionAdapter(getBaseContext());

        populate("categorie");
        populate("plat");
        populate("restaurant");
        populate("notePlat");
        populate("noteRestaurant");
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
        if (v.getId() == R.id.button) {
        sauvegardeShotsDB.dbHelper.onReset(sauvegardeShotsDB.dbHelper.getWritableDatabase());
       //     sauvegardeShotsDB.dbHelper.onReset(sauvegardeShotsDB.dbHelper.getWritableDatabase());
            recupData("categorie");
            recupData("plat");
            recupData("restaurant");
            recupData("notePlat");
            recupData("noteRestaurant");
            threadPopulate();

        }

    }

    private void recupData(String type) {
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
                                populate("categorie");
                                populate("plat");
                                populate("restaurant");
                                populate("notePlat");
                                populate("noteRestaurant");
                                if(progressBar.getProgress() == 100) {

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
                table = new String[] {GestionDBhelper.PLAT_ID, GestionDBhelper.PLAT_LIBELLE};
                tableInt = new int[] {R.id.commentaire, R.id.nom_fichier};

                try {
                    ListAdapter adapter = new SimpleCursorAdapter(this,
                            R.layout.affichage_ligne_base, c,
                            table, tableInt, 1
                    );
                    ((ListView)findViewById(R.id.ListViewDB)).setAdapter(adapter);
                }catch (Exception exception){
                    Log.wtf("ERREUR DE MERDE", exception);
                }

                break;
            case "restaurant":
                c =sauvegardeShotsDB.getAllDataRestaurants();
                table = new String[] {GestionDBhelper.RESTAURANT_ID_RESTAURANT, GestionDBhelper.RESTAURANT_LIBELLE};
                tableInt = new int[] {R.id.commentaire, R.id.nom_fichier};

                try {
                    ListAdapter adapter = new SimpleCursorAdapter(this,
                            R.layout.affichage_ligne_base, c,
                            table, tableInt, 1
                    );
                    ((ListView)findViewById(R.id.ListViewDB)).setAdapter(adapter);
                }catch (Exception exception){
                    Log.wtf("ERREUR DE MERDE", exception);
                }

                break;
            case "categorie":
                c =sauvegardeShotsDB.getAllDataCategories();
                table = new String[] {GestionDBhelper.CATEGORIE_ID_CATEGORIE, GestionDBhelper.CATEGORIE_LIBELLE};
                tableInt = new int[] {R.id.commentaire, R.id.nom_fichier};

                try {
                    ListAdapter adapter = new SimpleCursorAdapter(this,
                            R.layout.affichage_ligne_base, c,
                            table, tableInt, 1
                    );
                    ((ListView)findViewById(R.id.ListViewDB)).setAdapter(adapter);
                }catch (Exception exception){
                    Log.wtf("ERREUR DE MERDE", exception);
                }

                break;
            case "notePlat":
                c =sauvegardeShotsDB.getAllDataPlats();
                table = new String[] {GestionDBhelper.NOTE_PLAT_ID_NOTE_PLAT, GestionDBhelper.NOTE_PLAT_ID_PLAT, GestionDBhelper.NOTE_PLAT_NOTE, GestionDBhelper.NOTE_PLAT_COMMENTAIRE, GestionDBhelper.NOTE_PLAT_DATE};
                tableInt = new int[] {R.id.commentaire, R.id.nom_fichier};

                try {
                    ListAdapter adapter = new SimpleCursorAdapter(this,
                            R.layout.affichage_ligne_base, c,
                            table, tableInt, 1
                    );
                    ((ListView)findViewById(R.id.ListViewDB)).setAdapter(adapter);
                }catch (Exception exception){
                    Log.wtf("ERREUR DE MERDE", exception);
                }

                break;
            case "noteRestaurant":
                c =sauvegardeShotsDB.getAllDataPlats();
                table = new String[] {GestionDBhelper.NOTE_RESTAURANT_ID_NOTE_RESTAURANT, GestionDBhelper.NOTE_RESTAURANT_ID_RESTAURANT, GestionDBhelper.NOTE_RESTAURANT_NOTE, GestionDBhelper.NOTE_RESTAURANT_COMMENTAIRE, GestionDBhelper.NOTE_RESTAURANT_DATE};
                tableInt = new int[] {R.id.commentaire, R.id.nom_fichier};

                try {
                    ListAdapter adapter = new SimpleCursorAdapter(this,
                            R.layout.affichage_ligne_base, c,
                            table, tableInt, 1
                    );
                    ((ListView)findViewById(R.id.ListViewDB)).setAdapter(adapter);
                }catch (Exception exception){
                    Log.wtf("ERREUR DE MERDE", exception);
                }

                break;
        }


        sauvegardeShotsDB.close();
    }
}

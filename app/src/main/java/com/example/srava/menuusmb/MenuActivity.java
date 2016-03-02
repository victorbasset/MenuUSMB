package com.example.srava.menuusmb;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.srava.menuusmb.DB.MyGestionAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MenuActivity extends Activity implements View.OnClickListener {

    ProgressBar progressBar;
    private MyGestionAdapter sauvegardeShotsDB;
    private Cursor shotsDBcursor;

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
        Log.wtf("","Creation de base");
        sauvegardeShotsDB= new MyGestionAdapter(getBaseContext());

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


            final TextView connectionStatus = (TextView) findViewById(R.id.status );

            // Instanciation de la tâche asynchrone
            HttpRequestTaskManager result = new HttpRequestTaskManager();

            result.setConnectionStatus(connectionStatus);
            // On lui passe la progressBar et le texte de connectionStatus
            result.setProgressBar(progressBar);
            result.execute(new Post("plat"));

            sauvegardeShotsDB.open();
            sauvegardeShotsDB.insertShot(1, "salade");
            sauvegardeShotsDB.close();
            populate();
        }

    }
    private void populate(){
        sauvegardeShotsDB.open();

       // Log.wtf("", );
        ListAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.affichage_ligne_base, sauvegardeShotsDB.getAllData(),
                new String[] {"id_categorie", "libelle_categorie"},
                new int[] {R.id.nom_fichier, R.id.commentaire});
// Bind to our new adapter.
        ((ListView)findViewById(R.id.ListViewDB)).setAdapter(adapter);
        sauvegardeShotsDB.close();
    }
}

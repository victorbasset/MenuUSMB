package com.example.srava.menuusmb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MenuActivity extends Activity implements View.OnClickListener {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //new RequestTask().execute("http://www.quentinmodena.fr/");
        new RequestTask().execute("http://www.crous-grenoble.fr/restaurant/restaurant-universitaire-dannecy/");

        // Récuperation de la progressBar par l'id
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Récupération du boutton par l'id
        final Button connect = (Button) findViewById(R.id.button);

        //Set le listener sur le boutton
        connect.setOnClickListener(this);

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
        }
    }
}

package com.example.srava.menuusmb;

import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by bassetv on 27/01/2016.
 */
public class HttpRequestTaskManager extends AsyncTask<String, Integer, JSONObject> {


    //D�claration de toutes les objets n�cessaire : Progressbar, TextView..
    ProgressBar progressBar;
    TextView connectionStatus;
    private static final String FLAG_SUCCESS = "success";
    private static final String FLAG_MESSAGE = "message";
    private static final String RESTAU = "http://victorbasset.fr/MenuUSMB/restau.php";


    // Setter de la progressBar
    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    //Setteer du setConnectionStatus
    public void setConnectionStatus(TextView textView) {
        this.connectionStatus = textView;
    }


    //M�thode onProgressUpdate qui met � jour la progressBar � chaque appel avec un int en param�tre
    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        // Mise � jour de la ProgressBar
        progressBar.setProgress(values[0]);
    }


    @Override
    protected JSONObject doInBackground(String... string){

        Log.d("DoInBack", "DoInBack");


        //Instantiation de l'objet JSON
        JSONObject jsonResponse= new JSONObject();
        try{

            //Publication de la progressBar � 0%
            publishProgress(0);

            //url de mon site h�berger chez OVH
            URL url = new URL(RESTAU);
            HttpURLConnection connection = (HttpURLConnection )url.openConnection();

            //D�finition de la m�thode utilis� ici POST
            connection.setRequestMethod("POST");

            //Connection
            connection.connect();
            //Publication de la progressBar � 50%
            publishProgress(50);

            // D�codage de la r�ponse
            InputStream in = new BufferedInputStream(connection.getInputStream());
            jsonResponse = new JSONObject(convertStreamToString(in));

            //Gestion de toutes les erreurs
        }  catch (IOException e) {
            Log.e("IOException", "Error");
        }  catch(JSONException e){
            Log.e("JSONException", "Error");
        }  catch (NetworkOnMainThreadException e){
            connectionStatus.setText("Marche pas si android > 3.0!!");
        }
        //return la r�ponse JSON
        return jsonResponse;
    }



    @Override
    protected void onPostExecute( JSONObject result){

        //Oblig� de mettre un TryAndCatch pour une conversion de jSON
        try {
            Log.d("result",result.getString(FLAG_SUCCESS));
            int loginOK = result.getInt(FLAG_SUCCESS);
            connectionStatus.setText(result.getString(FLAG_MESSAGE));

            // On v�rifie si les logs sont OK !
            if(loginOK!=0)
            {
                connectionStatus.setText("Restau d�tect�");
                publishProgress(100);
            }
            else
            {
                connectionStatus.setText("Erreur de connexion");
                publishProgress(0);
            }
            //Gestion des erreurs et expections
        }  catch(JSONException e){
            Log.e("JSONException", "Error");
        }  catch (NetworkOnMainThreadException e) {
            Log.e("ThreadException", "android > 3.0!!");
        }
    }


    // Convertie StreamToString
    public String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}



package com.example.srava.menuusmb;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Created by modenaq on 12/02/2016.
 */
class RequestTask extends AsyncTask<String, String, Menu> {

    @Override
// username, password, message, mobile
    protected Menu doInBackground(String... url) {
        Log.d("test", "test");
        // constants
        Boolean recup=false;
        int timeoutSocket = 50000;
        int timeoutConnection = 50000;

        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        HttpClient client = new DefaultHttpClient(httpParameters);

        HttpGet httpget = new HttpGet(url[0]);

        try {
            HttpResponse getResponse = client.execute(httpget);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if(statusCode != HttpStatus.SC_OK) {
                Log.w("MyApp", "Download Error: " + statusCode + "| for URL: " + url);
                return null;
            }

            String line = "";
            StringBuilder total = new StringBuilder();

            HttpEntity getResponseEntity = getResponse.getEntity();

            BufferedReader reader = new BufferedReader(new InputStreamReader(getResponseEntity.getContent()));

            Menu menu = new Menu();
            String enCours=null;

            JourMenu jourMenu=new JourMenu();
            while((line = reader.readLine()) != null) {
                    if(line.indexOf("menu-repas")!=-1)
                        recup=true;
                    else if(line.indexOf("http://connect.facebook.net/fr_FR/all.js")!=-1)
                        recup=false;

                    if(recup==true){
                        line = line.replaceAll("<[^>]*>", "");
                        if(line.indexOf("Menu")!=-1)
                        {
                            if(jourMenu.getDate()!=null)
                                menu.addJourMenu(jourMenu);
                            jourMenu.clear();

                            jourMenu.setDate(line);
                            enCours="date";
                        }
                        else if(line.indexOf("Petit déjeuner")!=-1)
                        {

                            enCours="petitDejeuner";
                        }
                        else if(line.indexOf("MIDI")!=-1)
                        {

                            enCours="midi";
                        }
                        else if(line.indexOf("SOIR")!=-1)
                        {
                            enCours="soir";
                        }
                        else if(enCours=="petitDejeuner"){
                            jourMenu.addPetitDejeuner(line);
                        }
                        else if(enCours=="midi"){
                            jourMenu.addMidi(line);
                        }
                        else if(enCours=="soir"){
                            jourMenu.addSoir(line);
                        }
                        else{
                            //Autre
                        }

                        /*if(line!=""){
                            Log.d("line",line);
                            total.append(line);
                        }*/
                    }
            }

            /*line = total.toString();
            return line;*/
            Log.d("HTML","RETURN");
            return menu;
        } catch (Exception e) {
            Log.w("MyApp", "Download Exception : " + e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Menu result) {
        if(result==null)
            Log.d("HTMLerreur","Erreur connection");
        else{
            Log.d("HTML result", result.toString());
            //Pattern p = Pattern.compile("<td id='live_6_tableau_col1' valign=top>(.*)</td>");
        }
    }
}
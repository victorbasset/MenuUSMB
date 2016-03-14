package com.example.srava.menuusmb;

import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bassetv on 27/01/2016.
 */
public class HttpRequestTaskManager extends AsyncTask<Post, Integer, JSONObject> {


    //Déclaration de toutes les objets nécessaire : Progressbar, TextView..
    ProgressBar progressBar;
    TextView connectionStatus;
    private static final String FLAG_SUCCESS = "success";
    private static final String FLAG_MESSAGE = "message";
    private static final String RESTAU = "http://menuusmb.lightning-sphere.com";
    private Post postTmp;


    // Setter de la progressBar
    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    //Setteer du setConnectionStatus
    public void setConnectionStatus(TextView textView) {
        this.connectionStatus = textView;
    }


    //Méthode onProgressUpdate qui met à jour la progressBar à chaque appel avec un int en paramètre
    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        // Mise à jour de la ProgressBar
        progressBar.setProgress(values[0]);
    }


    @Override
    protected JSONObject doInBackground(Post... post){

        Log.d("DoInBack", "DoInBack");


        //Instantiation de l'objet JSON
        JSONObject jsonResponse= new JSONObject();
        try{

            //Publication de la progressBar à 0%
            publishProgress(0);

            //url de mon site héberger chez OVH
            URL url = new URL(RESTAU);
            HttpURLConnection connection = (HttpURLConnection )url.openConnection();

            //Définition de la méthode utilisé ici POST
            connection.setRequestMethod("POST");
            String urlParameters = "etat=" + post[0].getParametreUrl();
            postTmp=post[0];
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            connection.setRequestProperty("Content-Length", "" + postData.length);
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(postData);
            }

            //Connection
            connection.connect();
            //Publication de la progressBar à 50%
            publishProgress(50);

            // Décodage de la réponse
            InputStream in = new BufferedInputStream(connection.getInputStream());
            jsonResponse = new JSONObject(convertStreamToString(in));

            //Gestion de toutes les erreurs
        }  catch (IOException e) {
            Log.e("IOException", "Error"+e);
        }  catch(JSONException e){
            Log.e("JSONException recup", "Error"+e);
        }  catch (NetworkOnMainThreadException e){
            connectionStatus.setText("Marche pas si android > 3.0!!");
        }
        //return la réponse JSON
        return jsonResponse;
    }



    @Override
    protected void onPostExecute( JSONObject result){

        //Obligé de mettre un TryAndCatch pour une conversion de jSON
        try {

            Log.d("result",result.getString(FLAG_SUCCESS));
            int resSuccess = result.getInt(FLAG_SUCCESS);
            connectionStatus.setText(result.getString(FLAG_MESSAGE));

            // On vérifie si les logs sont OK !
            if(resSuccess!=0){

                //connectionStatus.setText(result.getString(FLAG_MESSAGE).toString());
                switch(postTmp.parametreUrl){
                    case "plat":
                        Plats p = deserializePlats(result.toString());
                        connectionStatus.setText(p.listePlats.toString());
                        publishProgress(75);
                        MenuActivity.sauvegardeShotsDB.open();
                        for(Plat plat : p.listePlats){
                            int id_plat= Integer.parseInt(plat.id_plat);
                            float prix=Float.parseFloat(plat.prix_plat);
                            int id_categorie= Integer.parseInt(plat.id_categorie);
                            int id_restaurant= Integer.parseInt(plat.id_restaurant);

                            MenuActivity.sauvegardeShotsDB.insertPlat(id_plat,plat.libelle_plat,prix,id_categorie,id_restaurant,plat.jour);
                        }
                        MenuActivity.sauvegardeShotsDB.close();
                        publishProgress(100);
                        break;
                    case "restaurant":
                        Restaurants r = deserializeRestaurants(result.toString());
                        connectionStatus.setText(r.listeRestaurants.toString());
                        publishProgress(75);
                        MenuActivity.sauvegardeShotsDB.open();
                        for(Restaurant rest : r.listeRestaurants){
                            int id_rest= Integer.parseInt(rest.id_restaurant);

                            MenuActivity.sauvegardeShotsDB.insertRestaurant(id_rest, rest.libelle_restaurant);
                        }
                        MenuActivity.sauvegardeShotsDB.close();
                        publishProgress(100);
                        break;
                    case "categorie":
                        Categories c = deserializeCategories(result.toString());
                        connectionStatus.setText(c.listeCategories.toString());
                        publishProgress(75);
                        MenuActivity.sauvegardeShotsDB.open();
                        for(Categorie categorie : c.listeCategories){
                            int id_categorie= Integer.parseInt(categorie.id_categorie);

                            MenuActivity.sauvegardeShotsDB.insertCategoriePlat(id_categorie, categorie.libelle_categorie);
                        }
                        MenuActivity.sauvegardeShotsDB.close();
                        publishProgress(100);
                        break;
                    case "notePlat":
                        NotesPlats np = deserializeNotesPlats(result.toString());
                        connectionStatus.setText(np.listeNotesPlats.toString());
                        publishProgress(90);
                        MenuActivity.sauvegardeShotsDB.open();
                        for(NotesPlat noteplat : np.listeNotesPlats){
                            int id_noteplat= Integer.parseInt(noteplat.id_note_plat);
                            int note= noteplat.note;
                            int id_plat=Integer.parseInt(noteplat.id_plat);
                            MenuActivity.sauvegardeShotsDB.insertNotePlat(id_noteplat, note, noteplat.commentaire, noteplat.date, id_plat);
                        }
                        MenuActivity.sauvegardeShotsDB.close();
                        publishProgress(100);
                        break;
                    case "noteRestaurant":
                        NoteRestaurants nr = deserializeNotesRestaurants(result.toString());
                        connectionStatus.setText(nr.listeNoteRestaurants.toString());
                        publishProgress(90);
                        MenuActivity.sauvegardeShotsDB.open();
                        for(NoteRestaurant noteRestaurant : nr.listeNoteRestaurants){
                            int id_noterestaurant= Integer.parseInt(noteRestaurant.id_note_restaurant);
                            int note= noteRestaurant.note;
                            int id_restaurant=Integer.parseInt(noteRestaurant.id_restaurant);
                            MenuActivity.sauvegardeShotsDB.insertNoteRestaurant(id_noterestaurant, note, noteRestaurant.commentaire, noteRestaurant.date, id_restaurant);
                        }
                        MenuActivity.sauvegardeShotsDB.close();
                        publishProgress(100);
                        break;
                }


            }
            else {
                connectionStatus.setText(result.getString(FLAG_MESSAGE).toString());
                publishProgress(0);
            }
            //Gestion des erreurs et expections
        }  catch(JSONException e){
            Log.e("JSONException Insert", "Error" +e);
        }  catch (NetworkOnMainThreadException e) {
            Log.e("ThreadException", "android > 3.0!!");
        }
    }


    // Convertie StreamToString
    public String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public Restaurants deserializeRestaurants(String chaineJson) throws JSONException {
        Restaurants restaurants=new Restaurants();
        JSONObject obj = new JSONObject(chaineJson);
        JSONArray array = obj.getJSONArray("message");
        for(int i = 0 ; i < array.length() ; i++){
            restaurants.listeRestaurants.add(new Restaurant(array.getJSONObject(i).getString("id_restaurant"), array.getJSONObject(i).getString("libelle_restaurant")));
        }
        return restaurants;
    }

    public Categories deserializeCategories(String chaineJson) throws JSONException {
        Categories categories=new Categories();
        JSONObject obj = new JSONObject(chaineJson);
        JSONArray array = obj.getJSONArray("message");
        for(int i = 0 ; i < array.length() ; i++){
            categories.listeCategories.add(new Categorie(array.getJSONObject(i).getString("id_categorie"), array.getJSONObject(i).getString("libelle_categorie")));
        }
        return categories;
    }

    public Plats deserializePlats(String chaineJson) throws JSONException {
        Plats plats=new Plats();
        JSONObject obj = new JSONObject(chaineJson);
        JSONArray array = obj.getJSONArray("message");
        for(int i = 0 ; i < array.length() ; i++){
            plats.listePlats.add(new Plat(array.getJSONObject(i).getString("id_plat"),
                    array.getJSONObject(i).getString("libelle_plat"),
                    array.getJSONObject(i).getString("prix"),
                    array.getJSONObject(i).getString("id_categorie"),
                    array.getJSONObject(i).getString("id_restaurant"),
                    array.getJSONObject(i).getString("jour")));
        }
        return plats;
    }

    public NotesPlats deserializeNotesPlats(String chaineJson) throws JSONException {
        NotesPlats notesPlats=new NotesPlats();
        JSONObject obj = new JSONObject(chaineJson);
        JSONArray array = obj.getJSONArray("message");
        for(int i = 0 ; i < array.length() ; i++){
            notesPlats.listeNotesPlats.add(new NotesPlat(array.getJSONObject(i).getString("id_note_plat"),
                    array.getJSONObject(i).getInt("note"),
                    array.getJSONObject(i).getString("commentaire"),
                    array.getJSONObject(i).getString("date"),
                    array.getJSONObject(i).getString("id_plat")));
        }
        return notesPlats;
    }

    public NoteRestaurants deserializeNotesRestaurants(String chaineJson) throws JSONException {
        NoteRestaurants noteRestaurants=new NoteRestaurants();
        JSONObject obj = new JSONObject(chaineJson);
        JSONArray array = obj.getJSONArray("message");
        for(int i = 0 ; i < array.length() ; i++){
            noteRestaurants.listeNoteRestaurants.add(new NoteRestaurant(array.getJSONObject(i).getString("id_note_restaurant"),
                    array.getJSONObject(i).getInt("note"),
                    array.getJSONObject(i).getString("commentaire"),
                    array.getJSONObject(i).getString("date"),
                    array.getJSONObject(i).getString("id_restaurant")));
        }
        return noteRestaurants;
    }
}



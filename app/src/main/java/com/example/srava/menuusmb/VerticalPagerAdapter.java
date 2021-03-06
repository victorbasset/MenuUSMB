package com.example.srava.menuusmb;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.example.srava.menuusmb.Class.Plat;
import com.example.srava.menuusmb.Class.Plats;
import com.example.srava.menuusmb.Class.Restaurants;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VerticalPagerAdapter extends PagerAdapter{

    private Context mContext;
    private int mParent;
    private int mChilds;
    private JSONArray mColors;

    public VerticalPagerAdapter(Context c, int parent, int childs){
        mContext = c;
        mParent = parent;
        mChilds = childs;
        loadJSONFromAsset(c);
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mChilds;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LinearLayout linear = new LinearLayout(mContext);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setGravity(Gravity.TOP);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        linear.setLayoutParams(lp);

        TextView tvParent = new TextView(mContext);
        tvParent.setGravity(Gravity.CENTER_HORIZONTAL);
        tvParent.setText(Restaurants.listeRestaurants.get(mParent).getLibelleRestaurant());
        tvParent.setTextColor(Color.BLACK);
        tvParent.setTextSize(30);
        tvParent.setBackgroundResource(R.drawable.textview_border);
        linear.addView(tvParent);

        TextView tvChild = new TextView(mContext);
        tvChild.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView tvEntreDB = new TextView(mContext);
        tvEntreDB.setGravity(Gravity.CENTER_HORIZONTAL);
        tvEntreDB.setTextColor(Color.WHITE);
        tvEntreDB.setTextSize(15);
        tvEntreDB.setPadding(0,0,0,75);

        TextView tvPlatDB = new TextView(mContext);
        tvPlatDB.setGravity(Gravity.CENTER_HORIZONTAL);
        tvPlatDB.setTextColor(Color.WHITE);
        tvPlatDB.setTextSize(15);
        tvPlatDB.setPadding(0,0,0,75);

        TextView tvDessertDB = new TextView(mContext);
        tvDessertDB.setGravity(Gravity.CENTER_HORIZONTAL);
        tvDessertDB.setTextColor(Color.WHITE);
        tvDessertDB.setTextSize(15);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

// Date du jour
        Calendar today = Calendar.getInstance();

// Date du lundi
        Calendar monday = (Calendar) today.clone();
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
// Date du mardi
        Calendar tuesday = (Calendar) today.clone();
        tuesday.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
// Date du mercredi
        Calendar wednesday = (Calendar) today.clone();
        wednesday.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
// Date du jeudi
        Calendar thursday = (Calendar) today.clone();
        thursday.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
// Date du vendredi
        Calendar friday = (Calendar) today.clone();
        friday.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);

        List<Plat> platLundi=new ArrayList<Plat>();
        List<Plat> platMardi=new ArrayList<Plat>();
        List<Plat> platMercredi=new ArrayList<Plat>();
        List<Plat> platJeudi=new ArrayList<Plat>();
        List<Plat> platVendredi=new ArrayList<Plat>();

        String[] separatedL = sdf.format(monday.getTime()).split("/");
        String datemodifyL =separatedL[2]+"-"+separatedL[1]+"-"+separatedL[0];

        String[] separatedM = sdf.format(tuesday.getTime()).split("/");
        String datemodifyM =separatedM[2]+"-"+separatedM[1]+"-"+separatedM[0];

        String[] separatedMe = sdf.format(wednesday.getTime()).split("/");
        String datemodifyMe =separatedMe[2]+"-"+separatedMe[1]+"-"+separatedMe[0];

        String[] separatedJ = sdf.format(thursday.getTime()).split("/");
        String datemodifyJ =separatedJ[2]+"-"+separatedJ[1]+"-"+separatedJ[0];

        String[] separatedV = sdf.format(friday.getTime()).split("/");
        String datemodifyV =separatedV[2]+"-"+separatedV[1]+"-"+separatedV[0];

        String wololo;
        for(int i=0; i < Plats.listePlats.size() ; i++){
            wololo=Plats.listePlats.get(i).jour.toString();
            if( datemodifyL.equals(wololo)){
                platLundi.add(Plats.listePlats.get(i));
            }
            else if( datemodifyM.equals(wololo)){
                platMardi.add(Plats.listePlats.get(i));
            }
            else if(   datemodifyMe.equals(wololo)){
                platMercredi.add(Plats.listePlats.get(i));
            }
            else if(  datemodifyJ.equals(wololo)){
                platJeudi.add(Plats.listePlats.get(i));
            }
            else if(  datemodifyV.equals(wololo) ){
                platVendredi.add(Plats.listePlats.get(i));
            }
        }


        Init(position, tvChild, tvEntreDB, tvPlatDB, tvDessertDB, sdf, monday, tuesday, wednesday,
                thursday, friday, platLundi, platMardi, platMercredi, platJeudi, platVendredi);

        tvChild.setTextColor(Color.BLACK);
        tvChild.setTextSize(20);
        tvChild.setBackgroundResource(R.drawable.textview_border2);
        linear.addView(tvChild);

        TextView tvEntre = new TextView(mContext);
        tvEntre.setGravity(Gravity.CENTER_HORIZONTAL);
        tvEntre.setText("Entrée");
        tvEntre.setTextColor(Color.parseColor("#C9C9C9"));
        tvEntre.setTextSize(15);
        tvEntre.setPadding(0,100,0,0);
        tvEntre.setTypeface(null, Typeface.ITALIC);
        linear.addView(tvEntre);


        linear.addView(tvEntreDB);

        TextView tvPlat = new TextView(mContext);
        tvPlat.setGravity(Gravity.CENTER_HORIZONTAL);
        tvPlat.setText("Plat");
        tvPlat.setTextColor(Color.parseColor("#C9C9C9"));
        tvPlat.setTextSize(15);
        tvPlat.setTypeface(null, Typeface.ITALIC);
        linear.addView(tvPlat);


        linear.addView(tvPlatDB);

        TextView tvDessert = new TextView(mContext);
        tvDessert.setGravity(Gravity.CENTER_HORIZONTAL);
        tvDessert.setText("Dessert");
        tvDessert.setTextColor(Color.parseColor("#C9C9C9"));
        tvDessert.setTextSize(15);
        tvDessert.setTypeface(null, Typeface.ITALIC);
        linear.addView(tvDessert);


        linear.addView(tvDessertDB);

        setColors(position, linear);
        container.addView(linear);
        return linear;
    }

    private void Init(int position, TextView tvChild, TextView tvEntreDB, TextView tvPlatDB, TextView tvDessertDB, SimpleDateFormat sdf, Calendar monday, Calendar tuesday, Calendar wednesday, Calendar thursday, Calendar friday, List<Plat> platLundi, List<Plat> platMardi, List<Plat> platMercredi, List<Plat> platJeudi, List<Plat> platVendredi) {
        boolean ntrE=false;
        boolean ntrP=false;
        boolean ntrD=false;
        for(int i = 0; i <= 4; i++)
        {

            for(int j = 0; j <= 3; j++)
            {
                switch (position) {
                    case 0:
                        tvChild.setText("Lundi " +sdf.format(monday.getTime()));
                        if(platLundi.size() != 0 ) {
                            ntrE=false;
                            ntrP=false;
                            ntrD=false;
                            for (Plat p : platLundi) {
                                if(p.id_restaurant.equals(Integer.toString(mParent+1))) {

                                    if (p.id_categorie.equals("1")){
                                        tvEntreDB.setText(p.libelle_plat);
                                        ntrE = true;
                                    }
                                    else if (p.id_categorie.equals("2")) {
                                        tvPlatDB.setText(p.libelle_plat);
                                        ntrP = true;
                                    }
                                    else if ( p.id_categorie.equals("3")) {
                                        tvDessertDB.setText(p.libelle_plat);
                                        ntrD = true;
                                    }
                                    else{

                                    }
                                }
                            }
                            if(!ntrE)
                                tvEntreDB.setText("Pas de plat");
                            if(!ntrP)
                                tvPlatDB.setText("Pas de plat");
                            if(!ntrD)
                                tvDessertDB.setText("Pas de plat");

                        }
                        else{
                            tvEntreDB.setText("Pas de plat");
                            tvPlatDB.setText("Pas de plat");
                            tvDessertDB.setText("Pas de plat");

                        }
                        break;
                    case 1:
                        tvChild.setText("Mardi " +sdf.format(tuesday.getTime()));
                        if(platMardi.size() != 0 ) {
                            ntrE=false;
                            ntrP=false;
                            ntrD=false;
                            for (Plat p : platMardi) {
                                if(p.id_restaurant.equals(Integer.toString(mParent+1))) {
                                    if (p.id_categorie.equals("1")) {
                                        tvEntreDB.setText(p.libelle_plat);
                                        ntrE = true;
                                    } else if (p.id_categorie.equals("2")) {
                                        tvPlatDB.setText(p.libelle_plat);
                                        ntrP = true;
                                    } else if (p.id_categorie.equals("3")) {
                                        tvDessertDB.setText(p.libelle_plat);
                                        ntrD = true;
                                    }

                                }
                            }
                            if(!ntrE)
                                tvEntreDB.setText("Pas de plat");
                            if(!ntrP)
                                tvPlatDB.setText("Pas de plat");
                            if(!ntrD)
                                tvDessertDB.setText("Pas de plat");

                        }
                        else{
                            tvEntreDB.setText("Pas de plat");
                            tvPlatDB.setText("Pas de plat");
                            tvDessertDB.setText("Pas de plat");

                        }
                        break;
                    case 2:
                        tvChild.setText("Mercredi " +sdf.format(wednesday.getTime()));
                        if(platMercredi.size() != 0 ) {
                            ntrE=false;
                            ntrP=false;
                            ntrD=false;
                            for (Plat p : platMercredi) {
                                if(p.id_restaurant.equals(Integer.toString(mParent+1))) {
                                    if (p.id_categorie.equals("1")){
                                        tvEntreDB.setText(p.libelle_plat);
                                        ntrE = true;
                                    }
                                    else if (p.id_categorie.equals("2")) {
                                        tvPlatDB.setText(p.libelle_plat);
                                        ntrP = true;
                                    }
                                    else if ( p.id_categorie.equals("3")) {
                                        tvDessertDB.setText(p.libelle_plat);
                                        ntrD = true;
                                    }
                                    else{

                                    }
                                }
                            }
                            if(!ntrE)
                                tvEntreDB.setText("Pas de plat");
                            if(!ntrP)
                                tvPlatDB.setText("Pas de plat");
                            if(!ntrD)
                                tvDessertDB.setText("Pas de plat");

                        }
                        else{
                            tvEntreDB.setText("Pas de plat");
                            tvPlatDB.setText("Pas de plat");
                            tvDessertDB.setText("Pas de plat");

                        }
                        break;
                    case 3:
                        tvChild.setText("Jeudi " +sdf.format(thursday.getTime()));
                        if(platJeudi.size() != 0 ) {
                            ntrE=false;
                            ntrP=false;
                            ntrD=false;
                            for (Plat p : platJeudi) {
                                if(p.id_restaurant.equals(Integer.toString(mParent+1))) {
                                    if (p.id_categorie.equals("1")){
                                        tvEntreDB.setText(p.libelle_plat);
                                        ntrE = true;
                                    }
                                    else if (p.id_categorie.equals("2")) {
                                        tvPlatDB.setText(p.libelle_plat);
                                        ntrP = true;
                                    }
                                    else if ( p.id_categorie.equals("3")) {
                                        tvDessertDB.setText(p.libelle_plat);
                                        ntrD = true;
                                    }
                                    else{

                                    }
                                }
                            }
                            if(!ntrE)
                                tvEntreDB.setText("Pas de plat");
                            if(!ntrP)
                                tvPlatDB.setText("Pas de plat");
                            if(!ntrD)
                                tvDessertDB.setText("Pas de plat");

                        }
                        else{
                            tvEntreDB.setText("Pas de plat");
                            tvPlatDB.setText("Pas de plat");
                            tvDessertDB.setText("Pas de plat");

                        }
                        break;
                    case 4:
                        tvChild.setText("Vendredi " +sdf.format(friday.getTime()));
                        if(platVendredi.size() != 0 ) {
                            ntrE=false;
                            ntrP=false;
                            ntrD=false;
                            for (Plat p : platVendredi) {
                                if(p.id_restaurant.equals(Integer.toString(mParent+1))) {
                                    if (p.id_categorie.equals("1")){
                                        tvEntreDB.setText(p.libelle_plat);
                                        ntrE = true;
                                    }
                                    else if (p.id_categorie.equals("2")) {
                                        tvPlatDB.setText(p.libelle_plat);
                                        ntrP = true;
                                    }
                                    else if ( p.id_categorie.equals("3")) {
                                        tvDessertDB.setText(p.libelle_plat);
                                        ntrD = true;
                                    }
                                    else{

                                    }
                                }
                            }
                            if(!ntrE)
                                tvEntreDB.setText("Pas de plat");
                            if(!ntrP)
                                tvPlatDB.setText("Pas de plat");
                            if(!ntrD)
                                tvDessertDB.setText("Pas de plat");

                        }
                        else{
                            tvEntreDB.setText("Pas de plat");
                            tvPlatDB.setText("Pas de plat");
                            tvDessertDB.setText("Pas de plat");

                        }
                        break;

                }
            }
        }
    }

    public void setColors(int position, View layout){

        try {
            String colorString = "#" + mColors.getJSONArray(mParent%10).getString(position%10);
            layout.setBackgroundColor(Color.parseColor(colorString));
        } catch (JSONException ex){
            Log.e("XXX", "Fail to load color ["+mParent+"]["+position+"]");
        }

    }

    public void loadJSONFromAsset(Context ctx) {
        try {
            InputStream is = ctx.getAssets().open("colors.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String stringJson = new String(buffer, "UTF-8");
            mColors = new JSONArray(stringJson);
        } catch (IOException ex) {
            Log.e("XXX", "Fail to load color JSON file");
            ex.printStackTrace();
        } catch (JSONException ex) {
            Log.e("XXX", "Fail to parse colors JSON");
            ex.printStackTrace();
        }
    }
}
package com.example.srava.menuusmb;

import android.content.Context;
import android.graphics.Color;
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
        linear.addView(tvParent);

        TextView tvChild = new TextView(mContext);
        tvChild.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView tvEntreDB = new TextView(mContext);
        tvEntreDB.setGravity(Gravity.CENTER_HORIZONTAL);
        tvEntreDB.setTextColor(Color.WHITE);
        tvEntreDB.setTextSize(15);

        TextView tvPlatDB = new TextView(mContext);
        tvPlatDB.setGravity(Gravity.CENTER_HORIZONTAL);
        tvPlatDB.setTextColor(Color.WHITE);
        tvPlatDB.setTextSize(15);

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
            if(wololo == datemodifyL){
                platLundi.add(Plats.listePlats.get(i));
            }
            else if( datemodifyM.equals(wololo)){
                platMardi.add(Plats.listePlats.get(i));
            }
            else if( wololo == datemodifyMe){
                platMercredi.add(Plats.listePlats.get(i));
            }
            else if( wololo == datemodifyJ){
                platJeudi.add(Plats.listePlats.get(i));
            }
            else if( wololo == datemodifyV){
                platVendredi.add(Plats.listePlats.get(i));
            }
        }

        if(position == 0) {
            if(mParent == 0) {
                tvChild.setText("Lundi " +sdf.format(monday.getTime()));
                if(platLundi.size() != 0) {
                    tvEntreDB.setText(platLundi.get(0).libelle_plat);
                }
                else
                    tvEntreDB.setText("Pas de plat");
                tvPlatDB.setText(Plats.listePlats.get(0).libelle_plat);
                tvDessertDB.setText(Plats.listePlats.get(0).libelle_plat);
            }
            if(mParent == 1) {
                tvChild.setText("Lundi " +sdf.format(monday.getTime()));
                tvEntreDB.setText(Plats.listePlats.get(0).libelle_plat);
                tvPlatDB.setText(Plats.listePlats.get(0).libelle_plat);
                tvDessertDB.setText(Plats.listePlats.get(0).libelle_plat);
            }
            if(mParent == 2) {
                tvChild.setText("Lundi " +sdf.format(monday.getTime()));
                tvEntreDB.setText(Plats.listePlats.get(0).libelle_plat);
                tvPlatDB.setText(Plats.listePlats.get(0).libelle_plat);
                tvDessertDB.setText(Plats.listePlats.get(0).libelle_plat);
            }
            if(mParent == 3) {
                tvChild.setText("Lundi " +sdf.format(monday.getTime()));
                tvEntreDB.setText(Plats.listePlats.get(0).libelle_plat);
                tvPlatDB.setText(Plats.listePlats.get(0).libelle_plat);
                tvDessertDB.setText(Plats.listePlats.get(0).libelle_plat);
            }

        }
        if(position == 1) {
            if(mParent == 0) {
                tvChild.setText("Mardi " +sdf.format(tuesday.getTime()));
                if(platMardi.size() != 0) {
                    tvEntreDB.setText(platMardi.get(0).libelle_plat);
                }
                else
                    tvEntreDB.setText("Pas de plat");
                tvPlatDB.setText(Plats.listePlats.get(0).libelle_plat);
                tvDessertDB.setText("dessert string Mardi");
            }
            if(mParent == 1) {
                tvChild.setText("Mardi " +sdf.format(tuesday.getTime()));
                tvEntreDB.setText("entree string Mardi");
                tvPlatDB.setText("plat string Mardi");
                tvDessertDB.setText("dessert string Mardi");
            }
            if(mParent == 2) {
                tvChild.setText("Mardi " +sdf.format(tuesday.getTime()));
                tvEntreDB.setText("entree string Mardi");
                tvPlatDB.setText("plat string Mardi");
                tvDessertDB.setText("dessert string Mardi");
            }
            if(mParent == 3) {
                tvChild.setText("Mardi " +sdf.format(tuesday.getTime()));
                tvEntreDB.setText("entree string Mardi");
                tvPlatDB.setText("plat string Mardi");
                tvDessertDB.setText("dessert string Mardi");
            }
        }
        if(position == 2) {
            if (mParent == 0) {
                tvChild.setText("Mercredi " +sdf.format(wednesday.getTime()));
                tvEntreDB.setText("entree string Mercredi");
                tvPlatDB.setText("plat string Mercredi");
                tvDessertDB.setText("dessert string Mercredi");
            }
            if (mParent == 1) {
                tvChild.setText("Mercredi " +sdf.format(wednesday.getTime()));
                tvEntreDB.setText("entree string Mercredi");
                tvPlatDB.setText("plat string Mercredi");
                tvDessertDB.setText("dessert string Mercredi");
            }
            if (mParent == 2) {
                tvChild.setText("Mercredi " +sdf.format(wednesday.getTime()));
                tvEntreDB.setText("entree string Mercredi");
                tvPlatDB.setText("plat string Mercredi");
                tvDessertDB.setText("dessert string Mercredi");
            }
            if (mParent == 3) {
                tvChild.setText("Mercredi " +sdf.format(wednesday.getTime()));
                tvEntreDB.setText("entree string Mercredi");
                tvPlatDB.setText("plat string Mercredi");
                tvDessertDB.setText("dessert string Mercredi");
            }
        }
        if(position == 3) {
            if (mParent == 0) {
                tvChild.setText("Jeudi " +sdf.format(thursday.getTime()));
                tvEntreDB.setText("entree string Jeudi");
                tvPlatDB.setText("plat string Jeudi");
                tvDessertDB.setText("dessert string Jeudi");
            }
            if (mParent == 1) {
                tvChild.setText("Jeudi " +sdf.format(thursday.getTime()));
                tvEntreDB.setText("entree string Jeudi");
                tvPlatDB.setText("plat string Jeudi");
                tvDessertDB.setText("dessert string Jeudi");
            }
            if (mParent == 2) {
                tvChild.setText("Jeudi " +sdf.format(thursday.getTime()));
                tvEntreDB.setText("entree string Jeudi");
                tvPlatDB.setText("plat string Jeudi");
                tvDessertDB.setText("dessert string Jeudi");
            }
            if (mParent == 3) {
                tvChild.setText("Jeudi " +sdf.format(thursday.getTime()));
                tvEntreDB.setText("entree string Jeudi");
                tvPlatDB.setText("plat string Jeudi");
                tvDessertDB.setText("dessert string Jeudi");
            }
        }
        if(position == 4) {
            if (mParent == 0) {
                tvChild.setText("Vendredi " +sdf.format(friday.getTime()));
                tvEntreDB.setText("entree string Vendredi");
                tvPlatDB.setText("plat string Vendredi");
                tvDessertDB.setText("dessert string Vendredi");
            }
            if (mParent == 1) {
                tvChild.setText("Vendredi " +sdf.format(friday.getTime()));
                tvEntreDB.setText("entree string Vendredi");
                tvPlatDB.setText("plat string Vendredi");
                tvDessertDB.setText("dessert string Vendredi");
            }
            if (mParent == 2) {
                tvChild.setText("Vendredi " +sdf.format(friday.getTime()));
                tvEntreDB.setText("entree string Vendredi");
                tvPlatDB.setText("plat string Vendredi");
                tvDessertDB.setText("dessert string Vendredi");
            }
            if (mParent == 3) {
                tvChild.setText("Vendredi " +sdf.format(friday.getTime()));
                tvEntreDB.setText("entree string Vendredi");
                tvPlatDB.setText("plat string Vendredi");
                tvDessertDB.setText("dessert string Vendredi");
            }
        }

        tvChild.setTextColor(Color.BLACK);
        tvChild.setTextSize(20);
        linear.addView(tvChild);

        TextView tvEntre = new TextView(mContext);
        tvEntre.setGravity(Gravity.CENTER_HORIZONTAL);
        tvEntre.setText("Entrée");
        tvEntre.setTextColor(Color.WHITE);
        tvEntre.setTextSize(15);
        linear.addView(tvEntre);


        linear.addView(tvEntreDB);

        TextView tvPlat = new TextView(mContext);
        tvPlat.setGravity(Gravity.CENTER_HORIZONTAL);
        tvPlat.setText("Plat");
        tvPlat.setTextColor(Color.WHITE);
        tvPlat.setTextSize(15);
        linear.addView(tvPlat);


        linear.addView(tvPlatDB);

        TextView tvDessert = new TextView(mContext);
        tvDessert.setGravity(Gravity.CENTER_HORIZONTAL);
        tvDessert.setText("Dessert");
        tvDessert.setTextColor(Color.WHITE);
        tvDessert.setTextSize(15);
        linear.addView(tvDessert);


        linear.addView(tvDessertDB);

        setColors(position, linear);
        container.addView(linear);
        return linear;
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
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

import com.example.srava.menuusmb.Class.Plats;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

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
        tvParent.setText("Restaurant :" + (mParent + 1));
        tvParent.setTextColor(Color.BLACK);
        tvParent.setTextSize(30);
        linear.addView(tvParent);

        TextView tvChild = new TextView(mContext);
        tvChild.setGravity(Gravity.CENTER_HORIZONTAL);
        position = position +1;

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

        if(position == 1) {
            tvChild.setText("Lundi");
            tvEntreDB.setText(Plats.listePlats.get(0).libelle_plat);
            tvPlatDB.setText("plat string Lundi");
            tvDessertDB.setText("dessert string Lundi");

        }
        if(position == 2) {
            tvChild.setText("Mardi");
            tvEntreDB.setText("entree string Mardi");
            tvPlatDB.setText("plat string Mardi");
            tvDessertDB.setText("dessert string Mardi");
        }
        if(position == 3) {
            tvChild.setText("Mercredi");
            tvEntreDB.setText("entree string Mercredi");
            tvPlatDB.setText("plat string Mercredi");
            tvDessertDB.setText("dessert string Mercredi");
        }
        if(position == 4) {
            tvChild.setText("Jeudi");
            tvEntreDB.setText("entree string Jeudi");
            tvPlatDB.setText("plat string Jeudi");
            tvDessertDB.setText("dessert string Jeudi");
        }
        if(position == 5) {
            tvChild.setText("Vendredi");
            tvEntreDB.setText("entree string Vendredi");
            tvPlatDB.setText("plat string Vendredi");
            tvDessertDB.setText("dessert string Vendredi");
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
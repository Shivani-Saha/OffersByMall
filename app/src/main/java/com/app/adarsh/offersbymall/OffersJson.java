package com.app.adarsh.offersbymall;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;


public class OffersJson {
    public static String[] offer_names,shop_names, categories;
    public static String SHOPNAME = "shop_name", OFFERNAME="offer_name", CATEGORY="category";
    private JSONArray offers = null;
    private String json;

    public OffersJson(String json){
        this.json=json;
    }
    public int getSize(){return offer_names.length;}

    protected void offersJson(){
        try{
            offers = new JSONArray(json);
            offer_names = new String[offers.length()];
            shop_names=new String[offers.length()];
            categories=new String[offers.length()];
            for(int i=0;i<offers.length();i++){
                JSONObject j = offers.getJSONObject(i);
                offer_names[i]=j.getString(OFFERNAME);
                shop_names[i]=j.getString(SHOPNAME);
                categories[i]=j.getString(CATEGORY);
                Log.d("asd", offer_names[i]+shop_names[i]+categories[i]);
            }

        }catch(JSONException e){e.printStackTrace();}
    }

}

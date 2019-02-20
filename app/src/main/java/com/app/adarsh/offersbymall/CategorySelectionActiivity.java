package com.app.adarsh.offersbymall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adarsh.offersbymall.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.adarsh.offersbymall.R.id.mShopName;
import static com.example.adarsh.offersbymall.R.id.offer;
import static com.google.android.gms.analytics.internal.zzy.e;


public class CategorySelectionActiivity extends AppCompatActivity {
    private ArrayList<OfferDetails> offerList;

    private Spinner mSpinner;
    private Firebase mRef;
    private String mMallSelected, cat;
    private Map<String, String> map;
    private RecyclerView mRecylerView;
    private ArrayList<ContentDetailList> arraylist;
    private ArrayList<CheckList> checkLists;
    private ProgressDialog mProgres;
    private String check;
    private Boolean found = false;
    private ActionBar actionBar;
    private MyRecyclerViewAdaptera adapters;
    private MyOfferRecyclerViewAdapter oadapter;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection_actiivity);
        mSpinner = (Spinner) findViewById(R.id.categorySpinner);
        mRecylerView = (RecyclerView) findViewById(R.id.setContentView);
        mProgres = new ProgressDialog(this);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // checkLists=new ArrayList<>();

        oadapter = new MyOfferRecyclerViewAdapter(this,offerList);
        mRecylerView.setAdapter(oadapter);
        mRecylerView.setLayoutManager(new LinearLayoutManager(CategorySelectionActiivity.this));
        Intent i = getIntent();
        mMallSelected = i.getStringExtra("mallname");
        ArrayAdapter<CharSequence> CategoryAdapter = ArrayAdapter.createFromResource(this, R.array.Category_Shops, android.R.layout.simple_spinner_item);
        CategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(CategoryAdapter);
        getSupportActionBar().setTitle(mMallSelected);
        getContentDetails();
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("VALUE",String.valueOf(parent.getItemAtPosition(position)));
                if(position==0){
                    Log.d("VALUE",String.valueOf(parent.getItemAtPosition(position)));
                    cat="";
                    categorySelection(cat);

                }
                else if (position == 1) {
                    cat = String.valueOf(parent.getItemAtPosition(position));
                    categorySelection(cat);
                }
                else if (position == 2) {
                    cat = String.valueOf(parent.getItemAtPosition(position));
                    categorySelection(cat);
                }
                else if (position == 3) {
                    cat = String.valueOf(parent.getItemAtPosition(position));
                    categorySelection(cat);
                }
                else if (position == 4) {
                    cat = String.valueOf(parent.getItemAtPosition(position));
                    categorySelection(cat);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void categorySelection(String categ){
        ArrayList<OfferDetails> temp = new ArrayList<>();
        if(offerList==null) {
            return;
        }
        if(categ==""){
            mRecylerView.setLayoutManager(new LinearLayoutManager(CategorySelectionActiivity.this));
            mRecylerView.setAdapter(new MyOfferRecyclerViewAdapter(CategorySelectionActiivity.this, offerList));
            return;
        }
        for(int i=0;i<offerList.size();i++){
            if(offerList.get(i).getCategory().equalsIgnoreCase(categ)){
                temp.add(offerList.get(i));
            }
        }
        mRecylerView.setLayoutManager(new LinearLayoutManager(CategorySelectionActiivity.this));
        Log.d("CodeKamp", "filtering");
        mRecylerView.setAdapter(new MyOfferRecyclerViewAdapter(CategorySelectionActiivity.this, temp));
    }

    public void parse(String s) {
        offerList = new ArrayList<>();

        OffersJson pj = new OffersJson(s);
        pj.offersJson();

        JSONArray a;
        try {
            a = new JSONArray(s);

        for (int i = 0; i < a.length(); i++) {
            OfferDetails od = new OfferDetails();
            JSONObject obj=a.getJSONObject(i);
            od.setCategory(obj.getString("shop_category"));
            od.setShopName(obj.getString("shop_name"));

            od.setOffer(obj.getString("offer_name"));
            od.setOffer_cost(obj.getString("offer_cost"));
            od.setOffer_image(obj.getString("offer_image"));

            od.setOffer2(obj.getString("offer2_name"));
            od.setOffer2_cost(obj.getString("offer2_cost"));
            od.setOffer2_image(obj.getString("offer2_image"));

            od.setOffer3(obj.getString("offer3_name"));
            od.setOffer3_cost(obj.getString("offer3_cost"));
            od.setOffer3_image(obj.getString("offer3_image"));

            if (od.getCategory().equalsIgnoreCase("clothing")) {
                od.setShopImage(R.mipmap.clothing);
            } else if (od.getCategory().equalsIgnoreCase("sports")) {
                od.setShopImage(R.mipmap.sports);
            } else if (od.getCategory().equalsIgnoreCase("homelifestyle")) {
                od.setShopImage(R.mipmap.homelifestyle);
            } else if (od.getCategory().equalsIgnoreCase("electronics")) {
                od.setShopImage(R.mipmap.electronics);
            } else if (od.getCategory().equalsIgnoreCase("food")) {
                od.setShopImage(R.mipmap.food);
            }
            offerList.add(od);
        }
            Log.e("ARRAY",offerList.toString());
        mRecylerView.setLayoutManager(new LinearLayoutManager(CategorySelectionActiivity.this));
        mRecylerView.setAdapter(new MyOfferRecyclerViewAdapter(CategorySelectionActiivity.this, offerList));
    }
        catch (Exception e){
            Toast.makeText(getBaseContext(),"Unable to parse",Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void getContentDetails() {
        requestQueue= Volley.newRequestQueue(this);
        mProgres.setMessage("Wait..");
        mProgres.show();
        arraylist = new ArrayList<>();
        StringRequest req = new StringRequest(Request.Method.POST, "http://offersbymall.com/test/offers_by_id.php",
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        Toast.makeText(CategorySelectionActiivity.this,response.toString(),Toast.LENGTH_SHORT);
                        Log.d("dsa", "dsa"+response);
                        mProgres.dismiss();
                        if(response!=null && !response.contains("null")) {
                            Log.e("FIND",response);
                            parse(response);
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError e){
                        Toast.makeText(CategorySelectionActiivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mall_name", mMallSelected.toLowerCase());
                Log.d("selected", mMallSelected.toLowerCase());
                params.put("submit", " ");
                return params;
            }

        };
        requestQueue.add(req);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.map) {
            Intent intent=new Intent(CategorySelectionActiivity.this,Maps.class);
            intent.putExtra("name",mMallSelected.toLowerCase());
            startActivityForResult(intent,12);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==12 && resultCode== Activity.RESULT_OK){

        }else if(requestCode==12 && resultCode==Activity.RESULT_CANCELED){
            Toast.makeText(getApplicationContext(),"Missing 'Maps' application. Please download Google Maps or Here Maps from Play Store",Toast.LENGTH_SHORT).show();
        }
    }
}


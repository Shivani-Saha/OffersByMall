package com.app.adarsh.offersbymall;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adarsh.offersbymall.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


public class VendorActivity extends AppCompatActivity {


    ArrayList<VendorInfo> array;
    RecyclerView recyclerView;
    VendorAdapter adapter;
    RequestQueue requestQueue;
    TextView text;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor2);
        array = new ArrayList<>();

        getSupportActionBar().setTitle("Vendor List");

        adapter = new VendorAdapter(array, getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        text = (TextView) findViewById(R.id.text);
        fetchVendorList();

    }

    void fetchVendorList() {
        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        String url = "http://offersbymall.com/test/approval.php";
        progressDialog.setMessage("Loading list...");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Toast.makeText(getBaseContext(),response.toString(),Toast.LENGTH_LONG).show();
                Log.e("MYERROR",response.toString());
                processArray(response);
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ERROR", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.hide();
                text.setVisibility(View.VISIBLE);
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    void processArray(JSONArray response) {
        try {
            array = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                VendorInfo vendorInfo = new VendorInfo();
                JSONObject a = (JSONObject) response.get(i);
                vendorInfo.setShop_category(a.getString("shop_category"));
                vendorInfo.setMall_name(a.getString("mall_name"));
                vendorInfo.setShop_name(a.getString("shop_name"));
                vendorInfo.setContact(a.getString("shop_phone"));
                vendorInfo.setEmail(a.getString("shop_email"));
                vendorInfo.setId(a.getString("id"));
                vendorInfo.setShop_no(a.getString("shop_no"));
                vendorInfo.setStat(a.getString("approved"));
                vendorInfo.setOwner(a.getString("owner_name"));
                vendorInfo.setImage(a.getString("shop_image"));
                array.add(vendorInfo);
                Log.e("Array", a.toString());
            }

            adapter = new VendorAdapter(array, this);
            recyclerView = (RecyclerView) findViewById(R.id.rec);
            recyclerView.setItemAnimator(new SlideInUpAnimator());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(adapter);


        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "HERE:"+e.toString(), Toast.LENGTH_SHORT).show();
            emptyList();
        }
    }
    void getApproval(final String id, final int pos){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        Toast.makeText(getBaseContext(),id.toString(),Toast.LENGTH_LONG).show();
        progressDialog.setMessage("Sending for approval..");
        progressDialog.show();
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url="http://offersbymall.com/test/change_approve.php";

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("MYERROR1",response.toString());
                        adapter.removeEntry(pos);
                        progressDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_LONG).show();
                VolleyLog.d("volley", "Error: " + error.getMessage());
                error.printStackTrace();
                progressDialog.hide();
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("submit"," ");
                return params;
            }
        };
        requestQueue.add(jsonObjRequest);
    }
    void emptyList(){
        text.setVisibility(View.VISIBLE);
    }

}

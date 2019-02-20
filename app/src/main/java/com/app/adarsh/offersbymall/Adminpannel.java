package com.app.adarsh.offersbymall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.adarsh.offersbymall.R;

public class Adminpannel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpannel);
        getSupportActionBar().setTitle("Administrator Panel");


    }

    public  void  vendor(View v)
    { Intent intent=new Intent(Adminpannel.this,VendorActivity.class);
        startActivity(intent);
    }
    }


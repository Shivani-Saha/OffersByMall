package com.app.adarsh.offersbymall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.adarsh.offersbymall.R;

public class OfferActivity extends AppCompatActivity {

    TextView nameText, offerText, shopText;
    String name, offer, shopno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        nameText = (TextView) findViewById(R.id.nameOfowner);
        offerText = (TextView) findViewById(R.id.nameOfoffer);
        shopText = (TextView) findViewById(R.id.noOfshop);


    }
}

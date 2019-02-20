package com.app.adarsh.offersbymall;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.example.adarsh.offersbymall.R;

public class City_MallsActivity extends AppCompatActivity {

    private GridView mGrid;
    private TextView mSetcityHeading;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city__malls);
        mGrid = (GridView) findViewById(R.id.citymallgridview);
        mGrid.setAdapter(new cityMallAdapter(this));
        mSetcityHeading = (TextView) findViewById(R.id.set_cityMall);
        mSetcityHeading.setText("Selection:" + getIntent().getStringExtra("cityName"));
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}

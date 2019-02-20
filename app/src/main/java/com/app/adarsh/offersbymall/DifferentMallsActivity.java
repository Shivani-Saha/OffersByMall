package com.app.adarsh.offersbymall;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.example.adarsh.offersbymall.R;

public class DifferentMallsActivity extends AppCompatActivity {
    private GridView mGridView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_malls);
        Bundle b = this.getIntent().getExtras();
        String[] array = b.getStringArray("allahabad");

        SharedPreferences pre = getSharedPreferences("mymall", MODE_PRIVATE);
        SharedPreferences.Editor ed = pre.edit();
        ed.putString("names",getIntent().getStringExtra("names"));
        ed.commit();
        mGridView = (GridView) findViewById(R.id.differentcitymallgridview);
        mGridView.setAdapter(new DifferentMallAdapter(this, array));
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("names"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}

package com.app.adarsh.offersbymall;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adarsh.offersbymall.R;

import java.util.ArrayList;
import java.util.HashMap;

public class skipActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private GridView mGridView;
    private ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> productList;
    private EditText inputSearch;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip);

        mGridView = (GridView) findViewById(R.id.gridview);
        mGridView.setAdapter(new MallsAdapter(this));
        mDrawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mNavigationView = (NavigationView) findViewById(R.id.navview);

        String products[] = {"GAUR CENTRAL MALL", "THE OPULENT MALL", "SHIPRA MALL", "METRO MALL", "SHOPPRIX MALL",
                "DUBAI MALL", "VINAYAK CITY CENTRE",
                "P SQUARE MALL", "CHEER SHOPPING OUTLET", "ATLANTIC MALL", "VMART MALL", "MAX", "EXPRESS VENUE", "CHENNAI CITY CENTRE",
                "SPENCER MALL","AMBIENCE MALL","PACIFIC MALL","WEST GATE MALL","MOMENTS MALL","STAR CITY MALL","CITY SQUARE MALL", "ABIRAMI MEGA MALL", "PHOENIX MALL", "ALSA MALL", "ANSAL PLAZA", "DLF EMPORIO",
                "SELECT CITYWALK", "INORBIT MALL", "SEASON MALL", "KUMAR PACIFIC MALL", "CENTRAL", "SGS MALL", "CENTRAL", "PHOENIX MARKET CITY",
                "THE GREAT INDIA PALACE", "SPICE WORLD MALL", "DLF", "SAB MALL", "SENIOR MALL", "STAR CITY MALL", "LULU MALL", "THE FOCUS MALL",
                "ABAD NUCLEUS MALL", "SOBHA CITY MALL", "CAPITOL MALL", "HiLITE MALL", "SOUTH CITY MALL", "MANI SQUARE", "QUEST MALL",
                "FORUM COURTYARD", "ACROPOLIS MALL", "CITY CENTER SALT LAKE", "CROWN INTERIORS", "SRS SHOPPING MALL", "PRISTINE MALL",
                "PARSMATH MALL", "CROWN PLAZA", "FUN CITY MALL", "TDI MALL", "GIP", "CITY SQUARE MALL"};
        lv = (ListView) findViewById(R.id.searchlist);
        inputSearch = (EditText) findViewById(R.id.inputsearch);

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        lv.setAdapter(adapter);
        lv.setVisibility(View.GONE);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                lv.setVisibility(View.GONE);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                lv.setVisibility(View.VISIBLE);
                skipActivity.this.adapter.getFilter().filter(s);
                lv.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.d("CodeKamp", "see:" + s);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(skipActivity.this, CategorySelectionActiivity.class);
                        String item = (String) parent.getItemAtPosition(position);
                        Log.d("CodeKamp", "item:" + item);
                        intent.putExtra("mallname", item);
                        startActivity(intent);
                        inputSearch.setText("");
                        lv.setVisibility(View.GONE);
                    }
                });


            }
        });

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return true;

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.registr:
                Toast.makeText(skipActivity.this, "Sign-In first to register", Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.home:
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.about:
                startActivity(new Intent(skipActivity.this, AboutActivity.class));
                mDrawerLayout.closeDrawers();
                return true;

            case R.id.sigin:
                startActivity(new Intent(skipActivity.this, LoginActivity.class));
                mDrawerLayout.closeDrawers();
                finish();
                return true;
            case R.id.exit:
                mDrawerLayout.closeDrawers();
                finish();
                return true;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

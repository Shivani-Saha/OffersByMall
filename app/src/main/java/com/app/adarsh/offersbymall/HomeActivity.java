package com.app.adarsh.offersbymall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adarsh.offersbymall.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // private Button mLogout;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mProgressDialog;
    private GridView mGridView;
    private Boolean check = false;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private FirebaseUser mUser;
    private String mDisplayName;
    private Uri mUserImage;
    private ImageView mImage;
    private ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> productList;
    private EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mGridView = (GridView) findViewById(R.id.gridview);
        mGridView.setAdapter(new MallsAdapter(this));
        mNavigationView = (NavigationView) findViewById(R.id.navview);
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mDisplayName = mUser.getDisplayName();
        mUserImage = mUser.getPhotoUrl();
        // mImage= (ImageView) findViewById(R.id.profileimage);
        // mImage.setImageURI(mUserImage);
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
                HomeActivity.this.adapter.getFilter().filter(s);
                lv.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                /*Intent intent = new Intent(HomeActivity.this,CategorySelectionActiivity.class);
                startActivity(intent);*/
                Log.d("CodeKamp", "see:" + s);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        inputSearch.setText("");
                        lv.setVisibility(View.GONE);
                        Intent intent = new Intent(HomeActivity.this,CategorySelectionActiivity.class);
                        String item = String.valueOf(lv.getItemAtPosition(position));
                        intent.putExtra("mallname", item);
                        startActivity(intent);
                    }
                });


            }
        });


        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavigationView.setNavigationItemSelectedListener(this);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
            }
        };


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.lgout:
                mProgressDialog.setMessage("Logging out...");
                mAuth.signOut();
                finish();
                mProgressDialog.dismiss();
        }
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return true;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (check == true)
            finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.registr:
                startActivity(new Intent(HomeActivity.this, ShopholderRegister.class));
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.home:
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                mDrawerLayout.closeDrawers();
                return true;
            case R.id.about:
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                mDrawerLayout.closeDrawers();
                return true;

        }
        return true;
    }

}

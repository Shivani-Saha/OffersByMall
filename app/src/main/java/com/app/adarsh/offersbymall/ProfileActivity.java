package com.app.adarsh.offersbymall;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adarsh.offersbymall.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private Spinner mSpinner_city, mSpinner_malls;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Vendor Offers");

        mSpinner_city = (Spinner) findViewById(R.id.city_spinner);
        mSpinner_malls = (Spinner) findViewById(R.id.malls_spinner);

        ArrayAdapter<CharSequence> CityAdapter = ArrayAdapter.createFromResource(this, R.array.Malls_name, android.R.layout.simple_spinner_item);
        CityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> MallsAdapter = ArrayAdapter.createFromResource(this, R.array.cityMalls_name, android.R.layout.simple_spinner_item);
        MallsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


    }


}

/*
    private Firebase mRef;
    private EditText mEditText;
    private Button mSubmit;
    private String uId;
    private String password;
    private TextView mNameView, mEmailView, mPhoneView, mVenDor;
    private Map<String, String> map;
    private String VendorId;
    private ArrayList<IdList> idLists;
    private ProgressDialog mProgressDialog;
    private Boolean found = false;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mEditText = (EditText) findViewById(R.id.profile_password);
        mSubmit = (Button) findViewById(R.id.submit_butn);
        mNameView = (TextView) findViewById(R.id.get_name);
        mNameView.setVisibility(View.GONE);
        mEmailView = (TextView) findViewById(R.id.get_email);
        mEmailView.setVisibility(View.GONE);
        mPhoneView = (TextView) findViewById(R.id.get_phone);
        mPhoneView.setVisibility(View.GONE);
        mVenDor = (TextView) findViewById(R.id.get_VendorId);
        mVenDor.setVisibility(View.GONE);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Vendor Profile Login");

        idLists = new ArrayList<>();
        mProgressDialog = new ProgressDialog(this);
        mSubmit.setEnabled(false);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uId = user.getUid();
        }
        Log.d("CodeKamp", "registerdId:" + uId);

        mRef = new Firebase("https://offersbymall-93fb1.firebaseio.com/users");
        mProgressDialog.setMessage("Wait..");
        mProgressDialog.show();

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mProgressDialog.setMessage("Wait..");
                mProgressDialog.show();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    VendorId = postSnapshot.getKey();
                    Log.d("CodeKamp", "Id: " + VendorId);
                    if (VendorId.equals(uId)) {
                        found = true;

                    }


                }

                if (found) {
                    mSubmit.setEnabled(true);
                    mProgressDialog.dismiss();
                    getProfile();
                } else {
                    Toast.makeText(getApplicationContext(), "You are not registered.", Toast.LENGTH_LONG).show();
                    mProgressDialog.dismiss();

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    public void getProfile() {

        mRef = new Firebase("https://offersbymall-93fb1.firebaseio.com/users/" + uId);
        Log.d("CodeKamp", String.valueOf(mRef));
        if (mRef == null) {
            Toast.makeText(getApplicationContext(), "You are not registered.", Toast.LENGTH_LONG).show();
        } else {
            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    map = dataSnapshot.getValue(Map.class);
                    password = map.get("password");
                    if (password == null) {
                        Toast.makeText(getApplicationContext(), "You are not registered.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("CodeKamp", "password:" + password);
                        mSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String inputPassword = mEditText.getText().toString();
                                if (inputPassword.equals(password)) {
                                    mNameView.setText("Name: " + map.get("name"));
                                    mNameView.setVisibility(View.VISIBLE);
                                    mEmailView.setText("Email: " + map.get("email"));
                                    mEmailView.setVisibility(View.VISIBLE);
                                    mPhoneView.setText("Phone No: " + map.get("phone"));
                                    mPhoneView.setVisibility(View.VISIBLE);
                                    mVenDor.setText("Vendor Id: " + map.get("password"));
                                    mVenDor.setVisibility(View.VISIBLE);


                                } else
                                    Toast.makeText(ProfileActivity.this, "Invalid Vendor Id", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Log.d("CodeKamp", "error:" + firebaseError);

                }
            });


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
*/

package com.app.adarsh.offersbymall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.adarsh.offersbymall.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Maps extends AppCompatActivity{



    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    double currentLatitude;
    double currentLongitude;
    private ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        String mallName=getIntent().getStringExtra("name");

        String pos="28.5437,77.1569";
        mallName.toLowerCase();
        if(mallName.equals("dlf emporio"))
            pos="28.5437,77.1569";
        if(mallName.equals("ansal plaza"))
            pos="28.5632,77.2244";
        if(mallName.equals("metro walk mall"))
            pos="28.7237,77.1135";
        if(mallName.equals("tdi mall"))
            pos="28.6507,77.1207";
        if(mallName.equals("select city mall"))
            pos="28.5286,77.2193";
        if(mallName.equals("v3s mall"))
            pos="28.6375,77.2869";
        if(mallName.equals("ambience mall"))
            pos="28.5412,77.1549";
        if(mallName.equals("shopprix mall"))
            pos="28.6427,77.3408";
        if(mallName.equals("silver city mall"))
            pos="28.6378,77.4599";
        if(mallName.equals("west gate mall"))
            pos="28.6531,77.1230";
        if(mallName.equals("star city mall"))
            pos="28.5927,77.2963";
        if(mallName.equals("city square mall"))
            pos="28.6502,77.1233";
        if(mallName.equals("mahagun metro mall"))
            pos="28.6447,77.3357";
        if(mallName.equals("pacific mall"))
            pos="28.6423,77.1063";
        if(mallName.equals("shipra mall"))
            pos="28.6343,77.3699";
        if(mallName.equals("gaur central mall"))
            pos="28.6738,77.4403";
        if(mallName.equals("opulent mall"))
            pos="28.6542,77.4368";
        if(mallName.equals("galaxie mall"))
            pos="28.6550,77.3452";
        if(mallName.equals("east delhi mall"))
            pos="28.6413,77.3168";
        if(mallName.equals("mark mall"))
            pos="28.6692,77.3791";
        if(mallName.equals("sahara mall"))
            pos="28.4796,77.0868";
        if(mallName.equals("dlf city centre mall"))
            pos="28.7030,77.1582";
        if(mallName.equals("mgf mall"))
            pos="28.4803,77.0802";
        if(mallName.equals("dlf mega mall"))
            pos="28.4757,77.0931";
        if(mallName.equals("central"))
            pos="28.4795,77.0757";
        if(mallName.equals("vipul agora mall"))
            pos="28.4797,77.0856";
        if(mallName.equals("plaza mall"))
            pos="28.4779,77.0731";
        if(mallName.equals("raheja mall"))
            pos="28.4236,77.0395";
        if(mallName.equals("centrestage mall"))
            pos="28.5677,77.3227";
        if(mallName.equals("dlf mall"))
            pos="28.5482,77.3222";
        if(mallName.equals("gip mall"))
            pos="28.5678,77.3261";
        if(mallName.equals("sab mall"))
            pos="28.5736,77.3237";
        if(mallName.equals("supertech shoprix mall"))
            pos="28.6427,77.3408";
        if(mallName.equals("wave mall"))
            pos="28.6450487,77.3262953";
        if(mallName.equals("spice world mall"))
            pos="28.5864,77.3414";
        if(mallName.equals("centre stage mall"))
            pos="28.5677,77.3227";
        if(mallName.equals("ansal mall"))
            pos="28.4641,77.5079";
        if(mallName.equals("parsmath mall"))
            pos="28.4066,77.3102";
        if(mallName.equals("eros ef3 mall"))
            pos="28.4076,77.3102";
        if(mallName.equals("crown plaza"))
            pos="28.3978,77.3132";
        if(mallName.equals("eldeco station"))
            pos="28.3860,77.3168";
        if(mallName.equals("pristine mall"))
            pos="28.4454,77.3157";
        if(mallName.equals("srs mall"))
            pos="28.3863,77.3170";
        if(mallName.equals("pvs mall"))
            pos="28.9528,77.7315";
        if(mallName.equals("melange mall"))
            pos="29.0648,77.7093";
        if(mallName.equals("paradise mall"))
            pos="28.9721,77.6791";
        if(mallName.equals("era mall"))
            pos="28.9685,77.6874";

        if(mallName.equals("moments mall"))
            pos="28.6574,77.1471";
        if(mallName.equals("parshvanath"))
            pos="28.6437,77.3266";
        if(mallName.equals("red mall"))
            pos="28.6704,77.4154";



        String fPos[] = pos.split(",");
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f?q=%f,%f("+mallName+")", Double.parseDouble(fPos[0]), Double.parseDouble(fPos[1]), Double.parseDouble(fPos[0]), Double.parseDouble(fPos[1]));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        try {
            startActivity(intent);
            setResult(Activity.RESULT_OK, getIntent());
            finish();
        }catch (ActivityNotFoundException e){
            setResult(Activity.RESULT_CANCELED, getIntent());
            finish();
        }
    }


}
package com.app.adarsh.offersbymall;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.content.pm.Signature;

import com.example.adarsh.offersbymall.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SplashActivity extends AppCompatActivity {

    private TypeWriter text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.facebook.samples.hellofacebook",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
          md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        text=(TypeWriter)findViewById(R.id.text);
        text.setCharacterDelay(50);
        text.animateText("Welcome to\nOffers By Mall");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3500);
                } catch (InterruptedException e) {
                }
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                if(sharedPreferences.getBoolean("first",true)){
                    SharedPreferences.Editor edit=sharedPreferences.edit();
                    edit.putBoolean("first",false);
                    edit.commit();
                    Intent i=new Intent(SplashActivity.this,FirstActivity.class);
                    SplashActivity.this.startActivity(i);
                    SplashActivity.this.finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
            }
        });

        thread.start();
    }
}

package com.app.adarsh.offersbymall;

import android.app.Application;

import com.firebase.client.Firebase;

public class FirebaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}

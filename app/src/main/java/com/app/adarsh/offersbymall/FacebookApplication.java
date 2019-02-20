package com.app.adarsh.offersbymall;


import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.firebase.client.Firebase;


public class FacebookApplication extends com.app.adarsh.offersbymall.AppController {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the SDK before executing any other operations,
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        Firebase.setAndroidContext(this);
    }
}
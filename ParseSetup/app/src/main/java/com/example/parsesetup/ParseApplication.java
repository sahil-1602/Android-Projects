package com.example.parsesetup;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        Parse.enableLocalDatastore(this);
//        Parse.initialize(new Parse.Configuration.Builder(this)
//                .applicationId("myappID")
//                .clientKey("z3mmROx7YWcI")
//                .server("http://18.218.212.220/parse/")
//                .build()
//        );
//
//        ParseUser.enableAutomaticUser();
//
//        ParseACL defaultACL = new ParseACL();
//        defaultACL.setPublicReadAccess(true);
//        defaultACL.setPublicWriteAccess(true);
//        ParseACL.setDefaultACL(defaultACL, true);
    }
}

//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        // Enable Local Datastore.
//        Parse.enableLocalDatastore(this);
//
//
//        // Add your initialization code here
//        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
//                .applicationId("myappID")
//                .clientKey("z3mmROx7YWcI")
//                .server("http://18.224.151.93/parse/")
//                .build()
//        );
//        ParseUser.enableAutomaticUser();
//
//        ParseACL defaultACL = new ParseACL();
//        defaultACL.setPublicReadAccess(true);
//        defaultACL.setPublicWriteAccess(true);
//        ParseACL.setDefaultACL(defaultACL, true);
//    }

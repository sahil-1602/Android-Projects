package com.example.parsesetup;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);



        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("myappID")
                .clientKey("z3mmROx7YWcI")
                .server("http://18.224.151.93/parse/")
                .build()
        );

        ParseObject object = new ParseObject("ExampleObject");
        object.put("myNumber", "123");
        object.put("myString", "rob");

        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException ex) {
                if (ex == null) {
                    Log.i("Parse Result", "Successful!");
                } else {
                    Log.i("Parse Result", "Failed" + ex.toString());
                }
            }
        });


        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        /* // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myappID")
                .clientKey("z3mmROx7YWcI")
                .server("http://18.224.151.93/parse/")
                .build()
        );

        /*ParseUser.enableRevocableSessionInBackground();

        ParseObject object = new ParseObject("ExampleObject");
        object.put("myNumber", "123");
        object.put("myString", "rob");

        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException ex) {
                if (ex == null) {
                    Log.i("Parse Result", "Successful!");
                } else {
                    Log.i("Parse Result", "Failed" + ex.toString());
                }
            }
        });*/



        /*ParseObject score = new ParseObject("Score");
        score.put("username","Faraaz");
        score.put("score",60);

        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    //OK
                    Log.i("ADDING TO SERVER :","Success");
                }else{
                    e.printStackTrace();
                }
            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

        query.whereEqualTo("username","Sahil");
        query.setLimit(1);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for (ParseObject object : objects){
                            Log.i("username",object.getString("username"));
                            Log.i("score",Integer.toString(object.getInt("score")));
                        }
                    }
                }
            }
        });*/


       /* ParseObject tweet = new ParseObject("Tweet");
        tweet.put("username","sahil_1601");
        tweet.put("tweet","India bans Tik-tok");

        tweet.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Log.i("Done","successfully");
                }else{
                    e.printStackTrace();
                }
            }
        });

        ParseQuery<ParseObject> tweetQuery = ParseQuery.getQuery("Tweet");

        tweetQuery.getInBackground("8GlSPaMkqB", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e==null && object != null) {
                    object.put("tweet","India Bans Tik-Tok and 58 other applications");
                    object.saveInBackground();

                    Log.i("Changed tweet",object.getString("tweet"));
                }else{
                    e.printStackTrace();
                }
            }
        });*/

       /*
       ParseUser user = new ParseUser();
       user.setUsername("Sahil16");
       user.setPassword("12345");

       user.signUpInBackground(new SignUpCallback() {
           @Override
           public void done(ParseException e) {
               if(e==null){
                   //OK
                   Log.i("Sign -up","Success");
               }
           }
       });
       */

       /*
       ParseUser.logInInBackground("Sahil16", "12345", new LogInCallback() {
           @Override
           public void done(ParseUser user, ParseException e) {
               if(user!=null){
                   Log.i("WE LOGGED IN","SUCCESSFULLY");
               }else{
                   e.printStackTrace();
               }
           }
       });
       */

       /*if(ParseUser.getCurrentUser()!=null){
           Log.i("Signed In",ParseUser.getCurrentUser().getUsername());
       }else{
           Log.i("No Luck","You are not signed in");
       }*/

        //LOG OUT
        //ParseUser.logOut();

       /* ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
        */


    }

}

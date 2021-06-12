package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        SQLiteDatabase database = this.openOrCreateDatabase("User", MODE_PRIVATE, null);

        database.execSQL("CREATE TABLE IF NOT EXISTS user (name VARCHAR, age INT(3))");

        database.execSQL("INSERT INTO user (name, age) VALUES ('Sahil',18)");
        database.execSQL("INSERT INTO user (name, age) VALUES ('Sadie',35)");

        Cursor c = database.rawQuery("SELECT * FROM user",null);

        int nameOfUser = c.getColumnIndex("name");
        int ageOfUser = c.getColumnIndex("age");

        c.moveToFirst();

        while (c!=null){

            Log.i("Name",c.getString(nameOfUser));

            Log.i("Age",c.getString((ageOfUser)));

            c.moveToNext();

        }*/

/*
        try {

            SQLiteDatabase eventDatabase = this.openOrCreateDatabase("Event",MODE_PRIVATE,null);

            eventDatabase.execSQL("CREATE TABLE IF NOT EXISTS event(name VARCHAR, year INT(4))");

            eventDatabase.execSQL("INSERT INTO event(name, year) VALUES('Debate Competition',2019)");
            eventDatabase.execSQL("INSERT INTO event(name, year) VALUES('Corona Virus Competition',2020)");

            Cursor c = eventDatabase.rawQuery("SELECT * FROM event",null);

            int nameOfEvent = c.getColumnIndex("name");
            int yearOfEvent = c.getColumnIndex("year");

            c.moveToFirst();

            while(c != null) {

                Log.i("Name of Event", c.getString(nameOfEvent));

                Log.i("Year of Event", Integer.toString(c.getInt(yearOfEvent)));

                c.moveToNext();
            }
        }catch (Exception e){
             e.printStackTrace();
        }*/


        try {
            SQLiteDatabase database = this.openOrCreateDatabase("User", MODE_PRIVATE, null);

            database.execSQL("CREATE TABLE IF NOT EXISTS newUser (name VARCHAR, age INT(3),id INTEGER PRIMARY KEY)");

//            database.execSQL("INSERT INTO newUser (name, age) VALUES ('Sahil',18)");
//            database.execSQL("INSERT INTO newUser (name, age) VALUES ('Sadie',35)");
//            database.execSQL("INSERT INTO newUser (name, age) VALUES ('Sadie',67)");

            database.execSQL("DELETE FROM newUser WHERE id = 3");

            Cursor c = database.rawQuery("SELECT * FROM newUser", null);

            int nameOfUser = c.getColumnIndex("name");
            int ageOfUser = c.getColumnIndex("age");
            int id = c.getColumnIndex("id");
            c.moveToFirst();

            while (c != null) {

                Log.i("Name", c.getString(nameOfUser));

                Log.i("Age", c.getString((ageOfUser)));

                Log.i("Id", c.getString((id)));

                c.moveToNext();

            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
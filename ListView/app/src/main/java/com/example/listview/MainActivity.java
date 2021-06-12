package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] family = {"Sahil","Mahesh","Vandna","Drashti","Druv","Vinit","Dipesh","Priyanshu","Febin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myFriends = findViewById(R.id.friendListView);

        ArrayList<String> friends = new ArrayList<String>();
        friends.add("Febin");
        friends.add("Deeps");
        friends.add("Dev");
        friends.add("Selvin");
        friends.add("Faraaz");
        friends.add("Deval");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,friends);

        myFriends.setAdapter(arrayAdapter);

        myFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arrayAdapter.getItem(i).toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
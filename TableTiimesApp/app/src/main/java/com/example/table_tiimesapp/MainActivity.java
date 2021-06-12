package com.example.table_tiimesapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView numberList = findViewById(R.id.numberListView);

        final SeekBar timesSeekBar = findViewById(R.id.timesSeekBar);
        timesSeekBar.setMax(20);
        timesSeekBar.setProgress(1);

        timesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int min =1;
                int timesNumber;
                if(i<min){
                    timesNumber=min;
                    timesSeekBar.setProgress(timesNumber);
                }else{
                    timesNumber=i;
                }
                Log.i("Number",Integer.toString(timesNumber));
                ArrayList<Integer> numbers = new ArrayList<Integer>();
                for(int j=0;j<10;j++){
                    int temp= j;
                    temp+=1;
                    numbers.add(temp*timesNumber);
                }

                ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_list_item_1,numbers);

                numberList.setAdapter(arrayAdapter);
            }



            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {



            }
        });

    }
}
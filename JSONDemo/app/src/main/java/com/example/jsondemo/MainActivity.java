package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            String result ="";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data!= -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);

                String weatherInfo = jsonObject.getString("weather");
                JSONObject main = (JSONObject) jsonObject.get("main");
                Log.i("Main Info",main.toString());
                Double temp = ( Double ) main.get( "temp" );  // Better to use BigDecimal instead of Double for accuracy. But I do not know how to get the JSON-Simple library to parse the original string input as a BigDecimal.
                Log.i("Temperature",temp.toString());


//                Log.i("Temperature Info",temperatureInfo);

                Log.i("Weather Info",weatherInfo);

                JSONArray jsonArray = new JSONArray(weatherInfo);

//                JSONArray jsonArray1 = new JSONArray(temperatureInfo);

                for(int i=0;i<jsonArray.length();i++){

                    JSONObject jsonPart = jsonArray.getJSONObject(i);

                    Log.i("Main",jsonPart.getString("main"));

                    Log.i("description",jsonPart.getString("description"));

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        task.execute("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02");

    }
}
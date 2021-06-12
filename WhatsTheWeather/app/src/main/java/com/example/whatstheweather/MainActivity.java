package com.example.whatstheweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText cityEditText;
    TextView weatherTextView;
    ProgressBar progressBar;


    public class DownloadTask extends AsyncTask<String,Void,String> {

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
                progressBar.setVisibility(View.INVISIBLE);
                weatherTextView.setText("Could'nt find the Weather, we're sorry :(");
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);

                String weatherInfo = jsonObject.getString("weather");

                JSONObject mainInfo = (JSONObject) jsonObject.get("main");
                Double temp = ( Double ) mainInfo.get( "temp" );

                Log.i("Weather Info",weatherInfo);

                JSONArray jsonArray = new JSONArray(weatherInfo);
                String message ="";


                message = "Temperature : " + temp.toString()+ "\r\n";


                for(int i=0;i<jsonArray.length();i++){

                    JSONObject jsonPart = jsonArray.getJSONObject(i);

                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("main");

                    if(!main.equals("") && !description.equals("")){
                        message+= description + "\r\n";
                    }else{
                        progressBar.setVisibility(View.INVISIBLE);
                        weatherTextView.setText("Could'nt find the Weather, we're sorry :(");
                    }

                }
                progressBar.setVisibility(View.INVISIBLE);
                if (!message.equals("")){
                    weatherTextView.setText(message);
                }
            }catch (Exception e){
                e.printStackTrace();
                progressBar.setVisibility(View.INVISIBLE);
                weatherTextView.setText("Could'nt find the Weather, we're sorry :(");
            }
        }
    }

    public void getWeather(View view){

        try {
            String encodedText = URLEncoder.encode(cityEditText.getText().toString(), "UTF-8");   // it handles spaces//
            InputMethodManager mngr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mngr.hideSoftInputFromWindow(cityEditText.getWindowToken(), 0);

            progressBar.setVisibility(View.VISIBLE);
            DownloadTask task = new DownloadTask();
            task.execute("https://openweathermap.org/data/2.5/weather?q=" + cityEditText.getText().toString() + "&appid=439d4b804bc8187953eb36d2a8c26a02");
        }catch (Exception e){
            e.printStackTrace();
            progressBar.setVisibility(View.INVISIBLE);
            weatherTextView.setText("Could'nt find the Weather, we're sorry :(");

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityEditText = findViewById(R.id.cityNameEditTextView);
        weatherTextView = findViewById(R.id.weatherTextView);
        progressBar = findViewById(R.id.progressBar);
    }
}